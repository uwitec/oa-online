<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
function alertSite(userId) {
	UserWebServiceDwr.userSession(userId,function(take){
		if (take == "true"){
			window.location.href = 'http://localhost:8080/FHIOA/index.do?method=login';
		}
	});
}