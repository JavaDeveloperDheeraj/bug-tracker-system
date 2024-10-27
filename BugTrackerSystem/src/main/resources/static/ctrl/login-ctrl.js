var app = angular.module('app', []); 
app.controller('LoginCtrl', function($scope,$http,$window) {
    
    init();
    function init() {
        
    }
 	
	$scope.credentials = {
    username: '',
    password: ''
  };
  
			$scope.userLogin = function() {
		   	 var authHeader = 'Basic ' + btoa($scope.user.userName + ':' + $scope.user.password);
		
		    $http({
		        method: 'POST',
		        url: '/BTS/app/login',
		        headers: {
		            'Authorization': authHeader
		        },
		        withCredentials: true 
		    }).then(function(response) {
				if(response.status == 200){
					localStorage.setItem('loginData', JSON.stringify(response.data));
				    $window.location.href = "/BTS/welcome";
				    		  }else{
				    			  console.log("Invalid Credentials");
				    		  }
		       
		        console.log(response); 
		    }, function(error) {
		        
		        console.error('Error:', error);
		    });
		};

  
		$scope.logout = function() {
		    localStorage.removeItem('loginData');
		    $window.location.href = "/BTS/login";
		};



});

