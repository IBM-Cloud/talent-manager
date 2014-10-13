'use strict';

angular.module('recruiterApp')
  .controller('ResultsCtrl', function ($scope, $rootScope, $http) {

    // $.get( "http://puppygifs.net/api/read/json", function( data ) {
    //   console.log(data);
    //   alert( "Load was performed." );
    // });

    // $http.get('db/candidates.json').
    // $http.get('/api/search?fname=EMORY&lname=WREN').
    //   error(function(data) {
    //     console.log(data);
    //     console.log('error');
    //   }).
    //   success(function(data) {
    //    // console.log(data);
    //    $rootScope.candidates = data;
    //    console.log('candidates loaded');
    //    $rootScope.filteredCandidates = $rootScope.candidates;

    //    for (var i=0; i < $rootScope.filteredCandidates.length; i++) {
    //       $rootScope.filteredCandidates[i].visible = true;
    //    }

    //    console.log('$rootScope.candidates retrieved from JSON:');
    //    console.log($rootScope.candidates);
    //   });

    $scope.skills=[];

    $scope.hasSkills = function() {
      $scope.$apply(function() {
        console.log('candidates length', $rootScope.candidates.length);
        console.log('skills length', $scope.skills.length);
        for (var i = 0; i < $rootScope.candidates.length; i++) {
          console.log('inside candidate', $rootScope.candidates[0].name);
          for (var j = 0; j < $scope.skills.length; j++) {
            console.log('inside skills');
            if ($rootScope.filteredCandidates[i].resumeInfo.techSkills.indexOf($scope.skills[j]) == -1) {
              console.log($rootScope.filteredCandidates[i].resumeInfo.techSkills);
              console.log('visible false');
              $rootScope.filteredCandidates[i].visible = false;
              $rootScope.filteredCandidates[i].expanded = false;
              // $rootScope.filteredCandidates.splice(i,1);
              // $scope.hasSkills();
            }
          }
        }
      })

      // console.log('Updated List:');
      // console.log($rootScope.filteredCandidates);
    };
    
    $scope.followUpCandidate = function(candidate) {
        console.log('Adding Candidate...');

        console.log('Following up on canidate', candidate);

        $http({
          url: "/api/followup",
          method: "POST",
          data: candidate,
          headers: {
        	  "Content-Type": "text/plain"
          }
        }).success(function(response){
            console.log('SUCCESS:', response);
            alert("Added an action to follow up with " + candidate.name);
        }).error(function(error){
            console.log('ERROR:', error);
            alert("Added an action to follow up with " + candidate.name);
        });

      };

    $scope.getCandidateDetails = function(candidate) {
      console.log('Getting candidate viz and responses...');
      console.log(candidate);

      $scope.candidateNameArray = candidate.name.split(' ');
      for (var i=0; i<=$scope.candidateNameArray.length; i++) {
        if ($scope.candidateNameArray[i] != null) {
          $scope.candidateNameArray[i] = $scope.candidateNameArray[i].toLowerCase();
          $scope.candidateNameArray[i] = $scope.candidateNameArray[i].substring(0,1).toUpperCase() +
            $scope.candidateNameArray[i].substring(1);
        }
      }
      $scope.candidateFirstName = $scope.candidateNameArray[0];
      $scope.candidateLastName = $scope.candidateNameArray[1];
      $scope.candidateFullName = $scope.candidateNameArray.join(' ');
      console.log($scope.candidateFullName);

      for (var i = 0; i < $rootScope.surveyQuestions.length; i++) {
        $rootScope.selectedCandidateSurvey[i] = {
          'question': $rootScope.surveyQuestions[i],
          'answer': candidate.qaResponses[i]
        };
      }

      $scope.candvizurl = '/api/viz?fname=' + $scope.candidateFirstName.toUpperCase() + '&lname=' + $scope.candidateLastName.toUpperCase();
      console.log($scope.candvizurl);
      $http.get($scope.candvizurl).
        error(function(data) {
          console.log(data);
          console.log('error');
        }).
        success(function(data) {
          console.log('CANDIDATE VIZ OBJECT HERE');
          $rootScope.candidateViz=data;

          // not working yet
          $('#candidate-viz').html($rootScope.candidateViz);
        });
    }

    // TAGS STUFFS
    $(document).ready( function() {
      $("#tag-typer").keypress( function(event) {
        var key = event.which;
        if (key == 13 || key == 44){
         event.preventDefault();
         var tag = $(this).val();
          if(tag.length > 0){
            $("<span class='tag' style='display:none' data-name='" +tag+ "'><span class='close'>&times;</span>" +tag+ " </span>").insertBefore(this).fadeIn(100);

            var skillTagArray = tag.split(' ');
            for (var i=0; i<=skillTagArray.length; i++) {
              if (skillTagArray[i] != null) {
                skillTagArray[i] = skillTagArray[i].toLowerCase();
                skillTagArray[i] = skillTagArray[i].substring(0,1).toUpperCase() + skillTagArray[i].substring(1);
              }
            }
            tag=skillTagArray.join(' ');

            $scope.skills.push(tag);
            console.log($scope.skills);
            $scope.hasSkills();
            $(this).val("");
          }
        }
      });

      $("#tags").on("click", ".close", function() {
        $(this).parent("span").fadeOut(100);
        var skillToRemove = $(this).parent("span").data().name;
        $scope.skills.splice($scope.skills.indexOf(skillToRemove),1);
        console.log($scope.skills);
        console.log('REMOVING SKILL');
        for (var i=0; i < $rootScope.filteredCandidates.length; i++) {
          $rootScope.filteredCandidates[i].visible = true;
        }
        $scope.hasSkills();
      });

      $(".colors li").click(function() {
        var c = $(this).css("background-color");
        $(".tag").css("background-color",c);
        $("#title").css("color",c);
      });
    });

  })
  .directive('resultsRight', function() {
    return {
      restrict: 'E',
      templateUrl: 'views/_results-right.html'
    };
  })
  .directive('userModal', function() {
    return {
      restrict: 'E',
      templateUrl: 'views/_user-modal.html'
    };
  })
  .directive('candidateModal', function() {
    return {
      restrict: 'E',
      templateUrl: 'views/_candidate-modal.html'
    };
  })
  ;

