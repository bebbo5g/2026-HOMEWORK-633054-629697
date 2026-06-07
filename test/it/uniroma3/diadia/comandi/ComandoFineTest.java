package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class ComandoFineTest {

	private Partita creaPartita() {
		Labirinto labirinto = new LabirintoBuilder().addStanzaIniziale("Atrio").addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord").getLabirinto();
		return new Partita(labirinto);
	}

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
		Partita partita = creaPartita();

		cmd.esegui(partita);

		assertEquals(partita.isFinita(), true);
	}

	@Test
	public void testEstendeAbstractComando() {
		assertTrue(new ComandoFine(null) instanceof AbstractComando);
	}
}
