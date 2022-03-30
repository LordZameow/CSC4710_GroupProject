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
public class transactionsDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public transactionsDAO() {
		
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
    
    
    public List<transactions> listAllUserTransactions(String username) throws SQLException {
        List<transactions> listTransactions = new ArrayList<transactions>();        
        String sql = "SELECT * FROM transactions WHERE sender = ? OR reciever = ? ORDER by transTime";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int transID=resultSet.getInt("transID");
            String sender=resultSet.getString("sender");
            String reciever=resultSet.getString("reciever");
            double ppsAmount=resultSet.getDouble("ppsAmount");
            String transTime=resultSet.getString("transTime");
            String transType=resultSet.getString("transType");
            double ppsPrice=resultSet.getDouble("ppsPrice");

             
            transactions transaction = new transactions(transID,sender,reciever,ppsAmount,transTime,transType,ppsPrice);
            listTransactions.add(transaction);
        }        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listTransactions;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    
    
   
}
