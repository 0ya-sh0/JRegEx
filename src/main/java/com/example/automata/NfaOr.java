package com.example.automata;

public class NfaOr implements INfa {

    private INfa[] nfas;

    public NfaOr(INfa... iNfas) {
        nfas = iNfas;
    }

    @Override
    public boolean run(String input) {
        for (INfa iNfa : nfas) {
            if (iNfa.run(input)) {
                return true;
            }
        }
        return false;
    }
}
