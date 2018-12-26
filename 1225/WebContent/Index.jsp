<%@page import="model.FavoriteCountryBean"%>
<%@page import="model.MainBean"%>
<%@page import="model.MainDAO"%>
<%@page import="model.FavoriteCountryDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>第九組</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- JQuery -->
<script type="text/javascript" src="bootstrap/js/jquery-3.3.1.min.js"></script>

<!-- Bootstrap核心 CSS-->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/mystyle.css" rel="stylesheet">
<!-- Bootstrap js -->
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>


		
<script>
function msg() {
  alert("刪除成功");
  location.reload();
}
</script>
</head>
<body>
	<%
		MainDAO mainDAO = new MainDAO();
		ArrayList<MainBean> countryData =  mainDAO.getCountryData();
		String user=null;
		if(session.getAttribute("user")==null)
			user=null;
		else
			user = session.getAttribute("user").toString();
		
	%>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
	    <a class="navbar-brand abs" href="MainServlet">疫網打盡</a>
	    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsingNavbar">
	        <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="navbar-collapse collapse" id="collapsingNavbar">
	        <ul class="navbar-nav ml-auto">
	        	<%if(user==null){%>
		        	<li class="nav-item">
						<a class="nav-link" href="Register.jsp">註冊</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="Login.jsp">登入</a>
					</li>
				<%}%>
				<%if(user!=null){%>
					<li class="nav-item">
						<a class="nav-link" href="LogoutServlet">Logout</a>
					</li>
				<%}%>
	        </ul>
	    </div>
	</nav><br />
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-10">
				<form action="RegionInformationServlet" method="post" target="informationFrame">
					<div class="row">
						<div class="col-md-10">
							<div class="form-group">
								<input type="hidden" name="user" value=<%=user%>>
								<select class="form-control" name="selectCountry"  id="country" onchange="contry_selected()">
									<%
										for (MainBean mainBean : countryData) {
									%>
									<option value="<%=mainBean.getISO3166()%>"><%=mainBean.getCountryName()%></option>
									<%
										}
									%>
								</select> 
							</div>
						</div>
						<div class="col-md-2">
							<button  type="submit" id="country_info" class="btn btn-success btn-block"  data-toggle="modal" data-target="#info">顯示國家資訊</button>
							<div class="modal fade" id="info">
								<div class="modal-dialog  modal-full">
									<div class="modal-content">
										<div class="modal-header">
								          <h4 class="modal-title">國家疫情資訊</h4>
								          <button type="button" class="close" data-dismiss="modal">&times;</button>
								        </div>
										<iframe name="informationFrame" width="100%"  style="border: 0;min-height:70vh;"></iframe>
										<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
							<div class="modal fade" id="message" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-lg">
								    <div class="modal-content">
										<iframe name="message1" style="border: 0" ></iframe>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
				<div class="row">
					<div class="col-md-12">
						<div id="map"></div>
					</div>
				</div>
				</div>
			<div class="col-md-2">
				<%if(user!=null){
			
					ArrayList<FavoriteCountryBean> favoriteCountryList = new ArrayList<FavoriteCountryBean>();
					FavoriteCountryDAO favoriteCountryDAO = new FavoriteCountryDAO();
					favoriteCountryList = favoriteCountryDAO.getFavoriteCountry(user);
					%>
				<div>
					  <button type="button" class="btn btn-warning btn-block " data-toggle="collapse" data-target="#demo">我的最愛列表</button>
					  
					  <div id="demo" class="collapse">
					    <table class="table table-hover">
					    <br />
						<%	
						System.out.print(user);
						if(favoriteCountryList.size()!=0){
						for (FavoriteCountryBean favoriteCountry : favoriteCountryList){
							System.out.print(favoriteCountry.getISO3166());
							%>
							<tr class="row">
								<td class="col-sm-6">
									<form action="RegionInformationServlet" method="post" target="informationFrame">
									<input type="hidden" name="user" value="<%=user%>">
									<input type="hidden" name="selectCountry" value="<%=favoriteCountry.getISO3166()%>">
									<button type="submit" class="btn btn-dark" data-toggle="modal"
				data-target="#info"><%=favoriteCountry.getCountry()%></button>
									</form>
								</td>
								<td class="col-sm-6">
									<form action="DeleteFavoriteServlet" method="post" target="message1">
									<input type="hidden" name="user" value="<%=user%>">
									<input type="hidden" name="selectCountry" value="<%=favoriteCountry.getISO3166()%>">
									<button type="submit" class="btn btn-danger" data-toggle="modal" data-target="#message1">刪除</button>
									</form>
								</td>
							</tr>
							<%		
						}}
						else{out.print("目前尚未加入任何國家");}
					%>
					</table>
				  </div>
				</div>
				<% } %>
			</div>
		</div>
	</div>
	<script>
	var clickvalue = {lat: 24.58, lng: 121.11};
	var markers = [];
	var map;
	
      function initMap() {
    	  map = new google.maps.Map(document.getElementById('map'), {
              zoom: 2,
              center: {lat: 24.58, lng: 121.11},
    		  maxZoom:5,
    		  minZoom:2
            });
    		
    		map.addListener('click', function(event) {
    			getCountry(event.latLng,map);
            });
    		

      }
      function getCountry(location,addrComponents) {
    	  //alert('zzz');
    	  //var input = location;
          //var latlngStr = input.split(',', 2);
          var latlng = location;
          map.center=location;
          var geocoder = new google.maps.Geocoder();
          geocoder.geocode({'location': latlng}, function(results, status) {
            if (status === 'OK') {
              if (results[0]) {
            	  setMapOnAll(null);
                var marker = new google.maps.Marker({
                  position: results[0].geometry.location,
                  map: map
                });
                map.setZoom(3);
                markers.push(marker);
                
                console.log(results[0]);
                for (var i=0; i<results[0].address_components.length; i++)
                    {
                        if (results[0].address_components[i].types[0] == "country") {
                                country = results[0].address_components[i].long_name;
                                country_code=results[0].address_components[i].short_name;
                           }
                    }
                var e = document.getElementById ("country");
                e.options [e.selectedIndex].text=country;
                e.options [e.selectedIndex].value=country_code;
                document.getElementById("country_info").click();
                //alert(country_code);
                
              } else {
                window.alert('No results found');
              }
            } else {
              window.alert('Geocoder failed due to: ' + status);
            }
          });
    	}

    	
      function contry_selected(){
    	  var geocoder = new google.maps.Geocoder();
    	  geocodeAddress(geocoder, map);
      }
      
  	function setMapOnAll(map) {
  		for (var i = 0; i < markers.length; i++) {
  			markers[i].setMap(map);
  		}
  	}
  	function geocodeAddress(geocoder, resultsMap) {
        
        var e = document.getElementById ("country");
        var scountry = e.options [e.selectedIndex].text;
        
        geocoder.geocode({'address': scountry}, function(results, status) {
          if (status === 'OK') {
            resultsMap.setCenter(results[0].geometry.location);
            setMapOnAll(null);
            var marker = new google.maps.Marker({
              map: resultsMap,
              position: results[0].geometry.location
            });
            map.setZoom(5);
            markers.push(marker);
          } else {
            alert('Geocode was not successful for the following reason: ' + status);
          }
        });
      }
    </script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCb2DMFtUuPNY1mEvvLpAounO5_cLGNpBA&callback=initMap"
    							async defer></script>
</body>
</html>