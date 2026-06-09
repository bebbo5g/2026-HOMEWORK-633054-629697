package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	public Set<Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashSet<>(); // speriamo bastino...
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()) {
			return false;
		}
		return this.attrezzi.add(attrezzo);
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (Attrezzo a : this.attrezzi) {
			if (a.getNome().equals(nomeAttrezzo)) {
				return a;
			}
		}
		return null;
	}

	public int getPeso() {
		int peso = 0;
		for (Attrezzo a : this.attrezzi) {
			if (a != null) {
				peso += a.getPeso();
			}
		}
		return peso;
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.contains(new Attrezzo(nomeAttrezzo, 0));
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo daRimuovere = getAttrezzo(nomeAttrezzo);
		if (daRimuovere != null)
			this.attrezzi.remove(daRimuovere);
		return daRimuovere;
	}

	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			for (Attrezzo a : this.attrezzi)
				s.append(a.toString() + " ");
		} else
			s.append("Borsa vuota");
		return s.toString();
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> risultato = new ArrayList<>(this.attrezzi);
		risultato.sort(new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				if (a1.getPeso() == a2.getPeso()) {
					return a1.getNome().compareTo(a2.getNome());
				}
				return a1.getPeso() - a2.getPeso();
			}
		});
		return risultato;
	}

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		return new TreeSet<>(this.attrezzi);
	}

	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> risultato = new HashMap<>();
		for (Attrezzo a : attrezzi) {
			int p = a.getPeso();
			if (!risultato.containsKey(p)) {
				risultato.put(p, new HashSet<>());
			}
			risultato.get(p).add(a);
		}
		return risultato;
	}

	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> ris = new TreeSet<>(new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				if (a1.getPeso() == a2.getPeso()) {
					return a1.getNome().compareTo(a2.getNome());
				}
				return a1.getPeso() - a2.getPeso();
			}
		});
		ris.addAll(this.attrezzi);
		return ris;
	}
}
