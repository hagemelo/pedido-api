angular.module("appCancelados", []);



angular.module("appCancelados").controller("canceladoscontroller", function ($scope, $http) { 

	$scope.showformnovopedido = false;
	$scope.habilitarButtonnovopedido = false;
 	$scope.pedidoscarregados = false;
 	$scope.errobackendcarregarpedidos = false;
  	$scope.produtoSelecionado=[];
  	$scope.itensselecionadas = [];
  	$scope.totalPedido = 'R$ 0,00';


  	var carregarPedidos = function(){

			$http.get("http://localhost:8080/atendimento/pedidoscancelados").then(function (response) {

			    var data = response.data;
			    var status = response.status;
			    var statusText = response.statusText;
			    var headers = response.headers;
			    var config = response.config;
			    $scope.pedidos = data;
			    $scope.pedidoscarregados = true;
			}, function myError(response) {

				var status = response.status;
				if (status != 200){
					$scope.errobackendcarregarpedidos = true;
				}

				$scope.statusRetorno = response.statusText;
	 			$scope.pedidoscarregados = false;

			}
			);
		};

	carregarPedidos();
	
	$scope.carregarItensPedido  = function(idpedido){

		$http.get("http://localhost:8080/atendimento/itensdopedido/" + idpedido).then(function (response) {

		  $scope.itensdopedido =  response.data;

		});
	};

});

