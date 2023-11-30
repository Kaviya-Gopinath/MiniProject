import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.ArrayList;
public class User {
    private String firstname;
    private String lastname;
    private String uuid;
    private byte pinHash[];
    private ArrayList<Account>accounts;
	
	public User(String firstname, String lastname, String pin, Bank theBank) {
		
		this.firstname = firstname;
		this.lastname = lastname;
		
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			this.pinHash=md.digest(pin.getBytes());
		}catch(NoSuchAlgorithmException e) 
		{
			System.err.println("error, caught NoSuchAlgorithmException");
		   e.printStackTrace();
		   System.exit(1);
		}
		
		this.uuid = theBank.getNewUseruuid();
		
	
		this.accounts = new ArrayList<Account>();
	
		System.out.printf("New user %s, %s with id %s created \n", lastname, firstname,this.uuid);
		
		
	}
	
	
	public void addAccount(Account Acct) {
		this.accounts.add(Acct);
		
	}
	
	public String getuuid()
	{
		return this.uuid;
	}
	
	
	public boolean validatepin(String pin) {
		
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(pin.getBytes()),this.pinHash);
		}catch(NoSuchAlgorithmException e) 
		{
			System.err.println("error, caught NoSuchAlgorithmException");
		   e.printStackTrace();
		   System.exit(1);
	}
    return false;
}
	
	
	public String getfirstname() {
		return this.firstname;
	}
	
	
	public void printAccountsSummary() {
		System.out.printf("\n\n%s's Accounts Summary\n", this.firstname);
		for(int a=0;a<this.accounts.size();a++) {
			System.out.printf("%d] %s\n", a+1,this.accounts.get(a).getSummaryLine());
		}
		System.out.println();
	}
	
	
	public int numAccounts() {
		return this.accounts.size();
	}
	
	
	public void printAcctTransHistory(int acctIndex) {
		this.accounts.get(acctIndex).printTransHistory();
	}
	
	
	public double getAcctBalance(int acctIndex) {
		return this.accounts.get(acctIndex).getBalance();
	}
	
	
	public String getAcctUUID(int acctIndex) {
		return this.accounts.get(acctIndex).getuuid();
	}
	
   public void addAcctTransaction(int accIndex, double amount, String memo) {
		this.accounts.get(accIndex).addTransaction(amount,memo);
	}
}
















