package com.example;

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
    }

    public boolean match(String input) {
        return nfa.run(input);
    }
}
