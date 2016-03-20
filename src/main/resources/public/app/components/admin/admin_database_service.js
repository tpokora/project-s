'use strict'

App.factory('DatabaseService', ['$http', '$q', function($http, $q) {
    return {
        fetchAllTablesDetails: function() {
            return $http.get('http://localhost:8080/rest/admin/database/table/list')
                .then(
                    function(response) {
                        return response.data.content.tables;
                    },
                    function(errResponse) {
                        console.error('Error while fetching tables details');
                        return $q.reject(errResponse);
                    }
                )
        }
    }
}])