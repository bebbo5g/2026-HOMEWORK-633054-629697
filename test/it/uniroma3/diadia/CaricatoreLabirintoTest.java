package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.CaricatoreLabirinto;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.FormatoLabirintoException;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.personaggi.Cane;

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
		assertEquals("N10", l.getStanzaVincente().getNome()); // Usato getStanzaVincente
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
		assertEquals("Biblioteca", l.getStanzaVincente().getNome()); // Usato getStanzaVincente
	}

	@Test
	public void testBilocale_adiacenza() throws Exception {
		Labirinto l = carica(
				"Stanze:\nN10\nBiblioteca\nEstremi:\nN10\nBiblioteca\nAttrezzi:\nUscite:\nN10 nord Biblioteca\n");
		assertEquals("Biblioteca", l.getStanzaIniziale().getStanzaAdiacente(Direzione.valueOf("nord")).getNome()); // Usato
																													// Enum
																													// Direzione
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
	public void testSezioneMancante() {
		// Il file non inizia con l'intestazione di una sezione (es. "Stanze:")
		assertThrows(FormatoLabirintoException.class, () -> carica("N10\nEstremi:\nAtrio\nN10\nAttrezzi:\nUscite:\n"));
	}

	@Test
	public void testPesoNonNumerico() {
		assertThrows(FormatoLabirintoException.class,
				() -> carica("Stanze:\nN10\nEstremi:\nN10\nN10\nAttrezzi:\nOsso pesante N10\nUscite:\n"));
	}

	@Test
	public void testDirezioneUscitaNonValida() {
		// Passiamo una direzione inventata ("su") per innescare
		// l'IllegalArgumentException
		assertThrows(FormatoLabirintoException.class,
				() -> carica("Stanze:\nN10\nEstremi:\nN10\nN10\nAttrezzi:\nUscite:\nN10 su Fantasma\n"));
	}

	// ── trilocale ────────────────────────────────────────────

	@Test
	public void testTrilocale_adiacenzeMultiple() throws Exception {
		Labirinto l = carica("Stanze:\nN10\nBiblioteca\nN11\nEstremi:\nN10\nBiblioteca\n"
				+ "Attrezzi:\nUscite:\nN10 nord Biblioteca\nN10 est N11\nN11 ovest N10\n");
		assertEquals("Biblioteca", l.getStanzaIniziale().getStanzaAdiacente(Direzione.valueOf("nord")).getNome());
		assertEquals("N11", l.getStanzaIniziale().getStanzaAdiacente(Direzione.valueOf("est")).getNome());
	}

	@Test
	public void testStanzaBuia() throws Exception {
		// Adattato al nuovo formato: la stanza buia va sotto "Stanze:" col prefisso
		// "buia"
		String testo = "Stanze:\nbuia Cantina lanterna\nEstremi:\nCantina\nCantina\nAttrezzi:\nUscite:\n";
		Labirinto l = carica(testo);
		assertTrue(l.getStanzaIniziale().getDescrizione().length() > 0);
	}

	@Test
	public void testPersonaggioMago() throws Exception {
		// Adattato al formato: mago <nomeStanza> <nome> <pres> <nomeAttrezzo>
		// <pesoAttrezzo>
		String testo = "Stanze:\nN10\nBiblioteca\nEstremi:\nN10\nBiblioteca\nAttrezzi:\n"
				+ "Personaggi:\nmago N10 Merlino Ciao! bacchetta 3\nUscite:\n";
		Labirinto l = carica(testo);
		assertNotNull(l.getStanzaIniziale().getPersonaggio());
		assertEquals("Merlino", l.getStanzaIniziale().getPersonaggio().getNome());
	}

	@Test
	public void testPersonaggioCane() throws Exception {
		// Adattato al formato: cane <nomeStanza> <nome> <pres> <cibo> <nomeAttrezzo>
		// <pesoAttrezzo>
		String testo = "Stanze:\nN10\nBiblioteca\nEstremi:\nN10\nBiblioteca\nAttrezzi:\n"
				+ "Personaggi:\ncane N10 Fido Bau! osso collare 2\nUscite:\n";
		Labirinto l = carica(testo);
		assertNotNull(l.getStanzaIniziale().getPersonaggio());
		assertTrue(l.getStanzaIniziale().getPersonaggio() instanceof Cane);
	}

	@Test
	public void testTipoPersonaggioSconosciuto() {
		// Cambiata l'eccezione da controllare
		String testo = "Stanze:\nN10\nEstremi:\nN10\nN10\nAttrezzi:\n"
				+ "Personaggi:\ndrago N10 Smaug Ruggito!\nUscite:\n";
		assertThrows(FormatoLabirintoException.class, () -> carica(testo));
	}

}
