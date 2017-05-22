package primalita;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.Random;

import aritmetica.AritmeticaModulare;

public class MillerRabin{
	private BigInteger numberTest;
	private AritmeticaModulare arit;
	private List<BigInteger> numeriTestati;
	/* [0] = s, esponente del 2
	 * [1] = t, numero (disparo) che moltiplica 2^s
	 * */
	private BigInteger[] numeroScomposto;
	
	public Integer numeroPassi = 0;
	public boolean primo = true;
	public String risultato ="";
	
	public MillerRabin(){
		this.numeriTestati = new ArrayList<BigInteger>();
		this.numeriTestati.add(BigInteger.ZERO);
		this.numeriTestati.add(BigInteger.ONE);
		this.arit = new AritmeticaModulare();
		this.primo = true;
		this.numeroScomposto = new BigInteger[2];
	}
	
	public void makeTest(){
		
		Random ran = new Random();
		
		scomponi();
		if(controllaNumeroPassi()){
			this.risultato = this.risultato.concat("Il numero di passi da eseguire è stato ridotto.\n\n");
		}
		
		Integer bMax = (int) (Math.sqrt(Double.valueOf(this.numberTest.toString())))-2;
		BigInteger t = this.numeroScomposto[1];
		BigInteger s = this.numeroScomposto[0];
		BigInteger menoUno = this.numberTest.add(BigInteger.ONE.negate());
		
		this.risultato = this.risultato.concat("Il numero da testare è n = " + this.numberTest.toString() + ".\n");
		if(pari()){
			this.risultato = this.risultato.concat("Il numero è pari, quindi sicuramente non primo.");
		}
		else{
			this.risultato = this.risultato.concat("Scompongo n-1 = (2^s)*t, dove s = " + s.toString() + " e t = " + t.toString() + ".\n");
			
			int i = 0;
			
			while(this.primo && i<=this.numeroPassi){
				BigInteger b = BigInteger.ZERO; 
				passoBase(b, ran, bMax, menoUno, s, t);
				i++;
			}
			if(primo){
				BigDecimal probabilità = new BigDecimal(1/Math.pow(4, i));
				this.risultato = this.risultato.concat("La probabilità che il numero non sia primo ma passi comunque il test è pari a " + probabilità.toString() + ".\n");
			}
		}
		
	}

	private boolean pari() {
		if(this.numberTest.remainder(new BigInteger("2"))==BigInteger.ZERO){
			return true;
		}
		return false;
	}

	public void passoBase(BigInteger b, Random ran, Integer bMax, BigInteger menoUno, BigInteger s, BigInteger t) {
		
		List<BigInteger> bCongrui = new ArrayList<BigInteger>();
		
		while(giaTestato(b) || b.compareTo(new BigInteger(bMax.toString()))>=0 || b.compareTo(BigInteger.ZERO)<=0){
			//l'oggetto ran si occupa di rendere la selezione di b casuale
			b = BigInteger.valueOf(ran.nextInt(bMax));
		}
		
		this.risultato = this.risultato.concat("\nInizio il test con un b casuale, 0<b<n. In questo caso, b = " +b.toString()+ ".\n");
		this.numeriTestati.add(b);
			
		BigInteger mcd = arit.MCD(numberTest, b);
		
		this.risultato = this.risultato.concat("MCD(" + this.numberTest.toString() + "," + b.toString() + ") = " + mcd.toString() + "\n");
		//se MCD(b,n)>1, il numero non è primo
		if(mcd.compareTo(BigInteger.ONE)==1){
			this.risultato = this.risultato.concat("Essendo MCD>1, il numero "+this.numberTest.toString()+" non è primo.");
			this.primo = false;
		}
		else{
			//se b^t è congruo a + o - 1, il numero è primo
			BigInteger congruo = b.modPow( t , this.numberTest);
				
			this.risultato = this.risultato.concat("Essendo MCD=1, controllo che b^t (mod n) sia congruo a 1 o -1.\n");
			this.risultato = this.risultato.concat("b^t (mod n) = " + congruo.toString() + "\n");
				
			if(congruo.compareTo(BigInteger.ONE)==0 || congruo.compareTo(menoUno)==0){
				this.risultato = this.risultato.concat("Essendo uguale ad uno o meno uno, il numero è pseudoprimo.\n");
				this.primo = true;
			}
			else{
				//altrimenti finché b^(2^r, con r massimo pari a s-1) non è congruo a -1 n potrebbe non essere primo.
				Integer r=0;
				if(s.compareTo(BigInteger.ZERO)!=0)
				{
					this.risultato = this.risultato.concat("Essendo diverso da +/- 1, controllo ogni b^(2(^r)*t),\nal fine di verificare che tutti siano congruo a -1,");
					this.risultato = this.risultato.concat("dove 0<r<s-1.\n"); 
					while(s.compareTo(new BigInteger(r.toString()))!=0)
					{
						this.risultato = this.risultato.concat("r attuale = " +r.toString() + "\n");
						//(2^r)*t
						BigInteger esp = (new BigInteger("2").pow(r)).multiply(t);
						this.risultato = this.risultato.concat("esponente = (2^r)*t="+esp.toString()+"\n");
						congruo = b.modPow(esp, this.numberTest);
						this.risultato = this.risultato.concat("b^esp mod n = " + congruo.toString() + "\n");
						
						bCongrui.add(congruo);
						r++;
					}
				}

				if(bCongrui.contains(menoUno)){
					this.risultato = this.risultato.concat("\nEsiste almeno un risultato congruo a -1,\nperciò il numero è pseudoprimo forte.\n");
					this.primo = true;
				}
				else{
					this.risultato = this.risultato.concat("\nNon esiste alcun risultato congruo a -1,\nperciò il numero non è primo.\n");
					this.primo = false;
				}
			}
		}
	}

	private boolean giaTestato(BigInteger b) {
		if(this.numeriTestati.contains(b))
			return true;
		return false;
	}

	public void nuovoNumeroDiTest(String numero){
		this.numberTest= new BigInteger(numero);
	}
	
	public String getRisultatoTestuale(){
		return this.risultato;
	}
	
	public void setNumeroPassi(Integer n){
		
		this.numeroPassi = n;
	}
	
	private boolean controllaNumeroPassi() {
		
		boolean cambiato = false;
		
		if(this.numberTest!=null){
			Integer max = ((int) (Math.sqrt(Double.valueOf(this.numberTest.toString()))))-2;
			if(this.numeroPassi>max){
				this.numeroPassi = max;
				cambiato = true;
			}
			
			if(this.numeroPassi==0){
				this.numeroPassi=1;
				cambiato = true;
			}
		}
		
		return cambiato;
	}
	
	private void scomponi(){
		//devo scomporre n-1=2(^s)*t
		BigInteger s= BigInteger.ZERO;
		BigInteger scomposto = this.numberTest.subtract(BigInteger.ONE);
		//finché il numero è pari
		while(scomposto.remainder(BigInteger.ONE.add(BigInteger.ONE)) == BigInteger.ZERO){
			scomposto = scomposto.divide(new BigInteger("2"));
			s = s.add(BigInteger.ONE);
		}	
		this.numeroScomposto[0]=s;
		this.numeroScomposto[1]=scomposto;	
	}
}
