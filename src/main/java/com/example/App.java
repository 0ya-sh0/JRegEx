package com.example;

import com.example.parser.Expr;
import com.example.parser.RegexParser;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        String input = "(ab|c)*z";
        RegexParser p = new RegexParser(input);
        Expr e = p.parse();
        System.out.println(e);
    }
}
