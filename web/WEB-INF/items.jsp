<%-- 
    Document   : items
    Created on : 22-Apr-2022, 4:33:07 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="manage.css">
        <title>Search</title>
    </head>
    <body>
        <h1>Hello - ${firstName} ${lastName}</h1>
        <a href="login?log_out" id="logout">Logout</a>
        <h2>Menu</h2>
        <ul>
             <li><a href="inventory">Inventory</a></li>
            <li><a href="user">Edit User Info</a></li>
            <li><a href="manage_users">Manage Users</a></li>
            <li><a href="manage_categories">Manage Categories</a></li>
        </ul>
        <form action="all" method="post">
            <table>
                <tr>
                    <td><label>Search by Item Name:</label></td>
                    <td><input type="text" name="search"></td>
            </tr>
            <tr>
                <td><input type="submit" name="search2" value="Search">
            </tr>
            </table>
        </form>
        <h4>${none}</h4>
        
        
        <c:if test="${ss}">
        <table id="manage">
            <th class="man">Category</th><th class="man">Item Name</th><th class="man">Price</th><th class="man">Owner</th>
        
        
        <c:forEach var="item" items="${items}"> 
            
                          
                    <tr>
                        <td class="man">${item.category.categoryName}</td>
                        <td class="man">${item.itemName}</td>
                        <td class="man">${item.price}</td>
                        <td class="man">${item.owner.firstName} ${item.owner.lastName}</td>
                    </tr>
                
           
        </c:forEach>
        </table>
        </c:if>
        
    </body>
</html>
