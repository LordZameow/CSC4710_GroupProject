import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PeopleDAO peopleDAO;
    private rootDAO rootDAO;
    private UserDAO userDAO;
    private transactionsDAO transactionsDAO;
    private tweetsDAO tweetsDAO;
 
    public void init() {
        peopleDAO = new PeopleDAO();
        rootDAO=new rootDAO();
        userDAO=new UserDAO();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost started: 000000000000000000000000000");
        doGet(request, response);
        System.out.println("doPost finished: 11111111111111111111111111");
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet started: 000000000000000000000000000"); 
     
        String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {
            case "/list": 
                System.out.println("The action is: list");
                listPeople(request, response);           	
                break;
            case "listUsers":
            	System.out.println("The action is: list users");
            	listUsers(request,response);
            	break;
            case "/new":
                System.out.println("The action is: new");
                showNewForm(request, response);
                break;
            case "/showUserLogin":
            	System.out.println("The action is: show user login");
            	showUserLogin(request,response);
            	break;
            case "/insert":
                System.out.println("The action is: insert");
            	   insertUser(request, response);
                break;
            case "/delete":
                System.out.println("The action is: delete");
            	   deletePeople(request, response);
                break;
            case "/edit":
                System.out.println("The action is: edit");
                showEditForm(request, response);
                break;
            case "/update":
                System.out.println("The action is: update");
            	   updatePeople(request, response);
                break;
            case "/rootLogin":
            	System.out.println("The action is root login");
            	while(!rootLogin(request,response)) {}
            	showInitializeDatabase(request,response);
            	break;
            case "/initializeDatabase":
            	System.out.println("The action is initialize database");
            	userDAO.initialize();
            	showUserLogin(request,response);
            	break;
            case "/register":
            	System.out.println("The action is: show register form");
            	showRegister(request,response);
            	break;
            case "/userLogin":
            	System.out.println("The action is: user login");
            	userLogin(request,response);
            	break;
            case "/showUserProfile":
            	System.out.println("The action is: show user profile");
            	showUserProfile(request,response);
            case "/buyPPS":
            	System.out.println("The action is: buy PPS");
            	buyPPS(request,response);
            	break;
            case "/sellPPS":
            	System.out.println("The action is: sell PPS");
            	sellPPS(request,response);
            	break;
            case "/listAllUserTransactions":
            	System.out.println("The action is: List all user transactions");
            	listUserTransactions(request,response);
            	break;
            default:
                System.out.println("Not sure which action, we will treat it as the list action");
                listUsers(request, response);           	
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
        System.out.println("doGet finished: 111111111111111111111111111111111111");
    }
    
    private void showInitializeDatabase(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("showInitializeDatabase started: 000000000000000000000000000");
     
        RequestDispatcher dispatcher = request.getRequestDispatcher("InitializeDatabase.jsp");
        dispatcher.forward(request, response);
        System.out.println("The user sees the InitializeDatabase page now.");
     
        System.out.println("showInitializeDatabase finished: 1111111111111111111111111111111");
    }
    
    private void showRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("UserRegister started: 000000000000000000000000000");
     
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserRegister.jsp");
        dispatcher.forward(request, response);
        System.out.println("The user sees the UserRegister page now.");
     
        System.out.println("UserRegister finished: 1111111111111111111111111111111");
    }
    
    private void showUserLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ShowUserLogin started: 000000000000000000000000000");
     
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserLogin.jsp");
        dispatcher.forward(request, response);
        System.out.println("The user sees the UserLogin page now.");
     
        System.out.println("UserLogin finished: 1111111111111111111111111111111");
    }
    
    private void listPeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("listPeople started: 00000000000000000000000000000000000");

     
        List<People> listPeople = peopleDAO.listAllPeople();
        request.setAttribute("listPeople", listPeople);       
        RequestDispatcher dispatcher = request.getRequestDispatcher("PeopleList.jsp");       
        dispatcher.forward(request, response);
     
        System.out.println("listPeople finished: 111111111111111111111111111111111111");
    }
    
    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("listUsers started: 00000000000000000000000000000000000");

     
        List<User> listUser = userDAO.listAllUser();
        request.setAttribute("listUsers", listUser);       
        RequestDispatcher dispatcher = request.getRequestDispatcher("UsersList.jsp");       
        dispatcher.forward(request, response);
     
        System.out.println("listUsers finished: 111111111111111111111111111111111111");
    }
    
    private void listUserTransactions(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("listTransactions started: 00000000000000000000000000000000000");
        String username=request.getParameter("username");
        List<transactions> listTransactions = transactionsDAO.listAllUserTransactions(username);
        request.setAttribute("listTransactions", listTransactions);       
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserTransactionsList.jsp");       
        dispatcher.forward(request, response);
     
        System.out.println("listTransactions finished: 111111111111111111111111111111111111");
    }
    
    private void showUserProfile(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("showUserProfile started: 00000000000000000000000000000000000");
        String username=request.getParameter("username");
        System.out.println(username);
        List<User> userProfile = userDAO.getUser(username);
        request.setAttribute("profile", userProfile);       
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserProfile.jsp");       
        dispatcher.forward(request, response);
        System.out.println("showUserProfile Finished: 111111111111111111111111111111111111");
        
    }
 
    // to insert a people
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("showNewForm started: 000000000000000000000000000");
     
        RequestDispatcher dispatcher = request.getRequestDispatcher("InsertPeopleForm.jsp");
        dispatcher.forward(request, response);
        System.out.println("The user sees the InsertPeopleForm page now.");
     
        System.out.println("showNewForm finished: 1111111111111111111111111111111");
    }
 
    // to present an update form to update an  existing Student
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        System.out.println("showEditForm started: 000000000000000000000000000");
     
        int id = Integer.parseInt(request.getParameter("id"));
        People existingPeople = peopleDAO.getPeople(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditPeopleForm.jsp");
        request.setAttribute("people", existingPeople);
        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
        System.out.println("Now you see the EditPeopleForm page in your browser.");
     
        System.out.println("showEditForm finished: 1111111111111111111111111111");
    }
 
    private boolean rootLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
    	String username=request.getParameter("username");
    	String password=request.getParameter("password");
    	root ROOT=new root();
    	root attempt=new root(username,password);
    	System.out.println("ROOT: "+ROOT.username+" "+ROOT.password+" ; Attempt: "+attempt.username+" "+attempt.password);
    	return rootDAO.isRoot(ROOT,attempt);
    }
    
    // after the data of a people are inserted, this method will be called to insert the new people into the DB
    // 
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException {
	    System.out.println("insertPeople started: 000000000000000000000000000");
	    
	    String userName = request.getParameter("username");
	    String password = request.getParameter("password");
	    String confirmPassword = request.getParameter("confirmPassword");
	    String firstName = request.getParameter("firstname");
	    String lastName = request.getParameter("lastname");
	    String age = request.getParameter("age");
	    System.out.println("username:" + userName + ", password: "+password + ", firstname: " + firstName + ", lastname: " + lastName+ ", age: " + age);
	    
	    int ageInt = Integer.valueOf(age);
	    if(!password.equals(confirmPassword))
	    	{
	    	response.sendRedirect("register");
	    	return;
	    	}
	    
	    User newUser = new User(userName,password,firstName,lastName,ageInt);
	    userDAO.insert(newUser);
	 
	    System.out.println("Ask the browser to call the list action next automatically");
	    response.sendRedirect("showUserLogin");  //
	 
	    System.out.println("insertPeople finished: 11111111111111111111111111");
    }
 
    private void userLogin(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
	    System.out.println("userLogin started: 000000000000000000000000000");
	    
	    String username=request.getParameter("username");
	    String password=request.getParameter("password");
	    if(userDAO.checkUser(username,password)) {
	    	HttpSession session = request.getSession();
	    	session.setAttribute("username",username);
	    	
	    	showUserProfile(request,response);
	    	//response.sendRedirect("listUsers");
	    }
	    else{
	    	response.sendRedirect("showUserLogin");
	    	}

    }
    
    private void buyPPS(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
	    System.out.println("buyPPS started: 000000000000000000000000000");
	    String username=(String)request.getSession().getAttribute("username");
	    System.out.println(username);
	    double amount=Double.parseDouble(request.getParameter("ppsAmount"));
	    if(!userDAO.checkBalance(username,amount)) {
	    	System.out.println("not enough money");
	    	response.sendRedirect("listUsers");
	    }
	    System.out.println("buying pps");
	    rootDAO.buyPPS(username, amount);
	    response.sendRedirect("listUsers");

	    
	    System.out.println("buyPPS finished: 11111111111111111111111111");
    }
    
    private void sellPPS(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
	    System.out.println("buyPPS started: 000000000000000000000000000");
	    String username=(String)request.getSession().getAttribute("username");
	    System.out.println(username);
	    double amount=Double.parseDouble(request.getParameter("ppsAmount"));
	    if(!userDAO.checkPPSBalance(username,amount)) {
	    	System.out.println("not enough pps");
	    	response.sendRedirect("listUsers");
	    }
	    System.out.println("selling pps");
	    rootDAO.sellPPS(username, amount);
	    response.sendRedirect("listUsers");

	    
	    System.out.println("buyPPS finished: 11111111111111111111111111");
    }
    
    private void updatePeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        System.out.println("updatePeople started: 000000000000000000000000000");
     
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String status = request.getParameter("status");
        System.out.println("name:" + name + ", address: "+address + ", status:" + status);
        
        People people = new People(id,name, address, status);
        peopleDAO.update(people);
        System.out.println("Ask the browser to call the list action next automatically");
        response.sendRedirect("list");
     
        System.out.println("updatePeople finished: 1111111111111111111111111111111");
    }
 
    private void deletePeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        System.out.println("deletePeople started: 000000000000000000000000000");
     
        int id = Integer.parseInt(request.getParameter("id"));
        //People people = new People(id);
        peopleDAO.delete(id);
        System.out.println("Ask the browser to call the list action next automatically");
        response.sendRedirect("list"); 
     
        System.out.println("deletePeople finished: 1111111111111111111111111111111");
    }
    
    private void followUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	String followeeID = (String) request.getSession().getAttribute("username");
    	String followerID= request.getParameter("followeeID");
    	
    	userDAO.follow(followeeID, followerID);
    }
    
    private void unfollowUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	String followeeID = (String) request.getSession().getAttribute("username");
    	String followerID= request.getParameter("followeeID");
    	
    	userDAO.unfollow(followeeID, followerID);
    	response.sendRedirect("UsersList.jsp");
    }

    private void likePost(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	String username = (String) request.getSession().getAttribute("username");
    	int tweetID= Integer.parseInt(request.getParameter("tweetID"));
    	
    	if(tweetsDAO.like(username,tweetID)) 
    		response.sendRedirect("feed");
    		
    }

    
    private void dislikePost(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	String username = (String) request.getSession().getAttribute("username");
    	int tweetID= Integer.parseInt(request.getParameter("tweetID"));
    	
    	if(tweetsDAO.dislike(username,tweetID)) 
    		response.sendRedirect("feed");
    		
    }
}
