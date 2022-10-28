package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.example.automata.Nfa;
import com.example.parser.RegexParser;

public class JRegEx {
    String regex;
    Nfa nfa;

    JRegEx(String input) {
        regex = input;
        nfa = new RegexParser(input).parse().toNfa();
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
