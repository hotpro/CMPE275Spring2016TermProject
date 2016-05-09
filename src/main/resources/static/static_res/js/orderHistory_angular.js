/**
 * Created by Lee on 5/9/16.
 */
angular.module('orderHistoryApp', ['ngAnimate', 'ui.bootstrap']);
angular.module('orderHistoryApp').controller('orderHistoryCtrl',
    function ($scope, $http) {
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
        }]
    });
