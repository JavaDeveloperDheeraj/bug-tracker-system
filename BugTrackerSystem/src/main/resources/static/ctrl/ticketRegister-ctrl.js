var app = angular.module('app', ['master']);
app.controller('ticketRegister-ctrl', function($scope, $http, $window, helperService) {
	$scope.isDragging = false;
	   $scope.issue = {};
	   $scope.issue.attachments = [];
	   // Allowed file types (modify as per your requirements)
	      const allowedFileTypes = ['image/jpeg', 'image/png', 'application/pdf']; // Add other allowed types

    init();

    function init() {
        
        helperService.categories().then(function(result) {
            $scope.categories = result.data;
           // console.log($scope.categories);
        });

        
        helperService.priorities().then(function(result) {
            $scope.priorities = result.data;
           // console.log($scope.priorities);
        });

        helperService.reproducibilites().then(function(result) {
            $scope.reproducibilites = result.data;
           // console.log($scope.reproducibilites);
        });

        helperService.severities().then(function(result) {
            $scope.severities = result.data;
           // console.log($scope.severities);
        });
		
		helperService.assignTo().then(function(result) {
			$scope.assignTo = result.data;
			//console.log($scope.assignTo);
		})
		
		
		    $scope.triggerFileInput = function() {
		        document.getElementById('attachment').click();
		    };

		    // Handle file selection
		    $scope.filesChanged = function() {
		        const files = document.getElementById('attachment').files;
		        $scope.$applyAsync(() => {
		            for (let i = 0; i < files.length; i++) {
		                const file = files[i];
		             
		                if (!allowedFileTypes.includes(file.type)) {
		                    alert('Invalid file type: ' + file.name + '. Please select a JPEG, PNG, or PDF file.');
		                    continue; 
		                }
		             
		                if ($scope.issue.attachments.some(att => att.name === file.name)) {
		                    alert('File with the name "' + file.name + '" is already attached.');
		                    continue; 
		                }
		               
		                $scope.issue.attachments.push(file);
		            }
		        });
		    };

		    // Remove file from attachments
		    $scope.removeFile = function(fileName) {
		        $scope.$applyAsync(() => {
		           
		            $scope.issue.attachments = $scope.issue.attachments.filter(file => file.name !== fileName);
		        });
		    };

		    // Drag-and-drop handlers
		    const dropArea = document.getElementById('drop-area');

		    dropArea.addEventListener('dragover', (e) => {
		        e.preventDefault();
		        $scope.$apply(() => $scope.isDragging = true);
		    });

		    dropArea.addEventListener('dragleave', (e) => {
		        $scope.$apply(() => $scope.isDragging = false);
		    });

		    dropArea.addEventListener('drop', (e) => {
		        e.preventDefault();
		        $scope.$apply(() => {
		            $scope.isDragging = false;
		            const files = e.dataTransfer.files;
		            for (let i = 0; i < files.length; i++) {
		                const file = files[i];
		              
		                if (!allowedFileTypes.includes(file.type)) {
		                    alert('Invalid file type: ' + file.name + '. Please select a JPEG, PNG, or PDF file.');
		                    continue; 
		                }
		               
		                if ($scope.issue.attachments.some(att => att.name === file.name)) {
		                    alert('File with the name "' + file.name + '" is already attached.');
		                    continue; 
		                }
		            
		                $scope.issue.attachments.push(file);
		            }
		        });
		    });

		
		
		
		$scope.saveticketDetails = function() {
		    const formData = new FormData();
		    
		    
		    let issueCopy = {
		        category: $scope.issue.category,
		        severity: $scope.issue.severity,
		        reproducibility: $scope.issue.reproducibility,
		        priority: $scope.issue.priority,
		        assignedTo: $scope.issue.assignedTo,
		        ticketSubject: $scope.issue.ticketSubject,
		        ticketDescription: $scope.issue.ticketDescription,
		        attachments: $scope.issue.attachments ? $scope.issue.attachments.map(file => {
		            return { fileName: file.name }; // Only retain the file name
		        }) : []
		    };

		   
		    formData.append('data', JSON.stringify(issueCopy));

		   
		    let files = $scope.issue.attachments;
		    if (files && files.length > 0) {
		        for (let i = 0; i < files.length; i++) {
		            formData.append('files', files[i]); 
		        }
		    }

		    
		    $http.post('/BTS/api/ticket', formData, {
		        transformRequest: angular.identity,
		        headers: { 'Content-Type': undefined }
		    }).then(function(response) {
		        console.log(response.data);
		        if (response.data.statusCode == 200) {
					alert('ticket raised succesffuly');
		            
		        }
		    }, function(response) {
		        if (response.data.statusCode == 417) {
		            console.log($scope.errMsg);
					alert('something went wrong');
		        }
		        console.log(response);
		    });
		};


		
		

    }
});
