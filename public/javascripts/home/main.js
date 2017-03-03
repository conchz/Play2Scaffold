define(['angular', './routes', './HomeCtrl'], function (angular, routes) {
    'use strict';

    return angular.module('app.home', [
        'ngRoute',
        'ui.bootstrap',
        'ui.bootstrap.tpls',
        'home.routes']);
});
