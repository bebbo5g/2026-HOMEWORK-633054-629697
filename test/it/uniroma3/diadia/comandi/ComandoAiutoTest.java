package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
		ComandoAiuto cmd = new ComandoAiuto(null);
		assertNull(cmd.getParametro());
	}

	@Test
	public void testEsegui_mostraComandi() {
		IOSimulator io = new IOSimulator();
		ComandoAiuto cmd = new ComandoAiuto(io);
		cmd.esegui(null);
		assertTrue(io.getOutput().stream().anyMatch(s -> s.contains("vai")));
	}

	@Test
	public void testEsegui_mostraFine() {
		IOSimulator io = new IOSimulator();
		ComandoAiuto cmd = new ComandoAiuto(io);
		cmd.esegui(null);
		assertTrue(io.getOutput().stream().anyMatch(s -> s.contains("fine")));
	}
}
