'use strict'

App.factory('HomeService', ['$http', '$q', function($http, $q) {
    return {
        home: function() {
            return $http.get('http://localhost:8080/project-s/rest/home')
                .then(
                    function(response) {
                        return response.data;
                    }, function(errResponse) {
                        console.error('Error while fetching home page');
                        return $q.reject(errResponse);
                    }
                );
            }
        }
    }
]);