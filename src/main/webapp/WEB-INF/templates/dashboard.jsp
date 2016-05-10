<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0021)https://dribbble.com/ -->
<html lang="en" ng-app="App">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        Yummy Group 9
    </title>
    <meta name="theme-color" content="#333333">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <!-- font family -->
    <link href='https://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>

    <!--[if gte IE 7]><!-->
    <link rel="stylesheet" media="screen, projection" href="static_res/css/admin.css">
    <!-- <![endif]-->
    <style>
        body {
            font-family: 'Oswald', sans-serif;
        }
    </style>
</head>

<body id="shots" class="logged-out not-pro not-self not-team grid-small grid-with-meta" ng-controller="dashboardCtrl">
    <div id="header" style="position: fixed;" >
        <div id="header-inner">
            <div id="logo">
                <h1>YummyTeam 9</h1>
            </div>
            <ul id="nav">
                <li id="t-signup">
                    <a href="#">Logout</a>
                </li>
            </ul>
        </div>
    </div>
    <!-- /header -->
    <hr>
    <div id="wrap" style="padding-top: 56px;">
        <div class="full-pitch group">
            <h1 class="compact">
              
              <div style="margin-right:30px; display: block; float: right; width: 120px;text-align: right;">
                <img alt="IMAGE" ng-if="ajaxImageUrl" ng-src="/upload_images/{{ajaxImageUrl.url}}" width="100px" height="75px">
                <img alt="IMAGE" ng-if="!ajaxImageUrl">
              </div>

              <div style="margin-right:20px; display: block; float: right; width: 320px;text-align: right;">
                <div style="padding: 0px 10px 10px 0px;">
                  <strong>Prep Time : </strong>
                  <input class="search-text" type="number" step="1" max="15" min="1" placeholder="1" ng-model="menuItem.preparationTime"/>
                </div>
                
                <div style="padding: 0px 10px 10px 0px;">
                  <strong>Photo : </strong>
                  <input type="file" name="file" ngf-select="upload($file)" ngf-accept="'image/*'"/>
                </div>
                <div style="padding: 0px 10px 10px 0px;">
                  <input type="button" value="SUBMIT" style="font-size: 20px;" ng-click="submit()"/>
                </div>
                
              </div>


              <div style="margin-right:50px; display: block; float: right; width: 250px;text-align: right;">
                <div style="padding: 0px 10px 10px 0px;">
                  <strong>Price : </strong>
                  <input class="search-text" type="number" step="0.1" max="999" min="0" placeholder="0" ng-model="menuItem.unitPrice"/>
                </div>
                
                <div style="padding: 0px 10px 10px 0px;">
                  <strong>Category : </strong>
                  <select ng-options="item as item.name for item in menuCategories track by item.id" ng-model="menuItem.category"></select>
                  <!-- 
                  <select>
                    <option value="1">Drink</option>
                    <option value="2">Appetizer</option>
                    <option value="3">Main course</option>
                    <option value="4">Desert</option>
                  </select>
                   -->
                </div>
              </div>

              <div style="margin-right:50px; display: block; float: right; width: 250px;text-align: right;">
                <div style="padding: 0px 10px 10px 0px;">
                  <strong>Name : </strong>
                  <input class="search-text" type="text" ng-model="menuItem.name"/>
                </div>
                
                <div style="padding: 0px 10px 10px 0px;">
                  <strong>Calories : </strong>
                  <input class="search-text" type="number" step="1" min="0" placeholder="0" ng-model="menuItem.calories"/>
                </div>
              </div>
            </h1>
        </div>
        <div id="wrap-inner">
            <div style="position: fixed; top: 218px;">
                <div>
                    <ul id="side-fixed-nav">
                        <div style="text-align: center; margin-bottom: 10px;"><strong>Menu</strong></div>
                        <li class="{{selectedCategory == c ? 'active' : ''}}" ng-repeat="c in menuCategories" ng-click="selectCategory(c)"><a ng-bind="c.name"></a></li>
                    </ul>
                </div>
            </div>
            <div id="content">
                <div id="main" class="main-full">
                    <ol class="dribbbles group">
                        <!-- ITEM -->
                        <li id="screenshot-{{m.id}}" class="group " ng-repeat="m in menuItemFilterByCategory()">
                            <div class="dribbble">
                                <div class="dribbble-shot">
                                    <div class="dribbble-img">
                                        <a class="dribbble-link">
                                            <div data-picture="" data-alt="Codex">
                                                <img alt="Codex" src="/upload_images/{{m.pictureURL}}">
                                            </div>
                                        </a>
                                        <a class="dribbble-over">
                                            <strong ng-bind="m.name"></strong>
                                            <div class="comment">
                                                Unit Price : {{m.unitPrice | currency:"USD $"}}
                                            </div>
                                            <div class="comment">
                                                Calories : {{m.calories}}
                                            </div>
                                            <em class="timestamp">Yummy!</em>
                                        </a>
                                    </div>
                                    <ul class="tools group" style="visibility: visible;">
                                        <li class="fav" ng-click="removeItem(m)">
                                            <a>Delete</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <h2 class="attribution hover-card-parent">
                              <span class="attribution-user">
                              <a class="url hoverable" rel="contact">{{m.name}} </a>
                              </span>
                            </h2>
                        </li>
                        <!-- END ITEM -->
                    </ol>
                </div>
            </div>
        </div>
    </div>
    <hr>
    <div id="footer">
        <div id="footer-inner">
            <h4 id="pixels-total" class="group">
              <strong>Yummy</strong> Group 9
            </h4>
            <p>Copyright © 2016 Yummy Group 9.</p>
        </div>
    </div>
    <!-- /footer -->
    
    <!-- SCRIPTS -->
    <script src="static_res/angular/angular.js"></script>
    <script src="static_res/angular/angular-animate.js"></script>
    <script src="static_res/js/dashboard_angular.js"></script>
    <script src="static_res/angular/ng-file-upload-shim.min.js"></script>
    <script src="static_res/angular/ng-file-upload.min.js"></script>
</body>

</html>
