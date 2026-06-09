package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Stanza stanzaCorrente;

	private Labirinto() {
	}

	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public Stanza getStanzaCorrente() {
		return stanzaCorrente;
	}

	public void setStanzaCorrente(Stanza s) {
		this.stanzaCorrente = s;
	}

	public void setStanzaVincente(Stanza s) {
		this.stanzaVincente = s;
	}

	public Stanza getStanza(String nome) {
		return builder_stanze_cache.get(nome);
	}

	private Map<String, Stanza> builder_stanze_cache = new HashMap<>();

	public static class LabirintoBuilder {

		private final Labirinto labirinto;
		private final Map<String, Stanza> stanze;
		private Stanza ultimaStanzaAggiunta;
		private boolean inizialeImpostata = false;

		private LabirintoBuilder() {
			this.labirinto = new Labirinto();
			this.stanze = new HashMap<>();
		}

		public LabirintoBuilder addStanza(String nome) {
			Stanza s = new Stanza(nome);
			stanze.put(nome, s);
			ultimaStanzaAggiunta = s;
			return this;
		}

		public LabirintoBuilder addStanzaIniziale(String nome) {
			if (!stanze.containsKey(nome))
				addStanza(nome);
			ultimaStanzaAggiunta = stanze.get(nome);
			labirinto.stanzaIniziale = ultimaStanzaAggiunta;
			labirinto.stanzaCorrente = ultimaStanzaAggiunta;
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String nome) {
			if (!stanze.containsKey(nome))
				addStanza(nome);
			ultimaStanzaAggiunta = stanze.get(nome);
			labirinto.stanzaVincente = ultimaStanzaAggiunta;
			return this;
		}

		public LabirintoBuilder addAttrezzo(String nomeStanza, String nome, int peso) {
			Stanza s = stanze.get(nomeStanza);
			if (s != null)
				s.addAttrezzo(new Attrezzo(nome, peso));
			return this;
		}

		public LabirintoBuilder addAdiacenza(String da, String a, Direzione dir) {
			Stanza s1 = stanze.get(da), s2 = stanze.get(a);
			if (s1 != null && s2 != null)
				s1.impostaStanzaAdiacente(dir, s2);
			return this;
		}

		public LabirintoBuilder addStanzaMagica(String nome) {
			Stanza s = new StanzaMagica(nome);
			stanze.put(nome, s);
			ultimaStanzaAggiunta = s;
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nome, String attLucente) {
			Stanza s = new StanzaBuia(nome, attLucente);
			stanze.put(nome, s);
			ultimaStanzaAggiunta = s;
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nome, String att, String dir) {
			Stanza s = new StanzaBloccata(nome, att, dir);
			stanze.put(nome, s);
			ultimaStanzaAggiunta = s;
			return this;
		}

		public LabirintoBuilder addCane(String nomeStanza, String nome, String pres, String cibo, Attrezzo att) {
			Stanza s = stanze.get(nomeStanza);
			if (s != null)
				s.setPersonaggio(new Cane(nome, pres, cibo, att));
			return this;
		}

		public LabirintoBuilder addStrega(String nomeStanza, String nome, String pres) {
			Stanza s = stanze.get(nomeStanza);
			if (s != null)
				s.setPersonaggio(new Strega(nome, pres));
			return this;
		}

		public LabirintoBuilder addMago(String nomeStanza, String nome, String pres, Attrezzo att) {
			Stanza s = stanze.get(nomeStanza);
			if (s != null)
				s.setPersonaggio(new Mago(nome, pres, att));
			return this;
		}

		public Labirinto getLabirinto() {
			labirinto.builder_stanze_cache = this.stanze;
			return this.labirinto;
		}

		public LabirintoBuilder addStanzaInizialeBloccata(String nome, String attSbloccante, String dirBloccata) {
			Stanza s = new StanzaBloccata(nome, attSbloccante, dirBloccata);
			stanze.put(nome, s);
			ultimaStanzaAggiunta = s;
			labirinto.stanzaIniziale = s;
			labirinto.stanzaCorrente = s;
			return this;
		}
	}
}