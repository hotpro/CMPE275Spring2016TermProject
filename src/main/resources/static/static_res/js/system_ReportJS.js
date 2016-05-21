/**
 * Created by Lee on 5/9/16.
 */
angular.module('systemReportApp', ['ngAnimate', 'ui.bootstrap']);
angular.module('systemReportApp').controller('systemReportCtrl',
    function ($scope, $http) {
        $scope.readOnly = false;
        $scope.rating = 3;
        $scope.itemsInOrder = "itemsInOrder";
        $scope.itemsInOrder
        $scope.orderHistory = [];
        $http({
            method: "GET",
            url: '/dashboard/getSystemReport',
            data: {
                "startTime": 123123,
                "endTime": 123123
            }
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


    });
