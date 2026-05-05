package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected {
	static final private int SOGLIA_MAGICA_DEFAULT = 2;
	final protected int sogliaMagica;
	protected int contatoreAttrezziPosati = 0;

	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	public StanzaMagicaProtected(String nome, int sogliaMagica) {
		super(nome);
		this.sogliaMagica = sogliaMagica;
	}

	protected Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		return new Attrezzo(new StringBuilder(attrezzo.getNome()).reverse().toString(), attrezzo.getPeso() * 2);
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		Attrezzo attr = this.contatoreAttrezziPosati++ > this.sogliaMagica ? this.modificaAttrezzo(attrezzo) : attrezzo;

		return super.addAttrezzo(attr);
	}

}
