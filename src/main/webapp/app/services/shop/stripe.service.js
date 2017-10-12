(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .factory('StripeService', StripeService);

    StripeService.$inject = ['$resource','$http'];

    function StripeService ($resource, $http) {

        var service = {
            charge: charge
        };

        return service;

        function charge (amount, cardInfo) {
            return $http.post('api/charge/' + amount, cardInfo).
            success(function(data, status, headers, config) {
                console.log(data);
                }).
              error(function(data, status, headers, config) {
                });
        }
    }
})();
