
var app = angular.module('bowlScore', []);


app.controller('Scores', function($scope, $http) {

    $scope.show = function (name) {
        $http.get('/scores/' + name)
                .success(function(data) {
                    $scope.scores = data;
            });
    };


});

app.controller('AddScore', function($scope, $http) {

    $scope.add = function() {
        $http.post('/scores/' + $scope.name + '/' + $scope.score)
            .success(function(data) {
                $scope.message = "Added " + $scope.name + " " + $scope.score;
        });
    };
});

app.controller('Charts', function($scope, $http) {

    $scope.avg = function(name) {
        $http.get('/scores/' + name + '/avg')
            .success(function(data) {
                $scope.chartData = data;
            });
    };

});




