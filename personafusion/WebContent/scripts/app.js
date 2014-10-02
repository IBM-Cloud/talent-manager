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
  .directive('newCandidateModal', function() {
    return {
      restrict: 'E',
      scope: true,
      templateUrl: 'views/_new-candidate-modal.html',
      controller: 'NewCandidateCtrl'
    };
  })
  .directive('capitalize', function() {
     return {
       require: 'ngModel',
       link: function(scope, element, attrs, modelCtrl) {
          var capitalize = function(inputValue) {
             if(inputValue == undefined) inputValue = '';
             var capitalized = inputValue.toUpperCase();
             if(capitalized !== inputValue) {
                modelCtrl.$setViewValue(capitalized);
                modelCtrl.$render();
              }
              return capitalized;
           }
           modelCtrl.$parsers.push(capitalize);
           capitalize(scope[attrs.ngModel]);  // capitalize initial value
       }
     };
  })
  .run(function($rootScope, $location, $http) {
    $rootScope.candidates = [];
    $rootScope.filteredCandidates = [];
    $rootScope.selectedEmployee = [];
    $rootScope.selectedEmployeePic = [];
    $rootScope.selectedEmployeeResponses = [];
    $rootScope.selectedEmployeeSurvey = [];
    $rootScope.selectedCandidateSurvey = [];
    $rootScope.selectedEmployeeTechSkills = [];
    $rootScope.selectedEmployeeTraits = [];
    $rootScope.employeeViz = '';
    $rootScope.candidateViz = '';
    $rootScope.surveyQuestions = [
      "What is the last technical or scientific concept you learned on your own? What prompted you to learn it? How did you teach yourself?",
      "Congratulations! After a hard-fought campaign, you are now President of the World. What is the first change that you would make?",
      "Tell me about a time when you had to deal with a difficult person. How did you handle the situation?",
      "How do you deal with conflict?",
      "Would you say you are good with dealing with high-pressure or stressful situations?"
    ];
  })
;
