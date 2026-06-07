package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class DiaDiaTest {

	@Test
	public void testGiocoCompleto() {
		IOSimulator io = new IOSimulator(Arrays.asList("vai nord"));
		DiaDia gioco = new DiaDia(io);

		gioco.gioca();

		assertTrue(io.getOutput().stream().anyMatch(s -> s.contains("Hai vinto!")));
	}
}
