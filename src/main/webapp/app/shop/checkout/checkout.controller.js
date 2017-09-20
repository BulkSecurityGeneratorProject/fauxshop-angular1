(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .controller('CheckoutController', CheckoutController);

    CheckoutController.$inject = ['$stateParams', 'Auth', 'LoginService'];

    function CheckoutController ($stateParams, Auth, LoginService) {
        var vm = this;

        Auth.activateAccount({key: $stateParams.key}).then(function () {
            vm.error = null;
            vm.success = 'OK';
        }).catch(function () {
            vm.success = null;
            vm.error = 'ERROR';
        });

        vm.login = LoginService.open;
    }
})();
