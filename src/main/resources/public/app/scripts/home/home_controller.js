'use strict'

App.controller('HomeController', ['$scope', 'HomeService', function($scope, HomeService) {
    var self = this;
    var homeContent = [];

    self.fetchHome = function() {
        HomeService.home()
            .then(
                function(data) {
                    self.homeContent = data;
                },
                function(errResponse) {
                    console.error('Error while fetching Home content');
                }
            );
    };

    self.fetchHome();
}]);