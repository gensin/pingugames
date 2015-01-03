var pingusgames = angular.module('pingusgames',['ngRoute']);

pingusgames.config(['$routeProvider', function($routeProvider) {
	$routeProvider.
		when('/pingusgames', {
			templateUrl: '../pages/main.html',
			controller: 'baseController'
		}).
		when('/login', {
			templateUrl: '../pages/login.html',
			controller: 'loginController'
		}).
		when('/register', {
			templateUrl: '../pages/register.html',
			controller: 'registerController'
		}).
		otherwise({
			redirectTo: '/pingusgames'
		});
	}]);