<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<header>
    <title>
        Place your city
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-lpyLfhYuitXl2zRZ5Bn2fqnhNAKOAaM/0Kr9laMspuaMiZfGmfwRNFh8HlMy49eQ"
            crossorigin="anonymous"></script>
</header>
<body>
<div style="width: 100%;">
    <form action="showResults" method="get" autocomplete="off">
        <div class="input-group mb-3" style="display: flex;width: 50%;margin-top: 50px;margin-left: auto;margin-right: auto;">
            <input type="text" class="form-control" name="city" placeholder="City" aria-label="Recipient's username"
                   aria-describedby="basic-addon2">
            <input type="submit" class="input-group-text" value="Submit">
        </div>
    </form>
</div>
</body>
</html>