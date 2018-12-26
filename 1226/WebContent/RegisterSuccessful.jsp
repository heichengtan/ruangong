<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>RegisterSuccessful Page</title>
<!-- JQuery -->
<script type="text/javascript" src="bootstrap/js/jquery-3.3.1.min.js"></script>
<!-- Bootstrap核心 CSS-->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/mystyle.css" rel="stylesheet">
<!-- Bootstrap js -->
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

<script>
function myFunction() {
  window.alert('恭喜您註冊成功！\n請點擊確定跳轉回首頁');
  window.location.href='MainServlet';
}
</script>
</head>
<body onload="myFunction()">
  <nav class="navbar navbar-expand-md navbar-dark bg-dark">
	    <a class="navbar-brand abs" href="MainServlet">疫網打盡</a>
	    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsingNavbar">
	        <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="navbar-collapse collapse" id="collapsingNavbar">
	        <ul class="navbar-nav ml-auto">
		        	<li class="nav-item">
						<a class="nav-link" href="MainServlet">返回首頁</a>
					</li>
	        </ul>
	    </div>
	</nav>
</body>
</html>