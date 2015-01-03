function baseController($scope,$http) {
	
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
		obj.playerId = "1";
		
		$http.post('/join', obj).
		success(function(data, status, headers, config) {
			//$scope.datos = data;
			alert("HAS ENTRADO!");
		}).
		error(function(data, status, headers, config) {
			//$scope.datos = "ERROR METODOD ENTRAR A SALA";
			alert("ERROR METODOD ENTRAR A SALA");
		});
	}
	
}

pingusgames.controller('baseController', ['$scope', '$http', baseController]);