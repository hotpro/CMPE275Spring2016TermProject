/**
 * Created by Lee on 5/9/16.
 */
angular.module('orderHistoryApp', ['ngAnimate', 'ui.bootstrap']);
angular.module('orderHistoryApp').controller('orderHistoryCtrl',
    function ($scope, $http) {
        $scope.itemsInOrder = "itemsInOrder";
        $scope.orderHistory = [];
        $http({
            method: "GET",
            url: '/order/getOrderHistory'
        }).success(function (data, status) {
            $scope.orderHistory = data;
            // var tmp = {
            //     "orderId": 2,
            //     "itemAndCount": [{"itemName": "wanglaoji", "count": 20}],
            //     "totalPrice": 30.00,
            //     "picuptime": 12389718294,
            //     "status": 0
            // }
            // $scope.orderHistory.push(tmp);
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

        $scope.cancelOrder = function (historyItem) {
            $http({
                method: "POST",
                url: '/order/cancel',
                data: historyItem.orderId
            }).success(function (data, status) {
                console.log(data);
                if (data.code == 0) {
                    location.reload();
                }
            }).error(function (data, status) {
            });
        }

    });
