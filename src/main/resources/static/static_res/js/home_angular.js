/**
 * Created by Lee on 5/5/16.
 */
angular.module('homeApp', ['ngAnimate', 'ui.bootstrap']);
angular.module('homeApp').controller('homeCtrl',
    function ($scope, $http) {
        $scope.readOnly = true;
        $scope.rating = 3;
        $scope.submitSuccess = false;
        $scope.submitFailed = false;
        $scope.popover = {"url": "myPopover"};
        $scope.itemNumber = 0;
        $scope.totalPrice = 0.00;
        $scope.items = [];
        $scope.cart = [];
        $scope.pickupTime = new Date();
        $scope.popup = {
            opened: false
        };
        $scope.categoryCount = {
            "drink": 0,
            "appetizier": 0,
            "mainCourse": 0,
            "dessert": 0
        };
        $scope.open = function () {
            $scope.popup.opened = true;
        };

        $scope.selectedPickupTime = function (pickupTime) {
            $scope.pickupTime = pickupTime;

        }


        //get data from controller
        $http({
            method: "GET",
            url: '/menu',
        }).success(function (data, status) {
            $scope.items = data;
            initItems($scope.items);
            console.log($scope.items);
            for (var i = 0; i < $scope.items.length; i++) {
                if ($scope.items[i].category == 0) {
                    console.log(0);
                    $scope.categoryCount.drink += 1;
                } else if ($scope.items[i].category == 1) {
                    console.log(1);
                    $scope.categoryCount.appetizier += 1;
                } else if ($scope.items[i].category == 2) {
                    console.log(2);
                    $scope.categoryCount.mainCourse += 1;
                } else if ($scope.items[i].category == 3) {
                    console.log(3);
                    $scope.categoryCount.dessert += 1;
                }
                //rating number
                if ($scope.items[i].rateCount == 0) {
                	$scope.items[i].ratingNum = -1;
                } else {
                	$scope.items[i].ratingNum = parseInt($scope.items[i].rating/$scope.items[i].rateCount);
                }
            }

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
                for (var i = 0; i < $scope.cart.length; i++) {
                    if ($scope.cart[i].id == item.id) {
                        $scope.cart.splice(i, 1);
                    }
                }
                return;
            } else {
                for (var i = 0; i < $scope.cart.length; i++) {
                    if ($scope.cart[i].id == item.id) {
                        $scope.cart[i].amount--;
                        break;
                    }
                    else if (i == $scope.cart.length - 1) {
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
                            $scope.cart[i].amount++;
                            break;
                        }
                        else if (i == $scope.cart.length - 1) {
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
        $scope.getEarliestPickupTime = function () {
            $scope.submitSuccess = false;
            $scope.submitFailed = false;
            var tmpCart = [];
            for (var i = 0; i < $scope.cart.length; i++) {
                var tmp = {};
                tmp.menuId = $scope.cart[i].id;
                tmp.count = $scope.cart[i].amount;
                tmpCart.push(tmp);
            }

            $http({
                method: "POST",
                url: '/order/getEarliestPickupTime',
                data: tmpCart
            }).success(function (data, status) {
                $scope.pickupTime = data.earliestPickupTime;
            }).error(function (data, status) {
            });
        }


        //order checkout
        $scope.checkOut = function () {
            var checkOut = {};
            var tmpCart = [];
            var emptyFlag = 0;
            for (var i = 0; i < $scope.cart.length; i++) {
                var tmp = {};
                tmp.menuId = $scope.cart[i].id;
                tmp.count = $scope.cart[i].amount;
                emptyFlag += $scope.cart[i].amount;
                tmpCart.push(tmp);
            }
            if (emptyFlag == 0) {
                $scope.submitFailed = true;
                $scope.submitConfirmation = "Please select item before you submit";
            } else {

                checkOut.orderTOList = tmpCart;

                if (typeof $scope.pickupTime == 'number') {
                    checkOut.pickupTime = $scope.pickupTime
                } else {
                    checkOut.pickupTime = Date.parse($scope.pickupTime);
                }

                $http({
                    method: "POST",
                    url: '/order/submit',
                    data: checkOut
                }).success(function (data, status) {
                    if (data.code == 0) {
                        $scope.submitSuccess = true;
                        $scope.submitConfirmation = data.message;
                        $scope.cart = [];
                        $scope.itemNumber = 0;
                        $scope.totalPrice = 0.00;
                        initItems($scope.items);
                    } else if (data.code == 1) {
                        $scope.submitFailed = true;
                        $scope.submitConfirmation = data.message;
                    }
                }).error(function (data, status) {
                });
            }
        }
    });

//add an value to items obj for counting amount
function initItems(items) {
    for (var i = 0; i < items.length; i++) {
        items[i].amount = 0;
    }
};
$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})

