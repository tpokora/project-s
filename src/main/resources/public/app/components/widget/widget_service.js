'use strict'

App.factory('WidgetService', ['$http', '$q', 'ConfigService', function($http, $q, ConfigService) {

    var baseUrl = '';
    ConfigService.baseUrl().then(function(data) {
        baseUrl = data;
    });


}]);