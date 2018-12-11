<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script>
$(document).ready( function(){	
	
	$("#searchBookSection").hide();
	$("#delete").hide();
	$("input[name='userOption'][value='add']").attr("checked", true);
	//show the subject / book section based on user option
	$("input[name='userOption']").change( function(){
		if($("input[name='userOption']:checked").val() == "search"){
			$("#searchBookSection").show();
			$("#message").html("");
			$("#addBookSection").hide();
			$("input[name='searchOption'][value='title']").attr("checked", true);
		} else{
			$("#searchBookSection").hide();
			$("#addBookSection").show();
		}
	});	
	
	$("#add").click(function(){
		$.validate({
			borderColorOnError : 'red',
		  	addValidClassOnAll : true,
		  	errorMessagePosition : 'top'
		});
	});
	
	$("#search").click(function(){		
		$.validate({
			borderColorOnError : 'red',
		  	addValidClassOnAll : true,
		  	errorMessagePosition : 'top',
			onSuccess : function($form) {
				event.preventDefault();
				$("#delete").hide();
				$.get("searchBook",{searchParam: $("#searchParam").val() ,searchType: $("input[name='searchOption']:checked").val()})
				.done(function(data) {
			   		$("#searchResult").html(data);
			   		if(data.indexOf("radio") != -1){
			   			$("#delete").show();
			   		}
			  	})
				.fail(function() {
				    alert( "error" );
				})
			}
		});
	});
	
	$("#delete").click(function(){
		event.preventDefault();
		if($("input[name='bookOption']:checked").val() == null){
			alert("Please choose a book to delete.");
			return;
		}
		let bookID = $("input[name='bookOption']:checked").val();
		$.get("deleteBook",{bookId: bookID})
		.done(function(data) {
			$("#"+bookID).remove();
	   		alert(data);
	  	})
		.fail(function(err) {
		    alert(err);
		})			
	});
});
</script>
</head>
<body>
	<form:form id="bookForm" method="POST">
		<div class="container">	
			<a href="/">Back to Home</a>
			<h2>Book Operation</h2>
			<label>Choose your option</label>
			<input type="radio" value="add" name="userOption"/>Add Book
			<input type="radio" value="search" name="userOption"/>Search Book
			<br>
			<c:if test="${not empty message}"><b id="message">${message}</b></c:if>
			<div id="addBookSection">
				<label>Title:</label><form:input type="text" class="form-control" id="title" path="title" name="title" data-validation="required"/><br>
				<label>Price:</label><form:input type="text" class="form-control" id="price" path="price" name="price" data-validation="required number" data-validation-allowing="range[1;1000000000]"/><br>
				<label>Volume</label><form:input type="text" class="form-control" id="volume" path="volume" name="volume" data-validation="required number" data-validation-allowing="range[1;5000]"/><br>
				<label>Published Date:(mm/dd/yyyy)</label><form:input type="text" id="publishedDate" path="publishDate" class="form-control" name="publishedDate" data-validation="required date" data-validation-format="mm/dd/yyyy"/><br>
				<button name="Add" class="btn btn-primary" id="add">Add</button>
			</div>			
			<div id="searchBookSection">
				<input type="radio" value="title" name="searchOption"/>Title
				<input type="radio" value="price" name="searchOption"/>Price
				<input type="radio" value="volume" name="searchOption"/>Volume<br>
				<label>Search Param:</label><input type="text" class="form-control" id="searchParam" name="searchParam" data-validation="required"/><br>
				<div>
					<button name="search" class="btn btn-primary" id="search">Search</button>
				</div><br>
				<div id="searchResult"></div><br>
				<div>
					<button name="delete" class="btn btn-primary" id="delete">Delete</button>
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>