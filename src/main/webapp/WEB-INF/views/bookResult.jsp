<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.springmvc.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<div class="panel">
<h3>Search Results</h3>
<c:if test="${not empty books}">
	<div class="input-group">
		<div class="col-sm-1"><b>Choose</b></div>
		<div class="col-sm-4"><b>Title</b></div>
		<div class="col-sm-1"><b>Price</b></div>
		<div class="col-sm-1"><b>Volume</b></div>
		<div class="col-sm-5"><b>Published Date</b></div>	
		<c:forEach items="${books}" var="book">
			<div id="${book.bookId}" class="input-group">
				<div class="col-sm-1"><input type="radio" name="bookOption" value="${book.bookId}"></div>
				<div class="col-sm-4">${book.title}</div>
				<div class="col-sm-1">${book.price}</div>
				<div class="col-sm-1">${book.volume}</div>
				<div class="col-sm-5">${book.publishDate}</div>
			</div>
		</c:forEach>
	</div>
</c:if>
<c:if test="${empty books}">
	<label>No search results found.</label>
</c:if>
</div>
<style type="text/css">
	.col-sm-1, .col-sm-4, .col-sm-5{
		border: 1px solid #000;
	}
</style>