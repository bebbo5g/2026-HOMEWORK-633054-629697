package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

	@Test
	public void testSenzaAttrezzoSblocca() {
		// Ordine corretto: nome stanza, attrezzo sbloccante, direzione bloccata
		StanzaBloccata stanza = new StanzaBloccata("test-room", "chiave", "sud");
		stanza.addAttrezzo(new Attrezzo("spada", 5));

		// Bisogna passare un tipo Direzione invece di una Stringa
		stanza.impostaStanzaAdiacente(Direzione.valueOf("sud"), new Stanza("sgabuzzino"));

		// Il test verifica che, non avendo la chiave, si rimanga nella test-room
		assertEquals("test-room", stanza.getStanzaAdiacente(Direzione.valueOf("sud")).getNome());
	}

	@Test
	public void testConAttrezzoSblocca() {
		StanzaBloccata stanza = new StanzaBloccata("test-room", "chiave", "sud");
		stanza.addAttrezzo(new Attrezzo("chiave", 1));
		stanza.impostaStanzaAdiacente(Direzione.valueOf("sud"), new Stanza("sgabuzzino"));

		// Il test verifica che, avendo la chiave, si acceda allo sgabuzzino
		assertEquals("sgabuzzino", stanza.getStanzaAdiacente(Direzione.valueOf("sud")).getNome());
	}
}