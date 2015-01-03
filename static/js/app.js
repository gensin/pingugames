var pingusgames = angular.module('pingusgames',['ngRoute']);

pingusgames.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
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
		when('/sala', {
			templateUrl: '../pages/sala.html',
			controller: 'salaController'
		}).
		otherwise({
			redirectTo: '/pingusgames'
		});
	}]);