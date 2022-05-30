package idbc_package;

import java.sql.Date;

public class customer
{
	int customerid;
	String firstname;
	String lastname;
	String datOfBirth;
	long contact;
	public int getCustomerid() {
		return customerid;
	}
	public int setCustomerid(int customerid) {
		return this.customerid = customerid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getDatOfBirth() {
		return datOfBirth;
	}
	public void setDatOfBirth(String datOfBirth) {
		this.datOfBirth = datOfBirth;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "customer [customerid=" + customerid + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", datOfBirth=" + datOfBirth + ", contact=" + contact + "]";
	}	
}
