package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
		if (this.isEmpty())
			return "Borsa vuota";

		StringBuilder s = new StringBuilder();

		s.append("Contenuto borsa (").append(this.getPeso()).append("kg/").append(this.pesoMax).append("kg):\n");
		s.append("  Per peso: ").append(this.getContenutoOrdinatoPerPeso()).append("\n");
		s.append("  Per nome: ").append(this.getContenutoOrdinatoPerNome()).append("\n");
		s.append("  Per peso raggruppato: ").append(this.getContenutoRaggruppatoPerPeso());

		return s.toString();
	}

	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

	public Collection<Attrezzo> getAttrezzi() {
		return Collections.unmodifiableCollection(this.attrezzi.values());
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> lista = new ArrayList<>(this.attrezzi.values());
		lista.sort(Comparator.comparingInt(Attrezzo::getPeso).thenComparing(Attrezzo::getNome));
		return lista;
	}

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> insieme = new TreeSet<>(Comparator.comparing(Attrezzo::getNome));
		insieme.addAll(this.attrezzi.values());
		return insieme;
	}

	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> mappa = new HashMap<>();

		for (Attrezzo a : this.attrezzi.values())
			mappa.computeIfAbsent(a.getPeso(), k -> new HashSet<>()).add(a);

		return mappa;
	}

}
