'use strict';

/**
 * @ngdoc overview
 * @name recruiterApp
 * @description
 * # recruiterApp
 *
 * Main module of the application.
 */
angular
  .module('recruiterApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/results', {
        templateUrl: 'views/results.html',
        controller: 'ResultsCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
  .filter('capitalize', function() {
   return function(input, scope) {
     var stringArray = input.split(' ');
     for (var i=0; i<=stringArray.length; i++) {
      if (stringArray[i] != null) {
        stringArray[i] = stringArray[i].toLowerCase();
        stringArray[i] = stringArray[i].substring(0,1).toUpperCase() + stringArray[i].substring(1);
      }
     }
     return stringArray.join(' ');
   }
  })
  .filter('percentage', ['$filter', function($filter) {
    return function(input, decimals) {
        return $filter('number')(input*100, decimals)+'%';
    };
  }])
  .filter('decimals', ['$filter', function($filter) {
    return function(input, decimals) {
        return $filter('number')(input*1, decimals)+'%';
    };
  }])
  .run(function($rootScope, $location, $http) {
    $rootScope.candidates = [];
    $rootScope.filteredCandidates = [];
    $rootScope.selectedEmployee = [];
    $rootScope.employeeViz = '';
  })
;
