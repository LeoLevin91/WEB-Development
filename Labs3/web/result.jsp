<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/result.css">
    <title>Result</title>
</head>
<body>
    <header>
        <div class="container">
            <div class="title">
<!--                <h1>Welcome, <%=reader.getName()%></h1>-->
                <h1>Welcome, UserName</h1>
            </div>
        </div>
    </header>
    <div class="container conOne">
        <a href="index.jsp">Log out</a>
        <a href="result.xml">Download XML</a>
    </div>
    <div class="container conTwo">
        <table class="data_table">
            <caption>Your tracks</caption>
            <tr>

                <th>Track title</th>
                <th>Album</th>
                <th>Duration</th>
                <th>Size</th>
            </tr>

            <tr>
                <td><%=track.getTitle()%></td>
                <td><%=trackList.getTrackList()%></td>
                <td><%=track.getDuration()%></td>
                <td><%=track.getSize()%></td>
            </tr>
        </table>
    </div>

</body>
</html>
