/**
 * Created by Lee on 5/5/16.
 */
angular.module('homeApp', ['ngAnimate', 'ui.bootstrap']);
angular.module('homeApp').controller('homeCtrl',
    function ($scope, $http) {
        $scope.popover = {"url": "myPopover"};
        $scope.itemNumber = 0;
        $scope.totalPrice = 0.00;
        $scope.items = [];
        $scope.cart = [];

        $http({
            method: "GET",
            url: '/menu',
        }).success(function (data, status) {
            $scope.items = data;
            initItems($scope.items);

        }).error(function (data, status) {
        });
        //min 1 item
        $scope.min = function (item) {
            if (item.amount == 0) {
                return;
            } else {
                for (var i = 0; i < $scope.cart.length; i++) {
                    if ($scope.cart[i].id == item.id) {
                        console.log("find");
                        $scope.cart[i].amount--;
                        break;
                    }
                    else if (i = $scope.cart.length - 1) {
                        console.log("not find");
                        item.amount--;
                        $scope.cart.push(item);
                    }

                }
                $scope.totalPrice -= item.unitPrice;
                $scope.itemNumber--;
            }
        };
        //plus 1 item
        $scope.plus = function (item) {
            if (item.amount >= 100) {
                return;
            }
            else {
                if ($scope.cart.length == 0) {
                    item.amount++;
                    $scope.cart.push(item);
                } else {

                    for (var i = 0; i < $scope.cart.length; i++) {
                        if ($scope.cart[i].id == item.id) {
                            console.log("find");
                            $scope.cart[i].amount++;
                            break;
                        }
                        else if (i == $scope.cart.length - 1) {
                            console.log("not find");
                            item.amount++;
                            $scope.cart.push(item);
                        }

                    }
                }
                $scope.totalPrice += item.unitPrice;
                $scope.itemNumber++;
            }
        };
});

//add an value to items obj for counting amount
function initItems(items) {
    for (var i = 0; i < items.length; i++) {
        items[i].amount = 0;
    }
};

