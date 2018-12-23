<%@ page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Register</title>
<!-- JQuery -->
<script type="text/javascript" src="bootstrap/js/jquery-3.3.1.min.js"></script>
<!-- Bootstrap核心 CSS-->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/mystyle.css" rel="stylesheet">
<!-- Bootstrap js -->
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script>
	function validate_fullname() {
		var fullname = document.form.fullname.value;
		
		if (fullname == null || fullname == "") {
			document.getElementById("fullname_label").innerText="此欄不得為空白"; 
			document.getElementById("fullname_label").style.display = 'block';
			return false;
		} 
		else if(fullname.length > 20){
			document.getElementById("fullname_label").innerText="最多輸入二十個字元"; 
			document.getElementById("fullname_label").style.display = 'block';
			return false;
		}
	}
	function validate_account() {
		var account = document.form.account.value;
		re = /^\w+$/;
		
		if (account == null || account == "") {
			document.getElementById("account_label").innerText="此欄不得為空白"; 
			document.getElementById("account_label").style.display = 'block';
			return false;
		} 
		else if(!re.test(account)){
			document.getElementById("account_label").innerText="此欄只能是英文及數字"; 
			document.getElementById("account_label").style.display = 'block';
			return false;
		}
		else if(account.length > 20){
			document.getElementById("account_label").innerText="最多輸入二十個字元"; 
			document.getElementById("account_label").style.display = 'block';
			return false;
		}
	}
	function validate_password() {
		var password = document.form.password.value;
		
		if (password == null || password == "") {
			document.getElementById("password_label").innerText="此欄不得為空白"; 
			document.getElementById("password_label").style.display = 'block';
			return false;
		} 
		else if(password.length < 6 ){
			document.getElementById("password_label").innerText="最少輸入六個字元"; 
			document.getElementById("password_label").style.display = 'block';
			return false;
		}
		else if(password.length > 20){
			document.getElementById("password_label").innerText="最多輸入二十個字元"; 
			document.getElementById("password_label").style.display = 'block';
			return false;
		}
	}
	function validate_conpassword() {
		var password = document.form.password.value;
		var conpassword = document.form.conpassword.value;
		
		if (conpassword == null || conpassword == "") {
			document.getElementById("conpassword_label").innerText="此欄不得為空白"; 
			document.getElementById("conpassword_label").style.display = 'block';
			return false;
		}
		else if(password!=conpassword){
			document.getElementById("conpassword_label").innerText="兩次密碼輸入不一樣"; 
			document.getElementById("conpassword_label").style.display = 'block';
			return false;
		}
	}
	function validate() {
		if(!validate_fullname()){
			return false;
		}
		if(!validate_account()){
			return false;
		}
		if(!validate_password()){
			return false;
		}
		if(!validate_conpassword()){
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
		    <div class="col-sm-10 col-md-10">
		      <div class="card border-info">
		        <div class="card-header">會員註冊</div>
		        <div class="card-body">
		              <form class="form-signin" name="form" action="RegisterServlet" method="post" onsubmit="return validate()">
						<div class="form-group">
			              	<div class="form-inline">
			              		<label class="col-md-3 col-sm-12">Full Name:</label>
			                		<input type="text"  class="form-control col-md-5 col-sm-12" name="fullname" onblur="validate_fullname()"  autofocus >
			                		<label  class="col-md-4 col-sm-12" id="fullname_label" style="color:red;display:none;" ></label>
			                </div>
						</div>
						<div class="form-group">
			                <div class="form-inline">
			                	<label class="control-label col-md-3 col-sm-6">User name:</label>
			                	<input type="text"  class="form-control col-md-5 col-sm-6" name="account"  onblur="validate_account()" >
			                	<label  class="col-md-4 col-sm-12" id="account_label" style="color:red;display:none;" ></label>
			                </div>
		                </div>
		                <div class="form-group">
			                <div class="form-inline">
			                	<label class="control-label col-md-3 col-sm-6">Password:</label>
			                	<input type="password" class="form-control col-md-5 col-sm-6" name="password"  onblur="validate_password()" >
			                	<label  class="col-md-4 col-sm-12" id="password_label" style="color:red;display:none;" ></label>
			                </div>
		                </div>
		                <div class="form-group">
			                <div class="form-inline">
			                	<label class="control-label col-md-3 col-sm-6">Confirm Password:</label>
			                	<input type="password" class="form-control col-md-5 col-sm-6" name="conpassword"  onblur="validate_conpassword()" >
			                	<label  class="col-md-4 col-sm-12" id="conpassword_label" style="color:red;display:none;" ></label>
			                </div>
		                </div>
		                <div class="form-group">
		                	<%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%>
		                </div>
		                <div class="form-group text-right">
			                <button class="btn btn-lg btn-primary" value="Register">Register</button>
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