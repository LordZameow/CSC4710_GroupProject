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

@WebServlet("/followDAO")
public class followDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public followDAO() {
		
	}
	
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
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public boolean follow(String followee, String follower) throws SQLException{
    	connect_func();
    	String sql = "INSERT INTO follow(followeeID, followerID) VALUES (?,?)";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, followee);
    	preparedStatement.setString(2, follower);
    	
    	return preparedStatement.executeUpdate() > 0;
    }
    
    public boolean unfollow(String followee, String follower) throws SQLException{
    	connect_func();
    	String sql = "DELETE FROM follow WHERE followeeID = ? and followerID = ?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, followee);
    	preparedStatement.setString(2, follower);
    	
    	return preparedStatement.executeUpdate() > 0;
    }
    
    public boolean isFollowing(String followee, String follower) throws SQLException{
    	connect_func();
    	String sql = "SELECT * FROM follow WHERE followeeID = ? and followerID = ?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, followee);
    	preparedStatement.setString(2, follower);
    	ResultSet resultSet = preparedStatement.executeQuery();
    	
    	if (resultSet.next()) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}