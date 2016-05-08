/**
 * Created by Xiaofeng on 5/5/16.
 */
angular.module('App', ['ngAnimate','ngFileUpload']);
angular.module('App').controller('dashboardCtrl', ['$scope', '$http', 'Upload', 
		function ($scope, $http, Upload) {

			$scope.menuCategories = [{"id": 1, "name": "Drink"}, 
			                         {"id": 2, "name": "Appetizer"}, 
			                         {"id": 3, "name": "Main course"}, 
			                         {"id": 4, "name": "Desert"},];
			
			$scope.ajaxImageUrl = null;
			
			$scope.menuItem = null;
			
			$scope.menuItemList = [];
			
			$scope.selectedCategory = $scope.menuCategories[0];
			
			$http.get('/dashboard/listItems')
    				.then(function successCallback(response) {
    				    console.log("success");
    				    if (response && angular.isArray(response.data)) {
    				    	$scope.menuItemList = response.data;
    				    	
    				    	console.log(angular.toJson(response.data));
    				    }
    				}, function errorCallback(response) {
    				    console.log("error");
    				});
	        
			// upload on file on select
		    $scope.upload = function (file) {
		        Upload.upload({
		            url: '/dashboard/addImage',
		            data: {file: file}
		        }).then(function (resp) {
		            //console.log('Success ' + angular.toJson(resp));
		            $scope.ajaxImageUrl = resp.data;
		        }, function (resp) {
		        	$scope.ajaxImageUrl = null;
		            console.log('Error status: ' + resp.status);
		        }, function (evt) {
		            
		        });
		    };
		    
		    $scope.submit = function () {
		    	if ($scope.menuItem && 
		    		$scope.menuItem.name && 
		    		$scope.menuItem.category && 
		    		$scope.menuItem.unitPrice && 
		    		$scope.menuItem.calories &&
		    		$scope.menuItem.preparationTime &&
		    		$scope.ajaxImageUrl) {
		    		var category = $scope.menuItem.category.id;
		    		$scope.menuItem.category = category;
		    		$scope.menuItem.pictureURL = $scope.ajaxImageUrl.url;
		    		console.log(angular.toJson($scope.menuItem));
		    		$http.post('/dashboard/addItem', 
		    				angular.fromJson($scope.menuItem))
		    				.then(function successCallback(response) {
		    				    console.log("success");
		    				    $scope.ajaxImageUrl = null;
		    					$scope.menuItem = null;
		    					if ($scope.menuItemList) {
		    						$scope.menuItemList.push(response.data);
		    					}
		    				}, function errorCallback(response) {
		    				    console.log("error");
		    				    $scope.ajaxImageUrl = null;
		    					$scope.menuItem = null;
		    				});
		    	}
		    }
		    
		    $scope.menuItemFilterByCategory = function() {
		    	if ($scope.selectedCategory) {
		    		var list = [];
		    		angular.forEach($scope.menuItemList, function(value, key) {
		    			if (value.category == $scope.selectedCategory.id) {
		    				this.push(value);
		    			}
		    		}, list);
		    		return list;
		    	} else {
		    		return [];
		    	}
		    }
		    
		    $scope.selectCategory = function(category) {
		    	$scope.selectedCategory = category;
		    }
		    
}]);