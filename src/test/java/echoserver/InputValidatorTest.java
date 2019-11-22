package echoserver;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class InputValidatorTest {
    InputValidator inputValidator;

    @Before
    public void initialize() {
        inputValidator = new InputValidator();
    }

    @Test
    public void returnsTrueIfInputEqualsQuit() {
        assertTrue(inputValidator.isQuit("quit"));
    }

    @Test
    public void returnsFalseIfInputIsNotQuit() {
        assertFalse(inputValidator.isQuit("hello"));
    }

    @Test
    public void returnsTrueRegardlessOfCaseSensitivityForQuit() {
        assertTrue(inputValidator.isQuit("QuIT"));
    }

    @Test
    public void returnsTrueIfQuitHasSpacesAtTheEnd() {
        assertTrue(inputValidator.isQuit("quit    "));
    }

    @Test
    public void returnsTrueIfQuitHasNewLineAtTheEnd() {
        assertTrue(inputValidator.isQuit("quit\n"));
    }

    @Test
    public void returnsTrueIfValidPortArgIsProvided() {
        assertTrue(inputValidator.isValidPort("5000"));
    }

    @Test
    public void returnsFalseIfInvalidPortArgIsProvided() {
        assertFalse(inputValidator.isValidPort("yo80"));
    }

    @Test
    public void returnsFalseIfPortArgIsNull() {
        assertFalse(inputValidator.isValidPort(null));
    }
}
