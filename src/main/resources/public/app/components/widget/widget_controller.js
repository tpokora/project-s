'use strict'

App.controller('WidgetController', ['$scope', '$q', '$stateParams', '$location', 'RSSService', function($scope, $q, $stateParams, $location, RSSService) {
    var self = this;
    var params = $stateParams;

    console.log("ASDASDAsd");

    this.fetchRSSBySource = function(source) {
        RSSService.fetchRSSBySource(source)
            .then(
                function(rss) {
                    console.log("Source: " + source);
                    $scope.rss = rss;
                },
                function(errResponse) {
                    console.error('Error while fetching RSS');
                }
            );
    };

    if (params.source != null) {
        console.log("RSSSource: " + params.source);
        this.fetchRSSBySource(params.source);
    }
}]);