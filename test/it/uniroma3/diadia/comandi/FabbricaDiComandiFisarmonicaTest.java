package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FabbricaDiComandiFisarmonicaTest {

	private FabbricaDiComandiFisarmonica fabbrica;

	@BeforeEach
	public void setUp() {
		this.fabbrica = new FabbricaDiComandiFisarmonica();
	}

	@Test
	public void testEmptyCmd() {
		Comando cmd = this.fabbrica.costruisciComando("");

		assertEquals(ComandoNonValido.class, cmd.getClass());
		// Effettuiamo il cast ad AbstractComando per poter chiamare getParametro()
		assertNull(((AbstractComando) cmd).getParametro());
	}

	@Test
	public void testAiuto() {
		Comando cmd = this.fabbrica.costruisciComando("aiuto");

		assertEquals(ComandoAiuto.class, cmd.getClass());
		assertNull(((AbstractComando) cmd).getParametro());
	}

	@Test
	public void testFine() {
		Comando cmd = this.fabbrica.costruisciComando("fine");

		assertEquals(ComandoFine.class, cmd.getClass());
		assertNull(((AbstractComando) cmd).getParametro());
	}

	@Test
	public void testGuarda() {
		Comando cmd = this.fabbrica.costruisciComando("guarda");

		assertEquals(ComandoGuarda.class, cmd.getClass());
		assertNull(((AbstractComando) cmd).getParametro());
	}

	@Test
	public void testPosa() {
		Comando cmd = this.fabbrica.costruisciComando("posa palla");

		assertEquals(ComandoPosa.class, cmd.getClass());
		assertEquals("palla", ((AbstractComando) cmd).getParametro());
	}

	@Test
	public void testPrendi() {
		Comando cmd = this.fabbrica.costruisciComando("prendi bastone");

		assertEquals(ComandoPrendi.class, cmd.getClass());
		assertEquals("bastone", ((AbstractComando) cmd).getParametro());
	}

	@Test
	public void testVai() {
		Comando cmd = this.fabbrica.costruisciComando("vai sud");

		assertEquals(ComandoVai.class, cmd.getClass());
		assertEquals("sud", ((AbstractComando) cmd).getParametro());
	}

	@Test
	public void testComandoNonValido() {
		Comando cmd = this.fabbrica.costruisciComando("my-cmd");

		assertEquals(ComandoNonValido.class, cmd.getClass());
		assertNull(((AbstractComando) cmd).getParametro());
	}
}