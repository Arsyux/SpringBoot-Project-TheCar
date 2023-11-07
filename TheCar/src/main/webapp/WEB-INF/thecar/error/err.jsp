<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="errorPage">
	<span class="errorHead">Error!</span><br/>
	<p>request_uri : <c:out value="${requestScope['javax.servlet.error.request_uri']}"/></p>
	<p>status_code : <c:out value="${requestScope['javax.servlet.error.status_code']}"/></p>
	<p>servlet_name : <c:out value="${requestScope['javax.servlet.error.servlet_name']}"/></p>
	<p>exception : <c:out value="${requestScope['javax.servlet.error.exception']}"/></p>
	<p>servlet_name : <c:out value="${requestScope['javax.servlet.error.servlet_name']}"/></p>
	<p>message : <c:out value="${requestScope['javax.servlet.error.message']}"/></p>
</div>

<%@ include file="../layout/footer.jsp" %>