'use strict';

angular.module('recruiterApp')
  .controller('NewCandidateCtrl', function ($scope, $http, $rootScope) {

    $scope.newUser = {};
    $scope.newUser.resumeInfo = {};
    $scope.newUser.firstName = '';
    $scope.newUser.lastName = '';
    $scope.newUser.role = '';
    $scope.newUser.imageUrl = '';
    $scope.newUser.resumeInfo.techSkills = [];
    $scope.newUser.resumeInfo.pastEmployers = [];
    $scope.newUser.responses = [];
    $scope.newUser.group = 'CANDIDATE';

    $scope.addCandidate = function() {
      console.log('Adding Candidate...');

      console.log('NEW USER', $scope.newUser);
      // $scope.newUserString = JSON.stringify($scope.newUser);
      // console.log('STRING', $scope.newUserString);

      $http({
        url: "/api/add",
        // dataType: "json",
        method: "POST",
        data: $scope.newUser,
        headers: {
            "Content-Type": "text/plain"
        }
      }).success(function(response){
          console.log('SUCCESS:', response);
      }).error(function(error){
          console.log('ERROR:', error);
      });

    };

  });
