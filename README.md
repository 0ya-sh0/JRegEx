# JRegEx
Java Regex Engine from scratch

## Example Usage
```Java
JRegEx regEx = new JRegEx("abc*|xy");

System.out.println(regEx.match("abc"));
System.out.println(regEx.match("abccc"));
System.out.println(regEx.match("xy"));

System.out.println(regEx.match("a"));
System.out.println(regEx.match("x"));
System.out.println(regEx.match("abcxy"));
```
