(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .factory('MessageService', MessageService);

    MessageService.$inject = ['$http','$resource', '$state'];

    function MessageService ($http, $resource, $state) {
        var message = [];

        var service = {
            createMessage: createMessage
        };

        return service;

        function createMessage(messageToSave) {
            return $http.post('api/messages', messageToSave).
            success(function(data, status, headers, config) {
                message.id = data.id;
                }).
              error(function(data, status, headers, config) {
                });
        }

    }
})();
