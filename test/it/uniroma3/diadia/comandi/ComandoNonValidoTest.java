package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;

public class ComandoNonValidoTest {
	@Test
	public void testGetNome() {
		ComandoNonValido cmd = new ComandoNonValido("fake", null);
		assertEquals("non-valido", cmd.getNome());
	}

	@Test
	public void testGetParametro() {
		ComandoNonValido cmd = new ComandoNonValido("my-cmd", null);
		assertEquals("my-cmd", cmd.getParametro());
	}

	@Test
	public void testEsegui_mostraMessaggioNonValido() {
		IOSimulator io = new IOSimulator();
		ComandoNonValido cmd = new ComandoNonValido("invalid", io);
		cmd.esegui(null);
		assertTrue(io.getOutput().stream().anyMatch(s -> s.contains("invalid")));
	}
}
