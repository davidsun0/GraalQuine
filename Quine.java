import org.graalvm.polyglot.*;

public class Quine {
    public static void main(String[] args) {
        // template for the outer Java program
        String[] jQuine = {
                "import org.graalvm.polyglot.*;",
                "",
                "public class Quine {",
                "    public static void main(String[] args) {",
                "        // template for the outer Java program",
                "        String[] jQuine = {",
                "        };",
                "        // embedded Python script to recreate this program",
                "        String[] pQuine = {",
                "        };",
                "        try (Context context = Context.newBuilder().allowAllAccess(true).build()) {",
                "            context.getPolyglotBindings().putMember(\"jQuine\", jQuine);",
                "            context.getPolyglotBindings().putMember(\"pQuine\", pQuine);",
                "            context.eval(\"python\", String.join(\"\n\", pQuine));",
                "        }",
                "    }",
                "}",
        };
        // embedded Python script to recreate this program
        String[] pQuine = {
                "import polyglot",
                "jQuine = polyglot.import_value('jQuine')",
                "# print the Java program itself",
                "for l in jQuine[:6]:",
                "    print(l)",
                "# print the contents of String[] jQuine",
                "for l in jQuine:",
                "    # substitute escape sequences - like repr, but handles double quotes",
                "    print('                \"%s\",'%l.replace('\\n', '\\\\n').replace('\"', '\\\\\"'))",
                "for l in jQuine[6:9]:",
                "    print(l)",
                "# print the python code, obtained from polyglot bindings",
                "pQuine = polyglot.import_value('pQuine')",
                "for l in pQuine:",
                "    l = l.replace('\\\\', '\\\\\\\\').replace('\"', '\\\\\"')",
                "    print('                \"%s\",'%l)",
                "for l in jQuine[9:]:",
                "    print(l.replace('\\n', '\\\\n'))",
        };
        try (Context context = Context.newBuilder().allowAllAccess(true).build()) {
            context.getPolyglotBindings().putMember("jQuine", jQuine);
            context.getPolyglotBindings().putMember("pQuine", pQuine);
            context.eval("python", String.join("\n", pQuine));
        }
    }
}
