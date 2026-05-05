package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import it.uniroma3.diadia.Partita;

public class ComandoFineTest {
	@Test
	public void testGetNome() {
		ComandoFine cmd = new ComandoFine(null);

		assertEquals(cmd.getNome(), "fine");
	}

	@Test
	public void testGetParametro() {
		ComandoFine cmd = new ComandoFine(null);

		assertEquals(cmd.getParametro(), null);
	}

	@Test
	public void testEsegui() {
		ComandoFine cmd = new ComandoFine(null);
		Partita partita = new Partita();

		cmd.esegui(partita);

		assertEquals(partita.isFinita(), true);
	}
}
