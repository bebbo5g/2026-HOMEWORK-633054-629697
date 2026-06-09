package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {

	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, "
			+ "con una mia magica azione, troverai un nuovo oggetto per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";

	private Attrezzo attrezzo;

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		if (this.attrezzo != null) {
			partita.lab.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			return MESSAGGIO_DONO;
		}
		return MESSAGGIO_SCUSE;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		Attrezzo attrezzoRidotto = new Attrezzo(attrezzo.getNome(), attrezzo.getPeso() / 2);
		partita.lab.getStanzaCorrente().addAttrezzo(attrezzoRidotto);
		this.attrezzo = attrezzoRidotto; // ← ora il mago ha qualcosa da donare!
		return "Il mago ha dimezzato il peso di " + attrezzo.getNome()
				+ " e lo ha lasciato cadere! Ora sembra più potente...";
	}
}