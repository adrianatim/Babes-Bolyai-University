<%@ page import="webubb.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style>
        body{ font: 17px sans-serif; text-align: center; }
        .button {
            border: none;
            color: white;
            padding: 16px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            transition-duration: 0.4s;
            cursor: pointer;
        }
        .button1 {
            background-color: white;
            color: black;
            border: 2px solid #a88beb;
        }
        .button1:hover {
            background-color: #a88beb;
            color: white;
        }
    </style>
    <script src="js/jquery-2.0.3.js"></script>
    <script src="js/ajax-utils.js"></script>
</head>
<body>
<%! String user; %>
<% user = session.getAttribute("user").toString();
    if (user != null) {
        out.println("<h1>Welcome " + user + "!</h1>");
%>

<form action="teamController", method="get">
    <h1>Display records</h1>
    <input type="submit" class="button button1" value="Show records"/>
</form>

<form action="playerController", method="get">
    <h1>Display your teams</h1>
    <input type="submit" class="button button1" value="Show teams"/>
</form>

<form action="playerController", method="post">
    <h1>Assign player to a team</h1>
    Enter player's age:<input type="text" name="age"> <BR>
    Enter team's name: <input type="text" name="tname"> <BR>
    <input type="submit" class="button button1" value="Assign"/>
</form>

<%
    }
%>

</body>
</html>