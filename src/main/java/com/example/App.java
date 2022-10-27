package com.example;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        String input = "abc*|xy";
        JRegEx regEx = new JRegEx(input);

        System.out.println(regEx.match("abc"));
        System.out.println(regEx.match("abccc"));
        System.out.println(regEx.match("xy"));

        System.out.println(regEx.match("a"));
        System.out.println(regEx.match("x"));
        System.out.println(regEx.match("abcxy"));

    }
}
