define([], function () {
    'use strict';

    var HomeCtrl = function ($scope, $rootScope, $http, $uibModal, $location, helper) {
        console.log(helper.sayHi());
        $rootScope.pageTitle = 'Play2Scaffold';
    };
    HomeCtrl.$inject = ['$scope', '$rootScope', '$http', '$uibModal', '$location', 'helper'];

    return {
        HomeCtrl: HomeCtrl
    };

});
