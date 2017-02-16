<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.weather.model.WeatherData" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Weather summary</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <h3>Weather Summary</h3>
   <ul class="nav nav-pills">
    <li class="active"><a data-toggle="pill" href="#C">Celsius</a></li>
    <li><a data-toggle="pill" href="#F">Fahrenheit</a></li>
  </ul>
  <%
  	WeatherData farData = (WeatherData)request.getAttribute("farData");
  	WeatherData celData = (WeatherData)request.getAttribute("celData");
  %>
  <div class="tab-content">
    <div id="C" class="tab-pane fade in active">
      <h3>Celsius</h3>
	  <div class="table-responsive">
       <table class="table table-striped">
    <thead>
      <tr>
	    <th>Summary</th>
        <th>Wind (km/h)</th>
        <th>Max. Temp(C)</th>
        <th>Min. Temp(C)</th>
		<th>Sunrise</th>
		<th>Sunset</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>${celData.summary}</td>
        <td>${celData.speed}</td>
        <td>${celData.maxTemp}</td>
		<td>${celData.minTemp}</td>
		<td></td>
		<td></td>
      </tr>
    </tbody>
  </table>
	  </div>
   </div>
    <div id="F" class="tab-pane fade">
      <h3>Fahrenheit</h3>
	<div class="table-responsive">  
		<table class="table table-striped">
    <thead>
      <tr>
        <th>Summary</th>
        <th>Wind (F)</th>
        <th>Max. Temp(F)</th>
        <th>Min. Temp(F)</th>
		<th>Sunrise</th>
		<th>Sunset</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>${farData.summary}</td>
        <td>${farData.speed}</td>
        <td>${farData.maxTemp}</td>
		<td>${farData.minTemp}</td>
		<td></td>
		<td></td>
      </tr>
    </tbody>
  </table>
    </div>
	
	</div>
   </div>
	
<p class="local-time-line"><span class="time-text">Local time in : ${farData.name}<span class="current-local-time"><%=(String) request.getAttribute("date")%></span></span></p>	
   
   </div>
</body>
</html>