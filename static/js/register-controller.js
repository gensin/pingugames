function registerController($scope,$http) {
	
	$scope.login = function() {
		$http.post('/register', {msg:''}).
		success(function(data, status, headers, config) {
			alert(data);
		}).
		error(function(data, status, headers, config) {
			alert("Error register");
		});
	}
	
}

pingusgames.controller('registerController', ['$scope', '$http', registerController]);