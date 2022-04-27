import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/rootDAO")
public class rootDAO {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public rootDAO() {

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
    
    public List<User> listBigInfluencers() throws SQLException {
        List<User> listUser = new ArrayList<User>();        
        String sql = "select followeeID from\r\n"
        		+ "(select followeeID, COUNT(followerID) myCount\r\n"
        		+ "from follow\r\n"
        		+ "Group by followeeID) derived_table\r\n"
        		+ "where myCount>7";
        connect_func();  
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

         
        while (resultSet.next()) {
            String username = resultSet.getString("followeeID");

              
            User bigInfluencers = new User(username);
            listUser.add(bigInfluencers);
        }        
        resultSet.close();
        statement.close();  
        disconnect();        
        return listUser;
    }
    
    public List<User> listBigWhales() throws SQLException {
        List<User> listUser = new ArrayList<User>();        
        String sql = "select username from user where ppsBal=(select max(ppsBal) from user)";
        connect_func();  
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

         
        while (resultSet.next()) {
            String username = resultSet.getString("username");

              
            User bigWhales = new User(username);
            listUser.add(bigWhales);
        }        
        resultSet.close();
        statement.close();  
        disconnect();        
        return listUser;
    }
    
    public List<User> listFrequentBuyers() throws SQLException {
        List<User> listUser = new ArrayList<User>();        
        String sql = "select sender from\r\n"
        		+ "(select sender, COUNT(sender) myCount\r\n"
        		+ "from transactions\r\n"
        		+ "where transType=\"buy\"\r\n"
        		+ "Group by sender ) derived_table\r\n"
        		+ "where myCount>10";
        connect_func();  
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

         
        while (resultSet.next()) {
            String username = resultSet.getString("sender");

              
            User frequentBuyers = new User(username);
            listUser.add(frequentBuyers);
        }        
        resultSet.close();
        statement.close();  
        disconnect();        
        return listUser;
    }
    
    public List<User> listGoodFollowers() throws SQLException {
        List<User> listUser = new ArrayList<User>();        
        String sql = "select followerID from\r\n"
        		+ "(select followerID, COUNT(followeeID) myCount\r\n"
        		+ "from follow\r\n"
        		+ "Group by followerID) derived_table\r\n"
        		+ "where myCount=(select count(username)-2 from user)";
        connect_func();  
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

         
        while (resultSet.next()) {
            String username = resultSet.getString("followerID");

              
            User frequentBuyers = new User(username);
            listUser.add(frequentBuyers);
        }        
        resultSet.close();
        statement.close();  
        disconnect();        
        return listUser;
    }

    public List<User> listDiamondHands() throws SQLException {
        List<User> listUser = new ArrayList<User>();        
        String sql = "select distinct sender from transactions where sender not in(\r\n"
        		+ "select distinct reciever from transactions)";
        connect_func();  
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

         
        while (resultSet.next()) {
            String username = resultSet.getString("sender");

              
            User diamondHands = new User(username);
            listUser.add(diamondHands);
        }        
        resultSet.close();
        statement.close();  
        disconnect();        
        return listUser;
    }
    
    public List<User> listPaperHands() throws SQLException {
        List<User> listUser = new ArrayList<User>();        
        String sql = "select username from user where ppsBal=0";
        connect_func();  
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

         
        while (resultSet.next()) {
            String username = resultSet.getString("username");

              
            User paperHands = new User(username);
            listUser.add(paperHands);
        }        
        resultSet.close();
        statement.close();  
        disconnect();        
        return listUser;
    }
    
    public List<User> listGoodInfluencers() throws SQLException {
        List<User> listUser = new ArrayList<User>();        
        String sql = "select sender from\r\n"
        		+ "(select T.sender,count(distinct T.reciever) tip\r\n"
        		+ "from transactions T where T.transType=\"tip\"\r\n"
        		+ "group by T.sender) tipTable\r\n"
        		+ "where tip=3";
        connect_func();  
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

         
        while (resultSet.next()) {
            String username = resultSet.getString("sender");

              
            User goodInfluencers = new User(username);
            listUser.add(goodInfluencers);
        }        
        resultSet.close();
        statement.close();  
        disconnect();        
        return listUser;
    }
    
    public List<User> listCommonFollowers(String followerX, String followerY) throws SQLException {
    	User user = null;
    	
        String sql = "select followerID from follow where followeeID= ? and followerID in(\r\n"
        		+ "select followerID from follow where followeeID= ? )";
        connect_func();
        List<User> listUser= new ArrayList<User>();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, followerX);
        preparedStatement.setString(2, followerX);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
        	String username = resultSet.getString("followerID");

            User commonFollowers = new User(username);
            listUser.add(commonFollowers);
        }
        
