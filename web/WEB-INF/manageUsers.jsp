
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="manage.css">
        <title>Manage Users</title>
    </head>
    <body>
        <h1>Manage Users - ${firstName} ${lastName}</h1>
        
        <a href="login?log_out" id="logout">Logout</a>
        <h2>Menu</h2>
        <ul>
            <li><a href="inventory">Inventory</a></li>
            <li><a href="user">Edit User Info</a></li>
            <li><a href="all">Search Items</a></li>
            <li><a href="manage_categories">Manage Categories</a></li>
        </ul>
        
        <h2 id="all">All Users</h2>
        <table id="manage">
            <tr>
                <td>Email</td>
                <td>First Name</td>
                <td>Last Name</td>
                <td>Delete</td>
                <td>Edit</td>
            </tr>
        <c:forEach var="user" items="${users}">
            <form action="manage_users" method="POST">
                
                    <tr>
                        <td class="man">${user.email}</td>
                        <td class="man">${user.firstName}</td>
                        <td class="man">${user.lastName}</td>
                        <td class="man"><input type="submit" name="manage_value" value="Delete"></td>
                        <td class="man"><input type="submit" name="manage_value" value="Edit"></td>
                        <input type="hidden" name="email_value" value="${user.email}">
                        <input type="hidden" name="action" value="manage">
                    </tr>
                
            </form>
        </c:forEach>  
            </table>

        <h4>${userDeleteSuccessful}</h4>
        <h4>${userDeleteError}</h4>
        <h4>${selfDeleteError}</h4>
        
        <br>
        <c:if test="${edit}">
        <h2>Edit User</h2>
        
        <!-- Form to create new users -->
        <form method="POST" action="manage_users">
            <table>
                <tr>
                    <td><label>Email: </label></td>
                    <td><input type="email" name="email" value="${email}" readonly></td>
                </tr>
                <tr>
                    <td><label>Password: </label></td>
                    <td><input type="text" name="edit_password" value="${edit_password_value}"></td>
                </tr>
                <tr>
                    <td><label>First Name: </label></td>
                    <td><input type="text" name="edit_first_name" value="${edit_firstname_value}"></td>
                </tr>
                <tr>
                    <td><label>Last Name: </label></td>
                    <td><input type="text" name="edit_last_name" value="${edit_lastname_value}"></td>
                </tr>
                <tr>
                    <td><label>Role: </label></td>
                    <td><input type="number" name="edit_role" min="1" max="3" value="${edit_role_value}"></td>
            
                <tr>
               <c:choose>
                    <c:when test="${active}">
                        <td><label>Active:</label></td> <td><input type="checkbox" name="active" checked></td>
                    
                </c:when>
                    
                            <c:otherwise>
                                <td><label>Active:</label></td>  <td><input type="checkbox" name="active"></td>
                            
                    </c:otherwise>
                </c:choose>
                </tr>
                            <tr>
                                <td><input type="submit" class="butt" name="addbutton" value="Edit User"></td>
                                <td><input type="hidden" name="action" value="edit"></td>
                            </tr>
            </table>
        </form>   
        </c:if>
        
        
        <h4>${userFindError}</h4>
        <h4>${userEditSuccessful}</h4>
        <h4>${userEditError}</h4>
        <br>
        
        <h2>Add User</h2>
        
        <!-- Form to create new users -->
        <form method="POST" action="manage_users">
            <table>
                <tr>
                    <td><label>Email: </label></td>
                    <td><input type="email" name="email"></td>
                </tr>
                <tr>
                    <td><label>Password: </label></td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td><label>First Name: </label></td>
                    <td><input type="text" name="first_name"></td>
                <tr>
                <tr>
                    <td><label>Last Name: </label></td>
                    <td><input type="text" name="last_name"></td>
                </tr>
                <tr>
                    <td><label>Role: </label></td>
                    <td><input type="number" name="role" min="1" max="3"></td>
                </tr>
                <tr>
                    <td><label>Active: </label></td>
                    <td><input type="checkbox" name="active"></td>
                </tr>
                <tr>
                    <td><input type="submit" class="butt" name="addbutton" value="Create User"></td>
                    <td><input type="hidden" name="action" value="create"></td>
                </tr>
            </table>
        </form>   
        
        <br>
        <h4>${userCreateSuccessful}</h4>
        <h4>${userCreateError}</h4>
        <br>
        
        
    </body>
</html>
