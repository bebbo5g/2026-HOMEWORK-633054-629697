package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {

	@Test
	public void testGetNome() {
		Stanza stanza = new Stanza("Aula");
		String nome = "Aula";

		String atteso = stanza.getNome();

		assertEquals(atteso, nome);
	}

	@Test
	public void testImpostaStanzaAdiacente() {
		Stanza corrente = new Stanza("Aula");
		Stanza adiacente = new Stanza("Ds2");

		corrente.impostaStanzaAdiacente("sud", adiacente);
		Stanza verifica = corrente.getStanzaAdiacente("sud");

		assertEquals(verifica, adiacente);
	}

	@Test
	public void testGetDescizione() {
		Stanza corr = new Stanza("Aula");
		String atteso = String.join("\n", "Aula", "Uscite: ", "Attrezzi nella stanza: ");

		String s = corr.getDescrizione();

		assertEquals(s, atteso);
	}

	@Test
	public void testAddAttrezzo() {
		Stanza corr = new Stanza("Aula");
		Attrezzo attrezzo = new Attrezzo("osso", 1);

		assertTrue(corr.addAttrezzo(attrezzo));
		assertTrue(corr.hasAttrezzo("osso"));
	}

	@Test
	public void testGetAttrezzo() {
		Stanza corr = new Stanza("Aula");
		Attrezzo attrezzo = new Attrezzo("osso", 1);
		String atteso = "osso";

		assertTrue(corr.addAttrezzo(attrezzo));
		assertTrue(corr.hasAttrezzo("osso"));

		assertEquals(corr.getAttrezzo(atteso), attrezzo);
	}

	@Test
	public void testRemoveAttrezzo() {
		Stanza corr = new Stanza("Aula");
		Attrezzo attrezzo = new Attrezzo("osso", 1);

		assertTrue(corr.addAttrezzo(attrezzo));
		assertTrue(corr.hasAttrezzo("osso"));

		assertTrue(corr.removeAttrezzo("osso"));
		assertFalse(corr.hasAttrezzo("osso"));

	}

	@Test
	public void testGetDirezioni() {
		Stanza corr = new Stanza("Aula");
		Stanza adiacente = new Stanza("Ds2");
		String[] atteso = { "sud" };

		corr.impostaStanzaAdiacente("sud", adiacente);

		assertArrayEquals(corr.getDirezioni(), atteso);
	}

}
