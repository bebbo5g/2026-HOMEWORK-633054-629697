package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {

	private Partita creaPartita() {
		Labirinto labirinto = new LabirintoBuilder().addStanzaIniziale("Atrio").addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord").getLabirinto();
		return new Partita(labirinto);
	}

	@Test
	public void testGetNome() {
		ComandoPrendi cmd = new ComandoPrendi("fake", null);

		assertEquals(cmd.getNome(), "prendi");
	}

	@Test
	public void testGetParametro() {
		ComandoPrendi cmd = new ComandoPrendi("attrezzo", null);

		assertEquals(cmd.getParametro(), "attrezzo");
	}

	@Test
	public void testEsegui_attrezzoRimossoDallaStanza() {
		IOSimulator io = new IOSimulator();
		ComandoPrendi cmd = new ComandoPrendi("palla", io);
		Partita partita = creaPartita();
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo("palla", 4));
		cmd.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("palla"));
	}

	@Test
	public void testEsegui_attrezzoAssenteInStanza() {
		IOSimulator io = new IOSimulator();
		ComandoPrendi cmd = new ComandoPrendi("spada", io);
		Partita partita = creaPartita();
		cmd.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}

	@Test
	public void testEsegui_parametroNull() {
		IOSimulator io = new IOSimulator();
		ComandoPrendi cmd = new ComandoPrendi(null, io);
		Partita partita = creaPartita();
		cmd.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
	}

	@Test
	public void testEstendeAbstractComando() {
		assertTrue(new ComandoPrendi("x", null) instanceof AbstractComando);
	}
}
