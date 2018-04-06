import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup(){
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanup(){
        System.setOut(System.out);
        System.setIn(System.in);
    }

    @Test
    public void gameTest(){
        String newLine = System.getProperty("line.separator");

        String inputData ="2"+newLine+
                "1 1"+newLine+
                "2 2"+newLine+
                "1 2"+newLine;

        String expectedOut="Podaj wielkość planszy." +newLine+
                "P|P" + newLine+
                "P|P" + newLine+
                "Kolej O" + newLine+
                "Podaj współrzędne pola na którym chcesz postawić znak: 'nr_wiersza''spacja''nr_kolumny'" +newLine+
                "O|P" +newLine+
                "P|P" +newLine+
                "Kolej X" +newLine+
                "Podaj współrzędne pola na którym chcesz postawić znak: 'nr_wiersza''spacja''nr_kolumny'" +newLine+
                "O|P" + newLine+
                "P|X" + newLine+
                "Kolej O" + newLine+
                "Podaj współrzędne pola na którym chcesz postawić znak: 'nr_wiersza''spacja''nr_kolumny'" + newLine+
                "=====    Wygrywa O   =====" + newLine +
                "O|O" + newLine +
                "P|X" + newLine;


        System.setIn(new ByteArrayInputStream(inputData.getBytes()));

        Main.main(new String[]{});

        assertEquals(expectedOut, outContent.toString());

    }

    @Test
    public void gameTest2(){
        String newLine = System.getProperty("line.separator");

        String inputData ="2"+newLine+
                "1 1"+newLine+
                "2 1"+newLine+
                "2 2"+newLine;

        String expectedOut="Podaj wielkość planszy." +newLine+
                "P|P" + newLine+
                "P|P" + newLine+
                "Kolej O" + newLine+
                "Podaj współrzędne pola na którym chcesz postawić znak: 'nr_wiersza''spacja''nr_kolumny'" +newLine+
                "O|P" +newLine+
                "P|P" +newLine+
                "Kolej X" +newLine+
                "Podaj współrzędne pola na którym chcesz postawić znak: 'nr_wiersza''spacja''nr_kolumny'" +newLine+
                "O|P" + newLine+
                "X|P" + newLine+
                "Kolej O" + newLine+
                "Podaj współrzędne pola na którym chcesz postawić znak: 'nr_wiersza''spacja''nr_kolumny'" + newLine+
                "=====    Wygrywa O   =====" + newLine +
                "O|P" + newLine +
                "X|O" + newLine;


        System.setIn(new ByteArrayInputStream(inputData.getBytes()));

        Main.main(new String[]{});

        assertEquals(expectedOut, outContent.toString());

    }

    @Test
    public void gameTest3(){
        String newLine = System.getProperty("line.separator");

        String inputData ="3"+newLine+
                "2 1"+newLine+
                "1 1"+newLine+
                "1 2"+newLine+
                "2 2"+newLine+
                "1 3"+newLine+
                "3 3"+newLine;

        String expectedOut="Podaj wielkość planszy." +newLine+
                "P|P|P" + newLine+
                "P|P|P" + newLine+
                "P|P|P" + newLine+
                "Kolej O" + newLine+
                "Podaj współrzędne pola na którym chcesz postawić znak: 'nr_wiersza''spacja''nr_kolumny'" +newLine+
                "P|P|P" + newLine+
                "O|P|P" + newLine+
                "P|P|P" + newLine+
                "Kolej X" +newLine+
                "Podaj współrzędne pola na którym chcesz postawić znak: 'nr_wiersza''spacja''nr_kolumny'" +newLine+
                "X|P|P" + newLine+
                "O|P|P" + newLine+
                "P|P|P" + newLine+
                "Kolej O" + newLine+
                "Podaj współrzędne pola na którym chcesz postawić znak: 'nr_wiersza''spacja''nr_kolumny'" + newLine+
                "X|O|P" + newLine+
                "O|P|P" + newLine+
                "P|P|P" + newLine+
                "Kolej X" +newLine+
                "Podaj współrzędne pola na którym chcesz postawić znak: 'nr_wiersza''spacja''nr_kolumny'" +newLine+
                "X|O|P" + newLine+
                "O|X|P" + newLine+
                "P|P|P" + newLine+
                "Kolej O" + newLine+
                "Podaj współrzędne pola na którym chcesz postawić znak: 'nr_wiersza''spacja''nr_kolumny'" + newLine+
                "X|O|O" + newLine+
                "O|X|P" + newLine+
                "P|P|P" + newLine+
                "Kolej X" +newLine+
                "Podaj współrzędne pola na którym chcesz postawić znak: 'nr_wiersza''spacja''nr_kolumny'" +newLine+
                "=====    Wygrywa X   ====="+newLine+
                "X|O|O" + newLine+
                "O|X|P" + newLine+
                "P|P|X" + newLine;


        System.setIn(new ByteArrayInputStream(inputData.getBytes()));

        Main.main(new String[]{});

        assertEquals(expectedOut, outContent.toString());

    }
}
