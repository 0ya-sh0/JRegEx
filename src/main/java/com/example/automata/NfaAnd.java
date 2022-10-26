package com.example.automata;

public class NfaAnd implements INfa {

    private INfa[] nfas;

    public NfaAnd(INfa... iNfas) {
        nfas = iNfas;
    }

    @Override
    public boolean run(String input) {
        for (INfa iNfa : nfas) {
            if (!iNfa.run(input)) {
                return false;
            }
        }
        return true;
    }

}
