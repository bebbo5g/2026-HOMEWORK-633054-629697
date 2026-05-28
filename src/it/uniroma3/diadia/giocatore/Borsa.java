package it.uniroma3.diadia.giocatore;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null)
			return false;

		if (this.getPeso() + attrezzo.getPeso() > this.pesoMax)
			return false;

		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}

	public int getPesoMax() {
		return this.pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public int getPeso() {
		int peso = 0;

		for (Attrezzo a : this.attrezzi.values())
			peso += a.getPeso();

		return peso;
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	public boolean isPiena() {
		return this.getPeso() >= this.pesoMax;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}

	public Attrezzo removeAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null)
			return null;
		return this.attrezzi.remove(attrezzo.getNome());
	}

	@Override
	public String toString() {
		if (!this.isEmpty()) {
			StringBuilder s = new StringBuilder();
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");

			for (Attrezzo a : this.attrezzi.values())
				s.append(a.toString()).append(" ");
			return s.toString();
		}

		return "Borsa vuota";
	}

	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

	public Collection<Attrezzo> getAttrezzi() {
		return Collections.unmodifiableCollection(this.attrezzi.values());
	}

}
