<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>YummyTeam9.Food -- home</title>
    <!-- font family -->
    <link href='https://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
    <!-- Bootstrap Core CSS -->
    <link href="/static_res/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/static_res/css/half-slider.css" rel="stylesheet">

    <!-- jQuery -->
    <script src="/static_res/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/static_res/bootstrap/js/bootstrap.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <!--angular JS-->
    <script src="/static_res/angular/angular.js"></script>
    <script src="/static_res/angular/angular-animate.js"></script>
    <script src="/static_res/angular/ui-bootstrap-tpls-0.13.4.min.js"></script>
    <script src="/static_res/angular/ui-bootstrap-tpls-1.3.2.js"></script>
    <script src="/static_res/angular/ui-bootstrap-tpls-0.14.2.min.js"></script>
    <script src="/static_res/js/orderHistory_angular.js"></script>
    <!-- Script to Activate the Carousel
    <!-- <script>
        $('#myCarousel').carousel({
            interval: 3000
            //changes the speed
        })
    </script> -->
    <![endif]-->
    <style>

        @font-face {
            font-family: "Bodoni SvtyTwo ITC TT";
            src: url("http://db.onlinewebfonts.com/t/8bc773512e829a52d207976b43c0ecca.eot");
            src: url("http://db.onlinewebfonts.com/t/8bc773512e829a52d207976b43c0ecca.eot?#iefix") format("embedded-opentype"),
            url("http://db.onlinewebfonts.com/t/8bc773512e829a52d207976b43c0ecca.woff2") format("woff2"),
            url("http://db.onlinewebfonts.com/t/8bc773512e829a52d207976b43c0ecca.woff") format("woff"),
            url("http://db.onlinewebfonts.com/t/8bc773512e829a52d207976b43c0ecca.ttf") format("truetype"),
            url("http://db.onlinewebfonts.com/t/8bc773512e829a52d207976b43c0ecca.svg#Bodoni SvtyTwo ITC TT") format("svg");
        }
        div.one {
            border: 1px darkgrey solid;
            height: 650px;
            margin: 10px 10px 10px 10px;
        }

        body {
            font-family: 'Oswald', sans-serif;
            background: url(/static_res/image/background5.jpg) repeat-y center center fixed;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }

        p.item {
            text-align: center;
            color: black;
            margin-bottom: 5px;
        }

        a:hover {
            color: blue;
        }

        .main-content {
            margin-top: 30px;
            background-color: rgba(220, 220, 220, 0.7);
            border-bottom-left-radius: 20px;
            border-top-left-radius: 20px;
            border-bottom-right-radius: 20px;
            border-top-right-radius: 20px;
        }

        .menu-content {
            border-bottom-left-radius: 20px;
            border-top-left-radius: 20px;
            border-bottom-right-radius: 20px;
            border-top-right-radius: 20px;
        }

        .menu-nav {
            border-bottom-left-radius: 20px;
            border-top-left-radius: 20px;
        }

        .menu-item-list {
            border-bottom-right-radius: 20px;
            border-top-right-radius: 20px;
        }

        -moz-selection {
            color: #fff;
            color: rgba(255, 255, 255, 0.85);
            background: #ea4c89
        }

        ::selection {
            color: #fff;
            color: rgba(255, 255, 255, 0.85);
            background: #ea4c89
        }

        #popoverMenu {
            display: none;
            float: right;
        }

        .popover {
            width: 700px;
        }

    </style>
</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-inverse" role="navigation" style="height: 80px;">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a class="navbar-brand" href="home.html"
					style="font-family: Kokonor;">YummyTeam9.Food</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<!-- <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1"> -->
			<ul class="nav navbar-nav navbar-right">
				<li style="width: 15%"><a href="home.html">Home</a></li>
				<li style="width: 15%"><a href="#">Login</a></li>
				<li style="width: 35%"><a href="#">Create Account</a></li>
				<li class="dropdown" style="width: 35%"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown">About Us<b
						class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#">Contacts</a></li>
						<li><a href="locations.html">Locations</a></li>
					</ul></li>
			</ul>
			<div align="center">
				<h2 style="color: #fff;">Login</h2>
			</div>
		</div>


		<!-- /.navbar-collapse -->
		<!-- /.container -->
	</nav>

	<!-- content -->
	<div style="margin-top: 50px; min-height: 60%;">
		<form class="form-horizontal col-md-4 col-md-offset-4" action="/login" method="post">
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="inputEmail3"
						placeholder="Email" name="username">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="inputPassword3"
						placeholder="Password" name="password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Sign in</button>
				</div>
			</div>
		</form>

	</div>

	<!-- Footer -->
	<footer class="footer">
		<div align="center" class="container">
			<p class="text-muted">copyright &copy; CMPE275 -- Project Group 9</p>
		</div>
	</footer>

	<!-- /.container -->


</body>

</html>