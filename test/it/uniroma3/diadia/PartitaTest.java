package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {

	@Test
	public void testIsFinitaInizio() {
		Partita partita = new Partita();
		assertFalse(partita.isFinita());
	}

	@Test
	public void testIsFinitaDopoSetFinita() {
		Partita partita = new Partita();
		partita.setFinita();
		assertTrue(partita.isFinita());
	}

	@Test
	public void testVintaInizio() {
		Partita partita = new Partita();
		assertFalse(partita.vinta());
	}

	@Test
	public void testVintaQuandoStanzaFinale() {
		Partita partita = new Partita();
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertTrue(partita.vinta());
	}

	@Test
	public void testIsFinitaCfuEsauriti() {
		Partita partita = new Partita();
		partita.getGiocatore().setCfu(0);
		assertTrue(partita.isFinita());
	}

	@Test
	public void testSetStanzaCorrenteDecremantaCfu() {
		Partita partita = new Partita();
		int cfuIniziali = partita.getGiocatore().getCfu();
		Stanza nuovaStanza = new Stanza("nuova");
		partita.setStanzaCorrente(nuovaStanza);
		assertEquals(cfuIniziali - 1, partita.getGiocatore().getCfu());
	}

	@Test
	public void testSetStanzaCorrenteStessaStanzaNonDecremantaCfu() {
		Partita partita = new Partita();
		int cfuIniziali = partita.getGiocatore().getCfu();
		partita.setStanzaCorrente(partita.getStanzaCorrente());
		assertEquals(cfuIniziali, partita.getGiocatore().getCfu());
	}

	@Test
	public void testSetStanzaCorrenteNull() {
		Partita partita = new Partita();
		Stanza stanzaPrima = partita.getStanzaCorrente();
		partita.setStanzaCorrente(null);
		assertEquals(stanzaPrima, partita.getStanzaCorrente());
	}
}