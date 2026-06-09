package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	Borsa borsa;

	@Before
	public void setUp() {
		borsa = new Borsa();
	}

	@Test
	public void getPesoMaxTest() {
		assertEquals(this.borsa.getPesoMax(), this.borsa.DEFAULT_PESO_MAX_BORSA);
	}

	@Test
	public void getPesoTest() {
		Attrezzo a = new Attrezzo("pala", 2);
		this.borsa.addAttrezzo(a);
		assertEquals(this.borsa.getPeso(), 2);
	}

	@Test
	public void removeTest() {
		Attrezzo a = new Attrezzo("pala", 2);
		this.borsa.addAttrezzo(a);
		this.borsa.removeAttrezzo(a.getNome());
		assertEquals(this.borsa.getPeso(), 0);
	}

	@Test
	public void getPesoTest1() {
		Attrezzo a = new Attrezzo("pala", 2);
		Attrezzo a1 = new Attrezzo("osso", 5);
		this.borsa.addAttrezzo(a);
		this.borsa.addAttrezzo(a1);
		this.borsa.removeAttrezzo(a1.getNome());
		assertEquals(this.borsa.getPeso(), 2);
	}

	@Test
	public void getPesoTest2() {
		Attrezzo a = new Attrezzo("pala", 2);
		Attrezzo a1 = new Attrezzo("osso", 5);
		Attrezzo a2 = new Attrezzo("cocco", 1);
		this.borsa.addAttrezzo(a);
		this.borsa.addAttrezzo(a1);
		this.borsa.addAttrezzo(a2);
		this.borsa.removeAttrezzo(a1.getNome());
		assertEquals(2, this.borsa.getNumeroAttrezzi());
	}

	@Test
	public void testOrdinatoPerPesoUnoAttrezzo() {
		borsa.addAttrezzo(new Attrezzo("pala", 3));
		List<Attrezzo> lista = borsa.getContenutoOrdinatoPerPeso();
		assertEquals(1, lista.size());
		assertEquals("pala", lista.get(0).getNome());
	}

	@Test
	public void testOrdinatoPerPesoDueAttrezziPesiDiversi() {
		assertTrue(borsa.addAttrezzo(new Attrezzo("piombo", 5)));
		assertTrue(borsa.addAttrezzo(new Attrezzo("piuma", 1)));
		List<Attrezzo> lista = borsa.getContenutoOrdinatoPerPeso();
		assertEquals(2, lista.size());
		assertEquals("piuma", lista.get(0).getNome());
		assertEquals("piombo", lista.get(1).getNome());
	}
}
