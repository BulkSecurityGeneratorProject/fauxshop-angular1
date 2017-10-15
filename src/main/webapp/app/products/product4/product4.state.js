(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('product4', {
            parent: 'product',
            url: '/product4',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/products/product4/product4.html',
                    controller: 'ProductController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('product4');
                    return $translate.refresh();
                }],
                productToDisplay: ['ProductsService', function(ProductsService) {
                    return ProductsService.getProductsByProductsId(4).get();
                }]
            }
        });
    }
})();
