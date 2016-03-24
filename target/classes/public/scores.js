
var app = angular.module('bowlScore', ["highcharts-ng"]);


app.controller('Scores', function($scope, $http) {
    $scope.message = "";

    var show = function (name) {
    $scope.message = "";
    console.log("show " + name);
        $http.get('/scores/' + name)
                .success(function(data) {
                    $scope.scores = data;
                    $scope.name = name;
            });

        $http.get('/scores/stats/' + name)
                .success(function(data) {
                    $scope.stat = data;
                });
    };

    $scope.show = show;

    var remove = function (score) {
    console.log("delete " + score.id);
        $http.delete('/scores/' + score.id)
                .success(function(data) {
                    $scope.message = "Deleted with id " + score.id;
                    $scope.show(score.name);
        });



    };
    $scope.remove = remove;

});

app.controller('AddScore', function($scope, $http) {
    $scope.name = "Aku";
    $scope.add = function() {
        $http.post('/scores/' + $scope.name + '/' + $scope.score)
            .success(function(data) {
                 if (data.score < 0) {
                    $scope.message = "Invalid score.";
                 } else {
                    $scope.message = "Added " + $scope.name + " " + $scope.score;
                 }
                $scope.score = "";
        });
    };
});

app.controller('AddPayer', function($scope, $http) {

    $scope.name = "Aku";
    getBowlers();

    $scope.add = function() {
        $http.post('/bowlers/' + $scope.name)
            .success(function(data) {
                $scope.message = "Added payer " + $scope.name;
                getBowlers();
            });
    }

    function getBowlers () {
        console.log('Getting bowlers...' );
         $http.get('/bowlers')
            .success(function(data) {
                $scope.bowlers = data;
         });
    }

})

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

                        yAxis: {
                            min: 0,
                            max: 300,
                            title: {
                                name: 'Averages'
                            }
                        },

                        loading: false
                    }

            });
    };

});




