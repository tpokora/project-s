'use strict'

App.controller('PropertiesController', ['$scope', '$http', function($scope, $http) {
    $scope.prop = {};
    $http.get('resources/developer.frontend.properties').then(function(response) {
        $scope.prop = response;

        console.log('Properties: ', $scope.prop);
   });
}]);