package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {
	private Partita creaPartita() {
		Labirinto labirinto = new LabirintoBuilder().addStanzaIniziale("Atrio").addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord").getLabirinto();
		return new Partita(labirinto);
	}

	@Test
	public void testGetNome() {
		ComandoPosa cmd = new ComandoPosa("fake", null);
		assertEquals("posa", cmd.getNome());
	}

	@Test
	public void testGetParametro() {
		ComandoPosa cmd = new ComandoPosa("attrezzo", null);
		assertEquals("attrezzo", cmd.getParametro());
	}

	@Test
	public void testEsegui_attrezzoRimossoDallaBorsa() {
		IOSimulator io = new IOSimulator();
		ComandoPosa cmd = new ComandoPosa("palla", io);
		Partita partita = creaPartita();
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("palla", 4));
		cmd.esegui(partita);
		assertNull(partita.getGiocatore().getBorsa().getAttrezzo("palla"));
	}

	@Test
	public void testEsegui_attrezzoAggiунtoInStanza() {
		IOSimulator io = new IOSimulator();
		ComandoPosa cmd = new ComandoPosa("palla", io);
		Partita partita = creaPartita();
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("palla", 4));
		cmd.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("palla"));
	}

	@Test
	public void testEsegui_attrezzoAssenteInBorsa() {
		IOSimulator io = new IOSimulator();
		ComandoPosa cmd = new ComandoPosa("spada", io);
		Partita partita = creaPartita();
		// borsa vuota — niente da posare
		cmd.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("spada"));
	}

	@Test
	public void testEsegui_borsaVuotaDopoSingolaRosa() {
		IOSimulator io = new IOSimulator();
		ComandoPosa cmd = new ComandoPosa("palla", io);
		Partita partita = creaPartita();
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("palla", 4));
		cmd.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
	}

	@Test
	public void testEsegui_parametroNull_nonCambiaStanza() {
		IOSimulator io = new IOSimulator();
		ComandoPosa cmd = new ComandoPosa(null, io);
		Partita partita = creaPartita();
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("palla", 4));
		cmd.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("palla"));
	}
}
