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

    <style>
        body {
            font-family: 'Oswald', sans-serif;
        }
    </style>

</head>
<body>

<div class="container">

    <form id="signupForm" role="form" method="POST" action="/user/signup">
        <h3>Sign Up</h3>
        <hr />
        <div class="form-group">
            <label for="id_email">Email</label>
            <input type="email" class="form-control" id="id_email" name="email" placeholder="Enter email">
            <span style="color: red">${param.error}</span>
        </div>
        <div>
            <label for="id_password">Password</label>
            <input type="password" class="form-control" id="id_password" name="password" placeholder="Enter password">
        </div>
        <div class="form-group">
            <label for="id_confirmPassword">Confirm Password</label>
            <input type="password" class="form-control" id="id_confirmPassword" name="confirmPassword"
                   placeholder="Confirm Password">
        </div>
        <p></p>
        <button type="button" onclick="check_psw()" class="btn btn-default">Sign Up</button>
    </form>
</div>

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
