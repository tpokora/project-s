'use strict'

App.factory('UserService', ['$http', '$q', function($http, $q) {
    return {
        fetchAllUsers: function() {
            return $http.get('http://localhost:8080/rest/user/list')
                .then(
                    function(response) {
                        return response.data.content.users;
                    },
                    function(errResponse) {
                        console.error('Error while fetching Users');
                        return $q.reject(errResponse);
                    }
                )
        },

        fetchUserById: function(id) {
            return $http.get('http://localhost:8080/rest/user/' + id)
                .then(
                    function(response) {
                        return response.data.content.users;
                    },
                    function(errResponse) {
                        console.error('Error while fetching User');
                        return $q.reject(errResponse);
                    }
                )
        },

        fetchUserByUsername: function(username) {
                    return $http.get('http://localhost:8080/rest/user/name/' + username)
                        .then(
                            function(response) {
                                return response.data.content.users;
                            },
                            function(errResponse) {
                                console.error('Error while fetching User');
                                return $q.reject(errResponse);
                            }
                        )
                },

        createUser: function(user) {
            return $http.post('http://localhost:8080/rest/user/new', user)
                .then(
                    function(response) {
                        return response.data;
                    }, function(errResponse) {
                        console.error('Error while creating new User');
                        return $q.reject(errResponse);
                    }
                )
        },

        updateUser: function(id, user) {
            return $http.put('http://localhost:8080/rest/user/' + id + '/update', user)
                .then(
                    function(response) {
                        return response.data.content.users;
                    },
                    function(errResponse) {
                        console.error('Error while updating user');
                        return $q.reject(errResponse);
                    }
                )
        },

        deleteUserById: function(id) {
            return $http.delete('http://localhost:8080/rest/user/' + id + '/delete')
                .then(
                    function(response) {
                        return response.data;
                    },
                    function(errResponse) {
                        console.error('Error while deleting User');
                        return $q.reject(errResponse);
                    }
                )
        }
    }
}]);