'use strict'

App.factory('ArticleService', ['$http', '$q', 'ConfigService', function($http, $q, ConfigService) {

    var baseUrl = '';
    ConfigService.baseUrl().then(function(data) {
        baseUrl = data;
    });

    return {
        fetchAllArticles: function() {
            return $http.get(baseUrl + '/rest/article/list')
                .then(function(response) {
                    return response.data.content.articles;
                }, function(errResponse) {
                    console.error('Error while fetching articles');
                    return $q.reject(errResponse);
                });
        },

        fetchArticleById: function(id) {
            return $http.get(baseUrl + '/rest/article/' + id)
                .then(function(response) {
                    return response.data.content.articles;
                }, function(errResponse) {
                    console.error('Error while fetching article');
                    return $q.reject(errResponse);
                });
        },

        createArticle: function(article) {
            return $http.post(baseUrl + '/rest/article/new', article)
                .then(
                    function(response) {
                        return response.data;
                    }, function(errResponse) {
                        console.error('Error while creating new Article');
                        return $q.reject(errResponse);
                    }
                )
        }
    }
}]);