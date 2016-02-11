'use strict'

App.controller('HomeController', ['$scope', 'HomeService', function($scope, HomeService) {
    var self = this;
    var content = [];

    self.fetchHome = function() {
        HomeService.home()
            .then(
                function(data) {
                    self.content = data;
                }, function(errResponse) {
                    console.error('Error while fetching Home content');
                }
            );
    };

    self.fetchHome();
}]);