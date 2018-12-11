<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Subject</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script>
$(document).ready( function(){	
	
	$("#searchSubjectSection").hide();
	$("#delete").hide();
	$("input[name='userOption'][value='add']").attr("checked", true);
	//show the subject / book section based on user option
	$("input[name='userOption']").change( function(){
		if($("input[name='userOption']:checked").val() == "search"){
			$("#searchSubjectSection").show();
			$("#message").html("");
			$("#addSubjectSection").hide();
			$("input[name='searchOption'][value='title']").attr("checked", true);
		} else{
			$("#searchSubjectSection").hide();
			$("#addSubjectSection").show();
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
				$.get("searchSubject",{searchParam: $("#searchParam").val() ,searchType: $("input[name='searchOption']:checked").val()})
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
		if($("input[name='subjectOption']:checked").val() == null){
			alert("Please choose a subject to delete.");
			return;
		}
		let subjectID = $("input[name='subjectOption']:checked").val();
		$.get("deleteSubject",{subjectId: subjectID})
		.done(function(data) {
			$("#"+subjectID).remove();
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
	<form:form id="subjectForm" method="POST">
		<div class="container">	
			<a href="/">Back to Home</a>
			<h2>Subject Operation</h2>
			<label>Choose your option</label>
			<input type="radio" value="add" name="userOption"/>Add Subject
			<input type="radio" value="search" name="userOption"/>Search Subject
			<br>
			<c:if test="${not empty message}"><b id="message">${message}</b></c:if>
			<div id="addSubjectSection">
				<label>Title:</label><form:input type="text" class="form-control" id="subtitle" name="subtitle" path="subtitle" data-validation="required"/><br>
				<label>Duration in Hours:</label><form:input type="text" class="form-control" id="duration" name="duration" path="durationInHours" data-validation="required number" data-validation-allowing="range[1;60]"/><br>
				<label>References:(Comma separated Book Ids)</label><form:input type="text" id="bookIds" class="form-control" name="bookIds" path="bookIds" data-validation="required"/><br>
				<button name="add" class="btn btn-primary" id="add">Add</button>
			</div>			
			<div id="searchSubjectSection">
				<input type="radio" value="title" name="searchOption"/>Title
				<input type="radio" value="duration" name="searchOption"/>Duration in Hours<br>
				<label>Search Param:</label><input type="text" class="form-control" id="searchParam" name="searchParam" data-validation="required"/><br>
				<div>
					<button name="search" class="btn btn-primary" id="search">Search</button>
				</div><br>
				<div id="searchResult"></div><br>
				<div>
					<button name="delete" class="btn btn-primary" id="delete">Delete</button>
				</div><br>
			</div>			
		</div>
	</form:form>
</body>
</html>