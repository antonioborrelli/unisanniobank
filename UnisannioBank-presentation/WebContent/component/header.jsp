<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//IT" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="resource/bootstrap-4.0.0-beta-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="resource/bootstrap-4.0.0-beta-dist/css/sticky-footer-navbar.css" >
<link rel="stylesheet" href="resource/bootstrap-4.0.0-beta-dist/css/unisannio.css" >

<title>Index</title>
</head>
<body>

 	<!-- Navbar -->
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
      <a class="navbar-brand" href="../view/">LOGO</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse navBarPrincipale" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
        	<li class="nav-item index">
            <a class="nav-link" href="../view/"></a>
          </li>
          <li class="nav-item console">
            <a class="nav-link" href="../view/console.php"></a>
          </li>
          <li class="nav-item admin">
            <a class="nav-link" href="../view/admin.php"></a>
          </li>
        </ul>
        <div class="form-inline mt-2 mt-md-0">
<!--           <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search"> -->
          <button class="btn btn-outline-success my-2 my-sm-0 btn-login" type="submit" style="display: none;">Login</button>
          <button class="btn btn-outline-success my-2 my-sm-0 btn-logout" type="submit" style="display: none;">Logout</button>
        </div>
      </div>
    </nav>
	<!-- Navbar fine-->
	
	
	<div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	style="display: none;">
	<div class="modal-dialog">
		<div class="loginmodal-container">
			<h1>Login to Your Account</h1>
			<br>
<!-- 			<form method="post" action="..."> -->
				<input type="text" name="user" placeholder="Username">
				<input type="password" name="pass" placeholder="Password"> 
				<input type="submit" name="login" class="login loginmodal-submit btn-modalLogin" value="Login">
<!-- 			</form> -->

			<div class="login-help">
				<a href="#">Register</a> - <a href="#">Forgot Password</a> 
			</div>
		</div>
	</div>
</div>
