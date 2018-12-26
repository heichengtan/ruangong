<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<!-- JQuery -->
<script type="text/javascript" src="bootstrap/js/jquery-3.3.1.min.js"></script>
<!-- Bootstrap核心 CSS-->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/mystyle.css" rel="stylesheet">
<!-- Bootstrap js -->
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script>
	function validate() {
		var username = document.form.username.value;
		var password = document.form.password.value;
		if (username == null || username == "") {
			alert("Username cannot be blank");
			return false;
		} else if (password == null || password == "") {
			alert("Password cannot be blank");
			return false;
		}
	}
</script>
</head>
<body>
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
	<div class="container">
		<div class="container p-5">
		  <div class="row justify-content-sm-center">
		    <div class="col-sm-10 col-md-6">
		      <div class="card border-info">
		        <div class="card-header">會員登入</div>
		        <div class="card-body">
		              <form class="form-signin" name="form" action="LoginServlet" method="post"
			onsubmit="return validate()">
						<div class="form-group">
			              	<div class="form-inline">
			              		<label class="col-md-4 col-sm-6">Account:</label>
			                		<input type="text" class="form-control col-md-8 col-sm-6" name="account" required autofocus>
			                </div>
						</div>
						<div class="form-group">
			                <div class="form-inline">
			                	<label class="control-label col-md-4 col-sm-6">Password:</label>
			                	<input type="password" class="form-control col-md-8 col-sm-6" name="password" required>
			                </div>
		                </div>
		                <div class="form-group">
		                	<span style="color: red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>
		                </div>
		                <div class="form-group text-right">
			                <button class="btn btn-lg btn-primary" type="submit">Sign in</button>
			                <button class="btn btn-lg btn-danger"  type="reset" value="Reset">Reset</button>
		                </div>
		              </form>
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
	</div>
</body>
</html>