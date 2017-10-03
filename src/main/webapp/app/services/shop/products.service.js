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

        function getProductsByProductsId (productsId) {
            var getProductsByProductsId = $resource('api/products/' + productsId, {}, {
                'get': {
                    method: 'GET',
                    transformResponse: function (data) {
                        data = angular.fromJson(data);
                        return data;
                    }
                }
            });

            return getProductsByProductsId;
        }
    }
})();
