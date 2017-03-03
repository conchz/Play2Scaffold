// `main.js` is the file that sbt-web will use as an entry point
(function (requirejs) {
    'use strict';

    // -- RequireJS config --
    requirejs.config({
        // Packages = top-level folders; loads a contained file named 'main.js"
        packages: ['commons', 'home'],
        shim: {
            'jsRoutes': {
                deps: [],
                // it's not a RequireJS module, so we have to tell it what var is returned
                exports: 'jsRoutes'
            },
            'angular': {
                deps: ['jquery'],
                exports: 'angular'
            },
            'bootstrap': ['jquery'],
            'angular-route': ['angular'],
            'angular-cookies': ['angular'],
            'angular-ui-bootstrap': ['angular'],
            'angular-ui-bootstrap-tpls': ['angular']
        },
        paths: {
            'requirejs': ['../lib/requirejs/require'],
            'jquery': ['../lib/jquery/jquery'],
            'bootstrap': ['../lib/bootstrap/js/bootstrap'],
            'angular': ['../lib/angularjs/angular'],
            'angular-route': ['../lib/angularjs/angular-route'],
            'angular-cookies': ['../lib/angularjs/angular-cookies'],
            'angular-ui-bootstrap': ['../lib/angular-ui-bootstrap/ui-bootstrap'],
            'angular-ui-bootstrap-tpls': ['../lib/angular-ui-bootstrap/ui-bootstrap-tpls'],
            'jsRoutes': ['/jsroutes']
        }
    });

    requirejs.onError = function (err) {
        console.log(err);
    };

    require([
            'angular',
            'angular-cookies',
            'angular-route',
            'angular-ui-bootstrap',
            'angular-ui-bootstrap-tpls',
            'jquery',
            'bootstrap',
            './app'],
        function (angular) {
            angular.bootstrap(document, ['app']);
        }
    );
})(requirejs);