        return listUser;
    }
    
    public List<User> listInactiveUsers(int year,int month,int day) throws SQLException {
    	User user = null;
    	String endDate=" 00:00:00.000";
    	int upperDayBound=day+1;
    	String lowerDateBound=year+"-"+month+"-"+day+endDate;
    	String upperDateBound=year+"-"+month+"-"+upperDayBound+endDate;
        String sql = "select username from user where username not in(\r\n"
        		+ "    select distinct reciever from transactions where transTime>= ? and transTime< ? \r\n"
        		+ "    union\r\n"
        		+ "	select distinct sender from transactions where transTime>= ? and transTime< ? )";
        connect_func();
        List<User> listUser= new ArrayList<User>();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, lowerDateBound);
        preparedStatement.setString(2, upperDateBound);
        preparedStatement.setString(3, lowerDateBound);
        preparedStatement.setString(4, upperDateBound);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
        	String username = resultSet.getString("username");

            User inactiveUsers = new User(username);
            listUser.add(inactiveUsers);
        }
        
        return listUser;
    }
    
    public List<User> listUserStats(String username) throws SQLException {
    	User user = null;
    	
        String sql = "select Count(transType) from transactions where sender= ? and transType=\"buy\"\r\n"
        		+ "union\r\n"
        		+ "select Count(transType) from transactions where reciever= ? and transType=\"sell\"\r\n"
        		+ "union\r\n"
        		+ "select Count(transType) from transactions where reciever= ? and transType=\"tip\"\r\n"
        		+ "union\r\n"
        		+ "select Count(followerID) from follow where followerID= ? \r\n"
        		+ "union\r\n"
        		+ "select Count(followeeID) from follow where followeeID= ? ";
        connect_func();
        List<User> listUser= new ArrayList<User>();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, username);
        preparedStatement.setString(3, username);
        preparedStatement.setString(4, username);
        preparedStatement.setString(5, username);
        
        int i=0;
        String[] statArray=new String[5];
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
        	String value = resultSet.getString("Count(transType)");
        	statArray[i]=value;
        	i++;
        }
        
        String buyCount=statArray[0];
        String sellCount=statArray[1];
        String tipCount=statArray[2];
        String followeeCount=statArray[3];
        int followerCount=Integer.parseInt(statArray[4]);
        User userStats = new User(buyCount,sellCount,tipCount,followeeCount,followerCount,"0");
        listUser.add(userStats);
        return listUser;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public boolean isRoot(root ROOT, root attempt) throws SQLException {
    	return (ROOT.username.equals(attempt.username) && ROOT.password.equals(attempt.password));
    }
    
    public void buyPPS(String username, double ppsAmount) throws SQLException {
    	connect_func();
    	String sql = "UPDATE user SET ppsBal=ppsBal+ ? WHERE username = ?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setDouble(1, ppsAmount);
    	preparedStatement.setString(2, username);
    	preparedStatement.executeUpdate();
    	
    	double dollarAmount=ppsAmount*.01;
    	String sq2="UPDATE user SET dollarBal=dollarBal- ? WHERE username =?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sq2);
    	preparedStatement.setDouble(1, dollarAmount);
    	preparedStatement.setString(2, username);
    	preparedStatement.executeUpdate();
    	
		String sq3 = "insert into transactions(sender, reciever, ppsAmount, transType, ppsPrice) values (?,?,?,?,?)";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sq3);
    	preparedStatement.setString(1, username);
    	preparedStatement.setString(2, "root");
    	preparedStatement.setDouble(3,ppsAmount);
    	preparedStatement.setString(4, "buy");
    	preparedStatement.setDouble(5, .01);
    	preparedStatement.executeUpdate();
    }
    
    
    public void sellPPS(String username, double ppsAmount) throws SQLException {
    	connect_func();
    	String sql = "UPDATE user SET ppsBal=ppsBal- ? WHERE username = ?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setDouble(1, ppsAmount);
    	preparedStatement.setString(2, username);
    	preparedStatement.executeUpdate();
    	
    	double dollarAmount=ppsAmount*.01;
    	String sq2="UPDATE user SET dollarBal=dollarBal+ ? WHERE username =?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sq2);
    	preparedStatement.setDouble(1, dollarAmount);
    	preparedStatement.setString(2, username);
    	preparedStatement.executeUpdate();
    	
		String sq3 = "insert into transactions(sender, reciever, ppsAmount, transType, ppsPrice) values (?,?,?,?,?)";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sq3);
    	preparedStatement.setString(1, "root");
    	preparedStatement.setString(2, username);
    	preparedStatement.setDouble(3,ppsAmount);
    	preparedStatement.setString(4, "sell");
    	preparedStatement.setDouble(5, .01);
    	preparedStatement.executeUpdate();
    }
    
}
