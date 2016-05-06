/**
 * Created by Lee on 5/5/16.
 */
angular.module('homeApp', ['ngAnimate', 'ui.bootstrap']);
angular.module('homeApp').controller('homeCtrl',
    function ($scope, $http) {
        $scope.itemNumber = 0;
        $scope.totalPrice = 0.00;
        // $scope.items = [{
        //     "id": 001,
        //     "name": "Wang Lao Ji",
        //     "price": 3.00,
        //     "calories": "100",
        //     "url": "image/wanglaoji.jpg",
        // }, {
        //     "id": 002,
        //     "name": "Wang Lao Ji",
        //     "price": 3.00,
        //     "calories": "100",
        //     "url": "image/pepsi.png",
        // }];

        $http({
            method : "GET",
            url : '/menuitems',
        }).success(function(data, status) {
            
            $scope.items = data;
            //initial items
            initItems($scope.items);

        }).error(function(data, status) {
        });



        //min 1 item
        $scope.min = function (item) {
            if (item.amount == 0) {
                return;
            } else {
                item.amount--;
                $scope.totalPrice -= item.price;
                $scope.itemNumber--;
            }
        };

        //plus 1 item
        $scope.plus = function (item) {
            if (item.amount >= 100) {
                return;
            }
            else {
                item.amount++;
                $scope.totalPrice += item.price;
                $scope.itemNumber++;
            }

        };

    });

//add an value to items obj for counting amount
function initItems(items) {
    for (var i = 0; i < items.length; i++) {
        items[i].amount = 0;
    }
}
