package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {

	private Stanza stanza;

	@Before
	public void setUp() {
		stanza = new Stanza("Aula");
	}

	@Test
	public void testGetNome() {
		assertEquals("Aula", stanza.getNome());
	}

	@Test
	public void testAddEHasAttrezzo() {
		Attrezzo a = new Attrezzo("chiave", 1);
		stanza.addAttrezzo(a);
		assertTrue(stanza.hasAttrezzo("chiave"));
	}

	@Test
	public void testRemoveAttrezzo() {
		Attrezzo a = new Attrezzo("chiave", 1);
		stanza.addAttrezzo(a);
		stanza.removeAttrezzo(a);
		assertFalse(stanza.hasAttrezzo("chiave"));
	}

	@Test
	public void testStanzaAdiacente() {
		Stanza corridoio = new Stanza("Corridoio");
		stanza.impostaStanzaAdiacente(Direzione.nord, corridoio);
		assertEquals(corridoio, stanza.getStanzaAdiacente(Direzione.nord));
	}
}