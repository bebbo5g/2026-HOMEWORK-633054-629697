package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {

	private StanzaMagica stanzaMagica;

	@Before
	public void setUp() {
		stanzaMagica = new StanzaMagica("magica", 2);
	}

	@Test
	public void testAddAttrezzoSottoSogliaNonModifica() {
		stanzaMagica.addAttrezzo(new Attrezzo("chiave", 2));
		assertTrue(stanzaMagica.hasAttrezzo("chiave"));
	}

	@Test
	public void testAddAttrezzoSottoSogliaPesoInvariato() {
		stanzaMagica.addAttrezzo(new Attrezzo("chiave", 2));
		assertEquals(2, stanzaMagica.getAttrezzo("chiave").getPeso());
	}

	@Test
	public void testAddAttrezzoOltreSogliaInverteNome() {
		stanzaMagica.addAttrezzo(new Attrezzo("a", 1));
		stanzaMagica.addAttrezzo(new Attrezzo("b", 1));
		stanzaMagica.addAttrezzo(new Attrezzo("chiave", 2));
		assertTrue(stanzaMagica.hasAttrezzo("evaihc"));
	}

	@Test
	public void testAddAttrezzoOltreSogliaDoppiaPeso() {
		stanzaMagica.addAttrezzo(new Attrezzo("a", 1));
		stanzaMagica.addAttrezzo(new Attrezzo("b", 1));
		stanzaMagica.addAttrezzo(new Attrezzo("chiave", 2));
		assertEquals(4, stanzaMagica.getAttrezzo("evaihc").getPeso());
	}

	@Test
	public void testAddAttrezzoSottoSogliaNonInverteNome() {
		stanzaMagica.addAttrezzo(new Attrezzo("chiave", 2));
		assertFalse(stanzaMagica.hasAttrezzo("evaihc"));
	}
}
