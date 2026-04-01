package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import static org.junit.jupiter.api.Assertions.*;

import  org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;


public class StanzaTest {
	
	private Stanza stanza;
	private Stanza adiacente;
	
	@BeforeEach
	public void setUp()
	{
		stanza = new Stanza("Aula");
		adiacente = new Stanza("Campus");
	}
	
	@Test
	public void testImpostaAdiacente() {
		stanza.impostaStanzaAdiacente("sud", adiacente);
		assertTrue(adiacente.equals(stanza.getStanzaAdiacente("sud")));
		
		stanza.impostaStanzaAdiacente("sud", new Stanza("Iacopo"));
		assertTrue(stanza.getStanzaAdiacente("sud").getNome().equals("Iacopo"));
	}
	
	@Test
	public void testGetDirezioni() {
		assertEquals(stanza.getDirezioni().length,0);
		stanza.impostaStanzaAdiacente("sud",adiacente);
		assertEquals(stanza.getDirezioni().length,1);
		assertEquals(stanza.getNumeroStanzeAdiacenti(),1);
	}
	
	
	@Test
	public void testGetNome()
	{
		assertEquals(stanza.getNome(), "Aula");
	}
	
	@Test
	public void testAddAttrezzo()
	{
		Attrezzo attrezzo = new Attrezzo("osso", 1);
		assertTrue(stanza.addAttrezzo(attrezzo));
	}
	
	@Test
	public void testHasAttrezzo()
	{
		Attrezzo attrezzo = new Attrezzo("osso", 1);
		stanza.addAttrezzo(attrezzo);
		assertTrue(stanza.hasAttrezzo("osso"));
	}
	
	@Test
	public void testGetAttrezzo()
	{
		Attrezzo attrezzo = new Attrezzo("osso", 1);
		stanza.addAttrezzo(attrezzo);
		assertEquals(stanza.getAttrezzo("osso"), attrezzo);
	}
	
	@Test
	public void testRemoveAttrezzo()
	{
		Attrezzo attrezzo = new Attrezzo("osso", 1);
		stanza.addAttrezzo(attrezzo);
		assertTrue(stanza.removeAttrezzo("osso"));
	}
}
