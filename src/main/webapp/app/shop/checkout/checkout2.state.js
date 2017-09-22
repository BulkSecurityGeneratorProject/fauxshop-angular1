(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('checkout2', {
            parent: 'app',
            url: '/checkout2',
            data: {
                authorities: [],
                pageTitle: 'checkout2.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/shop/checkout/checkout2.html',
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
