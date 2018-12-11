<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.springmvc.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<div class="panel">
<h3>Search Results</h3>
<c:if test="${not empty subjects}">
	<div class="input-group">
		<div class="col-sm-1"><b>Choose</b></div>
		<div class="col-sm-4"><b>Title</b></div>
		<div class="col-sm-1"><b>Duration</b></div>
		<div class="col-sm-6"><b>References</b></div>	
		<c:forEach items="${subjects}" var="subject">
			<div id="${subject.subjectId}" class="input-group">
				<div class="col-sm-1"><input type="radio" name="subjectOption" value="${subject.subjectId}"></div>
				<div class="col-sm-4">${subject.subtitle}</div>
				<div class="col-sm-1">${subject.durationInHours}</div>
				<div class="col-sm-6">
					<c:forEach items="${subject.references}" var="book" >			
						${book.title} ,
					</c:forEach>
				</div>
			</div>
		</c:forEach>
	</div>
</c:if>
<c:if test="${empty subjects}">
	<label>No search results found.</label>
</c:if>
</div>
<style type="text/css">
	.col-sm-1, .col-sm-4, .col-sm-6 {
		border: 1px solid #000;
	}
</style>