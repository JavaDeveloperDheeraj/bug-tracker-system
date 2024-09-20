var app = angular.module('userApp', []);

app.controller('userCtrl', ['$scope', '$http', function ($scope, $http) {
    // Initialize user data
    $scope.user = {
        username: '',
        password: '',
        role: '',
        developer: {
            developerId: ''
        }
    };

    // Function to submit the form
    $scope.submitForm = function (registerForm) {
        if (registerForm.$valid) {
            var requestData = {
                username: $scope.user.username,
                password: $scope.user.password,
                role: $scope.user.role,
                developer: {
                    developerId: $scope.user.developer.developerId
                }
            };

            // HTTP POST request to send data to the server
            $http.post('http://10.133.33.8:9090/app/register', requestData)
                .then(function (response) {
                    console.log('Form submitted successfully:', response.data);
                    alert('Registration successful!');
                })
                .catch(function (error) {
                    console.error('Error submitting form:', error);
                    alert('Error during registration. Please try again.');
                });
        } else {
            console.warn('Form is invalid.');
            alert('Please fill out all required fields.');
        }
    };
}]);
