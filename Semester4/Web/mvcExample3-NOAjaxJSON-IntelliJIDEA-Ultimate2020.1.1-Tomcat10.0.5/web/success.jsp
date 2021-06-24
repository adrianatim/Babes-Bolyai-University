<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Success</title>
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
<% user = (String) session.getAttribute("user");
    if (user != null) {
        out.println("<h1>Welcome " + user + "!</h1>");
%>

<%--ADD RECORD TO DATABASE--%>
<%--<form action="productController", method="post">--%>
<%--    <h1>Add record</h1>--%>
<%--    Enter id : <input type="text" name="id"> <BR>--%>
<%--    Enter name : <input type="text" name="name"> <BR>--%>
<%--    Enter description : <input type="text" name="description"> <BR>--%>
<%--    <input type="submit" class="button button1" value="Add"/>--%>
<%--</form>--%>

<%--DELETE RECORD FROM DATABASE--%>
<%--<form action="productController", method="post">--%>
<%--    <h1>Delete record</h1>--%>
<%--    Enter id : <input type="text" name="id"> <BR>--%>
<%--    <input type="submit" class="button button1" value="Delete"/>--%>
<%--</form>--%>

<form action="productController", method="post">
    <h1>Update record</h1>
    Enter id : <input type="text" name="id"> <BR>
    Enter name : <input type="text" name="name"> <BR>
    Enter description : <input type="text" name="description"> <BR>
    <input type="submit" class="button button1" value="Update"/>
</form>

<form action="productController", method="get">
    <h1>Display records</h1>
    Enter name to start : <input type="text" name="name"> <BR>
    <input type="submit" class="button button1" value="Show records"/>
</form>

<form action="orderController", method="get">
    <h1>Buy products</h1>
    Enter productId : <input type="text" name="productId"> <BR>
    Enter quantity : <input type="text" name="quantity"> <BR>
    <input type="submit" class="button button1" value="Buy"/>
</form>

<form action="orderController", method="post">
    <% session.setAttribute("command", "finalize");%>
    <input type="submit" class="button button1" value="Finish command"/>
</form>

<form action="orderController", method="post">
<%--    <% session.setAttribute("command", "cancel");%>--%>
<input type="submit" class="button button1" value="Cancel command"/>
</form>
<%
    }
%>

</body>
</html>