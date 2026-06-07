package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;

class FabbricaComandiIntrospettivaTest {

	private FabbricaComandiIntrospettiva creaFabbrica() {
		return new FabbricaComandiIntrospettiva(new IOSimulator());
	}

	@Test
	public void testComandoVai() {
		Comando cmd = creaFabbrica().costruisciComando("vai nord");
		assertEquals("vai", cmd.getNome());
		assertEquals("nord", cmd.getParametro());
	}

	@Test
	public void testComandoGuarda() {
		Comando cmd = creaFabbrica().costruisciComando("guarda");
		assertEquals("guarda", cmd.getNome());
	}

	@Test
	public void testComandoAiuto() {
		Comando cmd = creaFabbrica().costruisciComando("aiuto");
		assertEquals("aiuto", cmd.getNome());
	}

	@Test
	public void testComandoFine() {
		Comando cmd = creaFabbrica().costruisciComando("fine");
		assertEquals("fine", cmd.getNome());
	}

	@Test
	public void testComandoPrendi() {
		Comando cmd = creaFabbrica().costruisciComando("prendi osso");
		assertEquals("prendi", cmd.getNome());
		assertEquals("osso", cmd.getParametro());
	}

	@Test
	public void testComandoPosa() {
		Comando cmd = creaFabbrica().costruisciComando("posa osso");
		assertEquals("posa", cmd.getNome());
		assertEquals("osso", cmd.getParametro());
	}

	// ── casi limite ───────────────────────────────────────────

	@Test
	public void testComandoNonEsistente() {
		Comando cmd = creaFabbrica().costruisciComando("vola");
		assertEquals("non-valido", cmd.getNome());
	}

	@Test
	public void testIstruzioneVuota() {
		Comando cmd = creaFabbrica().costruisciComando("");
		assertEquals("non-valido", cmd.getNome());
	}

	@Test
	public void testComandoVaiSenzaParametro() {
		Comando cmd = creaFabbrica().costruisciComando("vai");
		assertEquals("vai", cmd.getNome());
		assertNull(cmd.getParametro());
	}

	@Test
	public void testComandoMaiuscolo() {
		Comando cmd = creaFabbrica().costruisciComando("VAI nord");
		assertEquals("vai", cmd.getNome());
	}

	@Test
	public void testComandoRegala() {
		Comando cmd = creaFabbrica().costruisciComando("regala osso");
		assertEquals("regala", cmd.getNome());
		assertEquals("osso", cmd.getParametro());
	}

}
