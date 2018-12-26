<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>

<%@page import="java.sql.Date"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="model.RegionInformationBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- JQuery -->
	<script type="text/javascript" src="bootstrap/js/jquery-3.3.1.min.js"></script>
	<!-- Bootstrap核心 CSS-->
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/mystyle.css" rel="stylesheet">
	<!-- Bootstrap js -->
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<%
	
		String user=null;
		if(session.getAttribute("user")==null)
			user=null;
		else
			user = session.getAttribute("user").toString();
	
		String ISO3166 = (String)request.getAttribute("ISO3166");
		ArrayList<RegionInformationBean> allRegionInformation = new ArrayList<RegionInformationBean>();
		ArrayList<RegionInformationBean> currentInformation = new ArrayList<RegionInformationBean>();
		ArrayList<RegionInformationBean> pastInformation = new ArrayList<RegionInformationBean>();
		java.util.Date current = new java.util.Date();
		
		
		
		if(request.getAttribute("countryData")!=null){
			allRegionInformation = (ArrayList<RegionInformationBean>)request.getAttribute("countryData");
			for(RegionInformationBean regionData : allRegionInformation){
				java.util.Date effectiveTime = new java.util.Date();
				java.util.Date expiresTime = new java.util.Date();
				effectiveTime.setTime(regionData.getEffective().getTime());
				expiresTime.setTime(regionData.getExpires().getTime());
				
				if(current.after(effectiveTime)&& current.before(expiresTime))
					currentInformation.add(regionData);
				else if(current.after(expiresTime))
					pastInformation.add(regionData);
			}
			if(currentInformation.size()==0){
				out.print("目前國家無疫情");}
			else{%>
		
        <div class="modal-body">
			<table class="table table-hover">
				<thead>
					<tr  class="row">
						<th class="col-sm-1">國家名稱</th>
						<th class="col-sm-1">疾病名稱</th> 
						<th class="col-sm-2">開始時間</th>
						<th class="col-sm-2">結束時間</th>
						<th class="col-sm-4">敘述</th>
						<th class="col-sm-1">警告</th>
						<th class="col-sm-1">建議</th>
					</tr>
			  	</thead>
				
		<%
					for(RegionInformationBean RegionData : currentInformation){
		%>
				<tbody>
					<tr  class="row">
						<td class="col-sm-1"><%=RegionData.getCountry_name()%></td>
						<td class="col-sm-1"><%=RegionData.getDisease_name() %></td>
						<td class="col-sm-2"><%=RegionData.getEffective() %></td>
						<td class="col-sm-2"><%=RegionData.getExpires() %></td>
						<td class="col-sm-4"><%=RegionData.getDescription() %></td>
						<td class="col-sm-1"><%if(RegionData.getAlert()==null) out.print("無");%></td>
						<td class="col-sm-1"><%if(RegionData.getSuggestion()==null) out.print("無"); %></td>
					</tr>
				</tbody>
			<%			}
		%>
			</table>
			
		</div>
		<%}%>
		<div class="modal-footer">
			
				<button type="button" class="btn btn-success" data-toggle="modal" data-target="#history">顯示國家歷史資訊</button>
				<!-- 我的最愛按鈕 -->
				<%if(user!=null){%>
					<form action="NewFavoriteCountryServlet" method="post" target="message2">
					<input type="hidden" name="ISO3166" value=<%=ISO3166%>>
					<button type="submit" class="btn btn-warning" data-toggle="modal" data-target="#message2">新增至我的最愛</button>
					</form>
				<%} %>
				<!-- 留言板 -->
				<form action="CommentServlet" method="post" >
					<input type="hidden" name="ISO3166" value=<%=ISO3166%>>
					<button type="submit" class="btn btn-info" data-toggle="modal" data-target="#comment">留言版</button>
				</form>		
				
		</div>
		
	
		<div class="modal fade" id="history">
		  <div class="modal-dialog  modal-full">
		    <div class="modal-content">
		    	<div class="modal-header">
		          <h4 class="modal-title">國家歷史資訊</h4>
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		        </div>
		        <div class="modal-body">
		          <%if(pastInformation.size()==0){
					out.print("目前國家無歷史疫情");
				  	}
					else{%>
				    <table class="table table-hover">
				    	<thead>
							<tr class="row">
								<th class="col-sm-1">國家名稱</th>
								<th class="col-sm-1">疾病名稱</th> 
								<th class="col-sm-2">開始時間</th>
								<th class="col-sm-2">結束時間</th>
								<th class="col-sm-4">敘述</th>
								<th class="col-sm-1">警告</th>
								<th class="col-sm-1">建議</th>
							</tr>
				  		</thead>
					
					<%
						for(RegionInformationBean regionData : pastInformation){
					%>
						<tbody>
							<tr class="row">
								<td class="col-sm-1"><%=regionData.getCountry_name()%></td>
								<td class="col-sm-1"><%=regionData.getDisease_name() %></td>
								<td class="col-sm-2"><%=regionData.getEffective() %></td>
								<td class="col-sm-2"><%=regionData.getExpires() %></td>
								<td class="col-sm-4"><%=regionData.getDescription() %></td>
								<td class="col-sm-1"><%if(regionData.getAlert()==null) out.print("無");%></td>
								<td class="col-sm-1"><%if(regionData.getSuggestion()==null) out.print("無"); %></td>
							</tr>
						</tbody>  
					<%
						}
					%>
				
					</table>
				<%		
				}
				%>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>
		    </div>
		  </div>
		</div>
		
	<%
	}else{
		out.print("國家無疫情");
	}
		
	%>	
	
	<div class="modal fade" id="message">
		<div class="modal-dialog modal-lg">
		    <div class="modal-content">
				<iframe name="message2" style="border: 0" ></iframe>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="comment">
		<div class="modal-dialog modal-full">
		    <div class="modal-content">
		    	<div class="modal-header">
		          <h4 class="modal-title">國家疫情資訊</h4>
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		        </div>
				<iframe name="comment" style="border: 0" width="100%" height="1000"></iframe>
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</body>
</html>