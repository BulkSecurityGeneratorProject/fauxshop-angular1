(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('activate', {
            parent: 'accountState',
            url: '/activate?key',
            data: {
                authorities: [],
                pageTitle: 'activate.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/account/activate/activate.html',
                    controller: 'ActivationController',
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
