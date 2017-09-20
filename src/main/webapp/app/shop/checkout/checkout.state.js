(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('checkout', {
            parent: 'app',
            url: '/checkout',
            data: {
                authorities: [],
                pageTitle: 'checkout.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/shop/checkout/checkout.html',
                    controller: 'CheckoutController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('activate');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
