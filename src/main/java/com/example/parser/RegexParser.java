package com.example.parser;

public class RegexParser {
    String input;

    public RegexParser(String input) {
        this.input = input;
    }

    public Expr parse() {
        Tokenizer t = new Tokenizer(input);
        ParserState s = new ParserState(t.getTokens());
        return Parser.parse(s);
    }

}
