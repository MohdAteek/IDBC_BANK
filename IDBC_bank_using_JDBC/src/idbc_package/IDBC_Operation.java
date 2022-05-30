package idbc_package;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import com.mysql.cj.protocol.Resultset;

public class IDBC_Operation 
{
	static int customerid=0;
	Connection con;
	public void  newCustomer(String dateOfBirth) throws ClassNotFoundException, SQLException
	{
		Scanner scanner=new Scanner(System.in);
		customer customer=new customer();
		
		System.out.print("Enter Customer Id=");
		 int cid=scanner.nextInt();
		customerid=customer.setCustomerid(cid);
		
		System.out.print("Enter your First Name=");
		String nameString=scanner.next();
	 	customer.setFirstname(nameString);
		
		System.out.print("Enter Your Last Name=");
		String lname=scanner.next();
		customer.setLastname(lname);
		
		//System.out.print("Enter Your Date Of Birth=");
		//String dateString=scanner.next();
		customer.setDatOfBirth(dateOfBirth);
		
		//customer.setDatOfBirth(Date.valueOf(dateString));
		
		
		System.out.print("Enter Your Contact Number");
		Long contactN=scanner.nextLong();
		customer.setContact(contactN);
		
		DAO dao=new DAO();
		dao.addCustomerRecord(customer);
		
		System.out.println("-----------Create Your Account--------------");
		System.out.println();
		System.out.println("Type save for Saving Account");
		System.out.println("Type pay for Payment Account");
		System.out.println();
		System.out.print("Enter Your Choice=");
		System.out.println();
		
		String choice =scanner.next();
		
		switch(choice)
		{
			case "save":
				IDBC_Operation idbc_Operation=new IDBC_Operation();
				idbc_Operation.savingAccount("saving");
				break;
				
			case "pay":
				IDBC_Operation idbc_Operation2=new IDBC_Operation();
				idbc_Operation2.paymentAccount("paying");
				break;
		}
		
		
	}
	public void  savingAccount(String saving) throws ClassNotFoundException, SQLException
	{
		SavingAccount account=new SavingAccount();
		Scanner scanner=new Scanner(System.in);
		float interest=2.5f;
		System.out.print("Enter Amount : ");
		System.out.println();
		long amount=scanner.nextLong();
		account.setAmountSaving(amount);
		account.setCustomerid(customerid);
		account.setSaving(saving);
		account.setInterest(interest);
		
		DAO dao=new DAO();
		dao.savingAccountRecord(account);
		
	}
	public void paymentAccount(String paying) throws ClassNotFoundException, SQLException
	{
		PayingAccount account=new PayingAccount();
		PayingAccount payingAccount=new PayingAccount();
		Scanner scanner=new Scanner(System.in);
		
			System.out.print("Enter Amount =");
			long amount=scanner.nextLong();
			account.setDeposite(amount);
			account.setCustomerid(customerid);
			account.setAccounttype(paying);
			
			DAO dao=new DAO();
			dao.payingAccountRecord(account);
		
	}
	
	/*public void depositeInterest() throws ClassNotFoundException, SQLException
	{
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter the Account Number=");
		long account=scanner.nextLong();
		System.out.print("Enter Amount to be deposite=");
		Double amount=scanner.nextDouble();
		con=Connection_Config.getConnection();
		PreparedStatement pst=con.prepareStatement("update customerAccount set amount=(amount+?) where accountnumber=?");
		pst.setDouble(1, amount*2.5f);
		pst.setLong(2, account);
		pst.execute();
		updateLogTable(amount,"Deposite",account);
		System.out.println("Money Deposited");
	}*/
	
	public void deposite() throws ClassNotFoundException, SQLException
	{
		
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter the Account Number=");
		long account=scanner.nextLong();
		System.out.print("Enter Amount to be deposite=");
		Double amount=scanner.nextDouble();
		con=Connection_Config.getConnection();
		PreparedStatement pst=con.prepareStatement("update customerAccount set amount=(amount+?) where accountnumber=?");
		pst.setDouble(1, amount);
		pst.setLong(2, account);
		pst.execute();
		updateLogTable(amount,"Deposite",account);
		System.out.println("Money Deposited");
	
	}
	
