<%-- 
    Document   : user
    Created on : Dec 1, 2021, 6:50:37 PM
    Author     : manve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="manage.css">
        <title>User Account</title>
    </head>
    <body>
        <h1>Edit User Info - ${firstName} ${lastName}</h1>
        
        <a href="login?log_out" id="logout">Logout</a>
        
        <h2>Menu</h2>
        <ul>
            <li><a href="inventory">Inventory</a></li>
            <li><a href="all">Search Items</a></li>
            <li><a href="manage_users">Manage Users</a></li>
            <li><a href="manage_categories">Manage Categories</a></li>
        </ul>
        
        <br>
        
        <form action="user" method="POST">
            <table>
                <tr>
                    <td><label>Email: </label></td>
                    <td><input type="email" name="email" readonly value="${email}"></td>
                </tr>
                <tr>
                    <td><label>Password: </label></td>
                    <td><input type="text" name="password"></td>
                </tr>
                <tr>
                    <td><label>First Name: </label></td>
                    <td><input type="text" name="first_name"></td>
                <tr>
                    <td><label>Last Name: </label></td>
                    <td><input type="text" name="last_name"></td>
                </tr>
                <tr>
                    <td><input class="butt" type="submit" name="action" value="Edit"></td>
                </tr>
            </table>
        </form>
        
        <br>
        <h4>${userEditSuccessful}</h4>
        <h4>${userEditError}</h4>
        
        
        
        <a href="login?deactivate" class="deac">Deactivate Account</a>
    </body>
</html>
