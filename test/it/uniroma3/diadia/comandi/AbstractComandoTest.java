package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;

public class AbstractComandoTest {

	@Test
	public void testAiuto_getParametroNull() {
		assertNull(new ComandoAiuto(new IOSimulator()).getParametro());
	}

	@Test
	public void testFine_getParametroNull() {
		assertNull(new ComandoFine(new IOSimulator()).getParametro());
	}

	@Test
	public void testGuarda_getParametroNull() {
		assertNull(new ComandoGuarda(new IOSimulator()).getParametro());
	}

	@Test
	public void testVai_getParametro() {
		assertEquals("nord", new ComandoVai("nord", new IOSimulator()).getParametro());
	}

	@Test
	public void testPrendi_getParametro() {
		assertEquals("osso", new ComandoPrendi("osso", new IOSimulator()).getParametro());
	}

	@Test
	public void testPosa_getParametro() {
		assertEquals("palla", new ComandoPosa("palla", new IOSimulator()).getParametro());
	}

	@Test
	public void testNonValido_getParametro() {
		assertEquals("vola", new ComandoNonValido("vola", new IOSimulator()).getParametro());
	}

	@Test
	public void testTuttiEstendonoAbstractComando() {
		assertTrue(new ComandoAiuto(new IOSimulator()) instanceof AbstractComando);
		assertTrue(new ComandoFine(new IOSimulator()) instanceof AbstractComando);
		assertTrue(new ComandoGuarda(new IOSimulator()) instanceof AbstractComando);
		assertTrue(new ComandoVai("nord", new IOSimulator()) instanceof AbstractComando);
		assertTrue(new ComandoPrendi("x", new IOSimulator()) instanceof AbstractComando);
		assertTrue(new ComandoPosa("x", new IOSimulator()) instanceof AbstractComando);
		assertTrue(new ComandoNonValido("x", new IOSimulator()) instanceof AbstractComando);
	}
}
