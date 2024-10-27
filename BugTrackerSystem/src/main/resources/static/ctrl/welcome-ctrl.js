var app = angular.module('app', []);

	app.controller('welcomeCtrl', function($scope,$http,$window,$sce) {
    
    init();
    function init() {
       
    }
 	
	var storedData = localStorage.getItem('loginData');
	    
	    if (storedData) {
	        $scope.data = JSON.parse(storedData);
	    } else {
	        console.error('No data found in localStorage');
	        $window.location.href = "/BTS/login"; 
	    }

		
		// Function to show details in modal
		    $scope.showDetails = function(ticket) {
		        $scope.selectedTicket = ticket;
		        $('#detailsModal').modal('show');
		    };
			
			// Function to create HTML for attachments
			                   $scope.getAttachmentsHtml = function(attachments) {
			                       var attachmentList = attachments.split(','); // Split attachments by comma
			                       var docHtml = '';
			                       var docCount = 1;

			                       for (var i = 0; i < attachmentList.length; i++) {
			                           var attachment = attachmentList[i].trim(); // Trim any whitespace
			                           docHtml += '<label class="col-lg-12 form-control-label">Document ' + (docCount++) + ' : </label>';
			                           docHtml += '<a href="/get-files/' + attachment + '" target="_blank">' + attachment + '</a><br>'; // Create a link for each attachment
			                       }

			                       return $sce.trustAsHtml(docHtml); // Use $sce to trust the HTML
			                   };
			              
							   // Function to create HTML for attachments
							                      $scope.getAttachmentsHtml = function(attachments) {
							                          var attachmentList = attachments.split(','); // Split attachments by comma
							                          var docHtml = '';
							                          var docCount = 1;

							                          for (var i = 0; i < attachmentList.length; i++) {
							                              var attachment = attachmentList[i].trim(); // Trim any whitespace
							                              docHtml += '<label class="col-lg-12 form-control-label">Document ' + (docCount++) + ' : </label>';
							                              docHtml += '<a href="/BTS/get-files/' + attachment + '" target="_blank">' + attachment + '</a><br>'; // Create a link for each attachment
							                          }

							                          return $sce.trustAsHtml(docHtml); // Use $sce to trust the HTML
							                      };
												  
					//functions to save conversations
					$scope.saveConversations = function(ticketNo){
						var ticketNo = ticketNo;
						alert(ticketNo);
						
					}
							                 

		});

