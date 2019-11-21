package echoserver;

public class InputValidator {
    public static boolean isQuit(String input) {
        return input.toLowerCase().trim().equals("quit");
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
