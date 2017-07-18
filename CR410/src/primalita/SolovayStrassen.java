package primalita;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aritmetica.AritmeticaModulare;

public class SolovayStrassen {
	
	private BigInteger numeroTest;
	private List<BigInteger> numeriTestati;
	private AritmeticaModulare aritm;
	private int numeroDiPassi;
	private boolean primo;
	
	private String risultato;
	
	public SolovayStrassen(int nPassi){
		/*maggiore è il numero di passi, maggiori
		 * maggiori saranno le possibilità che il numero
		 * sia verificato primo o meno.*/
		this.numeroDiPassi = nPassi;
		/*l'oggeto aritm permette operazioni modulari
		 * ed altre operazioni di base, nonché la 
		 * specifica di simboli di Legendre.*/
		this.aritm = new AritmeticaModulare();
		/*la seguente lista contiene i numeri b già testati, 
		*in modo da non ripetere inutilmente il procedimento.*/
		this.numeriTestati = new ArrayList<BigInteger>();
		this.numeriTestati.add(BigInteger.ZERO);
	}
	
	public SolovayStrassen(){
		/*se nulla è specificato, di default verranno
		 * compiuti 10 passi per il test*/
		new SolovayStrassen(10);
	}
	
	public void makeTest(){
		
		this.primo = true;
		this.risultato="";
		int i = 0;
		
		//non possono essere fatti più passaggi del numero di test!
		if(this.numeroDiPassi >= this.numeroTest.intValue()){
			this.numeroDiPassi = this.numeroTest.intValue() - 1;
		}
		
		//compio tanti test quanti scelti
		while(i<this.numeroDiPassi && primo){
			if(!passoBase()){
				this.primo = false;
			}
			i++;
		}
		
		/*se risulta ancora primo, calcolo la probabilita che non lo sia*/
		if(primo){
			this.risultato = this.risultato.concat("\n");
			try{
			BigDecimal probabilita = new BigDecimal((1/Math.pow(2,this.numeroDiPassi)));
			this.risultato = this.risultato.concat("La probabilità che il numero non sia primo è pari a "+probabilita.toPlainString()+".\n");
			}
			catch(Exception e){
				this.risultato = this.risultato.concat("La probabilità che il numero non sia primo è infinitamente piccola.");
			}
		}
	}
	
	private boolean passoBase(){
		//passo base da ripetere per il numero di passi previsto
		
		Random ran = new Random();
		BigInteger b = BigInteger.ZERO;
		
		/*b è il numero casuale compreso tra 0 ed il numero di test,
		 * ma non deve già essere testato*/
		while(giaTestato(b) || b.compareTo(numeroTest)>=0 || b.compareTo(BigInteger.ZERO)<=0){
			//l'oggetto ran si occupa di rendere la selezione di b casuale
			b = BigInteger.valueOf(ran.nextInt(this.numeroTest.intValue()));
		}
		this.numeriTestati.add(b);
		this.risultato = this.risultato.concat("Nuovo test con b = " + b.toString() + ".\n");
		
		BigInteger mcd = this.aritm.MCD(this.numeroTest, b);
		this.risultato = this.risultato.concat("Il MCD(" + this.numeroTest.toString() + " , " +b.toString()+ ") " );
		/*se il MCD risulta essere maggiore di 1...*/
		if(mcd.compareTo(BigInteger.ONE)==1){
			/*il numero non è primo*/
			this.risultato = this.risultato.concat(" > 1. \n");
			this.risultato = this.risultato.concat("Il numero scelto non è primo.\n");
			return false;
		}
		/*altrimenti, risultando essere uguale ad 1...*/
		else{
			this.risultato = this.risultato.concat(" = 1.\n");
			/*calcolo il simbolo di Legendre di (b/n) e l'esponente di b uguale a (n-1)/2*/
			this.risultato = this.risultato.concat("Controllo quindi che b^((n-1)/2) sia congruo \nal simbolo di Legendre (b n) in modulo n.\n");
			BigInteger simboloDiLegendre = new BigInteger(this.aritm.simboloDiLegendre(b, this.numeroTest).toString());
			this.risultato = this.risultato.concat("Il simbolo di Legendre = " + simboloDiLegendre.toString() + ";\n");
			/*notare che lo shift a destra è uguale a dividere per due*/
			BigInteger esponenteDiB = (this.numeroTest.subtract(BigInteger.ONE)).shiftRight(1);
			
			/*quindi controllo che b^esponenteDiB sia congruo al simboloDiLegendre, mod n*/
			BigInteger congruo = this.aritm.congruo(b.modPow(esponenteDiB, this.numeroTest), this.numeroTest);
			this.risultato = this.risultato.concat("b^((n-1)/2) mod " + this.numeroTest.toString() + " è congruo a " + congruo.toString() + ".\n");
			if(!(congruo.equals(simboloDiLegendre) || congruo.compareTo(this.numeroTest.subtract(BigInteger.ONE))==0)){
				/*se non sono uguali, il numero non è primo*/
				this.risultato = this.risultato.concat("Che non è uguale al simbolo di Legendre.\n");
				this.risultato = this.risultato.concat("Il numero non è primo.\n");
				return false;
			}
			/*se i precedenti test sono falliti, allora, il numero è probabilmente primo*/
			this.risultato = this.risultato.concat("Che è uguale al simbolo di Legendre.\n");
			this.risultato = this.risultato.concat("Il numero potrebbe essere primo. \n");
			this.risultato = this.risultato.concat("\n");
			return true;
		}
	}

	private boolean giaTestato(BigInteger b) {
		if(this.numeriTestati.contains(b))
			return true;
		return false;
	}

	public BigInteger getNumeroTest() {
		return numeroTest;
	}

	public void setNumeroTest(BigInteger numeroTest) {
		this.numeroTest = numeroTest;
	}

	public int getNumeroDiPassi() {
		return numeroDiPassi;
	}

	public void setNumeroDiPassi(int numeroDiPassi) {
		this.numeroDiPassi = numeroDiPassi;
	}
	
	public boolean getPrimalita(){
		return this.primo;
	}
	
	public String getRisultatoTestuale(){
		return this.risultato;
	}
	
	
	
}
