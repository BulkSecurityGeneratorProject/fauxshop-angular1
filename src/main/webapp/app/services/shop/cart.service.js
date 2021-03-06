(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .factory('CartService', CartService);

    CartService.$inject = ['$http','$resource', '$q'];

    function CartService ($http, $resource, $q) {
        var service = {
            getCartByUserId: getCartByUserId,
            addToCart: addToCart,
            removeFromCart: removeFromCart,
            updateCartQuantity: updateCartQuantity
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
                'save': {
                    method: 'POST',
                    transformResponse: function (data) {
                        return angular.fromJson(data);
                    }
                }
            });
            return addToCart
        }

        function removeFromCart(cartId) {
            var removeFromCart = $resource('api/cart/' + cartId, {}, {
                'post': { method:'POST' }
            });
            return removeFromCart
        }

        function updateCartQuantity(cartList) {
            return $http.post('api/cart/updateCartQuantity', cartList).
            success(function(data, status, headers, config) {
                }).
              error(function(data, status, headers, config) {
                });
        }
    }
})();
