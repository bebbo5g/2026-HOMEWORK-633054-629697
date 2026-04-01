package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiocatoreTest {
	
	private Giocatore giocatore;
	@BeforeEach
	public void setUp(){
		giocatore = new Giocatore();
	}

	@Test
	public void testSetGetCfu() {
		giocatore.setCfu(10);
		assertEquals(giocatore.getCfu(), 10);
	}

}
