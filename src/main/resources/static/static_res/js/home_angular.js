/**
 * Created by Lee on 5/5/16.
 */
angular.module('homeApp', ['ngAnimate', 'ui.bootstrap']);
angular.module('homeApp').controller('homeCtrl',
    function ($scope, $http) {
        $scope.itemNumber = 0;
        $scope.totalPrice = 0.00;
        $scope.items=[];
        
        $http({
            method : "GET",
            url : '/menu',
        }).success(function(data, status) {
            $scope.items = data;
            initItems($scope.items);

        }).error(function(data, status) {
        });



        //min 1 item
        $scope.min = function (item) {
            if (item.amount == 0) {
                return;
            } else {
                item.amount--;
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
                item.amount++;
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
}
