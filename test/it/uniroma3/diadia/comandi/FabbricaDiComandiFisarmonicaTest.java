package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FabbricaDiComandiFisarmonicaTest {
	FabbricaDiComandiFisarmonica fabbrica;

	@BeforeEach
	void setUp() {
		this.fabbrica = new FabbricaDiComandiFisarmonica(null);
	}

	@Test
	public void testEmptyCmd() {
		Comando cmd = this.fabbrica.costruisciComando("");

		assertEquals(cmd.getNome(), "non-valido");
		assertEquals(cmd.getParametro(), "<empty>");
	}

	@Test
	public void testAiuto() {
		Comando cmd = this.fabbrica.costruisciComando("aiuto");

		assertEquals(cmd.getNome(), "aiuto");
		assertEquals(cmd.getParametro(), null);
	}

	@Test
	public void testFine() {
		Comando cmd = this.fabbrica.costruisciComando("fine");

		assertEquals(cmd.getNome(), "fine");
		assertEquals(cmd.getParametro(), null);
	}

	@Test
	public void testGuarda() {
		Comando cmd = this.fabbrica.costruisciComando("guarda");

		assertEquals(cmd.getNome(), "guarda");
		assertEquals(cmd.getParametro(), null);
	}

	@Test
	public void testPosa() {
		Comando cmd = this.fabbrica.costruisciComando("posa palla");

		assertEquals(cmd.getNome(), "posa");
		assertEquals(cmd.getParametro(), "palla");
	}

	@Test
	public void testPrendi() {
		Comando cmd = this.fabbrica.costruisciComando("prendi bastone");

		assertEquals(cmd.getNome(), "prendi");
		assertEquals(cmd.getParametro(), "bastone");
	}

	@Test
	public void testVai() {
		Comando cmd = this.fabbrica.costruisciComando("vai sud");

		assertEquals(cmd.getNome(), "vai");
		assertEquals(cmd.getParametro(), "sud");
	}

	@Test
	public void testComandoNonValido() {
		Comando cmd = this.fabbrica.costruisciComando("my-cmd");

		assertEquals(cmd.getNome(), "non-valido");
		assertEquals(cmd.getParametro(), "my-cmd");
	}
}
