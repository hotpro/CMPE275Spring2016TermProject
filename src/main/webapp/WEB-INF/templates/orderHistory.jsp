<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" ng-app="orderHistoryApp">

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

        body {
            font-family: 'Oswald', sans-serif;
        }

        div.one {
            border: 1px darkgrey solid;
            height: 650px;
            margin: 10px 10px 10px 10px;
        }

        body {
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

<body ng-controller="orderHistoryCtrl">

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="home.html"
               style="font-family: Kokonor;">YummyTeam9.Food</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <!-- <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1"> -->
        <ul class="nav navbar-nav navbar-right">
            <li><a href="home.html">Home</a></li>
            <li><a href="#">Login</a></li>
            <li><a href="#">Create Account</a></li>
            <li class="dropdown"><a href="#" class="dropdown-toggle"
                                    data-toggle="dropdown">About Us<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Contacts</a></li>
                    <li><a href="locations.html">Locations</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <!-- /.navbar-collapse -->
    <!-- /.container -->
</nav>

<!-- Half Page Image Background Carousel Header -->
<header id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators" ng-non-bindable>
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for Slides -->
    <div class="carousel-inner" ng-non-bindable>
        <div class="item active">
            <!-- Set the first background image using inline CSS below. -->
            <div class="fill"
                 style="background-image: url('/static_res/image/chinesefood.jpg');"></div>
            <div class="carousel-caption">
                <h2>Delicious Chinese Food</h2>
            </div>
        </div>
        <div class="item">
            <!-- Set the second background image using inline CSS below. -->
            <div class="fill"
                 style="background-image: url('/static_res/image/appetizer.jpg');"></div>
            <div class="carousel-caption">
                <h2>Chinese Traditional Appetizer</h2>
            </div>
        </div>
        <div class="item">
            <!-- Set the third background image using inline CSS below. -->
            <div class="fill"
                 style="background-image: url('/static_res/image/dessert.jpg');"></div>
            <div class="carousel-caption">
                <h2>Delicate Desserts</h2>
            </div>
        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#myCarousel" ng-non-bindable data-slide="prev"> <i
            class="glyphicon glyphicon-chevron-left"></i>
    </a>
    <a class="right carousel-control" href="#myCarousel" ng-non-bindable data-slide="next"> <span
            class="glyphicon glyphicon-chevron-right"></span>
    </a>

</header>
<!-- content -->
<div align="center ">
    <table class="table" style="width:80%; margin:0 auto; font-family:  " ng-model="orderHistory">
        <thead style="font-size: x-large;">
        <tr>
            <th align="left">Order ID</th>
            <th align="left">Items</th>
            <th align="left">Pickup Time</th>
            <th align="left">Total Price</th>
            <th align="left">Status</th>
        </tr>
        </thead>
        <tbody ng-model="trStatus" style="font-size: larger;">
        <tr ng-repeat="historyItem in orderHistory" class="{{historyItem.status}}">
            <td align="left" style="width: 100px">{{historyItem.orderId}}</td>
            <td align="left" style="width: 200px">
                <table class="table table-hover" style="width: 100%">
                    <tr ng-repeat="item in historyItem.itemAndCount">
                        <td align="left">{{item.itemName}}</td>
                        <td align="center">x {{item.count}}</td>
                    </tr>
                </table>
            </td>
            <td align="left" style="width: 200px">{{historyItem.pickupTime}}</td>
            <td align="left" style="width: 100px">$ {{historyItem.totalPrice}}</td>
            <td align="left" style="width: 150px;">
                <div ng-if="historyItem.status == 'warning'">
                    <uib-progressbar class="progress-striped" value="40"
                                     type="warning">Not Start Yet</uib-progressbar>
                </div>
                <div ng-if="historyItem.status == 'info'">
                    <uib-progressbar class="progress-striped" value="70"
                                     type="info"> Processing</uib-progressbar>
                </div>
                <div ng-if="historyItem.status == 'success'">
                    <uib-progressbar class="progress-striped"
                                     type="success">Done</uib-progressbar>
                </div>
            </td>
            <td style="width: 100px;">
                <div ng-if="historyItem.status == 'warning'">
                    <button type="button" class="btn btn-danger active" ng-click="cancelOrder(historyItem)">Cancel
                    </button>
                </div>
                <div ng-if="historyItem.status == 'info'">
                    <button uib-popover="Cannot cancel because order is processing" popover-trigger="mouseenter"
                            type="button" class="btn btn-danger disabled">Cancel
                    </button>
                </div>
                <div ng-if="historyItem.status == 'success'">
                    <button uib-popover="Cannot cancel because order is done" popover-trigger="mouseenter"
                            type="button" class="btn btn-danger disabled">Cancel
                    </button>
                </div>
            </td>
        </tr>
        </tbody>
    </table>

</div>

<!-- Footer -->

<footer style="text-align: center;">
    <div class="row">
        <div class="col-lg-12">
            <p>Copyright &copy; CMPE275 -- Project Group 9</p>
        </div>
    </div>
    <!-- /.row -->
</footer>

<!-- /.container -->


</body>

</html>