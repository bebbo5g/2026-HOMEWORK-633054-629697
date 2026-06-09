package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AbstractComandoTest {

	@Test
	public void testAiuto_getParametroNull() {
		ComandoAiuto comando = new ComandoAiuto();
		assertNull(comando.getParametro());
	}

	@Test
	public void testFine_getParametroNull() {
		ComandoFine comando = new ComandoFine();
		assertNull(comando.getParametro());
	}

	@Test
	public void testGuarda_getParametroNull() {
		ComandoGuarda comando = new ComandoGuarda();
		assertNull(comando.getParametro());
	}

	@Test
	public void testVai_getParametro() {
		ComandoVai comando = new ComandoVai();
		comando.setParametro("nord");
		assertEquals("nord", comando.getParametro());
	}

	@Test
	public void testPrendi_getParametro() {
		ComandoPrendi comando = new ComandoPrendi();
		comando.setParametro("osso");
		assertEquals("osso", comando.getParametro());
	}

	@Test
	public void testPosa_getParametro() {
		ComandoPosa comando = new ComandoPosa();
		comando.setParametro("palla");
		assertEquals("palla", comando.getParametro());
	}

	@Test
	public void testNonValido_getParametro() {
		ComandoNonValido comando = new ComandoNonValido();
		comando.setParametro("vola");
		assertEquals("vola", comando.getParametro());
	}

	@Test
	public void testTuttiEstendonoAbstractComando() {
		assertTrue(new ComandoAiuto() instanceof AbstractComando);
		assertTrue(new ComandoFine() instanceof AbstractComando);
		assertTrue(new ComandoGuarda() instanceof AbstractComando);
		assertTrue(new ComandoVai() instanceof AbstractComando);
		assertTrue(new ComandoPrendi() instanceof AbstractComando);
		assertTrue(new ComandoPosa() instanceof AbstractComando);
		assertTrue(new ComandoNonValido() instanceof AbstractComando);
	}
}
