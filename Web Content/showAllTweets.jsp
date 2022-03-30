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
            <caption><h2>List of All Tweets</h2></caption>
            <tr>
                <th>Tweet ID</th>
                <th>Content</th>
                <th>Author</th>
                <th>Time of Post</th>
                <th>Action</th>
                <th>Like</th>
                <th>Unlike</th>
                <th>Total Likes</th>
                <th>Comment Content</th>
                <th>Comment</th>
            </tr>
            <c:forEach var="tweet" items="${listTweets}">
                <tr>
                    <td><c:out value="${tweet.getTweetID()}" /></td>
                    <td><c:out value="${tweet.getContent()}" /></td>
                    <td><c:out value="${tweet.getAuthor()}" /></td>
                    <td><c:out value="${tweet.getTransTime()}" /></td>
                    <td>
                        <a href="listComments?tweetID=<c:out value='${tweet.getTweetID()}' />">Show Comments</a>
                    </td>
                    <form action="likePost" method="post">
                    <input type="hidden" name="tweetID"
                       value="<c:out value='${tweet.getTweetID()}' />"
                       />
                    <td><input type="submit" value="like" /></td>
                    </form>
                    <form action="dislikePost" method="post">
                    <input type="hidden" name="tweetID"
                       value="<c:out value='${tweet.getTweetID()}' />"
                       />
                    <td><input type="submit" value="dislike" /></td>
                    </form>
                    <td><c:out value="${tweet.getLikeCounter()}" /></td>
                    <form action="comment" method="post">
                    <input type="hidden" name="tweetID"
                       value="<c:out value='${tweet.getTweetID()}' />"
                       />
                     <td>
                    <input type="text" name="commentContent" size="45"    
                    />
                </td>  
                    <td><input type="submit" value="comment" /></td>
                    </form>
                </tr>
	                    
                </c:forEach>
        </table>
        <a href = "javascript:history.back()">Back to previous page</a>
    </div>   
</body>
</html>