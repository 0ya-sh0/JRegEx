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
        JRegEx regEx = new JRegEx("(abc(abc)*)@(xyz(xyz)*).(123(123)*)");
        assertTrue(regEx.match("abc@xyz.123"));
        assertTrue(regEx.match("abcabc@xyzxyz.123123123"));

        assertFalse(regEx.match("abcab@xyzxyz.123123123"));
        assertFalse(regEx.match("abcabc@xyxyz.123123123"));
        assertFalse(regEx.match("abcabc@xyzxyz.12313123"));
        assertFalse(regEx.match("abcxyz.123"));
        assertFalse(regEx.match("abc@xyz123"));
        assertFalse(regEx.match("@."));
        assertFalse(regEx.match("abc@xyz."));
        assertFalse(regEx.match("abc@.123"));
        assertFalse(regEx.match("@xyz.123"));

    }

    @Test
    public void test8() {
        JRegEx regEx = new JRegEx("1(0|1)*0");

        assertTrue(regEx.match("10"));
        assertTrue(regEx.match("100"));
        assertTrue(regEx.match("110"));
        assertTrue(regEx.match("1100"));
        assertTrue(regEx.match("1010"));
        assertTrue(regEx.match("101001110"));

        assertFalse(regEx.match(""));
        assertFalse(regEx.match("0"));
        assertFalse(regEx.match("1"));
        assertFalse(regEx.match("00"));
        assertFalse(regEx.match("11"));

    }

    @Test
    public void test9() {
        JRegEx regEx = new JRegEx("10|(0|11)0*1");

        assertTrue(regEx.match("10"));
        assertTrue(regEx.match("01"));
        assertTrue(regEx.match("111"));
        assertTrue(regEx.match("001"));
        assertTrue(regEx.match("0001"));
        assertTrue(regEx.match("1101"));
        assertTrue(regEx.match("11001"));
        assertTrue(regEx.match("110001"));

        assertFalse(regEx.match(""));
        assertFalse(regEx.match("0"));
        assertFalse(regEx.match("1"));
        assertFalse(regEx.match("00"));
        assertFalse(regEx.match("11"));

    }

    @Test
    public void phoneNumber() {
        // 10 digit phone number with country code
        JRegEx regEx = new JRegEx(
                "(+91|0)(9|8|7)(9|8|7|6|5|4|3|2|1|0)(9|8|7|6|5|4|3|2|1|0)(9|8|7|6|5|4|3|2|1|0)(9|8|7|6|5|4|3|2|1|0)(9|8|7|6|5|4|3|2|1|0)(9|8|7|6|5|4|3|2|1|0)(9|8|7|6|5|4|3|2|1|0)(9|8|7|6|5|4|3|2|1|0)(9|8|7|6|5|4|3|2|1|0)");

        assertTrue(regEx.match("+919876543210"));
        assertTrue(regEx.match("09876543210"));

        assertFalse(regEx.match("06876543210"));
        assertFalse(regEx.match("+916876543210"));
        assertFalse(regEx.match("+91987654"));
        assertFalse(regEx.match("+9198765432100"));
        assertFalse(regEx.match("+919876abcd"));

    }

    @Test
    public void name() {
        JRegEx regEx = new JRegEx("((Mr|Ms|Mrs|Dr) (A|B|C)(x|y|z)*)|(A|B|C)(x|y|z)*)");
        assertTrue(regEx.match("A"));
        assertTrue(regEx.match("B"));
        assertTrue(regEx.match("B"));
        assertTrue(regEx.match("Ax"));
        assertTrue(regEx.match("Axyz"));
        assertTrue(regEx.match("Cyyy"));
        assertTrue(regEx.match("Mr Cyyy"));
        assertTrue(regEx.match("Ms Cyyy"));
        assertTrue(regEx.match("Dr Cyyy"));

        assertFalse(regEx.match("xyz"));
        assertFalse(regEx.match("1xyz"));
        assertFalse(regEx.match("Bx1yz"));
        assertFalse(regEx.match(" B"));
        assertFalse(regEx.match("Mr  B"));
        assertFalse(regEx.match("B "));

    }
}
