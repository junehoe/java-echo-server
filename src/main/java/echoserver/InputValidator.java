package echoserver;

public class InputValidator {
    private static final String QUIT = "quit";
    public static boolean isQuit(String input) {
        return input.toLowerCase().trim().equals(QUIT);
    }

    public static boolean isValidPort(String port) {
        try {
            Integer.parseInt(port);
        } catch (NullPointerException e) {
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
