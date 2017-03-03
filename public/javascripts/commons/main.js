define(['angular', './services/helper', './services/playRoutes'],
    function (angular) {
        'use strict';

        return angular.module('app.commons', ['commons.helper', 'commons.playRoutes']);
    });
