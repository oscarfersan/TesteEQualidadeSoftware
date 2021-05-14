<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<header>
    <title>
        Results
    </title>
</header>
<body>

<div style="margin-left: auto;margin-right: auto;flex-direction: row;display: flex; justify-content: space-between; width: 50%;">
    <div style="display: flex;flex-direction: column">
        <h1 id="cityName">${city.getName()}</h1>
        <h1>Country:${city.getCountry()}</h1>
    </div>
    <div style="display: flex;flex-direction: column">
        <h1 id="cityValue">${city.getValue()}µg/m³</h1>
        <h1 id="postion">${city.getLatitude()},${city.getLongitude()}</h1>
    </div>
</div>
<h6>Provided by <a href="https://docs.openaq.org/">https://docs.openaq.org</a></h6>
</body>
</html>