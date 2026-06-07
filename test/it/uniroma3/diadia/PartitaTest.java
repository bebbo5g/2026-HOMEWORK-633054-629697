package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {

	private Partita creaPartita() {
		Labirinto labirinto = new LabirintoBuilder().addStanzaIniziale("Atrio").addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord").getLabirinto();
		return new Partita(labirinto);
	}

	@Test
	public void testIsFinitaInizio() {
		Partita partita = creaPartita();
		assertFalse(partita.isFinita());
	}

	@Test
	public void testIsFinitaDopoSetFinita() {
		Partita partita = creaPartita();
		partita.setFinita();
		assertTrue(partita.isFinita());
	}

	@Test
	public void testVintaInizio() {
		Partita partita = creaPartita();
		assertFalse(partita.vinta()); // all'inizio si è in Atrio, non in Biblioteca
	}

	@Test
	public void testVintaQuandoStanzaFinale() {
		Partita partita = creaPartita();
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertTrue(partita.vinta());
	}

	@Test
	public void testIsFinitaCfuEsauriti() {
		Partita partita = creaPartita();
		partita.getGiocatore().setCfu(0);
		assertTrue(partita.isFinita());
	}

	@Test
	public void testSetStanzaCorrenteDecremantaCfu() {
		Partita partita = creaPartita();
		int cfuIniziali = partita.getGiocatore().getCfu();
		Stanza nuovaStanza = new Stanza("nuova");
		partita.setStanzaCorrente(nuovaStanza);
		assertEquals(cfuIniziali - 1, partita.getGiocatore().getCfu());
	}

	@Test
	public void testSetStanzaCorrenteStessaStanzaNonDecremantaCfu() {
		Partita partita = creaPartita();
		int cfuIniziali = partita.getGiocatore().getCfu();
		partita.setStanzaCorrente(partita.getStanzaCorrente());
		assertEquals(cfuIniziali, partita.getGiocatore().getCfu());
	}

	@Test
	public void testSetStanzaCorrenteNull() {
		Partita partita = creaPartita();
		Stanza stanzaPrima = partita.getStanzaCorrente();
		partita.setStanzaCorrente(null);
		assertEquals(stanzaPrima, partita.getStanzaCorrente());
	}
}