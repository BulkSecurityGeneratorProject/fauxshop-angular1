(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .controller('ProductController', ProductController);

    ProductController.$inject = ['$scope', 'Principal', 'ProductsService', 'productToDisplay', '$state'];

    function ProductController ($scope, Principal, ProductsService, productToDisplay, $state) {
        var vm = this;

        vm.product = productToDisplay;
    }
})();
