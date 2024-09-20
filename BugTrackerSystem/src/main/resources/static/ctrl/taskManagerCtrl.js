var app = angular.module('taskManager', []);

app.controller('taskManagerCtrl', ['$scope', '$http', function ($scope, $http) {
    $scope.formCtrl = {
        issue: {
            category: '',
            severity: '',
            reproducibility: '',
            priority: '',
            assignedTo: '',
            assignedBy: '',  // Add this field for 'assignedBy'
            ticketSubject: '',
            ticketDescription: '',
            attachments: '',
            estimatedTime: ''
        },

        // Function to validate and submit the form
        validateAndSubmit: function (issueForm) {
            console.log('Validation triggered');  // Add debug log

            var missingFields = [];
            var firstInvalidField = null;

            if (!this.issue.category) {
                missingFields.push('Category');
                if (!firstInvalidField) firstInvalidField = 'category';
            }
            if (!this.issue.severity) {
                missingFields.push('Severity');
                if (!firstInvalidField) firstInvalidField = 'severity';
            }
            if (!this.issue.reproducibility) {
                missingFields.push('Reproducibility');
                if (!firstInvalidField) firstInvalidField = 'reproducibility';
            }
            if (!this.issue.priority) {
                missingFields.push('Priority');
                if (!firstInvalidField) firstInvalidField = 'priority';
            }
            if (!this.issue.assignedTo) {
                missingFields.push('Assigned To');
                if (!firstInvalidField) firstInvalidField = 'assignedTo';
            }
            if (!this.issue.ticketSubject) {
                missingFields.push('Ticket Subject');
                if (!firstInvalidField) firstInvalidField = 'ticketSubject';
            }
            if (!this.issue.ticketDescription) {
                missingFields.push('Ticket Description');
                if (!firstInvalidField) firstInvalidField = 'ticketDescription';
            }

            // Check if attachment is uploaded
            var attachment = document.getElementById('attachment').files[0];
            if (!attachment) {
                missingFields.push('Attachment');
                if (!firstInvalidField) firstInvalidField = 'attachment';
            }

            // Log for debugging purposes
            console.log("Missing Fields: ", missingFields);

            if (missingFields.length > 0) {
                alert('Please fill out the following required fields: ' + missingFields.join(', '));
                if (firstInvalidField) {
                    console.log("First Invalid Field: " + firstInvalidField);
                    document.getElementById(firstInvalidField).focus();  // Focus on the first missing field
                }
                return;
            }

            // If validation passes, submit the form
            this.submitForm();
        },

        submitForm: function () {
            console.log('Form submission triggered');  // Add debug log

            // Automatically fill in additional fields
            this.issue.estimatedTime = this.issue.estimatedTime || 120;  // Default to 120 if not provided
            this.issue.assignedBy = 'Admin';  

            // Prepare data to be sent to the server as JSON
            var data = {
                status: 'CREATED',
                category: this.issue.category,
                severity: this.issue.severity,
                reproducibility: this.issue.reproducibility,
                priority: this.issue.priority,
                assignedTo: this.issue.assignedTo,
                assignedBy: this.issue.assignedBy,
                ticketSubject: this.issue.ticketSubject,
                ticketDescription: this.issue.ticketDescription,
                attachments: '',  // This will be filled with the attachment link later
                estimatedTime: this.issue.estimatedTime
            };

            // Log the prepared JSON data for debugging
            console.log('JSON data prepared: ', JSON.stringify(data));  // Add debug log

            // Upload the attachment first, if provided
            var attachment = document.getElementById('attachment').files[0];
            if (attachment) {
                var formData = new FormData();
                formData.append('file', attachment);

                // Upload the attachment file
                $http.post('http://10.133.33.8:9090/BTS/api/upload', formData, {
                    headers: { 'Content-Type': undefined },  // Ensure multipart/form-data is used
                    transformRequest: angu0lar.identity
                }).then(function (response) {
                    console.log('Attachment uploaded successfully', response.data);
                    data.attachments = response.data.attachmentLink;  // Assign the uploaded file link to attachments

                    // Now submit the form with the uploaded attachment link
                    $scope.formCtrl.submitData(data);
                }).catch(function (error) {
                    console.error('Error uploading attachment:', error);
                    alert('Error uploading attachment. Please try again.');
                });
            } else {
                // No attachment, proceed with submitting form data
                $scope.formCtrl.submitData(data);
            }
        },

        submitData: function (data) {
            // Send the data to the server using HTTP POST with application/json content type
            $http.post('http://10.133.33.8:9090/BTS/api/ticket', data, {
                headers: { 'Content-Type': 'application/json' }
            })
            .then(function (response) {
                console.log('Form submitted successfully!');  // Add debug log
                alert('Form submitted successfully!');

                // Reset the form after successful submission
                $scope.formCtrl.issue = {
                    category: '',
                    severity: '',
                    reproducibility: '',
                    priority: '',
                    assignedTo: '',
                    assignedBy: '',  // Reset assignedBy field
                    ticketSubject: '',
                    ticketDescription: '',
                    attachments: '',
                    estimatedTime: ''
                };
                document.getElementById('attachment').value = '';  // Clear the file input
            })
            .catch(function (error) {
                console.log('Error occurred:', error);  // Add debug log
                alert('Error submitting form. Please try again.');
            });
        }
    };

    // Initialize data fetching
    init();

    function init() {
        getCategoryOptions();
        getSeverityOptions();
        getReproducibilityOptions();
        getPriorityOptions();
        getAssignToOptions();
    }

    // Functions to fetch data (categories, severity, etc.)
   function getCategoryOptions() {
    $http.get('http://10.133.33.8:9090/BTS/api/category')
        .then(function (response) {
            if (response.data && response.data.length) {
                $scope.formCtrl.categoryOptions = response.data;  // Store data in formCtrl.categories
            } else {
                console.warn('No categories found in the response.');
            }
        })
        .catch(function (error) {
            console.error('Error fetching categories:', error);
        });
}


    // Fetch data for Severity options
		  function getSeverityOptions() {
		    $http.get('http://10.133.33.8:9090/BTS/api/severity-enums')
		        .then(function (response) {
		            if (response.data && response.data.length) {
		                $scope.formCtrl.severityOptions = response.data;
		            } else {
		                console.error('No data received for severity options');
		                $scope.error = "No severity options available.";
		            }
		        })
		        .catch(function (error) {
		            console.error('Error fetching severity options:', error);
		            $scope.error = "Failed to fetch severity options. Please try again later.";
		        });
		}



    // Fetch data for Reproducibility options
    function getReproducibilityOptions() {
    $http.get('http://10.133.33.8:9090/BTS/api/reproducibility-enums')
        .then(function (response) {
            if (response.data && response.data.length) {
                $scope.formCtrl.reproducibilityOptions = response.data;  // Store data in formCtrl.reproducibilityOptions
            }
        })
        .catch(function (error) {
            console.error('Error fetching reproducibility options:', error);
        });
}


    // Fetch data for Priority options
    function getPriorityOptions() {
    $http.get('http://10.133.33.8:9090/BTS/api/priority-enums')
        .then(function (response) {
            if (response.data && response.data.length) {
                $scope.formCtrl.priorityOptions = response.data;  // Store data in formCtrl.priorityOptions
            }
        })
        .catch(function (error) {
            console.error('Error fetching priority options:', error);
        });
}


    // Fetch data for Assign To options
   function getAssignToOptions() {
    $http.get('http://10.133.33.8:9090/BTS/api/role-enums')
        .then(function (response) {
            if (response.data && response.data.length) {
                $scope.formCtrl.assignToOptions = response.data;  // Store data in formCtrl.assignToOptions
            }
        })
        .catch(function (error) {
            console.error('Error fetching assignTo options:', error);
        });
}

}]);
