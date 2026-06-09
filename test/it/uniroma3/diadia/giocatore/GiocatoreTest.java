package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {

	Giocatore giocatore;

	@Before
	public void setUp() {
		giocatore = new Giocatore();
	}

	@Test
	public void testCFUiniziali() {
		assertEquals(20, giocatore.getCfu());
	}

	@Test
	public void setCfu1() {
		this.giocatore.setCfu(5);
		assertEquals(5, giocatore.getCfu());
	}

	@Test
	public void setCfu2() {
		this.giocatore.setCfu(5);
		assertEquals("CFU rimanenti = 5", giocatore.dimmiCfu());
	}
}