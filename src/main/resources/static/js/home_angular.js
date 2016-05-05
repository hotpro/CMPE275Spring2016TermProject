/**
 * Created by Lee on 5/5/16.
 */
angular.module('homeApp', ['ngAnimate', 'ui.bootstrap']);
angular.module('homeApp').controller('homeCtrl',
    function ($scope, $http) {
        $scope.itemNumber = 0;
        $scope.totalPrice = 0.00;
        $scope.items = [{
            "id": 001,
            "name": "Wang Lao Ji",
            "price": 3.00,
            "calories": "100",
            "url": "image/wanglaoji.jpg"
        }, {
            "id": 002,
            "name": "Wang Lao Ji",
            "price": 3.00,
            "calories": "100",
            "url": "image/wanglaoji.jpg"
        }, {
            "id": 003,
            "name": "Wang Lao Ji",
            "price": 3.00,
            "calories": "100",
            "url": "image/wanglaoji.jpg"
        }, {
            "id": 004,
            "name": "Wang Lao Ji",
            "price": 3.00,
            "calories": "100",
            "url": "image/wanglaoji.jpg"
        }, {
            "id": 005,
            "name": "Wang Lao Ji",
            "price": 3.00,
            "calories": "100",
            "url": "image/wanglaoji.jpg"
        }, {
            "id": 006,
            "name": "Wang Lao Ji",
            "price": 3.00,
            "calories": "100",
            "url": "image/wanglaoji.jpg"
        }]
        $scope.addToCart = function (item) {
            $scope.totalPrice += item.price;
            $scope.itemNumber++;

        }


    });
