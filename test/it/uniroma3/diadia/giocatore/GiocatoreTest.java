package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GiocatoreTest {

	@Test
	public void testCfuIniziali() {
		Giocatore giocatore = new Giocatore();

		assertEquals(giocatore.getCfu(), 20);
	}

	@Test
	public void testSetCfu() {
		Giocatore giocatore = new Giocatore();

		giocatore.setCfu(14);

		assertEquals(giocatore.getCfu(), 14);
	}
}
