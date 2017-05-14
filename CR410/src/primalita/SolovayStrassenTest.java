package primalita;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

public class SolovayStrassenTest {
	
	private SolovayStrassen SS;
	
	@Before
	public void test() {
		this.SS = new SolovayStrassen();
	}
	
	@Test
	public void TestNonPrimo(){
		this.SS.setNumeroTest(new BigInteger("15"));
		this.SS.makeTest();
		assertEquals(false, this.SS.getPrimalita());
		System.out.println(this.SS.getRisultatoTestuale());
	}

}
