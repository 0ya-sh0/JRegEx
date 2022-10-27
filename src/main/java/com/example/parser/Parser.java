package com.example.parser;

import java.util.List;
import java.util.Stack;

class ParserState {
    List<Token> tokens;
    Stack<Integer> state;
    int pos = 0;

    ParserState(List<Token> t) {
        tokens = t;
        state = new Stack<>();
    }

    public void save() {
        state.push(pos);
    }

    public void restore() {
        pos = state.pop();
    }

    public void success() {
        state.pop();
    }

    public Token token() {
        if (pos < tokens.size())
            return tokens.get(pos++);
        return null;
    }
}

class Parser {
    public static Expr parse(ParserState s) {
        return OrParser.parse(s);
    }
}

class OrParser {
    public static Expr parse(ParserState s) {
        s.save();
        Expr l = AndParser.parse(s);

        if (l == null) {
            s.restore();
            return null;
        }
        s.save();
        Token t = s.token();

        if (t == null || t.type != TokType.PIPE) {
            s.restore();
            s.success();
            return l;
        }
        Expr r = OrParser.parse(s);
        if (r == null) {
            s.restore();
            s.restore();
            return null;
        }
        s.success();
        s.success();
        return new EOr(l, r);
    }
}

class AndParser {
    public static Expr parse(ParserState s) {
        s.save();
        Expr l = StarParser.parse(s);
        if (l == null) {
            s.restore();
            return null;
        }
        Expr r = AndParser.parse(s);
        if (r == null) {
            s.success();
            return l;
        }
        s.success();
        return new EAnd(l, r);
    }
}

class StarParser {
    public static Expr parse(ParserState s) {
        Expr e = TerminalParser.parse(s);
        if (e == null)
            return e;
        s.save();
        Token t = s.token();
        if (t == null || t.type != TokType.STAR) {
            s.restore();
            return e;
        }
        s.success();
        return new EStar(e);
    }
}

class TerminalParser {
    public static Expr parse(ParserState s) {
        Expr e = SymbolParser.parse(s);
        if (e != null)
            return e;
        s.save();
        Token t = s.token();
        if (t == null || t.type != TokType.PLEFT) {
            s.restore();
            return null;
        }
        e = Parser.parse(s);
        if (e == null) {
            s.restore();
            return null;
        }
        t = s.token();
        if (t == null || t.type != TokType.PRIGHT) {
            s.restore();
            return null;
        }
        s.success();
        return e;
    }
}

class SymbolParser {
    public static Expr parse(ParserState s) {
        s.save();
        Token t = s.token();
        if (t == null || t.type != TokType.SYMBOL) {
            s.restore();
            return null;
        }
        s.success();
        return new ESymbol(t.value);
    }
}
