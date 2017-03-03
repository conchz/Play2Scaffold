define(['angular'], function (angular) {
    'use strict';

    var mod = angular.module('commons.helper', []);
    mod.service('helper', function () {
        return {
            sayHi: function () {
                return 'Welcome to use Play & Angular!';
            }
        };
    });

    return mod;
});
