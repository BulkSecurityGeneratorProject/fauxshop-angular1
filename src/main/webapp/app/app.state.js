(function() {
    'use strict';

    angular
        .module('fauxshopApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('app', {
            abstract: true,
            views: {
                'shopheader@': {
                    templateUrl: 'app/home/header/header.html',
                    controller: 'HomeController',
                    controllerAs: 'vm'
                },
                'navbar@': {
                    templateUrl: 'app/layouts/navbar/navbar.html',
                    controller: 'NavbarController',
                    controllerAs: 'vm'
                },
                'footer@': {
                    templateUrl: 'app/home/footer/footer.html',
                    controller: 'HomeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                authorize: ['Auth',
                    function (Auth) {
                        return Auth.authorize();
                    }
                ],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('global');
                }]
            }
        });
    }
})();
