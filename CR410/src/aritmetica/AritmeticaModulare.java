package aritmetica;

import java.math.*;

public class AritmeticaModulare {
	
	public AritmeticaModulare(){}
	
	/**
	 * @param a
	 * @param modulo
	 * @return ritorna il numero congruo ad a mod(modulo)
	 */
	public BigInteger congruo(BigInteger a, BigInteger modulo){
		while(a.compareTo(modulo)==1){
			a= a.add(modulo.negate());
		}
		return a;
	}
	
	/**
	 * @param a
	 * @param b
	 * @return ritorna il Massimo Comun Divisore
	 */
	public BigInteger MCD(BigInteger a, BigInteger b){
		return a.gcd(b);
	}
	
	/**
	 * Si suppone che b sia primo.
	 * @param a
	 * @param b
	 * @return ritorna il simbolo di Legendre di (a/b): -1 se a non è radice quadratica 
	 * di b, 0 se b divide a, 1 se a è radice quadratica di b.
	 */
	public Integer simboloDiLegendre(BigInteger a, BigInteger b){
		if(MCD(a,b).compareTo(BigInteger.ONE)==0){
			return 0;
		}
		if(a.modPow(b.subtract(BigInteger.ONE).divide(new BigInteger("2")), b).compareTo(BigInteger.ONE)!=0){
			return 1;
		}
		return -1;
	}

}
