package com.example.parser;

import java.util.ArrayList;
import java.util.List;

enum TokType {
    SYMBOL,
    PLEFT,
    PRIGHT,
    STAR,
    PIPE
}

class Token {
    public Character value;
    public TokType type;

    Token(TokType t, Character ch) {
        type = t;
        value = ch;
    }

    public String toString() {
        return type.toString() + "<" + value + ">";
    }
}

class Tokenizer {
    String input;
    List<Token> tokens;

    public Tokenizer(String input) {
        this.input = input;
        tokens = new ArrayList<>();
        for (char ch : input.toCharArray()) {
            tokens.add(convert(ch));
        }
    }

    public List<Token> getTokens() {
        return tokens;
    }

    private Token convert(char ch) {
        TokType type;
        switch (ch) {
            case '(':
                type = TokType.PLEFT;
                break;
            case ')':
                type = TokType.PRIGHT;
                break;
            case '*':
                type = TokType.STAR;
                break;
            case '|':
                type = TokType.PIPE;
                break;
            default:
                type = TokType.SYMBOL;
        }
        return new Token(type, Character.valueOf(ch));
    }
}
