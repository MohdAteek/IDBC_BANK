package idbc_package;

public class PayingAccount
{
	long deposite;
	int customerid;
	String accounttype;
	
	public long getDeposite() {
		return deposite;
	}
	public void setDeposite(long deposite) {
		this.deposite = deposite;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public String getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	@Override
	public String toString() {
		return "PayingAccount [deposite=" + deposite + ", customerid=" + customerid + ", accounttype=" + accounttype
				+ "]";
	}	
}
