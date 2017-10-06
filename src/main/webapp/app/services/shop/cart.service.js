(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .factory('CartService', CartService);

    CartService.$inject = ['$http','$resource'];

    function CartService ($http, $resource) {
        var service = {
            getCartByUserId: getCartByUserId,
            addToCart: addToCart
        };

        return service;

        function getCartByUserId (userId) {
            var cartByUserId = $resource('api/cart/' + userId, {}, {
                'get': {
                    method: 'GET',
                    isArray: true,
                    transformResponse: function (data) {
                        data = angular.fromJson(data);
                        return data;
                    }
                }
            });

            return cartByUserId;
        }

        function addToCart(id, productId, productQuantity) {
            var addToCart = $resource('api/cart/' + id + '/' + productId + '/' + productQuantity, {}, {
                'save': { method:'POST' }
            });
            return addToCart
        }
    }
})();