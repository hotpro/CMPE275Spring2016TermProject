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

        //get data from controller
        $http({
            method: "GET",
            url: '/menu',
        }).success(function (data, status) {
            $scope.items = data;
            initItems($scope.items);

        }).error(function (data, status) {
        });
        //delete item from cart
        $scope.deleteItem = function (item) {
            $scope.itemNumber -= item.amount;
            $scope.totalPrice = $scope.totalPrice - (item.amount * item.unitPrice);
            item.amount = 0
            for (var i = 0; i < $scope.cart.length; i++) {
                if ($scope.cart[i].id == item.id) {
                    $scope.cart.splice(i, 1);
                    break;
                }
            }
        }
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
                    else if (i == $scope.cart.length - 1) {
                        console.log("not find");
                        item.amount--;
                        $scope.cart.push(item);
                        break;
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
                            break;
                        }

                    }
                }
                $scope.totalPrice += item.unitPrice;
                $scope.itemNumber++;
            }
        };
        //preview for getting earliest pick up time then let client choose a time
        // $scope.preview = function () {
        //     $http({
        //         method: "POST",
        //         url: '/preview',
        //         data: {"cart": $scope.cart}
        //     }).success(function (data, status) {
        //         //wait for Order Controller
        //     }).error(function (data, status) {
        //     });
        // }


        // //wait for Order Controller
        // $scope.checkOut = function () {
        //     $http({
        //         method: "POST",
        //         url: '/submit',
        //         data: {"cart": $scope.cart}
        //     }).success(function (data, status) {
        //         //wait for Order Controller
        //     }).error(function (data, status) {
        //     });
        // }
});

//add an value to items obj for counting amount
function initItems(items) {
    for (var i = 0; i < items.length; i++) {
        items[i].amount = 0;
    }
};

