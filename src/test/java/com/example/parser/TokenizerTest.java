package com.example.parser;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TokenizerTest {
    @Test
    public void empty() {
        Tokenizer t = new Tokenizer("");
        assertTrue(t.tokens.size() == 0);
    }

    @Test
    public void singleSymbol() {
        Tokenizer t = new Tokenizer("a");
        assertTrue(t.tokens.size() == 1);
        assertTrue(t.tokens.get(0).type == TokType.SYMBOL);
    }

    @Test
    public void test0() {
        String input = "(abc)*|xyz";
        Tokenizer t = new Tokenizer(input);
        assertTrue(t.tokens.size() == input.length());
        assertArrayEquals(t.tokens.toArray(), new Token[] {
                new Token(TokType.PLEFT, Character.valueOf('(')),
                new Token(TokType.SYMBOL, Character.valueOf('a')),
                new Token(TokType.SYMBOL, Character.valueOf('b')),
                new Token(TokType.SYMBOL, Character.valueOf('c')),
                new Token(TokType.PRIGHT, Character.valueOf(')')),
                new Token(TokType.STAR, Character.valueOf('*')),
                new Token(TokType.PIPE, Character.valueOf('|')),
                new Token(TokType.SYMBOL, Character.valueOf('x')),
                new Token(TokType.SYMBOL, Character.valueOf('y')),
                new Token(TokType.SYMBOL, Character.valueOf('z')) });
    }
}
