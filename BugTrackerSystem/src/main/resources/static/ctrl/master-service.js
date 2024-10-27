var app = angular.module('master',[]);

app.service('helperService', ['$http', function($http) {
	    
	 	
	    
	    this.categories = function() {
	      return $http({
	        method: 'GET',
	        url: '/BTS/default/categories'
	      }).then(function(result) {
			console.log(result);
	        return result;
	      });
	    };
	    
	    this.priorities = function() {
		      return $http({
		        method: 'GET',
		        url: '/BTS/default/priorities'
		      }).then(function(result) {
		        return result;
		      });
		    };
		    
		    this.reproducibilites = function() {
			      return $http({
			        method: 'GET',
			        url: '/BTS/default/reproducibilites'
			      }).then(function(result) {
			        return result;
			      });
			    };
			    
			    this.severities = function() {
				      return $http({
				        method: 'GET',
				        url: '/BTS/default/severities'
				      }).then(function(result) {
				        return result;
				      });
				    };
					
					this.assignTo = function() {
						return $http({
							method: 'GET',
							url: '/BTS/default/assignTo'
						}).then(function(result) {
							return result;
						});
					}
					
				    
				   
	    
}]);


app.filter('unique', function() {
	   return function(collection, keyname) {
	      var output = [], 
	          keys = [];

	      angular.forEach(collection, function(item) {
	          var key = item[keyname];
	          if(keys.indexOf(key) === -1) {
	              keys.push(key);
	              output.push(item);
	          }
	      });

	      return output;
	   };
	});