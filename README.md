# Combined Java-Python Quine

A Java-Python quine for my GraalVM internship application.

## Running

This quine was developed and tested on Graal 21.0.0.2 with Java 11 and Python
3.8. Make sure that `javac` and `java` are GraalVM's.

```
$ javac Quine.java
$ java Quine > output.txt
$ diff output.txt Quine.java        # the files are identical
```

## Explanation 

This program demonstrates a multi-lingual quine written with GraalVM's polyglot
feature. The Java host passes a text representation of itself to a Python
snippet using Polyglot Bindings.

Because Java is the host language, the code is straightforward, but verbose.
A multi-lingual quine with Python, Ruby, and JavaScript may be more compact.
