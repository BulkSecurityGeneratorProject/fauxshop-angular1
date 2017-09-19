(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('product7', {
            parent: 'product',
            url: '/product7',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/products/product7/product7.html',
                    controller: 'ProductController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('product7');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
