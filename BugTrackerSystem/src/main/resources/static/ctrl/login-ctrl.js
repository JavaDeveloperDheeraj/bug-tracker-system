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
  
  // Define a function for login, perhaps inside a controller or a service
$scope.userLogin = function() {
    var authHeader = 'Basic ' + btoa($scope.user.userName + ':' + $scope.user.password);

    // Using AngularJS $http service to make the POST request
    $http({
        method: 'POST',
        url: '/BTS/app/login',
        headers: {
            'Authorization': authHeader
        },
        withCredentials: true // This ensures credentials (cookies, HTTP auth, etc.) are sent
    }).then(function(response) {
        // Success callback
        console.log(response.data); // Handle the response data here
    }, function(error) {
        // Error callback
        console.error('Error:', error);
    });
};

  
/*	$scope.userLogin = function() {
		alert('inside the view');
		
   var data = {
      username: $scope.user.userName,
      password: $scope.user.password
    };
	alert(data);
	console.log(data);
   
		
var authHeader = 'Basic '+btoa($scope.user.userName + ':' +$scope.user.password);
    $http.post('/BTS/app/login', {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': authHeader
            
        }
    }).then(function(response) {
        alert('Login successfuly!');
        console.log(response);
    }, function(error) {
        alert('Login failed!');
        console.log(error);
    });
  /* var xhr = new XMLHttpRequest();
xhr.withCredentials = true;
var authHeader = 'Basic '+btoa($scope.user.userName + ':' +$scope.user.password);
xhr.addEventListener("readystatechange", function() {
  if(this.readyState === 4) {
    console.log(this.responseText);
  }
});

xhr.open("POST", "/BTS/app/login");
xhr.setRequestHeader("Authorization", authHeader);

xhr.send();
};*/


});

