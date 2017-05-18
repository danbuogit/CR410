package controllo;

import java.math.BigInteger;

public class TextValidatore {
	
	private String num1;
	private String num2;
	BigInteger prova;
	Integer provaIndice;
	private boolean valido;
	
	public TextValidatore(String numeroTest, String numeroPassi){
		this.num1=numeroTest;
		this.num2=numeroPassi;
	}
	
	public boolean validate(){		
		try{
			this.prova = new BigInteger(this.num1);
			this.provaIndice = Integer.valueOf(this.num2);
			this.valido = validateLimits();
		}catch (Exception e){
			//non sono numeri
			this.valido = false;
		}
		
		return this.valido;
	}
	
	public boolean validateLimits(){
		
		if(this.prova.compareTo(new BigInteger(this.provaIndice.toString()))>0)
			return true;
		else
			return false;
	}
}
