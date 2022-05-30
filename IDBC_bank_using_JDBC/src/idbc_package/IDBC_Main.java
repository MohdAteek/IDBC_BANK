package idbc_package;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class IDBC_Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		IDBC_Operation idbc_Operation=new IDBC_Operation();
		System.out.println();
		System.out.println("-------------------------- IDBC BANK ---------------------------");
		System.out.println();
		System.out.println("1 for new Customer");
		System.out.println("2 for existing customer");
		System.out.println("3 for Exit");
		System.out.println();
		System.out.print("Enter Your Choice=");
		
		Scanner scanner=new Scanner(System.in);
		int choice =scanner.nextInt();
		
		switch(choice)
		{
			case 1:
				
				System.out.print("Enter Your Age (yy//MM/dd)=");
				String age=scanner.next();
				DAO dao=new DAO();
				String dateofBirth= dao.agecheck(age);
				//System.out.print(dateofBirth);
				idbc_Operation.newCustomer(dateofBirth);
				break;
				
			case 2:
				System.out.println("------------Please Choose suitable option-------------");
				System.out.println();
				System.out.println("1 for deposite");
				System.out.println("2 for withdraw");
				System.out.println("3 for check balance ");
				System.out.println("4 for checking Log Table");
				System.out.println("5 for check Interest Amount");
				System.out.println("-------Press : ");
				int choice1=scanner.nextInt();
				switch(choice1)
				{
				case 1:
					idbc_Operation.deposite();
					break;
				case 2:
					idbc_Operation.withdraw();
					break;
				case 3:
					idbc_Operation.checkBalance();
					break;
				case 4 :
					idbc_Operation.checkLogTable();
					break;
				case 5:
					idbc_Operation.checkInterestAmount();
					break;
				default:
					System.out.println("Invalid Selection");
					return;
				}
				break;
				
			case 3:
				System.exit(0);
				break;
		}	
	}
}
