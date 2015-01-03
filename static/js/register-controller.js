function registerController($scope,$http) {
	
	$scope.register = function() {
		$http.post('/register', {mail:'mail',name:'name'}).
		success(function(data, status, headers, config) {
			alert(data);
		}).
		error(function(data, status, headers, config) {
			alert("Error register");
		});
	}
	
}

pingusgames.controller('registerController', ['$scope', '$http', registerController]);