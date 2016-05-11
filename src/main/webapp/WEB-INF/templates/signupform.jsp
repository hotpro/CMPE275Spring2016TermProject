<%@ page language="java" contentType="text/html; UTF-8"
        pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en" ng-app="signup">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- font family -->
    <link href='https://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>

    <title>YummyTeam9.Food -- Sign up</title>
    <link href="static_res/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="static_res/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <style>
        body {
            font-family: 'Oswald', sans-serif;
        }
        .main {
                    background: url(static_res/image/signup_bg.jpg) repeat-y center center fixed;
                    -webkit-background-size: cover;
                    -moz-background-size: cover;
                    -o-background-size: cover;
                    background-size: cover;
                }
        .register-form-1 {
        	background: rgba(0,0,0,0.6);
        	border-radius: 8px;
        	color: rgb(197,197,197);
        	width: 550px;
        	margin: 110px auto 50px 315px;
        	padding: 10px 30px 30px 30px;
        }
        .register-form-1 h1, .register-form-1 p { color: rgb(255,255,255); }
        .register-form-1 a {
        	color: #FF9;
        	text-decoration: underline;
        }
        .register-form-1 a:hover { color: rgb(255,255,255); }
        .register-form-1 label { font-weight: 400;}
        .register-form-1 .form-control {
        	font-size: 16px;
        	padding: 10px 12px 10px 35px;
        }
        .register-form-1 input.form-control {	height: 45px; }
        .register-form-1 .fa { font-size: 20px; }
        .register-form-1 .form-control {
        	background-color: rgba(0, 0, 0, 0.6);
        	border: 1px solid rgba(176, 176, 176, 0.4);
        	color: white;
        }
        .register-form-1 .form-control:focus {
        	box-shadow: inset 0 1px 1px rgba(140, 220, 60, 0.7),0 0 10px rgba(140, 220, 60, 0.7);
        }
        .register-form-1 .templatemo-input-icon-container .fa {
        	color: rgb(197,197,197);
        	left: 12px;
        	top: 12px;
        }
        .register-form-1 .templatemo-input-icon-container .fa-envelope-o {
        	font-size: 18px;
        	top: 14px;
        }
        .templatemo-input-icon-container { position: relative; }
        .templatemo-input-icon-container .fa {
        	color: gray;
        	position: absolute;
        	left: 10px;
        	top: 10px;
        }
        .templatemo-input-icon-container input,
        .templatemo-input-icon-container textarea {
        	padding-left: 25px;
        }
    </style>

</head>
<body>
<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="/"
               style="font-family: Kokonor;">YummyTeam9.Food</a>
        </div>
    </div><!-- /.container -->
</nav><!-- /.navbar-collapse -->
<div class="container main" style="width:100%;">
    <div class="col-lg-6">
        <form class="form-horizontal register-form-1" id="signupForm" role="form" method="POST" action="/user/signup">
            <h3>Sign Up to <span
                    style="color:#f2f2f2; text-shadow: 2px 2px 4px #000000; font-family:Chalkboard;font-size:30px;margin-left:15px;">YummyTeam9</span>
            </h3>
        <hr />
        <div class="form-group">
            <label for="id_email" >Email</label>
            <div class="templatemo-input-icon-container">
            <i class="fa fa-envelope-o"></i>
            <input type="email" class="form-control" id="id_email" name="email" placeholder="Enter email">
            <span style="color: red">${param.error}</span>
        </div>
        </div>
        <div class="form-group">
            <label for="id_password">Password</label>
            <div class="templatemo-input-icon-container">
            <i class="fa fa-unlock-alt" aria-hidden="true"></i>
            <input type="password" class="form-control" id="id_password" name="password" placeholder="Enter password">
        </div>
        </div>
        <div class="form-group">
            <label for="id_confirmPassword">Confirm Password</label>
            <div class="templatemo-input-icon-container">
            <i class="fa fa-unlock-alt" aria-hidden="true"></i>
            <input type="password" class="form-control" id="id_confirmPassword" name="confirmPassword"
                   placeholder="Confirm Password">
        </div>
        </div>
        <br>
        <button type="button" onclick="check_psw()" class="btn btn-success" style="width:80px;margin-left:380px;">Join Us</button>
    </form>
</div>
</div>

<!-- Footer -->
<footer class="footer" style="height:50px;background-color:black;padding-top:15px;margin-top:0px;">
    <div align="center">
        <p class="text-muted" style="color:white;">copyright &copy; CMPE275 -- Project Group 9</p>
    </div>
</footer>
    <!-- Bootstrap Core JavaScript -->
    <script src="static_res/js/jquery.js"></script>
    <script src="static_res/bootstrap/js/bootstrap.min.js"></script>
    <script>
        function check_psw() {
            var signupForm = document.getElementById("signupForm");

            var psw = document.getElementById("id_password");
            var psw_confirm = document.getElementById("id_confirmPassword");
            if (psw.value != psw_confirm.value) {
                alert("Password not match!!!");
            } else {
                signupForm.submit();
            }
        }
    </script>
</body>
</html>
