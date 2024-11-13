package Shared;

public class TestSanitize {
    public static void main(String[] args) {
        String input = "<script>alert('XSS');</script>";
        String sanitized = InputSanitizer.sanitize(input);
        System.out.println("Resultado sanitizado: " + sanitized);
    }
}
