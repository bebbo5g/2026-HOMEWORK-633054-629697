package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;

public class ComandoGuardaTest {
	@Test
	public void testGetNome() {
		ComandoGuarda cmd = new ComandoGuarda(null);

		assertEquals(cmd.getNome(), "guarda");
	}

	@Test
	public void testGetParametro() {
		ComandoGuarda cmd = new ComandoGuarda(null);

		assertEquals(cmd.getParametro(), null);
	}

	@Test
	public void testEsegui() {
		IOSimulator io = new IOSimulator();
		ComandoGuarda cmd = new ComandoGuarda(io);

		Partita partita = new Partita();

		cmd.esegui(partita);

		assertEquals(io.getOutput(), partita.getStanzaCorrente().getDescrizione());
	}
}
