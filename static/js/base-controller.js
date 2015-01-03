function baseController($scope,$http) {
	
	console.log("here");
	
	$scope.algo = "asdfasdfa";
	
	$scope.goToLogin = function() {
		console.log("ahi");
	}
	
}

pingusgames.controller('baseController', ['$scope', '$http', baseController]);