function baseController($scope,$http,$location) {
	
	console.log("here");
	
	$scope.algo = "asdfasdfa";
	
	$scope.goToLogin = function() {
		console.log("ahi");
	}
	
	
	//METODO PARA CARGAS LAS SALAS
	$scope.obtenerSalas = function() {	

		$http.get('/getRoomStatuses').
		success(function(data, status, headers, config) {
			$scope.datos = data;
		}).
		error(function(data, status, headers, config) {
			alert("ERROR METODOD CARGAR SALAS");
		});
	}
	
	//METODO  PARA ENTRAR EN SALA
	$scope.entrarEnSala = function() {
		
		var obj = new Object();
		obj.roomId = "Sala 1";
		obj.playerId = "2";
		
		$http.post('/join', obj).
		success(function(data, status, headers, config) {
			$scope.datos = data;

			sessionStorage.Sala = "";
			sessionStorage.setItem("Sala","Sala 1");
			
			$location.path("/sala");
		}).
		error(function(data, status, headers, config) {
			alert("ERROR METODOD ENTRAR A SALA");
		});
	}
	
}

pingusgames.controller('baseController', ['$scope', '$http', '$location', baseController]);