function loginController($scope,$http) {
	
	$scope.login = function() {
		$http.post('/login', {msg:''}).
		success(function(data, status, headers, config) {
			alert(data);
		}).
		error(function(data, status, headers, config) {
			alert("Error login");
		});
	}
	
}

pingusgames.controller('loginController', ['$scope', '$http', loginController]);