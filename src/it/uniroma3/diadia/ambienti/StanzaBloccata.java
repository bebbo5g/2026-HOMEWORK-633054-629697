package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	final private String direzioneBloccata;
	final private String attrezzoSblocca;

	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSblocca) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSblocca = attrezzoSblocca;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (direzione.equals(this.direzioneBloccata) && !super.hasAttrezzo(this.attrezzoSblocca)) {
			return this;
		}

		return super.getStanzaAdiacente(direzione);
	}

}
