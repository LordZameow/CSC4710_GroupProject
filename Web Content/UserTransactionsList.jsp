<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>User Transaction List</title>
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
            <caption><h2>List of Transactions</h2></caption>
            <tr>
                <th>transID</th>
                <th>To</th>
                <th>From</th>
                <th>ppsAmount</th>
                <th>Transaction Time</th>
                <th>Transaction Type</th>
                <th>ppsPrice</th>
            </tr>
            <c:forEach var="transaction" items="${listTransactions}">
                <tr>
                    <td><c:out value="${transaction.transID}" /></td>
                    <td><c:out value="${transaction.sender}" /></td>
                    <td><c:out value="${transaction.reciever}" /></td>
                    <td><c:out value="${transaction.ppsAmount}" /></td>
                    <td><c:out value="${transaction.transTime}" /></td>
                    <td><c:out value="${transaction.transType}" /></td>
                    <td><c:out value="${transaction.ppsPrice}" /></td>
                </tr>
            </c:forEach>
        </table>
        <a href = "javascript:history.back()">Back to previous page</a>
    </div>   
</body>
</html>