package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestLabirinto {

	private Labirinto lab;

	@BeforeEach
	public void setUp() {
		// Creiamo il labirinto "di default" usando il Builder
		// al posto del vecchio costruttore new Labirinto(true)
		lab = Labirinto.newBuilder().addStanzaIniziale("Atrio").addStanzaVincente("Biblioteca").addStanza("Aula N11")
				.addStanza("Aula N10").addStanza("Laboratorio Campus").addAttrezzo("Atrio", "osso", 1)
				.addAttrezzo("Aula N10", "lanterna", 3).addAdiacenza("Atrio", "Biblioteca", Direzione.valueOf("nord"))
				.addAdiacenza("Atrio", "Aula N11", Direzione.valueOf("est"))
				.addAdiacenza("Atrio", "Aula N10", Direzione.valueOf("sud"))
				.addAdiacenza("Atrio", "Laboratorio Campus", Direzione.valueOf("ovest"))
				.addAdiacenza("Aula N11", "Atrio", Direzione.valueOf("ovest")).getLabirinto();
	}

	@Test
	public void testStanzaInizialeEFinale() {
		// Usiamo i getter corretti
		assertEquals("Atrio", lab.getStanzaIniziale().getNome());
		assertEquals("Biblioteca", lab.getStanzaVincente().getNome());
	}

	@Test
	public void testCollegamentiAtrio() {
		Stanza atrio = lab.getStanzaIniziale();

		// Passiamo l'Enum Direzione invece della stringa
		assertEquals("Biblioteca", atrio.getStanzaAdiacente(Direzione.valueOf("nord")).getNome());
		assertEquals("Aula N11", atrio.getStanzaAdiacente(Direzione.valueOf("est")).getNome());
		assertEquals("Aula N10", atrio.getStanzaAdiacente(Direzione.valueOf("sud")).getNome());
		assertEquals("Laboratorio Campus", atrio.getStanzaAdiacente(Direzione.valueOf("ovest")).getNome());
	}

	@Test
	public void testCollegamentiReciproci() {
		Stanza atrio = lab.getStanzaIniziale();
		Stanza aulaN11 = atrio.getStanzaAdiacente(Direzione.valueOf("est"));

		assertEquals(atrio, aulaN11.getStanzaAdiacente(Direzione.valueOf("ovest")));
	}

	@Test
	public void testAttrezzi() {
		Stanza atrio = lab.getStanzaIniziale();
		Stanza aulaN10 = atrio.getStanzaAdiacente(Direzione.valueOf("sud"));

		assertNotNull(atrio.getAttrezzo("osso"));
		assertNotNull(aulaN10.getAttrezzo("lanterna"));
	}
}
