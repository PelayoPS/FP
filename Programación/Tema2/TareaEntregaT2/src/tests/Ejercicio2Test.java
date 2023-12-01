package tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import src.Ejercicio2;

public class Ejercicio2Test {
    // personal use class ignore it

    // Happy path test with a non-leap year
    @Test
    public void testValidDateNonLeapYear() {
        // Act
        boolean isValid = Ejercicio2.validarFecha(28, 2, 2021);

        // Assert
        assertTrue(isValid);
    }

    // Happy path test with a leap year
    @Test
    public void testValidDateLeapYear() {
        // Act
        boolean isValid = Ejercicio2.validarFecha(29, 2, 2020);

        // Assert
        assertTrue(isValid);
    }

    // Edge case test for the start of the valid date range
    @Test
    public void testValidDateAtStartOfRange() {
        // Act
        boolean isValid = Ejercicio2.validarFecha(1, 1, 1979);

        // Assert
        assertTrue(isValid);
    }

    // Edge case test for the end of February on a non-leap year
    @Test
    public void testInvalidDateEndOfFebruaryNonLeapYear() {
        // Act
        boolean isValid = Ejercicio2.validarFecha(29, 2, 2021);

        // Assert
        assertFalse(isValid);
    }

    // Error case test for a date before the valid date range
    @Test
    public void testInvalidDateBeforeValidRange() {
        // Act
        boolean isValid = Ejercicio2.validarFecha(31, 12, 1977);

        // Assert
        assertFalse(isValid);
    }

    // Error case test for invalid month
    @Test
    public void testInvalidMonth() {
        // Act
        boolean isValid = Ejercicio2.validarFecha(10, 13, 2020);

        // Assert
        assertFalse(isValid);
    }

    // Error case test for invalid day
    @Test
    public void testInvalidDay() {
        // Act
        boolean isValid = Ejercicio2.validarFecha(32, 1, 2020);

        // Assert
        assertFalse(isValid);
    }

    // Test for leap year calculation
    @Test
    public void testLeapYear() {
        // Act
        boolean isLeap = Ejercicio2.anioBisiesto(2020);

        // Assert
        assertTrue(isLeap);
    }

    // Test for non-leap year calculation
    @Test
    public void testNonLeapYear() {
        // Act
        boolean isLeap = Ejercicio2.anioBisiesto(2021);

        // Assert
        assertFalse(isLeap);
    }

    // Test for century non-leap year
    @Test
    public void testCenturyNonLeapYear() {
        // Act
        boolean isLeap = Ejercicio2.anioBisiesto(1900);

        // Assert
        assertFalse(isLeap);
    }

    // Test for leap year that is also a century
    @Test
    public void testLeapYearThatIsCentury() {
        // Act
        boolean isLeap = Ejercicio2.anioBisiesto(2000);

        // Assert
        assertTrue(isLeap);
    }

    // Test for the number of days in February during a leap year
    @Test
    public void testDaysInFebruaryLeapYear() {
        // Act
        int days = Ejercicio2.calcularDias(2, 2020);

        // Assert
        assertEquals(29, days);
    }

    // Test for the number of days in February during a non-leap year
    @Test
    public void testDaysInFebruaryNonLeapYear() {
        // Act
        int days = Ejercicio2.calcularDias(2, 2021);

        // Assert
        assertEquals(28, days);
    }

    // Test for the number of days in April
    @Test
    public void testDaysInApril() {
        // Act
        int days = Ejercicio2.calcularDias(4, 2021);

        // Assert
        assertEquals(30, days);
    }

    // Test for the number of days in December
    @Test
    public void testDaysInDecember() {
        // Act
        int days = Ejercicio2.calcularDias(12, 2021);

        // Assert
        assertEquals(31, days);
    }

    // test for date 1/12/2023 getting the console output
    @Test
    public void testConsoleOutput() {
        // log the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        //save the original console output
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        //make keyboard input
        System.setIn(new ByteArrayInputStream("1\n12\n2023".getBytes()));
        //use the keyboard input
        Ejercicio2.main(new String[] {});
        // get the console output without all the \n so we only get the last line
        String consoleOutput = outContent.toString();
        consoleOutput = consoleOutput.split("\n")[3];
        consoleOutput = consoleOutput.substring(0, consoleOutput.length() - 1);
        String expectedOutput = "El día de la semana es viernes";
        // get last line of console output
        assertEquals(expectedOutput, consoleOutput);

        // reset the console output
        System.setOut(originalOut);
    }

}
