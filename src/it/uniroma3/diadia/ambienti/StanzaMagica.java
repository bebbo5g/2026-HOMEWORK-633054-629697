package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza {
	static final private int SOGLIA_MAGICA_DEFAULT = 2;
	final private int sogliaMagica;
	private int contatoreAttrezziPosati = 0;

	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	public StanzaMagica(String nome, int sogliaMagica) {
		super(nome);
		this.sogliaMagica = sogliaMagica;
	}

	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		return new Attrezzo(new StringBuilder(attrezzo.getNome()).reverse().toString(), attrezzo.getPeso() * 2);
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		Attrezzo attr = this.contatoreAttrezziPosati++ > this.sogliaMagica ? this.modificaAttrezzo(attrezzo) : attrezzo;

		return super.addAttrezzo(attr);
	}

}
