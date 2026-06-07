package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class ComandoVaiTest {

	private Partita creaPartita() {
		Labirinto labirinto = new LabirintoBuilder().addStanzaIniziale("Atrio").addStanza("Aula N10")
				.addAdiacenza("Atrio", "Aula N10", "sud").addAdiacenza("Aula N10", "Atrio", "nord")
				.addStanzaVincente("Biblioteca").addAdiacenza("Atrio", "Biblioteca", "nord").getLabirinto();
		return new Partita(labirinto);
	}

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
		Partita partita = creaPartita();
		partita.getStanzaCorrente();

		cmd.esegui(partita);

		assertEquals(partita.getStanzaCorrente().getNome(), "Aula N10");
	}

	@Test
	public void testEsegui_direzioneInesistente_stanzaNonCambia() {
		IOSimulator io = new IOSimulator();
		ComandoVai cmd = new ComandoVai("pippo", io);
		Partita partita = creaPartita();
		String stanzaPrima = partita.getStanzaCorrente().getNome();
		cmd.esegui(partita);
		assertEquals(stanzaPrima, partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testEsegui_direzioneInesistente_messaggioDirezioneInesistente() {
		IOSimulator io = new IOSimulator();
		ComandoVai cmd = new ComandoVai("pippo", io);
		Partita partita = creaPartita();
		cmd.esegui(partita);
		assertTrue(io.getOutput().stream().anyMatch(s -> s.contains("Direzione inesistente")));
	}

	@Test
	public void testEsegui_direzioneNull_nonCambiaStanza() {
		IOSimulator io = new IOSimulator();
		ComandoVai cmd = new ComandoVai(null, io);
		Partita partita = creaPartita();
		String stanzaPrima = partita.getStanzaCorrente().getNome();
		cmd.esegui(partita);
		assertEquals(stanzaPrima, partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testEsegui_cfuDiminuisce() {
		IOSimulator io = new IOSimulator();
		ComandoVai cmd = new ComandoVai("sud", io);
		Partita partita = creaPartita();
		int cfuPrima = partita.getGiocatore().getCfu();
		cmd.esegui(partita);
		assertEquals(cfuPrima - 1, partita.getGiocatore().getCfu());
	}

	@Test
	public void testEsegui_direzioneInesistente_cfuNonDiminuisce() {
		IOSimulator io = new IOSimulator();
		ComandoVai cmd = new ComandoVai("pippo", io);
		Partita partita = creaPartita();
		int cfuPrima = partita.getGiocatore().getCfu();
		cmd.esegui(partita);
		assertEquals(cfuPrima, partita.getGiocatore().getCfu());
	}

	@Test
	public void testEsegui_mostraDescrizioneNuovaStanza() {
		IOSimulator io = new IOSimulator();
		ComandoVai cmd = new ComandoVai("sud", io);
		Partita partita = creaPartita();
		cmd.esegui(partita);
		assertTrue(io.getOutput().stream().anyMatch(s -> s.contains("Aula N10")));
	}
}