	private void updateLogTable(Double amount, String string, long account) throws ClassNotFoundException, SQLException
	{
		// TODO Auto-generated method stub
		con=Connection_Config.getConnection();
		PreparedStatement pst=con.prepareStatement("insert into logtable(accountnumber,transactiontype,amount)values(?,?,?)");
		pst.setLong(1, account);
		pst.setString(2, string);
		pst.setDouble(3, amount);
		pst.execute();
	}
	public void withdraw() throws ClassNotFoundException, SQLException
	{
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter the Account Number=");
		long account=scanner.nextLong();
		System.out.print("Enter Amount to be withdraw=");
		Double amount=scanner.nextDouble();
		con=Connection_Config.getConnection();
		PreparedStatement prd=con.prepareStatement("Select amount from customerAccount where accountnumber=?");
		prd.setLong(1, account);
		ResultSet rs=prd.executeQuery();
		rs.next();
		{
			try
			{
				if (rs.getDouble(1)<amount)
				{
					throw new InsufficientfundsException();
					
				}
			}
			catch (Exception e)
			{
				System.exit(0);
			}
			
			PreparedStatement prd1=con.prepareStatement("update customerAccount set amount=(amount-?) where accountnumber=?");
			prd1.setDouble(1,amount);
			prd1.setLong(2, account);
			prd1.execute();
			
			System.out.println("Money Withdrawn");
			updateLogTable(amount, "Debit", account);
		}
		
	}
	public void checkBalance() throws ClassNotFoundException, SQLException
	{
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter Your Account Number to check the Balance=");
		long accountNumber=scanner.nextLong();
		con=Connection_Config.getConnection();
		PreparedStatement preparedStatement=con.prepareStatement("select amount from customerAccount where accountnumber=? ");
		preparedStatement.setLong(1, accountNumber);
		ResultSet rs=preparedStatement.executeQuery();
		while(rs.next())
		{
			System.out.print("Your Current Balance is : "+rs.getLong(1));
		}
	}
	public void checkLogTable() throws ClassNotFoundException, SQLException
	{
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter Your Account Number to check the Logtable=");
		long accountNumber=scanner.nextLong();
		con=Connection_Config.getConnection();
		PreparedStatement prd=con.prepareStatement("select * from logtable where accountnumber=? ");
		prd.setLong(1, accountNumber);
		ResultSet rs=prd.executeQuery();
		System.out.format("%-15s %-15s %-15s %-20s %-20s\n", "Log ID", "Transaction Date", "Account Number","Transaction Type","Amount");
		System.out.println();
		while(rs.next())
		{
			System.out.format("%-15s %-15s %-15s %-20s %-20s \n",rs.getInt(1),rs.getDate(2),rs.getBigDecimal(3)
					,rs.getString(4),rs.getLong(5));
		}
		
		
	}
	public void checkInterestAmount()
	{
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter Begining Date = ");
		String beginingDate=scanner.next();
		System.out.print("Enter Ending Date = ");
		String endDate=scanner.next();
		
		org.joda.time.LocalDate bdate=org.joda.time.LocalDate.parse(beginingDate);
		org.joda.time.LocalDate enddate=org.joda.time.LocalDate.parse(endDate);
		int check= Years.yearsBetween(bdate, enddate).getYears();
		
		System.out.println("Enter type of account ! choose between Saving and Paying");
		String choice=scanner.next();
		
		switch(choice)
		{
			case "saving":
				System.out.print("Enter The Amount = ");
				float amount=scanner.nextFloat();
				float amountWithInterest=amount+(amount*check*2.5f)/100;
				System.out.println("Amount After Adding the Interest = "+amountWithInterest);
				break;
			
			case "paying":
				System.out.print("Enter The Amount = ");
				float payingamount=scanner.nextFloat();
				System.out.println("Amount after adding the interest = "+payingamount);
				break;
		}
		
	}
}
