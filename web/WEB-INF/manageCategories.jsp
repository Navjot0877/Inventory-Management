
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="manage.css">
        <title>Manage Categories</title>
    </head>
    <body>
        <h1>Hello - ${firstName} ${lastName}</h1>
        
       <a href="login?log_out" id="logout">Logout</a>
        
        <h2>Menu</h2>
        <ul>
            <li><a href="inventory">Inventory</a></li>
            <li><a href="user">Edit User Info</a></li>
            <li><a href="all">Search Items</a></li>
            <li><a href="manage_users">Manage Users</a></li>
        </ul>
        
        <br>
        <table id="manage">
        <c:forEach var="category" items="${categories}">
            <form action="manage_categories" method="POST">
                
                    <tr>
                        <td class="man">${category.categoryName}</td>
                        <td class="man"><input type="submit" name="manage_value" value="Edit"></td>
                        <input type="hidden" name="edit_category_id" value="${category.categoryId}">
                        <input type="hidden" name="action" value="manage">
                        <input type="hidden" name="component" value="category">
                    </tr>
                
            </form>
        </c:forEach> 
            </table>
        
        <br>
        
        <h2>Add Category</h2>
        <form method="POST" action="manage_categories">
            <table class="tab">
                <tr>
                    <td><label>Category Name: </label></td>
                    <td><input type="text" class="in" name="new_category_name"></td>
                </tr>
                <tr>
            
                    <td><input type="submit" class="butt" name="add_category" value="Create Category"></td>
                    <td><input type="hidden" name="action" value="create"></td>
                </tr>
            </table>
        </form>
        
        <h4>${categoryCreateSuccessful}</h4>
        <h4>${categoryCreateError}</h4>
        <c:if test="${edit}">
        <h2>Edit Category</h2>
        <form method="POST" action="manage_categories">
            <table class="tab">
                <tr>
                    <td><label>Category Name: </label></td>
                    <td><input type="text" class="in" name="edit_category_name" value="${newCategoryName}"></td>
                <tr>
                    <td><input type="submit" class="butt" name="add_category" value="Edit Category"></td>
                    <td><input type="hidden" name="action" value="edit"></td>
                </tr>
            </table>
            
        </form>
        </c:if>

        <h4>${categoryFetchSuccessful}</h4>
        <h4>${categoryEditSuccessful}</h4>
        <h4>${categoryEditError}</h4>
    </body>
</html>
