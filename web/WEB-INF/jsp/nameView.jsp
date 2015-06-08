<%-- 
    Document   : nameView
    Created on : 2015-6-1, 17:53:21
    Author     : Heisaman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enter Your Name</title>
    </head>
    <body>
        <div align='center'>
            <h1>
		WELCOME TO SPRING MVC APP
		<a href="contacts.html">查看wechat企业号通讯录</a><br>
		<a href="messages.html">查看wechat企业号消息</a>
            </h1>
            <h3>Enter Your Name</h3>
            <spring:nestedPath path="name">
                <form action="" method="post">
                    Name:
                    <spring:bind path="value">
                        <input type="text" name="${status.expression}" value="${status.value}">
                    </spring:bind>
                    <input type="submit" value="OK">
                </form>
            </spring:nestedPath>
	</div>
    </body>
</html>
