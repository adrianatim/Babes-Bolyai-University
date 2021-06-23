<%@ page import="webubb.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style>

    </style>
    <script src="js/jquery-2.0.3.js"></script>
    <script src="js/ajax-utils.js"></script>
</head>
<body>
<%! User user; %>
<% user = (User) session.getAttribute("user");
    if (user != null) {
        out.println("Welcome " + user.getUsername() + "!");
%>

<p><button id="getDescendents" type="button">See descendents</button></p>
<section><table id="descendents-table"></table></section>
<%--<form action="familyController" method="post">--%>
<%--    <input type="submit" value="See descendents"/>--%>
<%--</form>--%>

<script>
    $(document).ready(function(){
        $("#getDescendents").click(function() {
            getUserDescendents(<%= user.getId() %>, function(assets) {
                console.log(assets);
                $("#descendents-table").html("");
                $("#descendents-table").append("<tr style='background-color: mediumseagreen'><td>Id</td><td>Username</td></tr>");
                for(var name in assets) {
                    $("#descendents-table").append("<tr><td>"+assets[name].id+"</td>" +
                        "<td>"+assets[name].username+"</td></tr>");
                }
            })
        })
    });
</script>
<%
    }
%>

</body>
</html>