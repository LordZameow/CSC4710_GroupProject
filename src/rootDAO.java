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
