
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="manage.css">
        <title>Home Inventory</title>
    </head>
    <body>
        <h1>Home Inventory - ${firstName} ${lastName}</h1>
        <h1>${noUserFound}</h1>
        
         <a href="login?log_out" id="logout">Logout</a>
        
        <h2>Menu</h2>
        <ul>
            <li><a href="user">Edit User Info</a></li>
            <li><a href="all">Search Items</a></li>
            <li><a href="manage_users">Manage Users</a></li>
            <li><a href="manage_categories">Manage Categories</a></li>
        </ul>
        
        <br>
        
        <c:if test="${size ne 0}">
            <h2>All Items</h2>
        <table id="manage">
            <th class="man">Category</th><th class="man">Item Name</th><th class="man">Price</th><th class="man">Delete</th><th class="man">Edit</th>
        
        <!-- Display all items of the user using the list and c:forEach-->
        <c:forEach var="item" items="${items}"> 
            <form action="inventory" method="POST">
                          
                    <tr>
                        <td class="man">${item.category.categoryName}</td>
                        <td class="man">${item.itemName}</td>
                        <td class="man">${item.price}</td>
                        <td class="man"><input type="submit" name="action" value="Delete"></td>
                        <td class="man"><input type="submit" name="action" value="Edit"></td>
                        <input type="hidden" name="itemID" value="${item.itemId}">
                        <input type="hidden" name="category" value="${item.category.categoryName}">
                    </tr>
                
            </form>
        </c:forEach>
        </table>
            </c:if>
        
        <h2>${noItems}</h2>
        
        <br>

        <h2>Add Item</h2>
         <!-- showing from to add items -->
        <form method="POST" action="inventory">
            <table>
                <tr>
                    <td><label>Category: </label></td>
                    <td><select name="category">                       
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.categoryId}">${category.categoryName}</option>
                    </c:forEach>
                        </select></td>
                </tr>
           
                <tr>
                    <td><label>Item Name: </label></td>
                    <td><input type="text" name="itemname" value="${itemName}"></td>
                </tr>
                <tr>
                    <td><label>Price: </label></td>
                    <td><input type="text" name="price" value="${price}"></td>
                </tr>
                <tr>
                    <td><input type="submit" class="butt" name="action" value="Add"></td>
                <tr>
            </table>
        </form>
        <h4>${insertSuccessful}</h4>
        <h4>${createError}</h4>
        <h4>${deleteSuccessful}</h4>
        <h4>${deleteError}</h4>
        
        <c:if test="${edit}">
        <h2>Edit Item</h2>
        <form method="POST" action="inventory">
            <table>
                <tr>
                    <td><label> Current Category: </label></td>
                    <td><input type="text" readonly value="${category}"></td>
                </tr>
                <tr>
                    <td><label>Choose New Category: </label></td>
                    <td><select name="newCategory">                       
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.categoryId}">${category.categoryName}</option>
                    </c:forEach>
                        </select></td>
                </tr>
                <tr>
                    <td><label>Item Name: </label></td>
            
                    <td><input type="text" name="newItemName" value="${editItemName}"></td>
                </tr>
                <tr>
                    <td><label>Price: </label></td>
                    <td> <input type="text" name="newPrice" value="${editPrice}"></td>
                </tr>
                <tr>
                    <td><input type="submit" class="butt" name="action" value="Edit Item"></td>
                </tr>
   
        </form>
         </c:if>
        <h4>${itemEditSuccessful}</h4>
        <h4>${itemEditError}</h4>
    </body>
</html>
