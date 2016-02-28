
var app = angular.module('bowlScore', ["highcharts-ng"]);


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
                $scope.name = name;

                var scores = [];
                var dates = [];
                for (var i in data) {
                    scores.push(data[i].score);
                    dates.push(data[i].formattedDate);
                }

                $scope.chartConfig = {
                        options: {
                            chart: {
                                type: 'line'
                            }
                        },
                        xAxis: {
                            categories: dates
                        },
                        series: [{
                            data: scores
                        }],
                        title: {
                            text: name
                        },

                        loading: false
                    }

            });
    };

});




