package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {

	Partita partita;
	IO io;
	ComandoPosa comandoPosa;

	@Before
	public void setUp() {
		partita = new Partita();
		comandoPosa = new ComandoPosa();
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
	public void testNienteInBorsa() {
		Stanza s1 = new Stanza("stanza");
		partita.lab.setStanzaCorrente(s1);
		comandoPosa.setParametro("pala");
		comandoPosa.esegui(partita, io);
		assertEquals(null, partita.lab.getStanzaCorrente().getAttrezzo("pala"));
	}

	@Test
	public void testParNull() {
		Stanza s1 = new Stanza("stanza");
		Attrezzo pala = new Attrezzo("pala", 1);
		partita.giocatore.borsa.addAttrezzo(pala);
		partita.lab.setStanzaCorrente(s1);
		comandoPosa.setParametro(null);
		comandoPosa.esegui(partita, io);
		assertEquals(0, partita.lab.getStanzaCorrente().attrezzi.size());
	}

	@Test
	public void testNonCe() {
		Stanza s1 = new Stanza("stanza");
		Attrezzo pala = new Attrezzo("pala", 1);
		partita.giocatore.borsa.addAttrezzo(pala);
		partita.lab.setStanzaCorrente(s1);
		comandoPosa.setParametro("palla");
		comandoPosa.esegui(partita, io);
		assertEquals(0, partita.lab.getStanzaCorrente().attrezzi.size());
	}

	@Test
	public void testCe() {
		Stanza s1 = new Stanza("stanza");
		Attrezzo pala = new Attrezzo("pala", 1);
		partita.giocatore.borsa.addAttrezzo(pala);
		partita.lab.setStanzaCorrente(s1);
		comandoPosa.setParametro("pala");
		comandoPosa.esegui(partita, io);
		assertTrue(partita.lab.getStanzaCorrente().hasAttrezzo("pala"));
	}
}
