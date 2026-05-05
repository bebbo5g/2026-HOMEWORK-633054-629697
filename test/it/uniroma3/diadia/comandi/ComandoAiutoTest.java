package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;

public class ComandoAiutoTest {
	@Test
	public void testGetNome() {
		ComandoAiuto cmd = new ComandoAiuto(null);

		assertEquals(cmd.getNome(), "aiuto");
	}

	@Test
	public void testGetParametro() {
		ComandoAiuto cmd = new ComandoAiuto(null);

		assertEquals(cmd.getParametro(), null);
	}

	@Test
	public void testEsegui() {
		IOSimulator io = new IOSimulator();
		ComandoAiuto cmd = new ComandoAiuto(io);

		cmd.esegui(null);

		assertEquals(io.getOutput(), "vai aiuto fine prendi posa guarda");
	}
}
