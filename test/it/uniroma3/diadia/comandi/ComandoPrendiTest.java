package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {

	Partita partita;
	ComandoPrendi comandoPrendi;
	IO io;

	@Before
	public void setUp() {
		partita = new Partita();
		comandoPrendi = new ComandoPrendi();
		io = new IO() {
			@Override
			public void mostraMessaggio(String msg) {
			}

			@Override
			public String leggiRiga() {
				return null;
			}
		};
	}

	@Test
	public void testNull1() {
		Attrezzo pala = new Attrezzo("pala", 1);
		Stanza s1 = new Stanza("stanza1");
		s1.addAttrezzo(pala);
		comandoPrendi.setParametro(null);
		comandoPrendi.esegui(partita, io);
		assertTrue(partita.giocatore.borsa.isEmpty());
	}

	@Test
	public void testNull2() {
		Attrezzo pala = new Attrezzo("pala", 1);
		Stanza s1 = new Stanza("stanza1");
		s1.addAttrezzo(pala);
		comandoPrendi.setParametro("palla");
		comandoPrendi.esegui(partita, io);
		assertTrue(partita.giocatore.borsa.isEmpty());
	}

	@Test
	public void testNoNull() {
		Attrezzo pala = new Attrezzo("pala", 1);
		Stanza s1 = new Stanza("stanza1");
		s1.addAttrezzo(pala);
		partita.lab.setStanzaCorrente(s1);
		comandoPrendi.setParametro("pala");
		comandoPrendi.esegui(partita, io);
		assertFalse(partita.giocatore.borsa.isEmpty());
	}

	@Test
	public void testTroppoPeso() {
		Attrezzo pala = new Attrezzo("pala", 8);
		Attrezzo osso = new Attrezzo("osso", 3);
		Stanza s1 = new Stanza("stanza1");
		s1.addAttrezzo(pala);
		s1.addAttrezzo(osso);
		partita.lab.setStanzaCorrente(s1);
		comandoPrendi.setParametro("pala");
		comandoPrendi.esegui(partita, io);
		comandoPrendi.setParametro("osso");
		comandoPrendi.esegui(partita, io);
		assertEquals(1, partita.giocatore.borsa.getNumeroAttrezzi());
	}
}
