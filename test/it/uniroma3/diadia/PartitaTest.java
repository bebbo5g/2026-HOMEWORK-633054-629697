package it.uniroma3.diadia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PartitaTest {

	private Partita partita;

	@Before
	public void setUp() {
		partita = new Partita();
	}

	@Test
	public void testPartitaNoVinta() {
		assertFalse(partita.vinta());
	}

	@Test
	public void testPartitaNoFinitaAllInizio() {
		assertFalse(partita.isFinita());
	}

	@Test
	public void testSetFinita() {
		partita.setFinita();
		assertTrue(partita.isFinita());
	}

	@Test
	public void testPartitaFinitaQuandoCfuZero() {
		partita.giocatore.setCfu(0);
		assertTrue(partita.isFinita());
	}

	@Test
	public void testPartitaVintaQuandoInStanzaVincente() {
		partita.lab.setStanzaCorrente(partita.lab.getStanzaVincente());
		assertTrue(partita.vinta());
	}

	@Test
	public void testPartitaFinitaQuandoVinta() {
		partita.lab.setStanzaCorrente(partita.lab.getStanzaVincente());
		assertTrue(partita.isFinita());
	}
}