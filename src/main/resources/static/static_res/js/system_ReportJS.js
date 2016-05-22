/**
 * Created by Lee on 5/9/16.
 */
angular.module('systemReportApp', ['ngAnimate', 'ui.bootstrap']);
angular.module('systemReportApp').controller('systemReportCtrl',
    function ($scope, $http) {
        $scope.itemsInOrder = "itemsInOrder";
        $scope.itemsInOrder
        $scope.orderHistory = [];
        $scope.startTime = new Date();
        $scope.endTime = new Date();
        $scope.popup = {
            start: false,
            end: false
        };
        $scope.start = function () {
            $scope.popup.start = true;
        };
        $scope.end = function () {
            $scope.popup.end = true;
        };
        $scope.selectedStartTime = function (startTime) {
            $scope.startTime = startTime;

        }
        $scope.selectedEndTime = function (endTime) {
            $scope.endTime = endTime;

        }

        $scope.sortByOrderTime = function () {
            var startTime;
            var endTime;
            if (typeof $scope.startTime == 'number') {
                startTime = $scope.startTime
            } else {
                startTime = Date.parse($scope.startTime);
            }
            if (typeof $scope.endTime == 'number') {
                endTime = $scope.endTime
            } else {
                endTime = Date.parse($scope.endTime);
            }
            $http({
                method: "GET",
                url: '/dashboard/getSystemReport?startTime=' + startTime + '&endTime=' + endTime,
            }).success(function (data, status) {
                $scope.orderHistory = data;
                $scope.orderHistory.sort(function (a, b) {
                    // Turn your strings into dates, and then subtract them
                    // to get a value that is either negative, positive, or zero.
                    return b.orderTime - a.orderTime;
                });
                for (var i = 0; i < $scope.orderHistory.length; i++) {
                    var time = new Date($scope.orderHistory[i].pickupTime);
                    var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
                    var days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
                    var day = days[time.getDay()];
                    var hr = time.getHours();
                    var min = time.getMinutes();
                    if (min < 10) {
                        min = "0" + min;
                    }
                    var ampm = hr < 12 ? "am" : "pm";
                    var date = time.getDate();
                    var month = months[time.getMonth()];
                    var year = time.getFullYear();
                    $scope.orderHistory[i].orderTime = day + " " + hr + ":" + min + ampm + " " + date + " " + month + " " + year;
                    $scope.orderHistory[i].startTime = day + " " + hr + ":" + min + ampm + " " + date + " " + month + " " + year;
                    $scope.orderHistory[i].readyTime = day + " " + hr + ":" + min + ampm + " " + date + " " + month + " " + year;
                    $scope.orderHistory[i].pickupTime = day + " " + hr + ":" + min + ampm + " " + date + " " + month + " " + year;
                    switch ($scope.orderHistory[i].status) {
                        case 0:
                            $scope.orderHistory[i].status = "warning";
                            break;
                        case 1:
                            $scope.orderHistory[i].status = "info";
                            break;
                        case 2:
                            $scope.orderHistory[i].status = "success";
                            break;
                    }
                }

            }).error(function (data, status) {
                console.log(data);
            });
        }
        $scope.sortByStartTime = function () {
            var startTime;
            var endTime;
            if (typeof $scope.startTime == 'number') {
                startTime = $scope.startTime
            } else {
                startTime = Date.parse($scope.startTime);
            }
            if (typeof $scope.endTime == 'number') {
                endTime = $scope.endTime
            } else {
                endTime = Date.parse($scope.endTime);
            }
            $http({
                method: "GET",
                url: '/dashboard/getSystemReport?startTime=' + startTime + '&endTime=' + endTime,
            }).success(function (data, status) {
                $scope.orderHistory = data;
                $scope.orderHistory.sort(function (a, b) {
                    // Turn your strings into dates, and then subtract them
                    // to get a value that is either negative, positive, or zero.
                    return a.startTime - b.startTime;
                });
                for (var i = 0; i < $scope.orderHistory.length; i++) {
                    var time = new Date($scope.orderHistory[i].pickupTime);
                    var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
                    var days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
                    var day = days[time.getDay()];
                    var hr = time.getHours();
                    var min = time.getMinutes();
                    if (min < 10) {
                        min = "0" + min;
                    }
                    var ampm = hr < 12 ? "am" : "pm";
                    var date = time.getDate();
                    var month = months[time.getMonth()];
                    var year = time.getFullYear();
                    $scope.orderHistory[i].orderTime = day + " " + hr + ":" + min + ampm + " " + date + " " + month + " " + year;
                    $scope.orderHistory[i].startTime = day + " " + hr + ":" + min + ampm + " " + date + " " + month + " " + year;
                    $scope.orderHistory[i].readyTime = day + " " + hr + ":" + min + ampm + " " + date + " " + month + " " + year;
                    $scope.orderHistory[i].pickupTime = day + " " + hr + ":" + min + ampm + " " + date + " " + month + " " + year;
                    switch ($scope.orderHistory[i].status) {
                        case 0:
                            $scope.orderHistory[i].status = "warning";
                            break;
                        case 1:
                            $scope.orderHistory[i].status = "info";
                            break;
                        case 2:
                            $scope.orderHistory[i].status = "success";
                            break;
                    }
                }

            }).error(function (data, status) {
                console.log(data);
            });
        }
        $scope.getSystemReport = function () {
            var startTime;
            var endTime;
            if (typeof $scope.startTime == 'number') {
                startTime = $scope.startTime
            } else {
                startTime = Date.parse($scope.startTime);
            }
            if (typeof $scope.endTime == 'number') {
                endTime = $scope.endTime
            } else {
                endTime = Date.parse($scope.endTime);
            }
            console.log("startTime parameter " + typeof startTime + " " + startTime);
            console.log("endTime parameter " + typeof endTime + " " + endTime);
            $http({
                method: "GET",
                url: '/dashboard/getSystemReport?startTime=' + startTime + '&endTime=' + endTime,
            }).success(function (data, status) {
                $scope.orderHistory = data;
                for (var i = 0; i < $scope.orderHistory.length; i++) {
                    var time = new Date($scope.orderHistory[i].pickupTime);
                    var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
                    var days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
                    var day = days[time.getDay()];
                    var hr = time.getHours();
                    var min = time.getMinutes();
                    if (min < 10) {
                        min = "0" + min;
                    }
                    var ampm = hr < 12 ? "am" : "pm";
                    var date = time.getDate();
                    var month = months[time.getMonth()];
                    var year = time.getFullYear();
                    $scope.orderHistory[i].orderTime = day + " " + hr + ":" + min + ampm + " " + date + " " + month + " " + year;
                    $scope.orderHistory[i].startTime = day + " " + hr + ":" + min + ampm + " " + date + " " + month + " " + year;
                    $scope.orderHistory[i].readyTime = day + " " + hr + ":" + min + ampm + " " + date + " " + month + " " + year;
                    $scope.orderHistory[i].pickupTime = day + " " + hr + ":" + min + ampm + " " + date + " " + month + " " + year;
                    switch ($scope.orderHistory[i].status) {
                        case 0:
                            $scope.orderHistory[i].status = "warning";
                            break;
                        case 1:
                            $scope.orderHistory[i].status = "info";
                            break;
                        case 2:
                            $scope.orderHistory[i].status = "success";
                            break;
                    }
                }

            }).error(function (data, status) {
                console.log(data);
            });
        }


    });

$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})
