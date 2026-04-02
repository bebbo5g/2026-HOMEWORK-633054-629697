package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	@Test
	public void testGetPesoBorsaVuota() {
		Borsa borsa = new Borsa();
		assertEquals(0, borsa.getPeso());
	}

	@Test
	public void testAddAttrezzoRitornaTrue() {
		Borsa borsa = new Borsa();
		Attrezzo spada = new Attrezzo("spada", 3);
		assertTrue(borsa.addAttrezzo(spada));
	}

	@Test
	public void testAddAttrezzoConPesoEccessivo() {
		Borsa borsa = new Borsa();
		Attrezzo masso = new Attrezzo("masso", 11);
		assertFalse(borsa.addAttrezzo(masso));
	}

	@Test
	public void testAddAttrezzoAggiornaPeso() {
		Borsa borsa = new Borsa();
		Attrezzo spada = new Attrezzo("spada", 3);
		borsa.addAttrezzo(spada);
		assertEquals(3, borsa.getPeso());
	}

	@Test
	public void testGetAttrezzo() {
		Borsa borsa = new Borsa();
		Attrezzo spada = new Attrezzo("spada", 3);
		borsa.addAttrezzo(spada);
		assertEquals(spada, borsa.getAttrezzo("spada"));
	}

	@Test
	public void testGetAttrezzoAssente() {
		Borsa borsa = new Borsa();
		assertNull(borsa.getAttrezzo("spada"));
	}

	@Test
	public void testRemoveAttrezzo() {
		Borsa borsa = new Borsa();
		Attrezzo spada = new Attrezzo("spada", 3);
		borsa.addAttrezzo(spada);
		assertNotNull(borsa.removeAttrezzo(spada));
		assertFalse(borsa.hasAttrezzo("spada"));
	}

	@Test
	public void testIsEmptyBorsaNuova() {
		Borsa borsa = new Borsa();
		assertTrue(borsa.isEmpty());
	}

	@Test
	public void testIsEmptyDopoAggiunta() {
		Borsa borsa = new Borsa();
		borsa.addAttrezzo(new Attrezzo("spada", 3));
		assertFalse(borsa.isEmpty());
	}
}