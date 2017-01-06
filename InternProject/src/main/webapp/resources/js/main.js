"use strict";

var app = angular.module("app", []);

app.controller("main", function($scope) {
	$scope.boardNameList = getBoardNameList();
	$scope.boardId = BOARD_LIST[0].id;
	$scope.boardName = BOARD_LIST[0].name;
	
	$scope.getBoardBaseUrl = function() {
		return BOARD_BASE_URL + $scope.boardId;
	}
	
	$scope.menuClick = function(event) {
		var targetName = event.target.innerHTML
		$scope.boardName = targetName
		$scope.boardId = getBoardId(targetName);
	};
});

function getBoardNameList() {
	var name = [];
	for (var i = 0 ; i < BOARD_LIST.length ; i++) {
		name.push(BOARD_LIST[i].name);
	}
	
	return name;
}

function getBoardId(name) {
	for (var i = 0 ; i < BOARD_LIST.length ; i++) {
		if (BOARD_LIST[i].name === name) {
			return BOARD_LIST[i].id;
		}
	}
}