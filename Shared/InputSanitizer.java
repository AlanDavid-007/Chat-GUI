package Shared;

public class InputSanitizer {
    public static String sanitize(String input) {
        if (input == null) {
            return null;
        }
        // Sanitiza os caracteres especiais HTML, convertendo-os para entidades seguras
        return input
                .replace("&", "&amp;")  // Evita interpretações erradas de caracteres especiais
                .replace("<", "&lt;")   // Substitui o < por &lt;
                .replace(">", "&gt;")   // Substitui o > por &gt;
                .replace("\"", "&quot;")  // Sanitiza aspas duplas
                .replace("'", "&#x27;");  // Sanitiza aspas simples
    }
}
