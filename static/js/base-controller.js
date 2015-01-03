console.log("asdf");

function baseController($scope,$http) {
	
	console.log("here");
	
	$scope.algo = "asdfasdfa";
	
	$scope.myFunction = function() {
		$http.post('/', {msg:''}).
		success(function(data, status, headers, config) {
			alert(data);
		}).
		error(function(data, status, headers, config) {
			alert("Error");
		});
	}
	
}

myApp.controller('baseController', ['$scope', '$http', baseController]);