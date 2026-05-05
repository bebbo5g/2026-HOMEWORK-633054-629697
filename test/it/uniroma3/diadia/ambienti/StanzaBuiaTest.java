package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

	@Test
	public void testSenzaAttrezzoLuce() {
		StanzaBuia stanza = new StanzaBuia("stanza-test", "lanterna");
		stanza.addAttrezzo(new Attrezzo("bastone", 2));

		assertEquals(stanza.getDescrizione(), "Qui c'è buio pesto!");
	}

	@Test
	public void testConAttrezzoLuce() {
		StanzaBuia stanza = new StanzaBuia("stanza-test", "lanterna");
		stanza.addAttrezzo(new Attrezzo("lanterna", 1));

		assertEquals(stanza.getDescrizione(), "stanza-test\nUscite: \nAttrezzi nella stanza: lanterna (1kg) ");
	}

}
