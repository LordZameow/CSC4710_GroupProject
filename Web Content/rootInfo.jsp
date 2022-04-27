<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Information for Root</title>
</head>
<body>

    <center>
        <h1>Root Database Info</h1>

    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h3>Big Influencers</h3></caption>
            <c:forEach var="user" items="${bigInfluencers}">
                <tr>
                    <td><c:out value="${user.userName}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>   
    
        <div align="center">
        <table border="1" cellpadding="5">
            <caption><h3>Big Whales</h3></caption>
            <c:forEach var="user" items="${bigWhales}">
                <tr>
                    <td><c:out value="${user.userName}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>   
    
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h3>Frequent Buyers</h3></caption>
            <c:forEach var="user" items="${frequentBuyers}">
                <tr>
                    <td><c:out value="${user.userName}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>   
    
        <div align="center">
        <table border="1" cellpadding="5">
            <caption><h3>Good Followers</h3></caption>
            <c:forEach var="user" items="${goodFollowers}">
                <tr>
                    <td><c:out value="${user.userName}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div> 
    
        <div align="center">
    <h3>Common Followers</h3>
    <form action="commonFollowers" method="post">
        User X:&nbsp;
        <select name="followerX">
            <c:forEach items="${listUser}" var="user">
                <option value="${user.userName}">
                  ${user.userName}
                </option>
            </c:forEach>
        </select>
        <br/><br/>
        User Y:&nbsp;
        <select name="followerY">
            <c:forEach items="${listUser}" var="user">
                <option value="${user.userName}">
                  ${user.userName}
                </option>
            </c:forEach>
        </select>
        <br/><br/>
        
        <input type="submit" value="Submit" />
    </form>
    <div align="center">
        <table border="1" cellpadding="5">
            <c:forEach var="user" items="${commonFollowers}">
                <tr>
                    <td><c:out value="${user.userName}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</div>
     <div align="center">
        <table border="1" cellpadding="5">
            <caption><h3>Diamond Hands</h3></caption>
            <c:forEach var="user" items="${diamondHands}">
                <tr>
                    <td><c:out value="${user.userName}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>   
       <div align="center">
        <table border="1" cellpadding="5">
            <caption><h3>Paper Hands</h3></caption>
            <c:forEach var="user" items="${paperHands}">
                <tr>
                    <td><c:out value="${user.userName}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>   
    
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h3>Good Influencers</h3></caption>
            <c:forEach var="user" items="${goodInfluencers}">
                <tr>
                    <td><c:out value="${user.userName}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>   
    
     <div align="center">
    <h3>Inactive Users</h3>
    <form action="listInactiveUsers" method="post">
        <th>Day:</th>
                <td>
                    <input type="text" name="year" size="25" value="year"/>
                </td> 
                 <td>
                    <input type="text" name="month" size="25" value="month (number)"/>
                </td>
                <td>
                    <input type="text" name="day" size="25" value="day"/>
                </td>         
                
        <input type="submit" value="Submit" />
    </form>
     <div align="center">
        <table border="1" cellpadding="5">
            <c:forEach var="user" items="${inactiveUsers}">
                <tr>
                    <td><c:out value="${user.userName}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div> 
    
    <div align="center">
    <h3>User Stats</h3>
    <form action="listStats" method="post">
        <th>User X: </th>
                <td>
                    <input type="text" name="username" size="20" />
                </td>    
        <input type="submit" value="Submit" />
    </form>
        <div align="center">
        <table border="1" cellpadding="5">
            <tr>
                <th>Buy Count</th>
                <th>Sell Count</th>
                <th>Tip Count</th>
                <th>Followee Count</th>
                <th>Follower Count</th>
            </tr>
            <c:forEach var="user" items="${userStats}">
                <tr>
                    <td><c:out value="${user.userName}" /></td>
                    <td><c:out value="${user.password}" /></td>
                    <td><c:out value="${user.firstName}" /></td>
                    <td><c:out value="${user.lastName}" /></td>
                    <td><c:out value="${user.age}" /></td>
                </tr>
            </c:forEach>
        </table>
</div>
     <a href = "javascript:history.back()">Back to previous page</a>
</body>
</html>