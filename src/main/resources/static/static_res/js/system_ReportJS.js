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
        function sortBy(time) {
            return function (a, b) {
                if (a[time] > b[time]) {
                    return 1;
                } else if (a[time] < b[time]) {
                    return -1;
                }
                return 0;
            }
        }

        $scope.sortByOrderTime = function () {
            $scope.orderHistory.sort(sortBy("orderTime"))
            location.reload()
        }
        $scope.sortByStartTime = function () {
            $scope.orderHistory.sort(sortBy("startTime"))
            location.reload()
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
            console.log("startTime parameter " + typeof startTime + " " + Date(startTime));
            console.log("endTime parameter " + typeof endTime + " " + Date(endTime));
            $http({
                method: "GET",
                url: '/dashboard/getSystemReport?startTime=' + startTime + '&endTime=' + endTime,
            }).success(function (data, status) {
                $scope.orderHistory = data;
                console.log($scope.orderHistory);
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
