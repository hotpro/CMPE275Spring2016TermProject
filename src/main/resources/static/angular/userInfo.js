angular.module('facebookApp', [ 'ngAnimate', 'ui.bootstrap' ]);
angular.module('facebookApp').controller('facebookCtrl',
		function($scope, $http) {
			$scope.userInfo = function() {
				$http({
					method : "POST",
					url : '/userInfo',
					data : {
						"overview" : $scope.overview,
						"work" : $scope.work,
						"education" : $scope.education,
						"email" : $scope.email,
					}
				}).success(function(data) {
						window.location.assign("/");
				}).error(function(error) {
				});

			}
		});