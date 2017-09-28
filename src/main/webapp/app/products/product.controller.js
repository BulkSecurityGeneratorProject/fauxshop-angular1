(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .controller('ProductController', ProductController);

    ProductController.$inject = ['$scope', 'Principal', 'ProductsService', '$state'];

    function ProductController ($scope, Principal, ProductsService, $state) {
        var vm = this;


        vm.product = getProductsByProductsId();

        function getProductsByProductsId () {
            vm.product = ProductsService.getProductsByProductsId();
        }
    }
})();
