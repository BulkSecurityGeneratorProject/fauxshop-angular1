(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .factory('StripeService', StripeService);

    StripeService.$inject = ['$resource'];

    function StripeService ($resource) {

        var service = {
            charge: charge
        };

        return service;

        function charge () {
            var charge = $resource('api/charge/' + amount + '/' + token, {}, {
                'post': { method:'POST' }
            });

            return getProductsByProductsId;
        }
    }
})();g
