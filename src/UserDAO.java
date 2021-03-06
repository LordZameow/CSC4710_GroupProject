import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/UserDAO")
public class UserDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public UserDAO() {
		
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     * "useSSL=false&user=john&password=pass1234"
     */
    protected void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/testdb?"
  			          + "useSSL=false&user=Fran&password=2489823172aA");
            System.out.println(connect);
        }
    }
    
    public void initialize() throws SQLException{
    	
    	String delData="DELETE from user";
    	connect_func();      
    	preparedStatement = (PreparedStatement) connect.prepareStatement(delData);
    	statement=connect.createStatement();
    	statement.executeUpdate(delData);
         
    	String insert1 = "insert into  user(userName, password, firstName, lastName, age, dollarBal, ppsBal) values "
    			+ "(\"nighthawk@verizon.net\", \"passOne\", \"Michael\", \"Smith\",\"20\",\"1000\",\"50\")";
    	String insert2 = "insert into  user(userName, password, firstName, lastName, age, dollarBal, ppsBal) values "
    			+ "(\"lydia@outlook.com\", \"passTwo\", \"Christopher\", \"Johnson\",\"21\",\"1000\",\"50\")";
    	String insert3 = "insert into  user(userName, password, firstName, lastName, age, dollarBal, ppsBal) values "
    			+ "(\"sabren@comcast.net\", \"passThree\", \"Jessica\", \"Williams\",\"22\",\"1000\",\"50\")";
    	String insert4 = "insert into  user(userName, password, firstName, lastName, age, dollarBal, ppsBal) values "
    			+ "(\"bulletin@me.com\", \"passFour\", \"Matthew\", \"Jones\",\"23\",\"1000\",\"50\")";
    	String insert5 = "insert into  user(userName, password, firstName, lastName, age, dollarBal, ppsBal) values "
    			+ "(\"mhanoh@outlook.com\", \"passFive\", \"Ashley\", \"Brown\",\"24\",\"1000\",\"50\")";
    	String insert6 = "insert into  user(userName, password, firstName, lastName, age, dollarBal, ppsBal) values "
    			+ "(\"smpeters@comcast.net\", \"passSix\", \"Jennifer\", \"Davis\",\"25\",\"1000\",\"50\")";
    	String insert7 = "insert into  user(userName, password, firstName, lastName, age, dollarBal, ppsBal) values "
    			+ "(\"mcraigw@live.com\", \"passSeven\", \"Joshua\", \"Miller\",\"26\",\"1000\",\"50\")";
    	String insert8 = "insert into  user(userName, password, firstName, lastName, age, dollarBal, ppsBal) values "
    			+ "(\"wenzlaff@comcast.net\", \"passEight\", \"Amanda\", \"Wilson\",\"27\",\"1000\",\"50\")";
    	String insert9 = "insert into  user(userName, password, firstName, lastName, age, dollarBal, ppsBal) values "
    			+ "(\"jimmichie@aol.com\", \"passNine\", \"Daniel\", \"Moore\",\"28\",\"1000\",\"50\")";
    	String insert10 = "insert into  user(userName, password, firstName, lastName, age, dollarBal, ppsBal) values "
    			+ "(\"kjohnson@outlook.com\", \"passTen\", \"David\", \"Taylor\",\"29\",\"1000\",\"50\")";
		statement.executeUpdate(insert1);
		statement.executeUpdate(insert2);
		statement.executeUpdate(insert3);
		statement.executeUpdate(insert4);
		statement.executeUpdate(insert5);
		statement.executeUpdate(insert6);
		statement.executeUpdate(insert7);
		statement.executeUpdate(insert8);
		statement.executeUpdate(insert9);
		statement.executeUpdate(insert10);
    }
    
    public List<User> listAllUser() throws SQLException {
        List<User> listUser = new ArrayList<User>();        
        String sql = "SELECT * FROM user";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String userName = resultSet.getString("username");
            String password = resultSet.getString("password");
            int age = resultSet.getInt("age");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");

             
            User user = new User(userName,password, firstName, lastName, age);
            if(!userName.equals("root"))
            	listUser.add(user);
        }        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listUser;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    
    
    public boolean insert(User user) throws SQLException {
    	connect_func();         
    	
    	//Query to see if the username is already existing
    	String sql = "SELECT * FROM user WHERE username = ?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, user.userName);
    	ResultSet resultSet = preparedStatement.executeQuery();
    	if (resultSet.next()) {//returns false if the username already exists 
    		return false;
    	}
    	else {
    		System.out.println("Last Name: "+ user.lastName+ "Password: "+user.password);
			String sq2 = "insert into  User(userName, password, age, firstName, lastName,dollarBal,ppsBal) values (?, ?, ?, ?,?,?,?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sq2);
			preparedStatement.setString(1, user.userName);
			preparedStatement.setString(2, user.password);
			preparedStatement.setInt(3, user.age);
			preparedStatement.setString(4, user.firstName);
			preparedStatement.setString(5, user.lastName);
			preparedStatement.setDouble(6, user.dollarBal);
			preparedStatement.setDouble(7, user.ppsBal);
		//		preparedStatement.executeUpdate();
			
		    boolean rowInserted = preparedStatement.executeUpdate() > 0;
		    preparedStatement.close();
		//        disconnect();
		    return rowInserted;
    	}
    }
    
    public boolean delete(String userName) throws SQLException {
        String sql = "DELETE FROM student WHERE id = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, userName);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowDeleted;     
    }
    
    public boolean update(User user) throws SQLException {
        String sql = "update user set userName=?, password = ?,firstName = ?, lastName =? , age = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, user.userName);
		preparedStatement.setString(2, user.password);
		preparedStatement.setString(3, user.firstName);
		preparedStatement.setString(3, user.lastName);
		preparedStatement.setInt(3, user.age);
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowUpdated;     
    }
    
    public List<User> getUser(String userName) throws SQLException {
    	User user = null;
    	
        String sql = "SELECT * FROM user WHERE username = ?";
        connect_func();
        List<User> listUser= new ArrayList<User>();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
        	String password = resultSet.getString("password");
            int age = resultSet.getInt("age");
            String firstName = resultSet.getString("firstname");
            String lastName = resultSet.getString("lastname");
            double dollarBal=resultSet.getDouble("dollarBal");
            double ppsBal=resultSet.getDouble("ppsBal");
             
            user = new User(userName, password, firstName, lastName, age, dollarBal, ppsBal);
        }
        
        listUser.add(user);
        return listUser;
    }
    
    public boolean checkUserName(String userName)throws SQLException {
    	connect_func();
    	String sql = "SELECT * FROM user WHERE username = ?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, userName);
    	ResultSet resultSet = preparedStatement.executeQuery();
    	
    	if (resultSet.next()) {//returns false if the username already exists 
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
    public boolean checkUser(String username,String password)throws SQLException {
    	connect_func();
    	String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, username);
    	preparedStatement.setString(2, password);
    	ResultSet resultSet = preparedStatement.executeQuery();
    	
    	if (resultSet.next()) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public boolean checkBalance(String username, double amount)throws SQLException{
    	connect_func();
    	String sql = "SELECT dollarBal FROM user WHERE username = ?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, username);
    	ResultSet resultSet = preparedStatement.executeQuery();
    	if (resultSet.next()) {
    		double userBal = resultSet.getDouble("dollarBal");
    		if(userBal>=amount*.01) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    	else {
    		return false;
    	}
    }
    
    public boolean checkPPSBalance(String username, double amount)throws SQLException{
    	connect_func();
    	String sql = "SELECT ppsBal FROM user WHERE username = ?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, username);
    	ResultSet resultSet = preparedStatement.executeQuery();
    	if (resultSet.next()) {
    		double ppsBal = resultSet.getDouble("ppsBal");
    		if(ppsBal>=amount) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    	else {
    		return false;
    	}
    }
    
    public void tipPPS(String username,String followee, double ppsAmount) throws SQLException {
    	connect_func();
    	String sql = "UPDATE user SET ppsBal=ppsBal- ? WHERE username = ?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setDouble(1, ppsAmount);
    	preparedStatement.setString(2, username);
    	preparedStatement.executeUpdate();
    	
    	String sq2="UPDATE user SET ppsBal=ppsBal+ ? WHERE username =?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sq2);
    	preparedStatement.setDouble(1, ppsAmount);
    	preparedStatement.setString(2, followee);
    	preparedStatement.executeUpdate();
    	
		String sq3 = "insert into transactions(sender, reciever, ppsAmount, transType, ppsPrice) values (?,?,?,?,?)";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sq3);
    	preparedStatement.setString(1, followee);
    	preparedStatement.setString(2, username);
    	preparedStatement.setDouble(3,ppsAmount);
    	preparedStatement.setString(4, "tip");
    	preparedStatement.setDouble(5, .01);
    	preparedStatement.executeUpdate();
    }
    
}
