
var app = angular.module('bowlScore', ["highcharts-ng"]);


app.controller('Scores', function($scope, $http) {

    $scope.show = function (name) {
        $http.get('/scores/' + name)
                .success(function(data) {
                    $scope.scores = data;
            });
    };

    $scope.del = function(scoreId) {
        $http.delete('/scores/' + scoreId)
                .success(function(data) {
                    $scope.message = "Deleted with id " + scoreId;
        });
    };
});

app.controller('AddScore', function($scope, $http) {

    $scope.add = function() {
        $http.post('/scores/' + $scope.name + '/' + $scope.score)
            .success(function(data) {
                $scope.message = "Added " + $scope.name + " " + $scope.score;
                $scope.score = "";
        });
    };
});

app.controller('Charts', function($scope, $http) {

    $scope.avg = function(name) {
        $http.get('/scores/' + name + '/avg')
            .success(function(data) {
                $scope.chartData = data;
                $scope.name = name;

                var avgs = [];
                var dates = [];
                var frames = [];
                for (var i in data) {
                    avgs.push(data[i].avg);
                    dates.push(data[i].date);

                }

                $scope.chartConfig = {

                        xAxis: {
                            categories: dates
                        },
                        series: [{
                            type: 'line',
                            name: 'Average',
                            data: avgs
                        }],
                        title: {
                            text: name
                        },

                        loading: false
                    }

            });
    };

});




