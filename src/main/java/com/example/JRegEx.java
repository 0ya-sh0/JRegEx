package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.example.automata.Nfa;
import com.example.parser.Expr;
import com.example.parser.RegexParser;

public class JRegEx {
    String regex;
    Expr expr;
    Nfa nfa;

    JRegEx(String input) {
        regex = input;
        RegexParser p = new RegexParser(input);
        expr = p.parse();
        nfa = expr.toNfa();
        nfa.removeExtraStates();
    }

    public boolean match(String input) {
        return nfa.run(input);
    }

    public void wrtieToFile() {
        File f = new File(regex + ".DOT");
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(nfa.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
