# WeatherSystem


# jquery.cityAutocomplete
City autocomplete dropdown

## How to Use?

City autocomplete depends on jquery and google maps js api:

```
<link rel="stylesheet" type="text/css" href="city-autocomplete.css"/>
<script type="text/javascript" src="//maps.googleapis.com/maps/api/js?libraries=places&language=en"></script>
<script src="jquery.js" type="text/javascript"></script>
<script src="jquery.city-autocomplete.js" type="text/javascript"></script>
```

Create input:

```
<input id="city" name="city" autocomplete="off">
```

then

```
$('input#city').cityAutocomplete();
```

You can set data-country attribute for input to search cities only for selected country:

```
<input id="city" name="city" autocomplete="off" data-country="ru">
```

==============================

#openweathermap API

API URL - 
http://api.openweathermap.org/data/2.5/find?q=London&units=imperial&appid=9cc54577343b5d8fab1803ba915d3594  

units=imperial //Fahrenheit 
units=metric //Celsius

========================




