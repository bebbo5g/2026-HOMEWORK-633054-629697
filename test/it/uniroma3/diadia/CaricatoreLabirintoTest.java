package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

class CaricatoreLabirintoTest {

	private Labirinto carica(String testo) throws Exception {
		CaricatoreLabirinto c = new CaricatoreLabirinto(new StringReader(testo));
		return c.carica();
	}

	// ── monolocale ───────────────────────────────────────────

	@Test
	public void testMonolocale_stanzaIniziale() throws Exception {
		Labirinto l = carica("Stanze:\nN10\nEstremi:\nN10\nN10\nAttrezzi:\nUscite:\n");
		assertEquals("N10", l.getStanzaIniziale().getNome());
	}

	@Test
	public void testMonolocale_stanzaFinale() throws Exception {
		Labirinto l = carica("Stanze:\nN10\nEstremi:\nN10\nN10\nAttrezzi:\nUscite:\n");
		assertEquals("N10", l.getStanzaFinale().getNome());
	}

	// ── bilocale ─────────────────────────────────────────────

	@Test
	public void testBilocale_stanzaIniziale() throws Exception {
		Labirinto l = carica(
				"Stanze:\nN10\nBiblioteca\nEstremi:\nN10\nBiblioteca\nAttrezzi:\nUscite:\nN10 nord Biblioteca\n");
		assertEquals("N10", l.getStanzaIniziale().getNome());
	}

	@Test
	public void testBilocale_stanzaFinale() throws Exception {
		Labirinto l = carica(
				"Stanze:\nN10\nBiblioteca\nEstremi:\nN10\nBiblioteca\nAttrezzi:\nUscite:\nN10 nord Biblioteca\n");
		assertEquals("Biblioteca", l.getStanzaFinale().getNome());
	}

	@Test
	public void testBilocale_adiacenza() throws Exception {
		Labirinto l = carica(
				"Stanze:\nN10\nBiblioteca\nEstremi:\nN10\nBiblioteca\nAttrezzi:\nUscite:\nN10 nord Biblioteca\n");
		assertEquals("Biblioteca", l.getStanzaIniziale().getStanzaAdiacente("nord").getNome());
	}

	// ── attrezzi ─────────────────────────────────────────────

	@Test
	public void testAttrezzo_presente() throws Exception {
		Labirinto l = carica("Stanze:\nN10\nBiblioteca\nEstremi:\nN10\nBiblioteca\nAttrezzi:\nOsso 5 N10\nUscite:\n");
		assertTrue(l.getStanzaIniziale().hasAttrezzo("Osso"));
	}

	@Test
	public void testAttrezzo_peso() throws Exception {
		Labirinto l = carica("Stanze:\nN10\nBiblioteca\nEstremi:\nN10\nBiblioteca\nAttrezzi:\nOsso 5 N10\nUscite:\n");
		assertEquals(5, l.getStanzaIniziale().getAttrezzo("Osso").getPeso());
	}

	// ── errori ───────────────────────────────────────────────

	@Test
	public void testStanzaInizialeNonDefinita() {
		assertThrows(FormatoFileNonValidoException.class,
				() -> carica("Stanze:\nN10\nEstremi:\nAtrio\nN10\nAttrezzi:\nUscite:\n"));
	}

	@Test
	public void testPesoNonNumerico() {
		assertThrows(FormatoFileNonValidoException.class,
				() -> carica("Stanze:\nN10\nEstremi:\nN10\nN10\nAttrezzi:\nOsso pesante N10\nUscite:\n"));
	}

	@Test
	public void testStanzaDestinazioneNonDefinita() {
		assertThrows(FormatoFileNonValidoException.class,
				() -> carica("Stanze:\nN10\nEstremi:\nN10\nN10\nAttrezzi:\nUscite:\nN10 nord Fantasma\n"));
	}

	// ── trilocale ────────────────────────────────────────────

	@Test
	public void testTrilocale_adiacenzeMultiple() throws Exception {
		Labirinto l = carica("Stanze:\nN10\nBiblioteca\nN11\nEstremi:\nN10\nBiblioteca\n"
				+ "Attrezzi:\nUscite:\nN10 nord Biblioteca\nN10 est N11\nN11 ovest N10\n");
		assertEquals("Biblioteca", l.getStanzaIniziale().getStanzaAdiacente("nord").getNome());
		assertEquals("N11", l.getStanzaIniziale().getStanzaAdiacente("est").getNome());
	}

}
