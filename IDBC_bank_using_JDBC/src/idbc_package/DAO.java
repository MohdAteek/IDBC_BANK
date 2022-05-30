package idbc_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.joda.time.LocalDate;
import org.joda.time.Years;

public class DAO
{
	Connection con;
	
	public void addCustomerRecord(customer objCustomer) throws ClassNotFoundException, SQLException
	{
		con=Connection_Config.getConnection();
		PreparedStatement prd=con.prepareStatement("insert into customer(customerid,firstname,lastname,dateOfBirth,contact)values(?,?,?,?,?)");
		prd.setInt(1,objCustomer.getCustomerid());
		prd.setString(2,objCustomer.getFirstname());
		prd.setString(3,objCustomer.getLastname());
		
		prd.setString(4, objCustomer.getDatOfBirth());
		//prd.setDate(4,objCustomer.getDatOfBirth());
		
		prd.setLong(5,objCustomer.getContact());
		prd.execute();
		System.out.println();
		System.out.println("----------Customer Data Insert Successfully------------");
		System.out.println();
		prd.close();		
	}
	
	public void savingAccountRecord(SavingAccount objAccount) throws ClassNotFoundException, SQLException
	{
		float interest= 2.5f;
		con=Connection_Config.getConnection();
		PreparedStatement prd=con.prepareStatement("insert into customerAccount(acountype,amount,interest,customerid)values(?,?,?,?)");
		prd.setString(1,objAccount.getSaving());
		prd.setFloat(2,objAccount.getAmountSaving());
		prd.setFloat(3,objAccount.getInterest());
		prd.setInt(4,objAccount.getCustomerid());
		prd.execute();
		System.out.println("_________________Welcome to IDBC Bank ! Your Saving Account Successfully Created_____________________");
		prd.close();
		
	}
	public void payingAccountRecord(PayingAccount account) throws ClassNotFoundException, SQLException 
	{
		con=Connection_Config.getConnection();
		PreparedStatement prd=con.prepareStatement("insert into customerAccount(acountype,amount,customerid)values(?,?,?)");
		prd.setString(1, account.getAccounttype());
		prd.setLong(2,account.getDeposite());
		prd.setInt(3,account.getCustomerid());
		prd.execute();
		System.out.println("__________________Welcome to IDBC Bank ! Your Paying Account Successfully Created____________________");
		prd.close();
		con.close();
	}
	public String agecheck(String datString)
	{
		String dateOFBirth=null;
		org.joda.time.LocalDate startdate=org.joda.time.LocalDate.parse(datString);
		org.joda.time.LocalDate enddate=org.joda.time.LocalDate.now();
		int check= Years.yearsBetween(startdate, enddate).getYears();
		
		try
		{
			if (check>18)
			{
				System.out.println();
				System.out.println("Congratulactions !  You are eligible for create an Account in IDBC Bank");
				System.out.println();
				dateOFBirth=datString;
				
			}
			else {
				throw new ageException();
			}
		}
		catch (Exception e)
		{
			System.exit(0);
		}
		return dateOFBirth;
	}
	
}
