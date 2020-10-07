var finalExam = angular.module("finalExamApp", ["ngRoute"]);

finalExam.controller("HomeCtrl", function($scope, $location){
	
});

finalExam.controller("editRacunCTrl", function($scope, $http, $routeParams, $location){

	var racunUrl = "api/racuni/" + $routeParams.rid;
	var tipRacunaUrl = "api/banke/" + $routeParams.bid + "/tipovi-racuna";
	var urlBan = "api/banke/";
	
	$scope.edRacunNew = {};
	$scope.edRacunNew.imePrezime = "";
	$scope.edRacunNew.jmdb = "";
	$scope.edRacunNew.bankaId = "";
	$scope.edRacunNew.brRacuna = "";
	$scope.edRacunNew.tipId = "";

	$scope.bankeEdit = [];
	$scope.tipoviEdit = [];


	var getBanke = function(){
		$http.get(urlBan).then(
			function success(res){
				$scope.bankeEdit = res.data;
				getRacun();
			},
			function error(){
				alert("failed to fetch banke")
			}
		)
	}
		
	var getRacun = function(){
		$http.get(racunUrl).then(
			function success(res){
				$scope.edRacunNew = res.data;
			},
			function error(){
				alert("failed to fetch racun")
			}
		)
	}

	var getTipove = function(){
		$http.get(tipRacunaUrl).then(
			function success(res){
				$scope.tipoviEdit = res.data;
				getBanke();
			},
			function error(){
				alert("failed to fetch tipove");
			}
		)
	}
	getTipove();

	$scope.getTipovePromena = function(bankId){
		var urlTip = "api/banke/" + bankId + "/tipovi-racuna";
		$http.get(urlTip).then(
			function success(res){
				$scope.tipoviEdit = res.data;
			},
			function error(){
				alert("failed to fetch tipove after change");
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

finalExam.controller("transactionCtrl", function($scope, $http, $location){

	var urlTransakcije = "api/transakcije";
	var urlRacuna = "/api/racuni";

	$scope.transakcija = {};

	$scope.transakcija.racunUplatiocId = "";
	$scope.transakcija.racunPrimaocId = "";
	$scope.transakcija.iznos = "";

	$scope.racuniTrans = [];

	$scope.makeTransaction = function(){
		if($scope.transakcija.racunUplatiocId == ""){
			alert("odaberite racun posiljaoc");
			return;
		}
		if($scope.transakcija.racunPrimaocId == ""){
			alert("odaberite racun primaoc");
			return;
		}
		if($scope.transakcija.racunUplatiocId === $scope.transakcija.racunPrimaocId){
			alert("odabrali ste isti racun za primaoca i posiljaoca");
			return;
		}
		if($scope.transakcija.iznos == ""){
			alert("unesite zeljeni iznos");
			return;
		}

		$http.post(urlTransakcije, $scope.transakcija).then(
			function success(res){
				alert("successful transaction")
				$location.path('/racuni');
			},
			function error(odgovor){

				if(odgovor.data.stateOfTransaction == false){
					alert("nema dovoljno sredstava");
				}else{
					alert("failed to make transaction");
				}
			}
		)
	}

	var getRacuneTrans = function(){
		var promise = $http.get(urlRacuna);
		promise.then(
			function success(res){
				$scope.racuniTrans = res.data;
			},
			function error(){
				alert("failed to fetch racune");
			}
		)
	}
	getRacuneTrans();


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
				if($scope.pageNum != 0 && $scope.racuni.length == 0){
					$scope.pageNum--;
					getRacune();
				}
				
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
				alert("choose your bank first");
				console.log("something happend");
				return;
			}
		)
	}
	

	$scope.todeleteRacun = function(delID){
		var urldelete = url + "/" + delID;
		$http.delete(urldelete).then(
			function success(){
				getRacune();
			},
			function error(response){
				if(response.status == 403){
					alert("Bad Request you have to pull all your funds!")
				}else{
					alert("failed to delete racun")
				}
				
			}
		)
	}


	$scope.toEditRacun = function(id, banksId){
		$location.path('/racuni/edit/' + id + "/" + banksId);
	}

	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getRacune();
	}

	$scope.findRacun = function(){
		$scope.pageNum = 0;
		getRacune();
	}

	$scope.clearSearch = function() {
		$scope.search.jmbg = "";
		$scope.search.bankaId = "";
		getRacune();
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
		.when('/racuni/edit/:rid/:bid', {
			templateUrl : '/app/html/edit-racun.html'
		})
		.when('/transaction', {
			templateUrl : '/app/html/transaction.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);