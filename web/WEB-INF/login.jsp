
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="final.css">
        <title>Login Page</title>
    </head>
    <body>
        <h1 id="log">Log In</h1>
        
        <form action="login" method="POST">
            <table>
                <tr>
                    <td><label>Email: </label></td>
                    <td><input type="text" name="email" value="${email}"></td>
                </tr>
                
            
                <tr>
                    <td><label>Password: </label></td>
                    <td><input type="password" name="password" value="${password}"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="loginButton" value="Log In"></td>
                    <td><input type="hidden" name="action" value="login"></td>
                </tr>
                </table>
               
            
            
        </form>
            
        <h4>${invalidLogin}</h4>
        <h4>${loggedOut}</h4>
        <h4>${userDeactivateSuccessful}</h4>
        <h4>${userDeactivateError}</h4>
        <h4>${accountDeactivate}</h4>
        
        <br>
        
        <h1  id="register">Register</h1>
        <form action="login" method="POST">
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
                </tr>
                <tr>
                <td><label>Last Name: </label></td>
                <td><input type="text" name="last_name"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="createButton" value="Register"></td>
                    <td><input type="hidden" name="action" value="register"></td>
                </tr>
            </table>
            
            
        </form>
        
        <h4>${userRegisterError}</h4>
        <h4>${userRegisterSuccessful}</h4>
        
    </body>
</html>
