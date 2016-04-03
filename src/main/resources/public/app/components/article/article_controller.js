'use strict'

App.controller('ArticleController', ['$scope', '$stateParams', '$location', 'ArticleService', function($scope, $stateParams, $location, ArticleService) {
    var self = this;
    var params = $stateParams;

    this.fetchAllArticles = function() {
        ArticleService.fetchAllArticles()
            .then(
                function(articles) {
                    $scope.articles = articles;
                }, function(errResponse) {
                    console.error('Error while fetching Articles');
                });
    };

    this.fetchAllArticles();

}]);