/**
 * Created by Lee on 5/9/16.
 */
angular.module('orderHistoryApp', ['ngAnimate', 'ui.bootstrap']);
angular.module('orderHistoryApp').controller('orderHistoryCtrl',
    function ($scope, $http) {
        $scope.itemsInOrder = "itemsInOrder";
        var time = new Date();
        $scope.orderHistory = [{
            "orderId": "001",
            "itemAndCount": [{
                "itemName": "wanglaoji",
                "count": 5
            }, {
                "itemName": "wanglaoji",
                "count": 5
            }, {
                "itemName": "wanglaoji",
                "count": 5
            }, {
                "itemName": "wanglaoji",
                "count": 5
            }],
            "pickupTime": time,
            "totalPrice": 15.00,
            "status": 0
        }, {
            "orderId": "001",
            "itemAndCount": [{
                "itemName": "wanglaoji",
                "count": 5
            }, {
                "itemName": "wanglaoji",
                "count": 5
            }, {
                "itemName": "wanglaoji",
                "count": 5
            }, {
                "itemName": "wanglaoji",
                "count": 5
            }],
            "pickupTime": time,
            "totalPrice": 15.00,
            "status": 2
        }, {
            "orderId": "001",
            "itemAndCount": [{
                "itemName": "wanglaoji",
                "count": 5
            }, {
                "itemName": "wanglaoji",
                "count": 5
            }, {
                "itemName": "wanglaoji",
                "count": 5
            }, {
                "itemName": "wanglaoji",
                "count": 5
            }],
            "pickupTime": time,
            "totalPrice": 15.00,
            "status": 1
        }, {
            "orderId": "001",
            "itemAndCount": [{
                "itemName": "wanglaoji",
                "count": 5
            }, {
                "itemName": "wanglaoji",
                "count": 5
            }, {
                "itemName": "wanglaoji",
                "count": 5
            }, {
                "itemName": "wanglaoji",
                "count": 5
            }],
            "pickupTime": time,
            "totalPrice": 15.00,
            "status": 0
        }, {
            "orderId": "001",
            "itemAndCount": [{
                "itemName": "wanglaoji",
                "count": 5
            }, {
                "itemName": "wanglaoji",
                "count": 5
            }, {
                "itemName": "wanglaoji",
                "count": 5
            }, {
                "itemName": "wanglaoji",
                "count": 5
            }],
            "pickupTime": time,
            "totalPrice": 15.00,
            "status": 0
        }];
        for (var i = 0; i < $scope.orderHistory.length; i++) {
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
        $scope.cancelOrder = function (historyItem) {
            $http({
                method: "GET",
                url: '/cancelOrder',
                data: historyItem.orderId
            }).success(function (data, status) {

            }).error(function (data, status) {
            });

        }

    });
