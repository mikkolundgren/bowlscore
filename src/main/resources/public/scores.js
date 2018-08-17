
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

app.controller('League', function($scope, $http){

    $scope.year = "2018";
    getLeague(false, $scope.year);

    $scope.setYear = function(year) {
        $scope.year = year;
        console.log("set year: " + $scope.year);
        getLeague($scope.fullteam, $scope.year);
    }

    $scope.toggleTeam = function () {
        console.log("toggle: " + $scope.fullteam);
        getLeague($scope.fullteam, $scope.year);
    }



    function getLeague(fullteam, year) {
        var url = "/scores/league";
        if (fullteam) {
            url += "?fullteam=true";
        } else {
            url += "?fullteam=false";
        }
        url += "&year=" + year;
        console.log("getLeague(" + url + ")");
        $http.get(url)
            .success(function(data) {
            //console.log('get league.', data);
                $scope.leagueScores = data.scores;
                $scope.totalSeries = data.numberOfSeries;
                $scope.numberOfTimes = data.numberOfTimes;
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
                                text: 'Averages'
                            }
                        },

                        loading: false
                    }

            });
    };

});




