angular.module("appRelatorio", []);



angular.module("appRelatorio").controller("relatoriocontroller", function ($scope, $http) { 

	
 	$scope.vendascarregados = false;
 	$scope.errobackendcarregarvendas = false;

 	$scope.itensvendascarregados = false;
 	$scope.errobackendcarregaritensvendas = false;

	$scope.diaselecionado = ""; 	
  	
  	var carregarVendas = function(){

			$http.get("http://localhost:8080/relatorio/vendasdia").then(function (response) {

			    var data = response.data;
			    var status = response.status;
			    var statusText = response.statusText;
			    var headers = response.headers;
			    var config = response.config;
			    $scope.vendas = data;
			    $scope.vendascarregados = true;
			}, function myError(response) {

				var status = response.status;
				if (status != 200){
					$scope.errobackendcarregarvendas = true;
				}

				$scope.statusRetorno = response.statusText;
	 			$scope.vendascarregados = false;

			}
			);
		};

	carregarVendas();
	
	$scope.carregarItensVendas = function(dia){

			$scope.diaselecionado = dia;
			var datadia = dia;
			    var pos = datadia.indexOf("/");
			    while (pos > -1){
					datadia = datadia.replace("/", "-");
					pos = datadia.indexOf("/");
				}
			   
			
				$http.get("http://localhost:8080/relatorio/itenspedidodia/" + datadia).then(function (response) {

			    var data = response.data;
			    var status = response.status;
			    var statusText = response.statusText;
			    var headers = response.headers;
			    var config = response.config;
			    $scope.itensvendas = data;
			    $scope.itensvendascarregados = true;
			}, function myError(response) {

				var status = response.status;
				if (status != 200){
					$scope.errobackendcarregaritensvendas = true;
				}

				$scope.statusRetorno = response.statusText;
	 			$scope.itensvendascarregados = false;

			}
			);
	};



});

