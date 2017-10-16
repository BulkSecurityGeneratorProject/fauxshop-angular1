(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('product6', {
            parent: 'product',
            url: '/product6',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/products/product6/product6.html',
                    controller: 'ProductController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('product6');
                    return $translate.refresh();
                }],
                productToDisplay: ['ProductsService', function(ProductsService) {
                    return ProductsService.getProductsByProductsId(6).get();
                }]
            }
        });
    }
})();
