'use strict'

App.factory('DatabaseService', ['$http', '$q', 'ConfigService', function($http, $q, ConfigService) {

    var baseUrl = '';
    ConfigService.baseUrl().then(function(data) {
        baseUrl = data;
    })

    return {
        fetchAllTablesDetails: function() {
            return $http.get(baseUrl + '/rest/admin/database/table/list')
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