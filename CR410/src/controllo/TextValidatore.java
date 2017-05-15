package controllo;

public class TextValidatore {
	
	private String num1;
	private String num2;
	private boolean valido;
	
	public TextValidatore(String numeroTest, String numeroPassi){
		this.num1=numeroTest;
		this.num2=numeroPassi;
	}
	
	public boolean validate(){		
		try{
			Integer.valueOf(this.num1);
			Integer.valueOf(this.num2);
			this.valido = true;
		}catch (Exception e){
			//non sono numeri
			this.valido = false;
		}
		
		return this.valido;
	}

}
