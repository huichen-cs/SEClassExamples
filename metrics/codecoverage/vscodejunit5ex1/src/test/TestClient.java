package test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Client;

public class TestClient {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testXTrue() {
        Client cli = new Client();
        cli.checkBool(true, false, false);
        Assertions.assertEquals("Flag is 1", outputStreamCaptor.toString().trim());
    }


    // @Test
    // public void testXFalse() {
    //     Client cli = new Client();
    //     cli.checkBool(false, false, false);
    //     Assertions.assertEquals("x is false", outputStreamCaptor.toString().trim());
    // }

    // @Test
    // public void testXTrueYFalse() {
    //     Client cli = new Client();
    //     cli.checkBool(true, false, false);
    //     Assertions.assertEquals("Flag is 1", outputStreamCaptor.toString().trim());
    // }

    // @Test
    // public void testXTrueYTrue() {
    //     Client cli = new Client();
    //     cli.checkBool(true, true, false);
    //     Assertions.assertEquals("Flag is 1", outputStreamCaptor.toString().trim());
    // }


    // @Test
    // public void testXTrueYTrueZTrue() {
    //     Client cli = new Client();
    //     cli.checkBool(true, true, true);
    //     Assertions.assertEquals("Flag is 0", outputStreamCaptor.toString().trim());
    // }




    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}