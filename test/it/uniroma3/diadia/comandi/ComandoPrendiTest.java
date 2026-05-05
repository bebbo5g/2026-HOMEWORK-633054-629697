package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {
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
	public void testEsegui() {
		IOSimulator io = new IOSimulator();
		ComandoPrendi cmd = new ComandoPrendi("palla", io);

		Stanza stanza = new Stanza("stanza-test");
		stanza.addAttrezzo(new Attrezzo("palla", 4));

		Partita partita = new Partita();
		partita.setStanzaCorrente(stanza);

		cmd.esegui(partita);

		assertEquals(stanza.getNumeroAttrezzi(), 0);
		assertEquals(partita.getGiocatore().borsa.hasAttrezzo("palla"), true);
	}
}
