'use strict';

angular.module('recruiterApp')
  .controller('MainCtrl', function ($scope, $http, $rootScope, $location) {

    // $http.get('/db/employees.json' ).
    $http.get('/api/people' ).
      success(function(data) {
       $scope.employees = data;
       console.log($scope.employees);
      });

    $rootScope.getResults = function(fullname, picurl, qaresponses, techskills, traits) {
      $rootScope.selectedEmployee = fullname;
      $rootScope.selectedEmployeePic = picurl;
      $rootScope.selectedEmployeeResponses = qaresponses;
      $rootScope.selectedEmployeeTechSkills = techskills;
      console.log('SelectedTechSkills', $rootScope.selectedEmployeeTechSkills);
      $rootScope.selectedEmployeeTraits = traits;

      for (var i = 0; i < $rootScope.surveyQuestions.length; i++) {
        $rootScope.selectedEmployeeSurvey[i] = {
          'question': $rootScope.surveyQuestions[i],
          'answer': $rootScope.selectedEmployeeResponses[i]
        };
      }

      console.log('surveyQuestions:');
      console.log($rootScope.surveyQuestions);

      $scope.nameArray = fullname.split(' ');
      for (var i=0; i<=$scope.nameArray.length; i++) {
        if ($scope.nameArray[i] != null) {
          $scope.nameArray[i] = $scope.nameArray[i].toLowerCase();
          $scope.nameArray[i] = $scope.nameArray[i].substring(0,1).toUpperCase() + $scope.nameArray[i].substring(1);
        }
      }
      $scope.firstName = $scope.nameArray[0];
      $scope.lastName = $scope.nameArray[1];
      $rootScope.selectedEmployee = $scope.firstName + ' ' + $scope.lastName
      $scope.url = '/api/search?fname=' + $scope.firstName.toUpperCase() + '&lname=' + $scope.lastName.toUpperCase();
      console.log($scope.url);
      $http.get($scope.url).
        error(function(data) {
          console.log(data);
          console.log('error');
        }).
        success(function(data) {
         // console.log(data);
         $rootScope.candidates = data;
         console.log('candidates loaded');
         $rootScope.filteredCandidates = $rootScope.candidates;

         for (var i=0; i < $rootScope.filteredCandidates.length; i++) {
            $rootScope.filteredCandidates[i].visible = true;
         }

         console.log('$rootScope.candidates retrieved from JSON:');
         console.log($rootScope.candidates);
        });

      $scope.vizurl = '/api/viz?fname=' + $scope.firstName.toUpperCase() + '&lname=' + $scope.lastName.toUpperCase();
      console.log($scope.vizurl);
      $http.get($scope.vizurl).
        error(function(data) {
          console.log(data);
          console.log('error');
        }).
        success(function(data) {
          console.log('VIZ OBJECT HERE');
          $rootScope.employeeViz=data;
          $('#employeeViz').html($rootScope.employeeViz);
        });

      $location.path( '/results' );
    };

    $rootScope.toggleExpanded = function(candidate) {
      if (candidate.expanded) {
        candidate.expanded = false;
      }
      else {
        candidate.expanded = true;
      }
    }

  });