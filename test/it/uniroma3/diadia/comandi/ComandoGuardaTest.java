package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class ComandoGuardaTest {
	private Partita creaPartita() {
		Labirinto labirinto = new LabirintoBuilder().addStanzaIniziale("Atrio").addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord").getLabirinto();
		return new Partita(labirinto);
	}

	@Test
	public void testGetNome() {
		ComandoGuarda cmd = new ComandoGuarda(null);
		assertEquals("guarda", cmd.getNome());
	}

	@Test
	public void testGetParametro() {
		ComandoGuarda cmd = new ComandoGuarda(null);
		assertNull(cmd.getParametro());
	}

	@Test
	public void testEsegui_mostraDescrizioneStanzaCorrente() {
		IOSimulator io = new IOSimulator();
		ComandoGuarda cmd = new ComandoGuarda(io);
		Partita partita = creaPartita();
		cmd.esegui(partita);
		assertTrue(io.getOutput().stream().anyMatch(s -> s.contains(partita.getStanzaCorrente().getNome())));
	}

	@Test
	public void testEsegui_mostraUscite() {
		IOSimulator io = new IOSimulator();
		ComandoGuarda cmd = new ComandoGuarda(io);
		Partita partita = creaPartita();
		cmd.esegui(partita);
		assertTrue(io.getOutput().stream().anyMatch(s -> s.contains("nord")));
	}
}
