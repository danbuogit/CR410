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

	public boolean primo = true;
	public String risultato ="";
	
	public MillerRabin(){
		this.numeriTestati = new ArrayList<BigInteger>();
		this.numeriTestati.add(BigInteger.ZERO);
		this.arit = new AritmeticaModulare();
		this.primo = true;
		this.numeroScomposto = new BigInteger[2];
	}
	
	public void makeTest() {
		Random ran = new Random();
		BigInteger b = BigInteger.ZERO;
		
		scomponi();
		
		Integer bMax = (int) (Math.sqrt(Double.valueOf(this.numberTest.toString())));
		BigInteger t = this.numeroScomposto[1];
		BigInteger s = this.numeroScomposto[0];
		BigInteger menoUno = this.numberTest.add(BigInteger.ONE.negate());
		
		this.risultato = this.risultato.concat("Il numero da testare è n = " + this.numberTest.toString() + ".\n");
		this.risultato = this.risultato.concat("Sapendo che n-1 = (2^s)*t, dove s = " + s.toString() + " e t = " + t.toString() + ".\n");
		
		while(giaTestato(b) || b.compareTo(new BigInteger(bMax.toString()))>=0 || b.compareTo(BigInteger.ZERO)<=0){
			//l'oggetto ran si occupa di rendere la selezione di b casuale
			b = BigInteger.valueOf(ran.nextInt(bMax));
			//voglio evitare che si utilizzi 1 come numero casuale
			if(b.compareTo(BigInteger.ONE)==0)
				b=b.add(BigInteger.ONE);
			
			this.risultato = this.risultato.concat("\nInizio il test con un b casuale, 0<b<n. In questo caso, b = " +b.toString()+ ".\n");
			
		}
		
		this.numeriTestati.add(b);
		
		//se MCD(b,n)>1, il numero non è primo
		BigInteger mcd = arit.MCD(numberTest, b);
		if(mcd.compareTo(BigInteger.ONE)==1){
			this.risultato = this.risultato.concat(" Essendo MCD>1, il numero "+this.numberTest.toString()+" non è primo.");
			this.primo = false;
		}
		//altrimenti...
		else{
			//se b^t è congruo a + o - 1, il numero è primo
			BigInteger congruo = b.modPow( t , this.numberTest);
			
			this.risultato = this.risultato.concat("Essendo MCD=1, controllo che b^t (mod n) sia congruo a 1 o -1.\n");
			this.risultato = this.risultato.concat("b^t (mod n) = " + congruo.toString() + "\n");
			
			if(congruo.compareTo(BigInteger.ONE)==0 || congruo.compareTo(menoUno)==0){
				this.risultato = this.risultato.concat("Essendo uguale ad uno o meno uno, il numero è primo con una probabilità di un tre quarti.");
				this.primo = true;
			}
			else{
				//altrimenti finché b^(2^r, con r massimo pari a s-1) non è congruo a -1 n potrebbe non essere primo.
				Integer r=1;
				if(s.compareTo(BigInteger.ZERO)!=0)
				{
					//s= s.add(BigInteger.ONE);
					this.risultato = this.risultato.concat("Essendo diverso da +/- 1, controllo ogni b^(2(^r)*t) finché non trovo che almeno uno di essi è congruo a -1.\n");
					this.risultato = this.risultato.concat("Inoltre, r è compreso tra 1 ed s-1.\n"); 
					while(s.compareTo(new BigInteger(r.toString()))!=0)
					{
						this.risultato = this.risultato.concat("r attuale = " +r.toString() + ".\n");
						//(2^r)*t
						BigInteger esp = (new BigInteger("2").pow(r)).multiply(t);
						this.risultato = this.risultato.concat("esponente = (2^r)*t="+esp.toString()+".\n");
						congruo = b.modPow(esp, this.numberTest);
						this.risultato = this.risultato.concat("b^esp mod n = " + congruo.toString() + ".\n");
						
						if(congruo.compareTo(menoUno)==0){
							
							//il numero è congruo a -1, quindi è primo
							BigDecimal probabilità = new BigDecimal(1/Math.pow(4, r));
							this.risultato = this.risultato.concat("Essendo congruo a -1, il numero non è primo con una probabilità dello " +probabilità.toPlainString());
							this.primo = true;
							return;
						}
						r++;
					}
				}
				if(s.compareTo(new BigInteger(r.toString()))==0){
					this.risultato = this.risultato.concat(" Tutti i casi sono stati testati, il numero non è primo.");
					this.primo=false;
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
