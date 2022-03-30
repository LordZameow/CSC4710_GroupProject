

public class root {
    protected String username;
    protected String password;
    protected double balance;
    protected double ppsPrice;
 
    public root() {
    	this.username="root";
    	this.password="pass1234";
    	this.balance=1000000000;
    	this.ppsPrice=.01;
    }
 
    public root(String username, String password) {
    	this.username=username;
    	this.password=password;
    	balance=1000000000;
    	ppsPrice=.01;
    }
    
    public root(String username, String password, double balance,double ppsPrice) {
        this.username=username;
        this.password=password;
        this.balance=balance;
        this.ppsPrice=ppsPrice;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username=username;
    }
 
    public String setPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public double getBalance() {
        return balance;
    }
 
    public void setBalance(double balance) {
        this.balance=balance;
    }
 
    public double getPPSPrice() {
        return ppsPrice;
    }
 
    public void setStatus(int ppsPrice) {
        this.ppsPrice=ppsPrice;
    }
}