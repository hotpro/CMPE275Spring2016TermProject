/**
 * Created by Lee on 5/9/16.
 */
angular.module('orderReportApp', ['ngAnimate', 'ui.bootstrap']);
angular.module('orderReportApp').controller('orderReportCtrl',
    function ($scope, $http) {
        $scope.itemsInOrder = "itemsInOrder";
        $scope.itemsInOrder
        $scope.orderReports = [];
        $scope.startTime = new Date();
        $scope.endTime = new Date();
        $scope.popup = {
            start: false,
            end: false
        };
        $scope.start = function () {
            $scope.popup.start = true;
        };
        $scope.end = function () {
            $scope.popup.end = true;
        };
        $scope.selectedStartTime = function (startTime) {
            $scope.startTime = startTime;

        }
        $scope.selectedEndTime = function (endTime) {
            $scope.endTime = endTime;

        }

        $scope.find = function () {
            var startTime;
            var endTime;
            if (typeof $scope.startTime == 'number') {
                startTime = $scope.startTime
            } else {
                startTime = Date.parse($scope.startTime);
            }
            if (typeof $scope.endTime == 'number') {
                endTime = $scope.endTime
            } else {
                endTime = Date.parse($scope.endTime);
            }
            $http({
                method: "GET",
                url: '/dashboard/getOrderReport?startTime=' + startTime + '&endTime=' + endTime,
            }).success(function (data, status) {
                $scope.orderReports = data;
            }).error(function (data, status) {
                console.log(data);
            });
        }

    });

$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})
