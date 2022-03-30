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

@WebServlet("/tweetsDAO")
public class tweetsDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public tweetsDAO() {
		
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
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void initialize() throws SQLException{
    	
    	String delData="DELETE from tweets";
    	connect_func();      
    	preparedStatement = (PreparedStatement) connect.prepareStatement(delData);
    	statement=connect.createStatement();
    	statement.executeUpdate(delData);
         
    	String insert1 = "insert into  tweets(content, author) values "
    			+ "(\"It is with immeasurable grief that we confirm the passing of Chadwick Boseman. Chadwick was diagnosed with stage III colon cancer in 2016, and battled with it these last 4 years as it progressed to stage IV\",\"nighthawk@verizon.net\")";
    	String insert2 = "insert into  tweets(content, author) values "
    			+ "(\"No one is born hating another person because of the color of his skin or his background or his religion\",\"lydia@outlook.com\")";
    	String insert3 = "insert into  tweets(content, author) values "
    			+ "(\"It's a new day in America\",\"sabren@comcast.net\")";
    	String insert4 = "insert into  tweets(content, author) values "
    			+ "(\"Kobe was a legend on the court and just getting started in what would have been just as meaningful a second act. To lose Gianna is even more heartbreaking to us as parents. Michelle and I send love and prayers to Vanessa and the entire Bryant family on an unthinkable day\",\"bulletin@me.com\")";
    	String insert5 = "insert into  tweets(content, author) values "
    			+ "(\"Congratulations to the Astronauts that left Earth today. Good choice\",\"mhanoh@outlook.com\")";
    	String insert6 = "insert into  tweets(content, author) values "
    			+ "(\"hello literally everyone\",\"smpeters@comcast.net\")";
    	String insert7 = "insert into  tweets(content, author) values "
    			+ "(\" 	teamwork makes the dream work\",\"mcraigw@live.com\")";
    	String insert8 = "insert into  tweets(content, author) values "
    			+ "(\"America, I'm honored that you have chosen me to lead our great country.\",\"wenzlaff@comcast.net\")";
    	String insert9 = "insert into  tweets(content, author) values "
    			+ "(\"teamwork makes the dream work\",\"kjohnson@outlook.com\")";
    	String insert10 = "insert into  tweets(content, author) values "
    			+ "(\"Happy Birthday to me\",\"kjohnson@outlook.com\")";
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
    
    public List<tweets> listAllTweets() throws SQLException {
        List<tweets> listTweets = new ArrayList<tweets>();        
        String sql = "SELECT * FROM tweets";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int tweetID=resultSet.getInt("tweetID");
            String content = resultSet.getString("content");
            String author = resultSet.getString("author");
            String transTime=resultSet.getString("transTime");
            int likeCounter=getLikeCounter(tweetID);
             
            tweets tweet = new tweets(tweetID,content,author,likeCounter,transTime);
            listTweets.add(tweet);
        }        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listTweets;
    }
    
    
    
    public boolean post(tweets tweet) throws SQLException {
    	connect_func();         
    	
		String sq2 = "INSERT INTO tweets(content,author) values (?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sq2);
		preparedStatement.setString(1, tweet.content);
		preparedStatement.setString(2, tweet.author);
		preparedStatement.executeUpdate();
		return true;
    	
    }
    
    public boolean delete(int tweetID) throws SQLException {
        String sql = "DELETE FROM tweets WHERE tweetID = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, tweetID);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowDeleted;     
    }
    
    public boolean comment(int tweetID, String comment) throws SQLException{
    	String sql = "INSERT INTO comment WHERE tweetID = ?";
    	connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, tweetID);
        
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//      disconnect();
        return rowInserted;
    }
    
    
    public boolean like(String username,int tweetID) throws SQLException{
    	String sql = "INSERT IGNORE INTO likes(userID,tweetID) values(?,?)";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setInt(2, tweetID);
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//      disconnect();
        return rowInserted;
    }

    public boolean dislike(String username,int tweetID) throws SQLException{
    	String sql = "DELETE from likes where userID = ? AND tweetID = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setInt(2, tweetID);
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//      disconnect();
        return rowDeleted;
    }
    
    public int getLikeCounter(int tweetID)throws SQLException{
    	String sql = "SELECT count(*) FROM likes WHERE tweetID = ?";
    	connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, tweetID);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
        	return resultSet.getInt(1);
        }
        else {
        	return -1;
        }
    }
    
    
}
