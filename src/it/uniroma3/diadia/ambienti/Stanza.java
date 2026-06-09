package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class Stanza {

	public String nome;
	public Set<Attrezzo> attrezzi;
	protected Map<Direzione, Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio;

	public Stanza(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<>();
		this.attrezzi = new HashSet<>();
	}

	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		this.stanzeAdiacenti.put(direzione, stanza);
	}

	public Stanza getStanzaAdiacente(Direzione direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	public String getNome() {
		return this.nome;
	}

	public String getDescrizione() {
		return this.toString();
	}

	public Set<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.add(attrezzo);
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.contains(new Attrezzo(nomeAttrezzo, 0));
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (Attrezzo a : this.attrezzi)
			if (a.getNome().equals(nomeAttrezzo))
				return a;
		return null;
	}

	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo);
	}

	public Set<Direzione> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (Direzione direzione : this.stanzeAdiacenti.keySet())
			if (direzione != null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi)
			if (attrezzo != null)
				risultato.append(attrezzo.toString() + " ");
		if (this.personaggio != null)
			risultato.append("\nPersonaggio: " + this.personaggio.getNome());
		return risultato.toString();
	}
}