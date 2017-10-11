(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .controller('CheckoutController', CheckoutController);

    CheckoutController.$inject = ['$stateParams', '$scope', '$state', 'CheckoutService', 'Auth', 'LoginService', 'CartService', 'ProductsService', 'StripeService', 'User', 'Principal'];

    function CheckoutController ($stateParams, $scope, $state, CheckoutService, Auth, LoginService, CartService, ProductsService, StripeService, User, Principal) {
        var vm = this;

        vm.formData = loadFormVariables();
        $scope.firstName = vm.formData.firstName;
        $scope.lastName = vm.formData.lastName;
        $scope.email = vm.formData.email;
        $scope.address1 = vm.formData.address1;
        $scope.address2 = vm.formData.address2;
        $scope.city = vm.formData.city;
        $scope.postcode = vm.formData.postcode;
        $scope.phone = vm.formData.phone;

        vm.tax = 20;
        vm.checkout = checkout;
        vm.goToCheckout2 = goToCheckout2;
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.cartInvoices = null;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function goToCheckout2() {
            CheckoutService.goToCheckout2($scope);
        }

        function checkout (event) {
            console.log("entering checkout() method in controller");
            var cardInfo = createCardInfo();
            var orderDTO = createOrderDTO();
            CheckoutService.createOrder(orderDTO);
            StripeService.charge(vm.total() * 100, cardInfo);
            console.log("WE MADE IT. existing checkout() method in controller");
        }

        function createCardInfo() {
            return JSON.stringify({type:"CardDTO", number:$scope.ccNumber, expMonth:$scope.ccExpMonth, expYear:$scope.ccExpYear, cvc:$scope.ccCvc});
        }

        function createOrderDTO() {
            return JSON.stringify({type:"OrderDTO", deliveryAddress1:$scope.address1,
            deliveryAddress2:$scope.address2,
            deliveryCity:$scope.city,
            deliveryCountry:$scope.country,
            deliveryName:$scope.firstName + " " + $scope.lastName,
            deliveryPostcode:$scope.postcode,
            deliveryState:$scope.state,
            id:vm.account.id,
            shippingCost:vm.shipping()});
        }

        function loadFormVariables(){
            return CheckoutService.loadFormVariables();
        }

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
                if (vm.account != null){
                    getCartInvoices();
                }
            });
        }

        function getCartInvoices() {
            vm.cartInvoices = CartService.getCartByUserId(vm.account.id).get();
        }

        function register () {
            $state.transitionTo('register');
        }

        vm.total = function() {
            var total = 0;
            angular.forEach(vm.cartInvoices, function(item) {
                    total += item.cartItemQuantity * item.productsPrice;
            })
            return total;
        }

        vm.shipping = function() {
            var shiping = 0;

            if(vm.total() < 100) {
                shiping = 25;
            } else {
                shiping = 0;
            }
            return shiping;
        }
    }
})();
