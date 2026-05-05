package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;

public class ComandoVaiTest {
	@Test
	public void testGetNome() {
		ComandoVai cmd = new ComandoVai("nord", null);

		assertEquals(cmd.getNome(), "vai");
	}

	@Test
	public void testGetParametro() {
		ComandoVai cmd = new ComandoVai("sud", null);

		assertEquals(cmd.getParametro(), "sud");
	}

	@Test
	public void testEsegui() {
		IOSimulator io = new IOSimulator();
		ComandoVai cmd = new ComandoVai("sud", io);
		Partita partita = new Partita();
		partita.getStanzaCorrente();

		cmd.esegui(partita);

		assertEquals(partita.getStanzaCorrente().getNome(), "Aula N10");
	}
}
