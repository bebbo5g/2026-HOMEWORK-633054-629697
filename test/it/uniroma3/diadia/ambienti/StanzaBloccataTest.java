package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

	@Test
	public void testSenzaAttrezzoSblocca() {
		StanzaBloccata stanza = new StanzaBloccata("test-room", "sud", "chiave");
		stanza.addAttrezzo(new Attrezzo("spada", 5));
		stanza.impostaStanzaAdiacente("sud", new Stanza("sgabuzzino"));

		assertEquals(stanza.getStanzaAdiacente("sud").getNome(), "test-room");
	}

	@Test
	public void testConAttrezzoSblocca() {
		StanzaBloccata stanza = new StanzaBloccata("test-room", "sud", "chiave");
		stanza.addAttrezzo(new Attrezzo("chiave", 1));
		stanza.impostaStanzaAdiacente("sud", new Stanza("sgabuzzino"));

		assertEquals(stanza.getStanzaAdiacente("sud").getNome(), "sgabuzzino");
	}
}
