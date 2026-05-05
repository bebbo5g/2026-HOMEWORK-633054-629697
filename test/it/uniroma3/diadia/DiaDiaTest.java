package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DiaDiaTest {

	@Test
	public void testGiocoCompleto() {
		String[] input = { "vai nord" };
		IOSimulator io = new IOSimulator(input);
		DiaDia gioco = new DiaDia(io);

		gioco.gioca();

		io.getOutput(); // skip MESSAGGIO_BENVENUTO

		assertEquals(io.getOutput().contains("Hai vinto!"), true);
	}
}
