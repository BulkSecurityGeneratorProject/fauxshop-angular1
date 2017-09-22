(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .factory('CheckoutService', CheckoutService);

    CheckoutService.$inject = ['$http','$resource'];

    function CheckoutService ($http, $resource) {
            console.log("INSIDE CheckoutService");

        var service = {
            checkoutThing: checkoutThing
        };

        return service;

        function checkoutThing() {
            console.log("inside checkoutThing()");

        var checkoutThing1 = $resource('api/checkout', {}, {
            'query': {method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'save': { method:'POST' }
        });

            checkoutThing1.query();
            checkoutThing1.save();
//            checkoutThing1.get();

        }
    }
})();
