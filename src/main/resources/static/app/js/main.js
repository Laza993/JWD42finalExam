var finalExam = angular.module("finalExamApp", ["ngRoute"]);

finalExam.controller("HomeCtrl", function($scope, $location){
	
});

finalExam.controller("editRacunCTrl", function($scope, $http, $routeParams, $location){
	console.log($routeParams.rid);

	var racunUrl = "api/racuni/" + $routeParams.rid;
	var urlBan = "api/banke/";
	
	$scope.edRacunNew = {};
	$scope.edRacunNew.imePrezime = "";
	$scope.edRacunNew.jmdb = "";
	$scope.edRacunNew.bankaId = "";
	$scope.edRacunNew.brRacuna = "";
	$scope.edRacunNew.tipId = "";


	var getBanke = function(){
		$http.get(urlBan).then(
			function success(res){
				$scope.banke = res.data;
				getRacun();
			},
			function error(){
				alert("failed to fetch banke")
			}
		)
	}
	getBanke();

	var getRacun = function(){

		$http.get(racunUrl).then(
			function success(res){
				$scope.edRacunNew = res.data;
				getTipove();
			},
			function error(){
				alert("failed to fetch racun")
			}
		)

	}
	

	var getTipove = function(){
		var urlTip = "api/banke/" + $scope.edRacunNew.bankaId + "/tipovi-racuna";
		$http.get(urlTip).then(
			function success(res){
				$scope.tipovi = res.data;
			},
			function error(){
				alert("failed to fetch tipove")
			}
		)
	}
	

	$scope.editRacun = function(){
		$http.put(racunUrl, $scope.edRacunNew).then(
			function success(res){
				$location.path('/racuni');
			}, 
			function error(){
				alert("failed to edit racun")
			}
		)
	}

});


finalExam.controller("RacunCTRL", function($scope, $http, $location){
	var url = "/api/racuni";
	var urlB = "api/banke";

	$scope.racuni = [];

	$scope.RacunNew = {};
	$scope.RacunNew.imePrezime = "";
	$scope.RacunNew.jmdb = "";
	$scope.RacunNew.bankaId = "";
	$scope.RacunNew.bankaNaziv = "";
	$scope.RacunNew.brRacuna = "";
	$scope.RacunNew.tipNaziv = "";
	$scope.RacunNew.tipId = "";

	$scope.pageNum= 0 ;
	$scope.totalPages = 1;

	$scope.banke = [];
	$scope.tipovi = [];

	$scope.search = {};
	$scope.search.jmbg = "";
	$scope.search.bankaId = "";



	$scope.addRacun = function(){
		console.log($scope.RacunNew);
		
		$http.post(url, $scope.RacunNew).then(
			function success(res){

				getRacune();
				$scope.RacunNew.imePrezime = "";
				$scope.RacunNew.jmdb = "";
				$scope.RacunNew.bankaId = "";
				$scope.RacunNew.brRacuna = "";
				$scope.RacunNew.tipId = "";
				
			}, 
			function error(){
				alert("failed to add racun")
			}
		)
	}


	var getRacune = function(){
		var config = {params:{}};
		

		if($scope.search.jmbg != ""){
			config.params.jmbg = $scope.search.jmbg;
		}
		if($scope.search.bankaId != ""){
			config.params.bankaId = $scope.search.bankaId;
		}

		config.params.pageNum = $scope.pageNum;

		var promise = $http.get(url, config)
		promise.then(
			function success(res){
				$scope.racuni = res.data;
				$scope.totalPages = res.headers("totalPages")
				console.log($scope.racuni);
				
				

			}, 
			function error(){
				alert("failed to fetch racune")
			}
		)
	}
	getRacune();

	var getBanke = function(){
		$http.get(urlB).then(
			function success(res){
				$scope.banke = res.data;
			},
			function error(){
				alert("failed to fetch banke")
			}
		)
	}
	getBanke();

	$scope.getTipovi = function(){
		var urlTip = "api/banke/" + $scope.RacunNew.bankaId + "/tipovi-racuna";
		$http.get(urlTip).then(
			function success(res){
				$scope.tipovi = res.data;
			},
			function error(){
				alert("failed to fetch tipove")
			}
		)
	}
	

	$scope.todeleteRacun = function(delID){
		var urldelete = url + "/" + delID;
		$http.delete(urldelete).then(
			function success(res){

				getRacune();
			},
			function error(){

				alert("failed to delete racun")
			}
		)
	}


	$scope.toEditRacun = function(id){
		$location.path('/racuni/edit/' + id);
	}

	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getRacune();
	}

	$scope.findRacun = function(){
		$scope.pageNum = 0;
		getRacune();
		$scope.search.jmbg = "";
		$scope.search.bankaId = "";
	}
});

finalExam.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html'
		})
		.when('/racuni', {
			templateUrl : '/app/html/racuni.html'
		})
		.when('/racuni/edit/:rid', {
			templateUrl : '/app/html/edit-racun.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);