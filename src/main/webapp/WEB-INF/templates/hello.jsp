<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" ng-app="homeApp">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>YummyTeam9.Food -- home</title>

    <!-- Bootstrap Core CSS -->
    <link href="static_res/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="static_res/css/half-slider.css" rel="stylesheet">

    <!-- jQuery -->
    <script src="static_res/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="static_res/bootstrap/js/bootstrap.min.js"></script>

    <!-- Script to Activate the Carousel -->
    <script>
        $('.carousel').carousel({
            interval: 5000
            //changes the speed
        })
    </script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <!--angular JS-->
    <script src="static_res/angular/angular.js"></script>
    <script src="static_res/angular/angular-animate.js"></script>
    <script src="static_res/angular/ui-bootstrap-tpls-0.13.4.min.js"></script>
    <script src="static_res/angular/ui-bootstrap-tpls-0.14.2.min.js"></script>
    <script src="static_res/js/home_angular.js"></script>
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
            font-family: "Bodoni SvtyTwo ITC TT";
        }

        div.one {
            border: 1px darkgrey solid;
            height: 650px;
            margin: 10px 10px 10px 10px;
        }

        body {
            background: url(static_res/image/background5.jpg) repeat-y center center fixed;
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

        #popoverMenu {display: none; float: right;}
        .popover{
            width:700px;
        }

    </style>
</head>

<body ng-controller="homeCtrl">

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
<header id="myCarousel" class="carousel slide">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for Slides -->
    <div class="carousel-inner">
        <div class="item active">
            <!-- Set the first background image using inline CSS below. -->
            <div class="fill"
                 style="background-image: url('static_res/image/chinesefood.jpg');"></div>
            <div class="carousel-caption">
                <h2>Delicious Chinese Food</h2>
            </div>
        </div>
        <div class="item">
            <!-- Set the second background image using inline CSS below. -->
            <div class="fill"
                 style="background-image: url('static_res/image/appetizer.jpg');"></div>
            <div class="carousel-caption">
                <h2>Chinese Traditional Appetizer</h2>
            </div>
        </div>
        <div class="item">
            <!-- Set the third background image using inline CSS below. -->
            <div class="fill"
                 style="background-image: url('static_res/image/dessert.jpg');"></div>
            <div class="carousel-caption">
                <h2>Delicate Desserts</h2>
            </div>
        </div>
    </div>

    <!-- Controls -->
    <a class="left" href="#myCarousel" data-slide="prev"> <span
            class="icon-prev"></span>
    </a> <a class="right" href="#myCarousel" data-slide="next"> <span
        class="icon-next"></span>
</a>

</header>

<!-- Page Content -->
<div class="container main-content">

    <div class="row">
        <div class="col-lg-9">
            <h1>
                Order Food on <b>YummyTeam9.Food</b>
            </h1>
            <p>You can order various Chinese food on our website, and pickup
                your order when you are convenient.</p>
        </div>
        
        <div id="popoverMenu">
            <table style="width:100%" ng-repeat="preview in items">
                <tr>
                    <td ng-if="preview.amount != 0">{{preview.name}}</td>
                    <td ng-if="preview.amount != 0">{{preview.amount}}</td>
                </tr>
            </table>
            Total:<span>{{totalPrice}}</span><br>
            <button type="button" class="btn btn-primary">Check Out</button>
        </div>
        <div class="col-lg-3" style="margin-top: 35px;">
            <a ui-sref="site.cart" style="padding: 5px 0 0 0; width: 150px">
                <ngcart-summary class="ng-isolate-scope">
                    <div class="row">
                        <div popover-placement="bottom" uib-popover-template="popover.url" popover-title="Cart"
                             class="col-md-2 col-md-offset-5 text-right" ng-click="getEarliestPickupTime()">
                            <svg version="1.1" class="icon"
                                 xmlns="http://www.w3.org/2000/svg"
                                 xmlns:xlink="http://www.w3.org/1999/xlink" width="30px"
                                 height="30px" xml:space="preserve">
			<path
                    d="M27.715,10.48l-2.938,6.312c-0.082,0.264-0.477,0.968-1.318,0.968H11.831
				c-0.89,0-1.479-0.638-1.602-0.904l-2.048-6.524C7.629,8.514,8.715,7.933,9.462,7.933c0.748,0,14.915,0,16.805,0
				C27.947,7.933,28.17,9.389,27.715,10.48L27.715,10.48z M9.736,9.619c0.01,0.061,0.026,0.137,0.056,0.226l1.742,6.208
				c0.026,0.017,0.058,0.028,0.089,0.028h11.629l2.92-6.27c0.025-0.073,0.045-0.137,0.053-0.192H9.736L9.736,9.619z M13.544,25.534
				c-0.819,0-1.482-0.662-1.482-1.482s0.663-1.484,1.482-1.484c0.824,0,1.486,0.664,1.486,1.484S14.369,25.534,13.544,25.534
				L13.544,25.534z M23.375,25.534c-0.82,0-1.482-0.662-1.482-1.482s0.662-1.484,1.482-1.484c0.822,0,1.486,0.664,1.486,1.484
				S24.197,25.534,23.375,25.534L23.375,25.534z M24.576,21.575H13.965c-2.274,0-3.179-2.151-3.219-2.244
				c-0.012-0.024-0.021-0.053-0.028-0.076c0,0-3.56-12.118-3.834-13.05c-0.26-0.881-0.477-1.007-1.146-1.007H2.9
				c-0.455,0-0.82-0.364-0.82-0.818s0.365-0.82,0.82-0.82h2.841c1.827,0,2.4,1.103,2.715,2.181
				c0.264,0.898,3.569,12.146,3.821,12.999c0.087,0.188,0.611,1.197,1.688,1.197h10.611c0.451,0,0.818,0.368,0.818,0.818
				C25.395,21.21,25.027,21.575,24.576,21.575L24.576,21.575z"></path></svg>
                        </div>
                        <div class="col-md-3">
                            <span ng-bind="itemNumber"></span> items <br> $<span
                                ng-bind="totalPrice"></span>
                        </div>
                    </div>
                </ngcart-summary>
            </a>
        </div>
    </div>
    <script type="text/ng-template" id="myPopover">
        <div ng-hide="submitSuccess">
        <div>
            <table style="width:100%; table-layout: fixed;" ng-repeat="item in cart">
                <tr>
                    <td ng-if="preview.amount != 0" align="left" style="width:60%;">{{item.name}}</td>
                    <td ng-if="preview.amount != 0" align="right">X</td>
                    <td ng-if="preview.amount != 0" align="right">{{item.amount}}</td>
                    <td ng-if="preview.amount != 0" align="right">
                        <button type="button" class="btn btn-xs btn-danger" ng-click="deleteItem(item)">x</button>
                    </td>
                </tr>
            </table>
        </div>
        <div>
            <br>
            <p>Earliest Pick up Time, You Can Satisfy to Your Own Time </p>
            <span class="input-group">
                <input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="pickupTime"
                       is-open="popup.opened" datepicker-options="dateOptions" ng-required="true" close-text="Close"
                       alt-input-formats="altInputFormats"/>
          <span class="input-group-btn">
            <button type="button" class="btn btn-default" ng-click="open()"><i
                    class="glyphicon glyphicon-calendar"></i></button>
          </span>
            </span>
            <uib-timepicker ng-model="pickupTime" show-meridian="false"></uib-timepicker>
        </div>
        <div>Total:<span>{{totalPrice}}</span></div>
        <div>
            <button type="button" class="btn  btn-sm btn-primary" ng-click="checkOut()">Check Out</button>
        </div>
        </div>
        <div ng-show="submitSuccess">
            <h1 ng-model="submitConfirmation"> {{submitConfirmation}}</h1>
        </div>
    </script>

    <div class="row one menu-content" style="width: 100%; margin-left: 0px; margin-right: 0px;">
        <div class="col-lg-2 menu-nav"
             style="background-color: black; height: 100%; overflow: auto; padding-top: 20px;">
            <ul class="nav nav-stacked" style="text-align: center; color: white;">
                <li class="active"><a data-toggle="pill" href="#drink">Drink</a></li>
                <li><a data-toggle="pill" href="#appetizier">Appetizier</a></li>
                <li><a data-toggle="pill" href="#main_course">Main Course</a></li>
                <li><a data-toggle="pill" href="#dessert">Dessert</a></li>
            </ul>
        </div>

        <div class="col-lg-10 menu-item-list"
             style="background-color: white; opacity: 0.9; height: 100%; overflow: auto; margin-bottom: 30px;">
            <div class="tab-content">
                <div id="drink" class="tab-pane fade in active">
                    <div class="row" style="margin: 10px 0px 0px 10px">
                        <div class="col-lg-3" ng-if="item.category == 0" ng-repeat="item in items">
                            <img ng-src="{{'static_res/' + item.pictureURL}}"
                                 class="img-thumbnail" alt="{{item.name}}"
                                 style="width: 100%; height: 100%;">
                            <p class="item">
                                {{item.name}}<br> Calories : {{item.calories}}<br>
                                Unit Price : $ {{item.unitPrice}}
                            </p>
                            <div align="center">
                                <button type="button" class="btn btn-warning" id="min"
                                        ng-click="min(item)">-
                                </button>
                                <span ng-bind="item.amount"></span>
                                <button type="button" class="btn btn-primary" id="plus"
                                        ng-click="plus(item)">+
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="appetizier" class="tab-pane fade">
                    <div class="row" style="margin: 10px 0px 0px 10px">
                        <div class="col-lg-3" ng-if="item.category == 1" ng-repeat="item in items">
                            <img ng-src="{{'static_res/' + item.pictureURL}}"
                                 class="img-thumbnail" alt="{{item.name}}"
                                 style="width: 100%; height: 100%;">
                            <p class="item">
                                {{item.name}}<br> Calories : {{item.calories}}<br>
                                Unit Price : $ {{item.unitPrice}}
                            </p>
                            <div align="center">
                                <button type="button" class="btn btn-warning" id="min"
                                        ng-click="min(item)">-
                                </button>
                                <span ng-bind="item.amount"></span>
                                <button type="button" class="btn btn-primary" id="plus"
                                        ng-click="plus(item)">+
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="main_course" class="tab-pane fade">
                    <div class="row" style="margin: 10px 0px 0px 10px">
                        <div class="col-lg-3" ng-if="item.category == 2" ng-repeat="item in items">
                            <img ng-src="{{'static_res/' + item.pictureURL}}"
                                 class="img-thumbnail" alt="{{item.name}}"
                                 style="width: 100%; height: 100%;">
                            <p class="item">
                                {{item.name}}<br> Calories : {{item.calories}}<br>
                                Unit Price : $ {{item.unitPrice}}
                            </p>
                            <div align="center">
                                <button type="button" class="btn btn-warning" id="min"
                                        ng-click="min(item)">-
                                </button>
                                <span ng-bind="item.amount"></span>
                                <button type="button" class="btn btn-primary" id="plus"
                                        ng-click="plus(item)">+
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="dessert" class="tab-pane fade">
                    <div class="row" style="margin: 10px 0px 0px 10px">
                        <div class="col-lg-3" ng-if="item.category == 3" ng-repeat="item in items">
                            <img ng-src="{{'static_res/' + item.pictureURL}}"
                                 class="img-thumbnail" alt="{{item.name}}"
                                 style="width: 100%; height: 100%;">
                            <p class="item">
                                {{item.name}}<br> Calories : {{item.calories}}<br>
                                Unit Price : $ {{item.unitPrice}}
                            </p>
                            <div align="center">
                                <button type="button" class="btn btn-warning" id="min"
                                        ng-click="min(item)">-
                                </button>
                                <span ng-bind="item.amount"></span>
                                <button type="button" class="btn btn-primary" id="plus"
                                        ng-click="plus(item)">+
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--
<hr>
  -->
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