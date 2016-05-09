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

    <title>YummyTeam9.Food -- Sign up</title>
    <link href="static_res/bootstrap/css/bootstrap.min.css" rel="stylesheet">


</head>
<body>

<div class="container">

    <form role="form" method="POST" action="/user/signup">
        <h3>Sign Up</h3>
        <hr />
        <div class="form-group">
            <label for="id_username">Username</label>
            <input type="text" class="form-control" id="id_username" placeholder="Enter username">
        </div>
        <div class="form-group">
            <label for="id_email">Email</label>
            <input type="email" class="form-control" id="id_email" placeholder="Enter email">
        </div>
        <div>
            <label for="id_password">Password</label>
            <input type="password" class="form-control" id="id_password" placeholder="Enter password">
        </div>
        <p></p>
        <button type="submit" class="btn btn-default">Sign Up</button>
    </form>
</div>

    <!-- Bootstrap Core JavaScript -->
    <script src="static_res/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
