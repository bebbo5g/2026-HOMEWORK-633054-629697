package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class TestLabirinto {

	@Test
	public void testStanzaInizialeEFinale() {
		Labirinto lab = new Labirinto();

		assertEquals("Atrio", lab.iniziale.getNome());
		assertEquals("Biblioteca", lab.finale.getNome());
	}

	@Test
	public void testCollegamentiAtrio() {
		Labirinto lab = new Labirinto();
		Stanza atrio = lab.iniziale;

		assertEquals("Biblioteca", atrio.getStanzaAdiacente("nord").getNome());
		assertEquals("Aula N11", atrio.getStanzaAdiacente("est").getNome());
		assertEquals("Aula N10", atrio.getStanzaAdiacente("sud").getNome());
		assertEquals("Laboratorio Campus", atrio.getStanzaAdiacente("ovest").getNome());
	}

	@Test
	public void testCollegamentiReciproci() {
		Labirinto lab = new Labirinto();
		Stanza atrio = lab.iniziale;
		Stanza aulaN11 = atrio.getStanzaAdiacente("est");

		assertEquals(atrio, aulaN11.getStanzaAdiacente("ovest"));
	}

	@Test
	public void testAttrezzi() {
		Labirinto lab = new Labirinto();

		Stanza atrio = lab.iniziale;
		Stanza aulaN10 = atrio.getStanzaAdiacente("sud");

		assertNotNull(atrio.getAttrezzo("osso"));
		assertNotNull(aulaN10.getAttrezzo("lanterna"));
	}

}
