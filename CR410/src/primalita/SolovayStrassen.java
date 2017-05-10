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
		this.primo = true;
		
		//TODO: impostare il numero di test dall'interfaccia
	}
	
	public SolovayStrassen(){
		/*se nulla è specificato, di default verranno
		 * compiuti 10 passi per il test*/
		new SolovayStrassen(10);
	}
	
	public void makeTest(){
		
		int i = 0;
		//compio tanti test quanti scelti
		while(i<this.numeroDiPassi && primo){
			if(!passoBase()){
				this.primo = false;
			}
			i++;
		}
		
		/*se risulta ancora primo, calcolo la probabilita che non lo sia*/
		if(primo){
			@SuppressWarnings("unused")
			BigDecimal probabilita = new BigDecimal(1/Math.pow(2,this.numeroDiPassi));
		}
	}
	
	private boolean passoBase(){
		//passo base da ripetere per il numero di passi previsto
		
		Random ran = new Random();
		BigInteger b = BigInteger.ZERO;
		
		/*b è il numero casuale compreso tra 0 ed il numero di test,
		 * ma non deve già essere testato*/
		while(giaTestato(b)){
			//l'oggetto ran si occupa di rendere la selezione di b casuale
			b = BigInteger.valueOf(ran.nextInt(this.numeroTest.intValue()-1));
		}
		this.numeriTestati.add(b);
		
		BigInteger mcd = this.aritm.MCD(this.numeroTest, b);
		
		/*se il MCD risulta essere maggiore di 1...*/
		if(mcd.compareTo(BigInteger.ONE)==1){
			/*il numero non è primo*/
			return false;
		}
		/*altrimenti, risultando essere uguale ad 1...*/
		else{
			/*calcolo il simbolo di Legendre di (b/n) e l'esponente di b uguale a (n-1)/2*/
			BigInteger simboloDiLegendre = new BigInteger(this.aritm.simboloDiLegendre(b, this.numeroTest).toString());
			/*notare che lo shift a destra è uguale a dividere per due*/
			BigInteger esponenteDiB = (this.numeroTest.subtract(BigInteger.ONE)).shiftRight(1);
			
			/*quindi controllo che b^esponenteDiB sia congruo al simboloDiLegendre, mod n*/
			if(this.aritm.congruo(b.modPow(esponenteDiB, this.numeroTest), this.numeroTest)== simboloDiLegendre){
				/*se sono uguali, il numero non è primo*/
				return false;
			}
			/*se i precedenti test sono falliti, allora, il numero è probabilmente primo*/
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
	
	
	
}
