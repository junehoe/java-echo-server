package echoserver;

import org.junit.Test;
import static org.junit.Assert.*;

public class InputValidatorTest {
    @Test
    public void returnsTrueIfInputEqualsQuit() {
        assertTrue(InputValidator.isQuit("quit"));
    }

    @Test
    public void returnsFalseIfInputIsNotQuit() {
        assertFalse(InputValidator.isQuit("hello"));
    }

    @Test
    public void returnsTrueRegardlessOfCaseSensitivityForQuit() {
        assertTrue(InputValidator.isQuit("QuIT"));
    }

    @Test
    public void returnsTrueIfQuitHasSpacesAtTheEnd() {
        assertTrue(InputValidator.isQuit("quit    "));
    }

    @Test
    public void returnsTrueIfQuitHasNewLineAtTheEnd() {
        assertTrue(InputValidator.isQuit("quit\n"));
    }

    @Test
    public void returnsTrueIfValidPortArgIsProvided() {
        assertTrue(InputValidator.isValidPort("5000"));
    }

    @Test
    public void returnsFalseIfInvalidPortArgIsProvided() {
        assertFalse(InputValidator.isValidPort("yo80"));
    }

    @Test
    public void returnsFalseIfPortArgIsNull() {
        assertFalse(InputValidator.isValidPort(null));
    }
}
