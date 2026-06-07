package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;

public class Cane extends AbstractPersonaggio {

	private static final String MESSAGGIO_MORSO = "Bau! Il cane ti morde! Perdi un CFU!";
	private static final int CFU_PERSI = 1;

	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		int cfuAttuali = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(cfuAttuali - CFU_PERSI);
		return MESSAGGIO_MORSO;
	}
}
