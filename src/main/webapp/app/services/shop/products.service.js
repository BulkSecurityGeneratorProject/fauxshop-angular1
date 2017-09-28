(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .factory('ProductsService', ProductsService);

    ProductsService.$inject = ['$resource'];

    function ProductsService ($resource) {

        var service = {
            getProductsByProductsId: getProductsByProductsId
        };

        return service;

        function getProductsByProductsId () {
            var getProductsByProductsId = $resource('api/products/1', {}, {
                'get': { method: 'GET', params: {}, isArray: false,
                    interceptor: {
                        response: function(response) {
                            // expose response
                            return response;
                        }
                    }
                }
            });

            getProductsByProductsId.get();
        }
    }
})();
