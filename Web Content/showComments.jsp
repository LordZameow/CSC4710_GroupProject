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
            <caption><h2>List of Comments</h2></caption>
            <tr>
                <th>Tweet ID</th>
                <th>Content</th>
                <th>Author</th>
                <th>Time of Post</th>
                <th>Comment ID</th>
            </tr>
            <c:forEach var="comment" items="${listComments}">
                <tr>
                    <td><c:out value="${comment.getTweetID()}" /></td>
                    <td><c:out value="${comment.getContent()}" /></td>
                    <td><c:out value="${comment.getCommenter()}" /></td>
                    <td><c:out value="${comment.getTransTime()}" /></td>
	                <td><c:out value="${comment.getID()}" /></td>    
                </c:forEach>
        </table>
        <a href = "javascript:history.back()">Back to previous page</a>
    </div>   
</body>
</html>