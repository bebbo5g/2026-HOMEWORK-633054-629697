package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

    private static final String PACKAGE_COMANDI = "it.uniroma3.diadia.comandi";

    @Override
    public Comando costruisciComando(String istruzione) {
        Scanner scannerDiParole = new Scanner(istruzione);
        String nomeComando = null;
        String parametro = null;
        Comando comando = null;

        if (scannerDiParole.hasNext())
            nomeComando = scannerDiParole.next();
        if (scannerDiParole.hasNext())
            parametro = scannerDiParole.next();

        try {
            String nomeClasse = PACKAGE_COMANDI + ".Comando"
                    + Character.toUpperCase(nomeComando.charAt(0))
                    + nomeComando.substring(1);
            Class<?> classe = Class.forName(nomeClasse);
            comando = (Comando) classe.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            comando = new ComandoNonValido();
        }

        comando.setParametro(parametro);
        scannerDiParole.close();
        return comando;
    }
}