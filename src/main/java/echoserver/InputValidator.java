package echoserver;

public class InputValidator {
    private static final String QUIT = "quit";
    public boolean isQuit(String input) {
        return input.toLowerCase().trim().equals(QUIT);
    }

    public boolean isValidPort(String port) {
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
