package primalita;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

public class SolovayStrassenTest {
	
	private SolovayStrassen SS;
	
	@Before
	public void test() {
		this.SS = new SolovayStrassen(10);
	}
	
	@Test
	public void TestPrimo(){
		this.SS.setNumeroTest(new BigInteger("997"));
		this.SS.setNumeroDiPassi(500);
		this.SS.makeTest();
		assertEquals(true, this.SS.getPrimalita());
		System.out.println(this.SS.getRisultatoTestuale());
	}
	
//	@Test
//	public void TestNonPrimo(){
//		this.SS.setNumeroTest(new BigInteger("25"));
//		this.SS.setNumeroDiPassi(150);
//		this.SS.makeTest();
//		
//		assertEquals(false, this.SS.getPrimalita());
//		System.out.println(this.SS.getRisultatoTestuale());
//	}

}
