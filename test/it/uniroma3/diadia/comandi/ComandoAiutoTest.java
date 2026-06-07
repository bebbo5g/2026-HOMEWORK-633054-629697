package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;

public class ComandoAiutoTest {
	@Test
	public void testGetNome() {
		ComandoAiuto cmd = new ComandoAiuto(null);
		assertEquals("aiuto", cmd.getNome());
	}

	@Test
	public void testGetParametro() {
		assertNull(new ComandoAiuto(null).getParametro());
	}

	@Test
	public void testEsegui_mostraTuttiIComandi() {
		IOSimulator io = new IOSimulator();

		new ComandoVai("nord", io);
		new ComandoPrendi("x", io);
		new ComandoPosa("x", io);
		new ComandoGuarda(io);
		new ComandoFine(io);
		new ComandoAiuto(io).esegui(null);

		for (String nome : AbstractComando.getNomiComandi())
			assertTrue(io.getOutput().stream().anyMatch(s -> s.contains(nome)), "Manca il comando: " + nome);
	}

	@Test
	public void testNomiComandiNonContieneNonValido() {
		new ComandoNonValido("x", null);
		assertFalse(AbstractComando.getNomiComandi().contains("nonvalido"));
	}
}
