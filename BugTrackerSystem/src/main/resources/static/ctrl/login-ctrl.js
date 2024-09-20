var app = angular.module('app', []); // No need to inject $scope here

app.controller('LoginCtrl', function($scope,$http) {
    
    init();
    function init() {
        
    }
    
  //  Login Method
 /*  $scope.userLogin = function() {
	console.log($scope.user);
	alert('hello');
    $http.post('/BTS/app/login', $scope.user).then(function(response) {
            alert('hello world');
            alert(response);
            alert(response.data);
        }, function(error) {
            console.log(error);
        });
	};*/
	
	$scope.credentials = {
    username: '',
    password: ''
  };
	$scope.userLogin = function() {
		alert('inside the view');
		
   var data = {
      username: $scope.user.userName,
      password: $scope.user.password
    };
	alert(data);
	console.log(data);
    var authHeader = btoa($scope.user.userName + ':' +$scope.user.password);
		var username= $scope.user.userName;
		var password= $scope.user.password;
   
    alert('-------------into the view---------------');
		
    $http.post('/BTS/app/login', {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': 'Basic ' + authHeader
            
        },data
    }).then(function(response) {
        alert('Login successfuly!');
        console.log(response);
    }, function(error) {
        alert('Login failed!');
        console.log(error);
    });
};


});

