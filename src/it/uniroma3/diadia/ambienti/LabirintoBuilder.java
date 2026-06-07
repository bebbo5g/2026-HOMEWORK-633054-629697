package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class LabirintoBuilder {
	private Labirinto labirinto;
	private Map<String, Stanza> stanze;
	private Stanza ultimaStanzaAggiunta;

	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.stanze = new HashMap<>();
	}

	public LabirintoBuilder addStanzaIniziale(String nome) {
		Stanza s = new Stanza(nome);

		this.stanze.put(nome, s);
		this.labirinto.setIniziale(s);
		this.ultimaStanzaAggiunta = s;

		return this;
	}

	public LabirintoBuilder addStanzaVincente(String nome) {
		Stanza s = this.stanze.get(nome);

		if (s == null) {
			s = new Stanza(nome);
			this.stanze.put(nome, s);
		}

		this.labirinto.setFinale(s);
		this.ultimaStanzaAggiunta = s;

		return this;
	}

	public LabirintoBuilder addStanza(String nome) {
		Stanza s = new Stanza(nome);

		this.stanze.put(nome, s);
		this.ultimaStanzaAggiunta = s;

		return this;
	}

	public LabirintoBuilder addStanzaBuia(String nome, String attrezzoLuce) {
		Stanza s = new StanzaBuia(nome, attrezzoLuce);

		this.stanze.put(nome, s);
		this.ultimaStanzaAggiunta = s;

		return this;
	}

	public LabirintoBuilder addStanzaMagica(String nome) {
		Stanza s = new StanzaMagica(nome);

		this.stanze.put(nome, s);
		this.ultimaStanzaAggiunta = s;

		return this;
	}

	public LabirintoBuilder addStanzaBloccata(String nome, String direzione, String attrezzoSblocca) {
		Stanza s = new StanzaBloccata(nome, direzione, attrezzoSblocca);

		this.stanze.put(nome, s);
		this.ultimaStanzaAggiunta = s;

		return this;
	}

	public LabirintoBuilder addAdiacenza(String da, String a, String direzione) {
		Stanza stanzaDa = this.stanze.get(da);
		Stanza stanzaA = this.stanze.get(a);
		if (stanzaDa != null && stanzaA != null) {
			stanzaDa.impostaStanzaAdiacente(direzione, stanzaA);
		}
		return this;
	}

	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		if (this.ultimaStanzaAggiunta != null)
			this.ultimaStanzaAggiunta.addAttrezzo(new Attrezzo(nome, peso));
		return this;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	// restituisce la mappa nome→stanza (usata dai test per verificare contenuto)
	public Map<String, Stanza> getListaStanze() {
		return this.stanze;
	}

	// versione con soglia esplicita (i test usano addStanzaMagica(nome, soglia))
	public LabirintoBuilder addStanzaMagica(String nome, int sogliaMagica) {
		Stanza s = new StanzaMagica(nome, sogliaMagica);
		this.stanze.put(nome, s);
		this.ultimaStanzaAggiunta = s;
		return this;
	}

	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso, String nomeStanza) {
		Stanza s = this.stanze.get(nomeStanza);
		if (s != null)
			s.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
		return this;
	}

	public LabirintoBuilder addPersonaggio(AbstractPersonaggio personaggio, String nomeStanza) {
		Stanza s = this.stanze.get(nomeStanza);
		if (s != null)
			s.setPersonaggio(personaggio);
		return this;
	}
}
