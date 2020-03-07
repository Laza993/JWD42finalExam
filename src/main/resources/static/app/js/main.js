var finalExam = angular.module("finalExamApp", ["ngRoute"]);

finalExam.controller("HomeCtrl", function($scope, $location){
	
	// $scope.toActivities = function(){
	// 	$location.path("activities");
	// }

	// $scope.toUsers = function(){
	// 	$location.path("users");
	// }
});


finalExam.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);