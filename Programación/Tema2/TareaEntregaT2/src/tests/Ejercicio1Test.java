package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import src.Ejercicio1;
/**
 * Personal use class ignore it
 */
public class Ejercicio1Test {
    

    // Happy path test with realistic test values
    @Test
    public void testDniValidoHappyPath() {
        // Arrange
        int dni = 12345678;
        char letter = 'Z'; // Assuming 'Z' is the correct letter for the given DNI

        // Act
        String result = Ejercicio1.dniValido(dni, letter);

        // Assert
        assertEquals("El dni es correcto", result);
    }


    // Edge case: DNI at the upper boundary of validity
    @Test
    public void testDniValidoUpperEdge() {
        // Arrange
        int dni = 99999999;
        char letter = 'R'; // Assuming 'R' is the correct letter for the given DNI

        // Act
        String result = Ejercicio1.dniValido(dni, letter);

        // Assert
        assertEquals("El dni es correcto", result);
    }

    // Error case: DNI is negative
    @Test
    public void testDniValidoNegative() {
        // Arrange
        int dni = -12345678;
        char letter = 'Z'; // Letter is irrelevant in this case

        // Act
        String result = Ejercicio1.dniValido(dni, letter);

        // Assert
        assertEquals("El dni no es correcto", result);
    }

    // Error case: DNI has more than 8 digits
    @Test
    public void testDniValidoTooLong() {
        // Arrange
        int dni = 123456789;
        char letter = 'Z'; // Letter is irrelevant in this case

        // Act
        String result = Ejercicio1.dniValido(dni, letter);

        // Assert
        assertEquals("El dni no es correcto", result);
    }

    // Error case: DNI has less than 8 digits but is positive
    @Test
    public void testDniValidoTooShort() {
        // Arrange
        int dni = 1234567;
        char letter = 'Z'; // Assuming 'Z' is the correct letter for the given DNI

        // Act
        String result = Ejercicio1.dniValido(dni, letter);

        // Assert
        assertEquals("El dni no es correcto", result);
    }

    // Error case: Incorrect letter for a valid DNI
    @Test
    public void testDniValidoIncorrectLetter() {
        // Arrange
        int dni = 12345678;
        char letter = 'X'; // Incorrect letter for the given DNI

        // Act
        String result = Ejercicio1.dniValido(dni, letter);

        // Assert
        assertEquals("El dni no es correcto", result);
    }

    // Test the letraDni function directly with a valid DNI
    @Test
    public void testLetraDniValid() {
        // Arrange
        int dni = 12345678;
        char expectedLetter = 'Z'; // Assuming 'Z' is the correct letter for the given DNI

        // Act
        char result = Ejercicio1.letraDni(dni);

        // Assert
        assertEquals(expectedLetter, result);
    }
}

