<%@ page import="musicLibrary.User" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="musicLibrary.TrackList" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="musicLibrary.Track" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
        response.setHeader("Error", "5;url=index.jsp");
    }
%>





<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/result.css">
    <title>Result</title>
</head>
<body>
    <header>
        <div class="container">
            <div class="title">
                <h1>Welcome, <%=user.getName()%></h1>
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
            <%
                ArrayList<TrackList> trackLists = user.getTrackLists();
                for(TrackList tr : trackLists){
                    for(int i = 0; i < tr.getSizeTracksArray(); i++){
                        Track track = tr.getTrackToIndex(i);
            %>

            <tr>

                <td><%=track.getTitle()%></td>
                <td><%=tr.getID()%></td>
                <td><%=track.getDuration()%></td>
                <td><%=track.getSize()%></td>
            </tr>

            <%
                    }
                }

            %>
        </table>
    </div>

</body>
</html>
