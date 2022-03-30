<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>User Profile Page</title>
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
             
        </h2>
--%>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>User Profile</h2></caption>
            <tr>
                <th>Username</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Age</th>
                <th>Dollar Balance</th>
                <th>PPswap Balance</th>
            </tr>
            <c:forEach var="user" items="${profile}">
                <tr>
                    <td><c:out value="${user.userName}" /></td>
                    <td><c:out value="${user.firstName}" /></td>
                    <td><c:out value="${user.lastName}" /></td>
                    <td><c:out value="${user.age}" /></td>
                    <td><c:out value="${user.dollarBal}" /></td>
                    <td><c:out value="${user.getPPSBal()}" /></td>
                    
                </tr>
            </c:forEach>
        </table>
        <form action="buyPPS" method="post">
            <td colspan="2" align="center">
                <label for="buyAmount">Buy PPS:</label>
                <input type="number" id="ppsAmount" name="ppsAmount" min="0" max="1000" step="10" value="0">
                <input type="submit" value="Buy PPSwap" />
            </td>
         </form>
        
        <form action="sellPPS" method="post">
            <td colspan="2" align="center">
                <label for="sellAmount">Sell PPS:</label>
                <input type="number" id="ppsAmount" name="ppsAmount" min="0" max="1000" step="10" value="0">
                <input type="submit" value="Sell PPSwap" />
                </td>
        </form>
        
        <form action="postTweet" method="post">
            <td>
                <label for="tweet">Post a Tweet:</label>
                <input type="text" name="postTweet" size="50"
                value="<c:out value='${tweet.content}' />"
                />
                <input type="submit" value="Post Tweet" />
            </td>
        </form>

        <a href = "listUsers">List all Users</a>
        <a href = "listAllUserTransactions">List all User Transactions</a>
    </div>   
</body>
</html>