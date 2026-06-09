package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

	private String ciboPreferito;
	private Attrezzo attrezzoInBocca;

	public Cane(String nome, String presentazione, String ciboPreferito, Attrezzo attrezzoInBocca) {
		super(nome, presentazione);
		this.ciboPreferito = ciboPreferito;
		this.attrezzoInBocca = attrezzoInBocca;
	}

	@Override
	public String agisci(Partita partita) {
		partita.giocatore.setCfu(partita.giocatore.getCfu() - 1);
		return "Il cane ti ha morso! Hai perso un CFU!";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo.getNome().equals(this.ciboPreferito)) {
			if (this.attrezzoInBocca != null) {
				partita.lab.getStanzaCorrente().addAttrezzo(this.attrezzoInBocca);
				this.attrezzoInBocca = null;
			}
			return "Il cane ha accettato " + attrezzo.getNome() + " e ha lasciato cadere un attrezzo!";
		}
		// regalo rifiutato: restituisce l'attrezzo alla borsa del giocatore
		partita.giocatore.borsa.addAttrezzo(attrezzo);
		partita.giocatore.setCfu(partita.giocatore.getCfu() - 1);
		return "Il cane non vuole " + attrezzo.getNome() + " e ti ha morso! Hai perso un CFU!";
	}
}