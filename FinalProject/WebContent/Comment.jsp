<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.CommentBean"%>
<!DOCTYPE html>
<html>
<head>
<!-- JQuery -->
<script type="text/javascript" src="bootstrap/js/jquery-3.3.1.min.js"></script>
<!-- Bootstrap核心 CSS-->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap js -->
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	<%	
		String ISO3166 = request.getAttribute("ISO3166").toString();
		String user = null;
		if (session.getAttribute("user") == null)
			user = null;
		else
			user = session.getAttribute("user").toString();
		ArrayList<CommentBean> comment = new ArrayList<CommentBean>();

		if (request.getAttribute("comment") == null)
			out.print("目前無留言");
		else {
			comment = (ArrayList<CommentBean>) request.getAttribute("comment");
	%>
	<div class="modal-body">
	<table class="table table-hover">
		<thead>
			<tr class="row">
				<th class="col-sm-6">留言內容</th>
				<th class="col-sm-2">留言時間</th>
				<th class="col-sm-2">修改</th>
				<th class="col-sm-2">刪除</th>
			</tr>
		</thead>
		<%
			for (CommentBean commentbean : comment) {
		%>
		<tr class="row">
			<td class="col-sm-6"><%=commentbean.getContent()%></td>
			<td class="col-sm-2"><%=commentbean.getComment_time()%></td>
			
			<td class="col-sm-2">
			<%
				if (commentbean.getMemberNum().equals(user)) {
			%>
				<button type="button" class="btn btn-warning" data-toggle="modal"
					data-target="#updateComment">修改</button> <!-- 顯示修改視窗 -->
			<%
				}
				else {
			%>
				<button type="button" class="btn disabled">修改</button>
			<%}%>
			
				<div class="modal fade" id="updateComment">
					<div class="modal-dialog ">
						<div class="modal-content">
							<div class="modal-header">
					         <h4 class="modal-title">修改留言</h4>
					         <button type="button" class="close" data-dismiss="modal">&times;</button>
					       </div>
					       <div class="modal-body">
							<form action="UpdateCommentServlet" method="post"
								target="message2">
								<input type="hidden" name="comment"
									value=<%=commentbean.getCommentNum()%>>
								<p>留言內容</p>
								<textarea class="form-control"  name="UpdateContent"><%=commentbean.getContent()%></textarea>
								
							</form>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary"
									data-toggle="modal" data-target="#message">修改</button>
					            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					        </div>
						</div>
					</div>
				</div>
			</td>
			<td class="col-sm-2">
			<%
				if (commentbean.getMemberNum().equals(user)) {
			%>
				<form action="DeleteCommentServlet" method="post" target="message2">
					<input type="hidden" name="comment"
						value=<%=commentbean.getCommentNum()%>>
					<button type="submit" class="btn btn-danger" data-toggle="modal"
						data-target="#message">刪除</button>
						<%
				}
				else {
			%>
				<button type="button" class="btn disabled">刪除</button>
			<%}%>
				</form>
			</td>

			
		</tr>
		<%
			}
		%>
	</table>
	<%
		}
	%>
	</div>
	<div class="modal-footer">
		<%if(user!=null){ %>
		<!-- 新增留言 -->
		<button type="button" class="btn btn-info" data-toggle="modal"
			data-target="#newComment">新增留言</button>
		<!-- 顯示新增視窗 -->
		<div class="modal fade" id="newComment" >
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
			         <h4 class="modal-title">新增留言</h4>
			         <button type="button" class="close" data-dismiss="modal">&times;</button>
			       </div>
			       <div class="modal-body">
					<form action="NewCommentServlet" method="post" target="message2">
						<input type="hidden" name="user"value=<%=user%>>
						<input type="hidden" name="ISO3166"value=<%=ISO3166%>>
						<textarea class="form-control" name="NewContent" placeholder="請輸入留言內容"></textarea>
						
					</form>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#message">新增</button>
			          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
			        </div>
				</div>
			</div>
		</div>
		<%} %>
      
    </div>
	
	<!-- 顯示刪除成功訊息視窗 -->
	<div class="modal fade" id="message" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<iframe name="message2" style="border: 0"></iframe>
			</div>
		</div>
	</div>
</body>
</html>