'use strict'

App.controller('ArticleController', ['$scope', '$stateParams', '$location', 'ArticleService', function($scope, $stateParams, $location, ArticleService) {
    var self = this;
    var params = $stateParams;

    $scope.newArticle = {
        id: '',
        title: '',
        content: '',
        date: '',
        userId: ''
    };

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

    this.fetchArticleById = function(id) {
        ArticleService.fetchArticleById(id)
            .then(function(article) {
                $scope.article = article;
            }, function(errResponse) {
                console.error('Error while fetching Article');
            });
    };

    if(params.id != null) {
        this.fetchArticleById(params.id);
    }

    $scope.createArticle = function(userId) {
        $scope.newArticle.userId;
        ArticleService.createArticle($scope.newArticle)
            .then(function() {
                $location.path('/articles');
            });
    };

}]);