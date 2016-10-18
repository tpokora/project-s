'use strict'

App.factory('ArticleService', ['$http', '$q', 'APIConfig', function($http, $q, APIConfig) {

    return {
        fetchAllArticles: function() {
            return $http.get(APIConfig.API_END_POINT() + '/rest/article/list')
                .then(function(response) {
                    return response.data.content.articles;
                }, function(errResponse) {
                    console.error('Error while fetching articles');
                    return $q.reject(errResponse);
                });
        },

        fetchArticleById: function(id) {
            return $http.get(APIConfig.API_END_POINT() + '/rest/article/' + id)
                .then(function(response) {
                    return response.data.content.articles;
                }, function(errResponse) {
                    console.error('Error while fetching article');
                    return $q.reject(errResponse);
                });
        },

        createArticle: function(article) {
            return $http.post(APIConfig.API_END_POINT() + '/rest/article/new', article)
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
