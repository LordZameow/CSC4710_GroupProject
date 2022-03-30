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

@WebServlet("/commentDAO")
	public class commentDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public commentDAO() {
		
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
  			          + "useSSL=false&user=john&password=pass1234");
            System.out.println(connect);
        }
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public List<tweets> listAllTweets() throws SQLException {
        List<tweets> listTweets = new ArrayList<tweets>();        
        String sql = "SELECT * FROM comments";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int tweetID = resultSet.getInt("tweetID");
            String content = resultSet.getString("content");
            String author = resultSet.getString("author");
            String transTime = resultSet.getString("transTime");
            
             
            tweets tweet = new tweets(tweetID,content,author,transTime);
            listTweets.add(tweet);
        }        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listTweets;
    }
    
    public boolean comment(int tweetID, String content, String commenter) throws SQLException{
    	String sql = "INSERT INTO comment WHERE tweetID = ?";
    	connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, tweetID);
        preparedStatement.setString(2, content);
        preparedStatement.setString(3, commenter);
        
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//      disconnect();
        return rowInserted;
    }
    
}
