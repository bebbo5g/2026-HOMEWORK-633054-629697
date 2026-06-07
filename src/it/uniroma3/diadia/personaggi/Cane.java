package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

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

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo.getNome().equals("osso")) {
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("osso rosicchiato", 1));
			return "Woof! Grazie per l'osso! *scodinzola*";
		}
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		return "Bau! Questo non mi piace! *morde* Perdi 1 CFU!";
	}
}
