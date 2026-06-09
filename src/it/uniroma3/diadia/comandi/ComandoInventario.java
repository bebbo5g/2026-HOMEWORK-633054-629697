package it.uniroma3.diadia.comandi;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoInventario extends AbstractComando {

    @Override
    public void esegui(Partita partita, IO io) {
        Borsa borsa = partita.giocatore.borsa;

        if (borsa.isEmpty()) {
            io.mostraMessaggio("La borsa è vuota!");
            return;
        }

        io.mostraMessaggio("Ordinati per peso:");
        List<Attrezzo> perPeso = borsa.getContenutoOrdinatoPerPeso();
        StringBuilder listaSB = new StringBuilder("[ ");
        for (Attrezzo a : perPeso)
            listaSB.append(a.getNome() + ":" + a.getPeso() + " ");
        listaSB.append("]");
        io.mostraMessaggio(listaSB.toString());

        // { libro, piombo, piuma, ps }
        io.mostraMessaggio("Ordinati per nome:");
        SortedSet<Attrezzo> perNome = borsa.getContenutoOrdinatoPerNome();
        StringBuilder setSB = new StringBuilder("{ ");
        for (Attrezzo a : perNome)
            setSB.append(a.getNome() + ":" + a.getPeso() + " ");
        setSB.append("}");
        io.mostraMessaggio(setSB.toString());

        // (1, { piuma }) ; (5, { libro, ps }) ; (10, { piombo })
        io.mostraMessaggio("Raggruppati per peso:");
        Map<Integer, Set<Attrezzo>> raggruppati = borsa.getContenutoRaggruppatoPerPeso();
        StringBuilder mappaSB = new StringBuilder();
        for (Map.Entry<Integer, Set<Attrezzo>> entry : raggruppati.entrySet()) {
            mappaSB.append("(" + entry.getKey() + ", { ");
            for (Attrezzo a : entry.getValue())
                mappaSB.append(a.getNome() + " ");
            mappaSB.append("}) ; ");
        }
        io.mostraMessaggio(mappaSB.toString());
    }
}