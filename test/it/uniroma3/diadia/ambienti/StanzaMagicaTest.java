package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {

	@Test
	public void testMagicaNonTransforma() {
		StanzaMagica magica = new StanzaMagica("Magica");

		magica.addAttrezzo(new Attrezzo("spada", 2));
		magica.addAttrezzo(new Attrezzo("scudo", 3));
		magica.addAttrezzo(new Attrezzo("chiave", 1));

		assertTrue(magica.hasAttrezzo("spada"));
		assertTrue(magica.hasAttrezzo("scudo"));
		assertTrue(magica.hasAttrezzo("chiave"));
	}

	@Test
	public void testMagicaTrasforma() {
		StanzaMagica magica = new StanzaMagica("Magica");

		magica.addAttrezzo(new Attrezzo("a", 1));
		magica.addAttrezzo(new Attrezzo("b", 1));
		magica.addAttrezzo(new Attrezzo("c", 1));
		magica.addAttrezzo(new Attrezzo("ossa", 2));

		assertFalse(magica.hasAttrezzo("ossa"));
		assertTrue(magica.hasAttrezzo("asso"));
	}
}
