package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DiaDiaTest {

	@Test
	public void testGiocoCompleto() {
		String[] input = { "vai nord" };
		IOSimulator io = new IOSimulator(input);
		DiaDia gioco = new DiaDia(io);

		gioco.gioca();

		/*
		 * io.getOutput(); // skip MESSAGGIO_BENVENUTO
		 * 
		 * assertEquals(io.getOutput().contains("Hai vinto!"), true);
		 */

		boolean haVinto = false;
		String msg;
		while ((msg = io.getOutput()) != null) {
			if (msg.contains("Hai vinto!")) {
				haVinto = true;
				break;
			}
		}

		assertTrue(haVinto);
	}
}
