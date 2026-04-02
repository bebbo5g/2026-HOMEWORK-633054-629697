git package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	
	private Borsa borsa;
	
	@BeforeEach
	void setUp(){
		borsa = new Borsa();
	}

	@Test
	public void testAddAttrezzo() {
		Attrezzo attrezzo = new Attrezzo("osso", 1);
		assertTrue(borsa.addAttrezzo(attrezzo));
		
	}

}
