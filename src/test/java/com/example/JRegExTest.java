package com.example;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JRegExTest {
    @Test
    public void test0() {
        JRegEx regEx = new JRegEx("a");
        assertTrue(regEx.match("a"));

        assertFalse(regEx.match(""));
        assertFalse(regEx.match("ab"));
        assertFalse(regEx.match("xa"));
    }

    @Test
    public void test1() {
        JRegEx regEx = new JRegEx("ab");
        assertTrue(regEx.match("ab"));

        assertFalse(regEx.match(""));
        assertFalse(regEx.match("a"));
        assertFalse(regEx.match("b"));
        assertFalse(regEx.match("acb"));
    }

    @Test
    public void test2() {
        JRegEx regEx = new JRegEx("a|b");
        assertTrue(regEx.match("a"));
        assertTrue(regEx.match("b"));

        assertFalse(regEx.match(""));
        assertFalse(regEx.match("ab"));
        assertFalse(regEx.match("x"));
        assertFalse(regEx.match("xa"));
        assertFalse(regEx.match("xb"));
    }

    @Test
    public void test3() {
        JRegEx regEx = new JRegEx("a*");
        assertTrue(regEx.match(""));
        assertTrue(regEx.match("a"));
        assertTrue(regEx.match("aaaaa"));

        assertFalse(regEx.match("b"));
        assertFalse(regEx.match("ab"));
    }

    @Test
    public void test4() {
        JRegEx regEx = new JRegEx("a*b*");
        assertTrue(regEx.match(""));
        assertTrue(regEx.match("a"));
        assertTrue(regEx.match("b"));
        assertTrue(regEx.match("ab"));
        assertTrue(regEx.match("aaaabbbb"));

        assertFalse(regEx.match("ba"));
        assertFalse(regEx.match("aabaa"));
    }

    @Test
    public void test5() {
        JRegEx regEx = new JRegEx("ab|xy");
        assertTrue(regEx.match("ab"));
        assertTrue(regEx.match("xy"));

        assertFalse(regEx.match("abxy"));
        assertFalse(regEx.match("abc"));
        assertFalse(regEx.match("xyz"));
    }

    @Test
    public void test6() {
        JRegEx regEx = new JRegEx("(ab|xy)cd");

        assertFalse(regEx.match("abxycd"));
        assertFalse(regEx.match("ab"));
        assertFalse(regEx.match("xy"));
        assertTrue(regEx.match("abcd"));
        assertTrue(regEx.match("xycd"));
    }

    @Test
    public void test7() {
        JRegEx regEx = new JRegEx("cd(ab|xy)");

        assertFalse(regEx.match("cdabxy"));
        assertFalse(regEx.match("ab"));
        assertFalse(regEx.match("xy"));
        assertTrue(regEx.match("cdab"));
        assertTrue(regEx.match("cdxy"));
    }

    @Test
    public void emailLike() {
        JRegEx regEx = new JRegEx("(abc|(abc)*)@(xyz|(xyz)*).(123|(123)*)");
        assertTrue(regEx.match("abc@xyz.123"));
        assertTrue(regEx.match("abcabc@xyzxyz.123123123"));
    }
}
