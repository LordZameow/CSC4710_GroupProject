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
  			          + "useSSL=false&user=john&password=pass1234");
            System.out.println(connect);
        }
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
            String firstName = resultSet.getString("firstname");
            String lastName = resultSet.getString("lastname");

             
            User user = new User(userName,password, firstName, lastName, age);
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
    	
    	//Quary to see if the username is already existing
    	String sql = "SELECT * FROM user WHERE username = ?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, user.userName);
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	if (resultSet.next()) {//returns false if the username already exists 
    		return false;
    	}
    	else {
			String sq2 = "insert into  User(userName, password, firstName, lastName, age) values (?, ?, ?, ?,?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sq2);
			preparedStatement.setString(1, user.userName);
			preparedStatement.setString(2, user.password);
			preparedStatement.setString(3, user.firstName);
			preparedStatement.setString(3, user.lastName);
			preparedStatement.setInt(3, user.age);
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
    
    public User getUser(String userName) throws SQLException {
    	User user = null;
        String sql = "SELECT * FROM student WHERE username = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, userName);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	String password = resultSet.getString("password");
            int age = resultSet.getInt("age");
            String firstName = resultSet.getString("firstname");
            String lastName = resultSet.getString("lastname");
             
            user = new User(userName, password, firstName, lastName, age);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    public boolean checkUserName(String userName)throws SQLException {
    	connect_func();
    	String sql = "SELECT * FROM user WHERE username = ?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, userName);
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	if (resultSet.next()) {//returns false if the username already exists 
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
    public boolean checkUser(String username,String password)throws SQLException {
    	connect_func();
    	String sql = "SELECT * FROM user WHERE username =? AND password = ?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, username);
    	preparedStatement.setString(2, password);
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	if (resultSet.next()) {//returns false if the username already exists 
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
}
