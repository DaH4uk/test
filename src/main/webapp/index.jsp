<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File upload form</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>


    <form method="POST" action="upload" enctype="multipart/form-data" >
        File:
        <input type="file" name="file" id="file" /> <br/>
        Destination:
        <input type="text" value="/tmp" name="destination"/>
        </br>
        <input type="submit" value="Upload" name="upload" id="upload" />
    </form>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</body>
</html>
