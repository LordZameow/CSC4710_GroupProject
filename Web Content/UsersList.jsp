<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>User Profile List</title>
</head>
<body>

<%-- need to debut this part of the code to make it work, ideally we would like to see 
 all people are listed intially when the page is run as the entry page.
 
<%
if(request.getParameter("listPeople") == null) { // we want to make sure that we already have all the people
	PeopleDAO peopleDAO = new PeopleDAO();        // listed in attribute 'listPeople'
	List<People> listPeople = peopleDAO.listAllPeople();
	request.setAttribute("listPeople", listPeople);       
}
%>
--%>
<%--
    <center>
        <h1>People Management Main Menu</h1>
        <h2>
            <a href="new">Add New People</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">List All People</a>
             <%=request.getAttribute("username")%>!
        </h2>
--%>
<h2>
Logged in as user:
<% String value=(String)session.getAttribute("username");%>
<% out.print(value); %>
</h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>Username</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Age</th>
                <th>Actions</th>
                <th>Follow</th>
                <th>Unfollow</th>
            </tr>
            <c:forEach var="user" items="${listUsers}">
                <tr>
                    <td><c:out value="${user.userName}" /></td>
                    <td><c:out value="${user.firstName}" /></td>
                    <td><c:out value="${user.lastName}" /></td>
                    <td><c:out value="${user.age}" /></td>
                    <td>
                        <a href="showUserProfile?username=<c:out value='${user.userName}' />">See profile</a>
                    </td>
                    <form action="follow" method="post">
                    <input type="hidden" name="followeeID"
                       value="<c:out value='${user.userName}' />"
                       />
                    <td><input type="submit" value="follow" /></td>
                    </form>
                    <form action="unfollow" method="post">
                    <input type="hidden" name="followeeID"
                       value="<c:out value='${user.userName}' />"
                       />
                    <td><input type="submit" value="unfollow" /></td>
                    </form>
                </tr>
            </c:forEach>
        </table>
        <a href = "javascript:history.back()">Back to previous page</a>
    </div>   
</body>
</html>