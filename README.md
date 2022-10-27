# JRegEx
Java Regex Engine from scratch

## Example Usage
```Java
JRegEx regEx = new JRegEx("abc*|xy");

//true
regEx.match("abc");
regEx.match("abccc");
regEx.match("xy");

//false
regEx.match("a");
regEx.match("x");
regEx.match("abcxy");
```
