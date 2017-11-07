(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .controller('CheckoutController', CheckoutController);

    CheckoutController.$inject = ['$stateParams', '$scope', '$state', 'CheckoutService', 'Auth', 'LoginService', 'CartService', 'ProductsService', 'StripeService', 'User', 'Principal'];

    function CheckoutController ($stateParams, $scope, $state, CheckoutService, Auth, LoginService, CartService, ProductsService, StripeService, User, Principal) {
        var vm = this;

        vm.checkoutData = loadCheckoutData();
        $scope.firstName = vm.checkoutData.firstName;
        $scope.lastName = vm.checkoutData.lastName;
        $scope.email = vm.checkoutData.email;
        $scope.address1 = vm.checkoutData.address1;
        $scope.address2 = vm.checkoutData.address2;
        $scope.city = vm.checkoutData.city;
        $scope.country = vm.checkoutData.country;
        $scope.country = "USA";
        $scope.postcode = vm.checkoutData.postcode;
        $scope.phone = vm.checkoutData.phone;
        $scope.createdOrdersRecordId = vm.checkoutData.createdOrdersRecordId;

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
            var cardInfo = createCardInfo();
            var orderDTO = createOrderDTO();
            // Create the order
            CheckoutService.createOrder(orderDTO)
            // Then charge using Stripe payment
                .then(function() {
                    StripeService.charge(vm.total() * 100, cardInfo)
            // Then update the status of the order to 'paid'
                .then(function(result) {
                    var orderDTOWithChargeId = createOrderDTOWithChargeId(result);
                    CheckoutService.updateChargeId(orderDTOWithChargeId);
                })
            // Then navigate back to the home page
                .then(function() {
                    $state.transitionTo('home', {}, { reload: true});
                })
            })
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
            orderId:$scope.createdOrdersRecordId,
            shippingCost:vm.shipping()
            });
        }

        function createOrderDTOWithChargeId(chargeRecord) {
            return JSON.stringify({type:"OrderDTO",
            orderId:$scope.createdOrdersRecordId,
            stripeChargeId:chargeRecord.data.id});
        }

        function loadCheckoutData(){
            return CheckoutService.loadCheckoutData();
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
