(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('termsandconditions', {
            parent: 'app',
            url: '/termsandconditions',
            data: {
                authorities: [],
                pageTitle: 'termsandconditions.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/shop/termsandconditions/termsandconditions.html',
                    controller: 'TermsAndConditionsController',
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
