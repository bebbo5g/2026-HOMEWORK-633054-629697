package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {
	@Test
	public void testGetNome() {
		ComandoPosa cmd = new ComandoPosa("fake", null);

		assertEquals(cmd.getNome(), "posa");
	}

	@Test
	public void testGetParametro() {
		ComandoPosa cmd = new ComandoPosa("attrezzo", null);

		assertEquals(cmd.getParametro(), "attrezzo");
	}

	@Test
	public void testEsegui() {
		IOSimulator io = new IOSimulator();
		ComandoPosa cmd = new ComandoPosa("palla", io);

		Stanza stanza = new Stanza("stanza-test");

		Partita partita = new Partita();
		partita.getGiocatore().borsa.addAttrezzo(new Attrezzo("palla", 4));

		cmd.esegui(partita);

		assertEquals(stanza.hasAttrezzo("palla"), false);
		assertEquals(partita.getGiocatore().borsa.getAttrezzo("palla"), null);
	}
}
