(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .controller('FaqController', FaqController);

    FaqController.$inject = ['$window', '$stateParams', '$state', '$scope', 'Auth', 'LoginService', 'CartService', 'User', 'Principal'];

    function FaqController ($window, $stateParams, $state, $scope, Auth, LoginService, CartService, User, Principal) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.cartInvoices = null;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            console.log('$window.localStorage.guestId: ' + $window.localStorage.guestId);
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
                if (vm.account != null){
                    getCartInvoices();
                } else {
                    getGuestCartInvoices();
                }
            });
        }

        function getGuestCartInvoices() {
            vm.cartInvoices = CartService.getCartByUserId($window.localStorage.guestId).get();
        }

    function register () {
        $state.go('register');
    }

    function getCartInvoices() {
        vm.cartInvoices = CartService.getCartByUserId(vm.account.id).get();
    }

    Auth.activateAccount({key: $stateParams.key}).then(function () {
        vm.error = null;
        vm.success = 'OK';
    }).catch(function () {
        vm.success = null;
        vm.error = 'ERROR';
    });

    }
})();
