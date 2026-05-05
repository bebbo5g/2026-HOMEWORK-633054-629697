package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;

public class ComandoNonValidoTest {
	@Test
	public void testGetNome() {
		ComandoNonValido cmd = new ComandoNonValido("fake", null);

		assertEquals(cmd.getNome(), "non-valido");
	}

	@Test
	public void testGetParametro() {
		ComandoNonValido cmd = new ComandoNonValido("my-cmd", null);

		assertEquals(cmd.getParametro(), "my-cmd");
	}

	@Test
	public void testEsegui() {
		IOSimulator io = new IOSimulator();
		ComandoNonValido cmd = new ComandoNonValido("invalid", io);

		cmd.esegui(null);

		assertEquals(io.getOutput(), "Comando \"invalid\" non valido");
	}
}
