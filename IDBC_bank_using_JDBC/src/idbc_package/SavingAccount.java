package idbc_package;

public class SavingAccount
{
	float amountSaving;
    int customerid;
    String saving;
    float interest;
    
	public float getAmountSaving() {
		return amountSaving;
	}
	public void setAmountSaving(float amountSaving) {
		this.amountSaving = amountSaving;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public String getSaving() {
		return saving;
	}
	public void setSaving(String saving) {
		this.saving = saving;
	}
	
	
	
	public float getInterest() {
		return interest;
	}
	public void setInterest(float interest) {
		this.interest = interest;
	}
	@Override
	public String toString() {
		return "SavingAccount [amountSaving=" + amountSaving + ", customerid=" + customerid + ", saving=" + saving
				+ ", interest=" + interest + "]";
	}
	
	
	
	
}
