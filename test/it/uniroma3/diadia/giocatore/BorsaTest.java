package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

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

	@Test
	public void testSortedSetOrdinatoPerPesoAttrezziStessoPeso() {
		Borsa borsa = new Borsa();
		Attrezzo libro = new Attrezzo("libro", 5);
		Attrezzo ps = new Attrezzo("ps", 5);

		borsa.addAttrezzo(libro);
		borsa.addAttrezzo(ps);

		SortedSet<Attrezzo> risultato = borsa.getSortedSetOrdinatoPerPeso();

		assertEquals(2, risultato.size());

		assertEquals("libro", risultato.first().getNome());
		assertEquals("ps", risultato.last().getNome());
	}

	@Test
	public void testOrdinatoPerPeso_vuota() {
		Borsa borsa = new Borsa();

		assertTrue(borsa.getContenutoOrdinatoPerPeso().isEmpty());
	}

	@Test
	public void testOrdinatoPerPeso_unSoloAttrezzo() {
		Borsa borsa = new Borsa();

		borsa.addAttrezzo(new Attrezzo("piuma", 1));

		assertEquals("piuma", borsa.getContenutoOrdinatoPerPeso().get(0).getNome());
	}

	@Test
	public void testOrdinatoPerPeso_ordinePeso() {
		Borsa borsa = new Borsa();

		borsa.addAttrezzo(new Attrezzo("b", 7));
		borsa.addAttrezzo(new Attrezzo("a", 1));

		List<Attrezzo> lista = borsa.getContenutoOrdinatoPerPeso();

		assertEquals("a", lista.get(0).getNome()); // peso 1 prima
		assertEquals("b", lista.get(1).getNome()); // peso 10 dopo
	}

	@Test
	public void testOrdinatoPerPeso_paritaPesoOrdinaNome() {
		Borsa borsa = new Borsa();

		borsa.addAttrezzo(new Attrezzo("ps", 5));
		borsa.addAttrezzo(new Attrezzo("libro", 5));

		List<Attrezzo> lista = borsa.getContenutoOrdinatoPerPeso();

		assertEquals("libro", lista.get(0).getNome()); // l < p
		assertEquals("ps", lista.get(1).getNome());
	}

	@Test
	public void testOrdinatoPerNome_vuota() {
		Borsa borsa = new Borsa();

		assertTrue(borsa.getContenutoOrdinatoPerNome().isEmpty());
	}

	@Test
	public void testOrdinatoPerNome_unSoloAttrezzo() {
		Borsa borsa = new Borsa();

		borsa.addAttrezzo(new Attrezzo("piuma", 1));

		assertEquals("piuma", borsa.getContenutoOrdinatoPerNome().first().getNome());
	}

	@Test
	public void testOrdinatoPerNome_ordineAlfabetico() {
		Borsa borsa = new Borsa();

		borsa.addAttrezzo(new Attrezzo("ps", 5));
		borsa.addAttrezzo(new Attrezzo("libro", 5));

		SortedSet<Attrezzo> insieme = borsa.getContenutoOrdinatoPerNome();

		assertEquals("libro", insieme.first().getNome());
		assertEquals("ps", insieme.last().getNome());
	}

	@Test
	public void testRaggruppatoPerPeso_vuota() {
		Borsa borsa = new Borsa();

		assertTrue(borsa.getContenutoRaggruppatoPerPeso().isEmpty());
	}

	@Test
	public void testRaggruppatoPerPeso_unSoloAttrezzo() {
		Borsa borsa = new Borsa();

		borsa.addAttrezzo(new Attrezzo("piuma", 1));

		Map<Integer, Set<Attrezzo>> mappa = borsa.getContenutoRaggruppatoPerPeso();

		assertEquals(1, mappa.size());
		assertTrue(mappa.containsKey(1));
		assertEquals(1, mappa.get(1).size());
	}

	@Test
	public void testRaggruppatoPerPeso_dueAttrezziStessoPeso() {
		Borsa borsa = new Borsa();

		borsa.addAttrezzo(new Attrezzo("libro", 5));
		borsa.addAttrezzo(new Attrezzo("ps", 5));

		Map<Integer, Set<Attrezzo>> mappa = borsa.getContenutoRaggruppatoPerPeso();

		assertEquals(1, mappa.size()); // una sola chiave
		assertEquals(2, mappa.get(5).size()); // due attrezzi sotto peso 5
	}

	@Test
	public void testRaggruppatoPerPeso_pesoDistinti() {
		Borsa borsa = new Borsa();

		borsa.addAttrezzo(new Attrezzo("piuma", 1));
		borsa.addAttrezzo(new Attrezzo("piombo", 8));

		Map<Integer, Set<Attrezzo>> mappa = borsa.getContenutoRaggruppatoPerPeso();

		assertEquals(2, mappa.size());
		assertTrue(mappa.containsKey(1));
		assertTrue(mappa.containsKey(8));
	}

	@Test
	public void testSortedSetPerPeso_vuota() {
		Borsa borsa = new Borsa();

		assertTrue(borsa.getSortedSetOrdinatoPerPeso().isEmpty());
	}

	@Test
	public void testSortedSetPerPeso_ordinePeso() {
		Borsa borsa = new Borsa();

		borsa.addAttrezzo(new Attrezzo("b", 8));
		borsa.addAttrezzo(new Attrezzo("a", 1));

		SortedSet<Attrezzo> set = borsa.getSortedSetOrdinatoPerPeso();

		assertEquals("a", set.first().getNome()); // peso minore prima
		assertEquals("b", set.last().getNome());
	}

	@Test
	public void testSortedSetPerPeso_stessoPesoNomeDiversoEntrambiPresenti() {
		Borsa borsa = new Borsa();

		borsa.addAttrezzo(new Attrezzo("ps", 5));
		borsa.addAttrezzo(new Attrezzo("libro", 5));

		SortedSet<Attrezzo> set = borsa.getSortedSetOrdinatoPerPeso();

		assertEquals(2, set.size()); // non si perdono
		assertEquals("libro", set.first().getNome()); // l < p a parità di peso
		assertEquals("ps", set.last().getNome());
	}
}