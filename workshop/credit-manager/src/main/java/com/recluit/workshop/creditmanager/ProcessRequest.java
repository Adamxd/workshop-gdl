package com.recluit.workshop.creditmanager;

import java.util.ArrayList;

public class ProcessRequest extends ExampleSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String option;
	private String rfc;
	private ArrayList<Loan> array;

	
	
	
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String execute(){
		
		if(option.equals("01")){
			array = new ArrayList<Loan>();
			try {
				array = FetchFromCB.fetch(rfc, array);
				FetchFromDB.fetch(rfc, array);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("option: "+option);
			if (array.isEmpty()){
				return "rfcNotFound";
			}
			return "success";
		}
		else if(option.equals("02")){
			if (NewLoan.isElegible(rfc)){
				if (FetchFromDB.isCustomer(rfc)){
					return "newLoan";
				}
					return "newCustomer";
			}
			return "NoElegible";
		}
		return "failure";
	}
	
	
	
	public ArrayList<Loan> getArray() {
		return array;
	}
	public void setArray(ArrayList<Loan> array) {
		this.array = array;
	}
	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
}
