(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('product8', {
            parent: 'product',
            url: '/product8',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/products/product8/product8.html',
                    controller: 'ProductController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('product8');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
