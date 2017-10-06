(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .controller('ProductController', ProductController);

    ProductController.$inject = ['$scope', 'Principal', 'ProductsService', 'LoginService', 'CartService', 'productToDisplay', '$state'];

    function ProductController ($scope, Principal, ProductsService, LoginService, CartService, productToDisplay, $state) {
        var vm = this;

        vm.product = productToDisplay;
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.cartInvoices = null;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });
        vm.addToCart = addToCart;

        getAccount();

    function register () {
        $state.go('register');
    }

    function getAccount() {
        Principal.identity().then(function(account) {
            vm.account = account;
            vm.isAuthenticated = Principal.isAuthenticated;
        });
    }

    function addToCart() {
        CartService.addToCart(vm.account.id, 1, 1).save();
    }

    }
})();
