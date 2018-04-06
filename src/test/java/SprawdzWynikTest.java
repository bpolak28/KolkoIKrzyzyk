import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
@RunWith(JUnitParamsRunner.class)
public class SprawdzWynikTest {

    Plansza nowaPlansza;
    Gra nowaGra;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Before
    public void setup(){
        nowaPlansza = new Plansza(3);
         nowaGra = new Gra();
    }

    @Test
    public void stworzPlanszeTest3(){
        String data = "3";
        InputStream stdin = System.in;
        Pola[][] polaGra;
        Pola[][] plansza = {
                {Pola.P, Pola.P, Pola.P},
                {Pola.P, Pola.P, Pola.P},
                {Pola.P, Pola.P, Pola.P}
        };
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Plansza graPlansza = nowaGra.stworzPlansze();
            polaGra = graPlansza.getPlansza();
        } finally {
            System.setIn(stdin);
        }
        assertArrayEquals(plansza,polaGra);

    }

    @Test
    public void stworzPlanszeTest(){
        Pola[][] plansza = {
                {Pola.P, Pola.P, Pola.P},
                {Pola.P, Pola.P, Pola.P},
                {Pola.P, Pola.P, Pola.P}
        };
        Pola[][] plansza2;
        String data = "3";
        InputStream stdin = System.in;
        try{
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner skan = new Scanner(System.in);
            int wielkosc = skan.nextInt();
            this.nowaPlansza = new Plansza(wielkosc);
            plansza2=nowaPlansza.getPlansza();
        } finally {
            System.setIn(stdin);
        }
        assertArrayEquals(plansza,plansza2);

    }

    @Test
    public void stworzPlanszeTest2(){
        Pola[][] plansza = {
                {Pola.P, Pola.P, Pola.P, Pola.P},
                {Pola.P, Pola.P, Pola.P, Pola.P},
                {Pola.P, Pola.P, Pola.P, Pola.P},
                {Pola.P, Pola.P, Pola.P, Pola.P}
        };
        Pola[][] plansza2;
        String data = "4";
        InputStream stdin = System.in;
        try{
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner skan = new Scanner(System.in);
            int wielkosc = skan.nextInt();
            this.nowaPlansza = new Plansza(wielkosc);
            plansza2=nowaPlansza.getPlansza();
        } finally {
            System.setIn(stdin);
        }
        assertArrayEquals(plansza,plansza2);

    }

    @Test(expected = InvalidNumbersExeption.class)
    @Parameters({"0,0","1,1","2,2","0,1","0,2","1,0","1,2","2,0","2,1"})
    public void wstawZnakTestRzuceniaWyjatku(int a,int b) throws InvalidNumbersExeption{
        Pola[][] plansza = {
                {Pola.X, Pola.X, Pola.X},
                {Pola.O, Pola.O, Pola.X},
                {Pola.O, Pola.X, Pola.O}
        };
        nowaPlansza.wstawZnak(plansza,a,b);
    }

    @Test
    public void wstawZnakTest() throws InvalidNumbersExeption{
        Pola[][] plansza = {
                {Pola.X, Pola.X, Pola.X},
                {Pola.O, Pola.P, Pola.X},
                {Pola.O, Pola.X, Pola.P}
        };
        boolean rezultat = nowaPlansza.wstawZnak(plansza,1,1);
        assertEquals(true,rezultat);
    }

    @Test
    public void wstawZnakTest2()throws InvalidNumbersExeption{
        Pola[][] plansza = {
                {Pola.X, Pola.X, Pola.X},
                {Pola.O, Pola.P, Pola.X},
                {Pola.O, Pola.X, Pola.P}
        };
        boolean rezultat = nowaPlansza.wstawZnak(plansza,2,2);
        assertEquals(true,rezultat);
    }

    @Test(expected = InvalidNumbersExeption.class)
    public void wstawZnakTest3()throws InvalidNumbersExeption{
        Pola[][] plansza = {
                {Pola.X, Pola.X, Pola.X},
                {Pola.O, Pola.P, Pola.X},
                {Pola.O, Pola.X, Pola.P}
        };
        nowaPlansza.wstawZnak(plansza,0,0);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void wstawZnakTest4()throws InvalidNumbersExeption{
        Pola[][] plansza = {
                {Pola.X, Pola.X, Pola.X},
                {Pola.O, Pola.P, Pola.X},
                {Pola.O, Pola.X, Pola.P}
        };
        nowaPlansza.wstawZnak(plansza,4,4);
    }

    @Test
    public void setCzyjaKolejTest(){
        nowaPlansza.setCzyjaKolej(3);
        int wartosc = nowaPlansza.getCzyjaKolej();
        assertEquals(3,wartosc);
    }

    @Test
    @Parameters({"1","2","3","4","5"})
    public void getCzyjaKolejTest(int value){
        //given
        nowaPlansza.setCzyjaKolej(value);
        //when
        int wartos = nowaPlansza.getCzyjaKolej();
        //then
        assertEquals(value,wartos);
    }

    @Test
    public void sprawdzCzyKoniecTest(){
        boolean rezultat = nowaPlansza.sprawdzCzyKoniec(nowaPlansza.getPlansza());
        assertEquals(false,rezultat);
    }

    @Test
    public void sprawdzCzyKoniecTest2(){
        Pola[][] plansza = {
                {Pola.X, Pola.X, Pola.X},
                {Pola.O, Pola.P, Pola.X},
                {Pola.O, Pola.X, Pola.P}
        };
        boolean rezultat = nowaPlansza.sprawdzCzyKoniec(plansza);
        assertEquals(true,rezultat);
    }

    @Test
    public void sprawdzCzyKoniecTest3(){
        Pola[][] plansza = {
                {Pola.O, Pola.X, Pola.X},
                {Pola.O, Pola.P, Pola.X},
                {Pola.O, Pola.X, Pola.P}
        };
        boolean rezultat = nowaPlansza.sprawdzCzyKoniec(plansza);
        assertEquals(true,rezultat);
    }

    @Test
    public void sprawdzCzyKoniecTest4(){
        Pola[][] plansza = {
                {Pola.X, Pola.O, Pola.X},
                {Pola.O, Pola.P, Pola.X},
                {Pola.O, Pola.X, Pola.O}
        };
        boolean rezultat = nowaPlansza.sprawdzCzyKoniec(plansza);
        assertEquals(false,rezultat);
    }

    @Test
    public void sprawdzCzyKoniecTest5(){
        Pola[][] plansza = {
                {Pola.X, Pola.O, Pola.X},
                {Pola.O, Pola.O, Pola.X},
                {Pola.O, Pola.X, Pola.O}
        };
        boolean rezultat = nowaPlansza.sprawdzCzyKoniec(plansza);
        assertEquals(true,rezultat);
    }


    @Test
    public void sprawdzWygranaTest(){
        Pola[][] plansza = {
                {Pola.X, Pola.X, Pola.X},
                {Pola.O, Pola.P, Pola.X},
                {Pola.O, Pola.X, Pola.P}
        };
        Pola rezultat = nowaPlansza.sprawdzWygrana(plansza);

        assertEquals(Pola.X,rezultat);
    }

    @Test
    public void sprawdzWygranaTest2(){
        Pola[][] plansza = {
                {Pola.X, Pola.X, Pola.P},
                {Pola.O, Pola.O, Pola.O},
                {Pola.O, Pola.X, Pola.P}
        };
        Pola rezultat = nowaPlansza.sprawdzWygrana(plansza);

        assertEquals(Pola.O,rezultat);
    }

    @Test
    public void sprawdzWygranaTest3(){
        Pola[][] plansza = {
                {Pola.X, Pola.O, Pola.X},
                {Pola.O, Pola.P, Pola.X},
                {Pola.O, Pola.X, Pola.X}
        };
        Pola rezultat = nowaPlansza.sprawdzWygrana(plansza);

        assertEquals(Pola.X,rezultat);
    }

    @Test
    public void sprawdzWygranaTest4(){
        Pola[][] plansza = {
                {Pola.X, Pola.O, Pola.X},
                {Pola.O, Pola.O, Pola.X},
                {Pola.O, Pola.O, Pola.P}
        };
        Pola rezultat = nowaPlansza.sprawdzWygrana(plansza);

        assertEquals(Pola.O,rezultat);
    }

    @Test
    public void sprawdzWygranaPrzekatneTest(){
        Pola[][] plansza = {
                {Pola.X, Pola.O, Pola.P},
                {Pola.O, Pola.X, Pola.P},
                {Pola.O, Pola.X, Pola.X}
        };
        Pola rezultat = nowaPlansza.sprawdzWygranaPrzekatne(plansza);

        assertEquals(Pola.X,rezultat);
    }

    @Test
    public void sprawdzWygranaPrzekatneTest2(){
        Pola[][] plansza = {
                {Pola.O, Pola.X, Pola.P},
                {Pola.P, Pola.O, Pola.P},
                {Pola.P, Pola.X, Pola.O}
        };
        Pola rezultat = nowaPlansza.sprawdzWygranaPrzekatne(plansza);

        assertEquals(Pola.O,rezultat);
    }

    @Test
    public void sprawdzWygranaPrzekatneTest3(){
        Pola[][] plansza = {
                {Pola.X, Pola.O, Pola.X},
                {Pola.O, Pola.X, Pola.P},
                {Pola.X, Pola.O, Pola.P}
        };
        Pola rezultat = nowaPlansza.sprawdzWygranaPrzekatne(plansza);

        assertEquals(Pola.X,rezultat);
    }

    @Test
    public void sprawdzWygranaPrzekatneTest4(){
        Pola[][] plansza = {
                {Pola.X, Pola.P, Pola.O},
                {Pola.P, Pola.O, Pola.P},
                {Pola.O, Pola.X, Pola.X}
        };
        Pola rezultat = nowaPlansza.sprawdzWygranaPrzekatne(plansza);

        assertEquals(Pola.O,rezultat);
    }

    @Test
    public void sprawdzCzyPelnaPlanszaTest(){
        Pola[][] nowaPlansza = {
                {Pola.O,Pola.X,Pola.O},
                {Pola.X,Pola.O,Pola.X},
                {Pola.X,Pola.O,Pola.O}
        };
        assertEquals(1, this.nowaPlansza.sprawdzCzyPelnaPlansza(nowaPlansza));
    }

    @Test
    public void sprawdzCzyPelnaPlanszaTest2(){
        Pola[][] nowaPlansza = {
                {Pola.O,Pola.X,Pola.O},
                {Pola.X,Pola.O,Pola.X},
                {Pola.X,Pola.P,Pola.O}
        };
        assertEquals(0, this.nowaPlansza.sprawdzCzyPelnaPlansza(nowaPlansza));
    }

    @Test
    public void sprawdzCzyPelnaPlanszaTest3(){
        Pola[][] nowaPlansza = {
                {Pola.P,Pola.P,Pola.P},
                {Pola.P,Pola.P,Pola.P},
                {Pola.P,Pola.P,Pola.P}
        };
        assertEquals(0, this.nowaPlansza.sprawdzCzyPelnaPlansza(nowaPlansza));
    }

    @Test
    public void sprawdzCzyPelnaPlanszaTest4(){
        Pola[][] nowaPlansza = {
                {Pola.O,Pola.X,Pola.P},
                {Pola.X,Pola.O,Pola.X},
                {Pola.X,Pola.O,Pola.O}
        };
        assertEquals(0, this.nowaPlansza.sprawdzCzyPelnaPlansza(nowaPlansza));
    }

    @Test
    public void sprawdzCzyPelnaPlanszaTest5(){
        Pola[][] nowaPlansza = {
                {Pola.O,Pola.O,Pola.O},
                {Pola.O,Pola.O,Pola.O},
                {Pola.O,Pola.O,Pola.O}
        };
        assertEquals(1, this.nowaPlansza.sprawdzCzyPelnaPlansza(nowaPlansza));
    }

    @Test
    public void sprawdzCzyPelnaPlanszaTest6(){
        Pola[][] nowaPlansza = {
                {Pola.X,Pola.X,Pola.X},
                {Pola.X,Pola.X,Pola.X},
                {Pola.X,Pola.X,Pola.X}
        };
        assertEquals(1, this.nowaPlansza.sprawdzCzyPelnaPlansza(nowaPlansza));
    }

    @Test
    @Parameters({"1","3","5","7","9"})
    public void czyjaKolejTest(int value){
        //given
        nowaPlansza.setCzyjaKolej(value);
        //when
        Pola result = nowaPlansza.czyjaKolej();
        //then
        assertEquals(Pola.X, result);
    }

    @Test
    @Parameters({"0","2","4","6","8","10"})
    public void czyjaKolejTest2(int value){
        //given
        nowaPlansza.setCzyjaKolej(value);
        //when
        Pola result = nowaPlansza.czyjaKolej();
        //then
        assertEquals(Pola.O, result);
    }

    @Test
    public void wyswietlPlanszeTest(){
        String newLine = System.getProperty("line.separator");
        nowaPlansza.wyswietlPlansze(nowaPlansza.getPlansza());
        assertEquals("P|P|P"+newLine+"P|P|P"+newLine+"P|P|P"+newLine,outContent.toString());
    }

    @Test
    public void wyswietlPlanszeTest2(){
        Pola[][] nowaPlansza = {
                {Pola.O,Pola.X,Pola.P},
                {Pola.X,Pola.O,Pola.X},
                {Pola.X,Pola.O,Pola.O}
        };
        String newLine = System.getProperty("line.separator");
        this.nowaPlansza.wyswietlPlansze(nowaPlansza);
        assertEquals("O|X|P"+newLine+"X|O|X"+newLine+"X|O|O"+newLine,outContent.toString());
    }

    @Test
    public void wyswietlPlanszeTest3(){
        Pola[][] nowaPlansza = {
                {Pola.O,Pola.O,Pola.O},
                {Pola.O,Pola.O,Pola.O},
                {Pola.O,Pola.O,Pola.O}
        };
        String newLine = System.getProperty("line.separator");
        this.nowaPlansza.wyswietlPlansze(nowaPlansza);
        assertEquals("O|O|O"+newLine+"O|O|O"+newLine+"O|O|O"+newLine,outContent.toString());
    }

    @Test
    public void wyswietlPlanszeTest4(){
        Pola[][] nowaPlansza = {
                {Pola.X,Pola.X,Pola.X},
                {Pola.X,Pola.X,Pola.X},
                {Pola.X,Pola.X,Pola.X}
        };
        String newLine = System.getProperty("line.separator");
        this.nowaPlansza.wyswietlPlansze(nowaPlansza);
        assertEquals("X|X|X"+newLine+"X|X|X"+newLine+"X|X|X"+newLine,outContent.toString());
    }

    @Test
    public void wygranaKrzyzykaPoPrzekatnej(){
        Pola[][] plansza = {
                {Pola.X, Pola.P, Pola.P},
                {Pola.P, Pola.X, Pola.O},
                {Pola.P, Pola.O, Pola.X}
        };
        char rezultat = nowaPlansza.sprawdzWynik(plansza);

        assertEquals('X',rezultat);
    }

    @Test
    public void wygranaKolkaPoPrzekatnej(){
        Pola[][] plansza = {
                {Pola.O, Pola.P, Pola.P},
                {Pola.P, Pola.O, Pola.X},
                {Pola.P, Pola.X, Pola.O}
        };
        char rezultat = nowaPlansza.sprawdzWynik(plansza);

        assertEquals('O',rezultat);
    }

    @Test
    public void wygranaKrzyzykaPoDrugiejPrzekatnej(){
        Pola[][] plansza = {
                {Pola.P, Pola.P, Pola.X},
                {Pola.P, Pola.X, Pola.O},
                {Pola.X, Pola.O, Pola.P}
        };
        char rezultat = nowaPlansza.sprawdzWynik(plansza);

        assertEquals('X',rezultat);
    }

    @Test
    public void wygranaKolkaPoDrugiejPrzekatnej(){
        Pola[][] plansza = {
                {Pola.X, Pola.P, Pola.O},
                {Pola.P, Pola.O, Pola.P},
                {Pola.O, Pola.P, Pola.X}
        };
        char rezultat = nowaPlansza.sprawdzWynik(plansza);

        assertEquals('O',rezultat);
    }

    @Test
    public void wygranaKrzyzykaPoPierwszejKolumnie(){
        Pola[][] plansza = {
                {Pola.X, Pola.P, Pola.P},
                {Pola.X, Pola.P, Pola.O},
                {Pola.X, Pola.O, Pola.P}
        };
        char rezultat = nowaPlansza.sprawdzWynik(plansza);

        assertEquals('X',rezultat);
    }

    @Test
    public void wygranaKrzyzykaPoDrugiejKolumnie(){
        Pola[][] plansza = {
                {Pola.P, Pola.X, Pola.P},
                {Pola.P, Pola.X, Pola.O},
                {Pola.O, Pola.X, Pola.P}
        };
        char rezultat = nowaPlansza.sprawdzWynik(plansza);

        assertEquals('X',rezultat);
    }

    @Test
    public void wygranaKrzyzykaPoTrzeciejKolumnie(){
        Pola[][] plansza = {
                {Pola.P, Pola.P, Pola.X},
                {Pola.P, Pola.P, Pola.X},
                {Pola.P, Pola.O, Pola.X}
        };
        char rezultat = nowaPlansza.sprawdzWynik(plansza);

        assertEquals('X',rezultat);
    }

    @Test
    public void wygranaKolkaPoPierwszejKolumnie(){
        Pola[][] plansza = {
                {Pola.O, Pola.P, Pola.P},
                {Pola.O, Pola.P, Pola.X},
                {Pola.O, Pola.X, Pola.P}
        };
        char rezultat = nowaPlansza.sprawdzWynik(plansza);

        assertEquals('O',rezultat);
    }

    @Test
    public void wygranaKolkaPoDrugiejKolumnie(){
        Pola[][] plansza = {
                {Pola.X, Pola.O, Pola.P},
                {Pola.P, Pola.O, Pola.X},
                {Pola.P, Pola.O, Pola.P}
        };
        char rezultat = nowaPlansza.sprawdzWynik(plansza);

        assertEquals('O',rezultat);
    }

    @Test
    public void wygranaKolkaPoTzeciejKolumnie(){
        Pola[][] plansza = {
                {Pola.X, Pola.P, Pola.O},
                {Pola.P, Pola.P, Pola.O},
                {Pola.P, Pola.X, Pola.O}
        };
        char rezultat = nowaPlansza.sprawdzWynik(plansza);

        assertEquals('O',rezultat);
    }

    @Test
    public void wygranaKrzyzykaPoPierwszymWierszu(){
        Pola[][] plansza = {
                {Pola.X, Pola.X, Pola.X},
                {Pola.O, Pola.P, Pola.X},
                {Pola.O, Pola.X, Pola.P}
        };
        char rezultat = nowaPlansza.sprawdzWynik(plansza);

        assertEquals('X',rezultat);
    }

    @Test
    public void wygranaKrzyzykaPoDrugimWierszu(){
        Pola[][] plansza = {
                {Pola.P, Pola.P, Pola.P},
                {Pola.X, Pola.X, Pola.X},
                {Pola.O, Pola.X, Pola.P}
        };
        char rezultat = nowaPlansza.sprawdzWynik(plansza);

        assertEquals('X',rezultat);
    }

    @Test
    public void wygranaKrzyzykaPoTrzecimWierszu(){
        Pola[][] plansza = {
                {Pola.P, Pola.P, Pola.P},
                {Pola.O, Pola.P, Pola.O},
                {Pola.X, Pola.X, Pola.X}
        };
        char rezultat = nowaPlansza.sprawdzWynik(plansza);

        assertEquals('X',rezultat);
    }

    @Test
    public void wygranaKolkaPoPierwszymWierszu(){
        Pola[][] plansza = {
                {Pola.O, Pola.O, Pola.O},
                {Pola.P, Pola.P, Pola.X},
                {Pola.P, Pola.X, Pola.P}
        };
        char rezultat = nowaPlansza.sprawdzWynik(plansza);

        assertEquals('O',rezultat);
    }

    @Test
    public void wygranaKolkaPoDrugimWierszu(){
        Pola[][] plansza = {
                {Pola.P, Pola.X, Pola.P},
                {Pola.O, Pola.O, Pola.O},
                {Pola.P, Pola.X, Pola.P}
        };
        char rezultat = nowaPlansza.sprawdzWynik(plansza);

        assertEquals('O',rezultat);
    }

    @Test
    public void wygranaKolkaPoTrzecimWierszu(){
        Pola[][] plansza = {
                {Pola.P, Pola.P, Pola.P},
                {Pola.P, Pola.P, Pola.X},
                {Pola.O, Pola.O, Pola.O}
        };
        char rezultat = nowaPlansza.sprawdzWynik(plansza);

        assertEquals('O',rezultat);
    }

    @Test
    public void remis(){
        Pola[][] plansza = {
                {Pola.O, Pola.X, Pola.X},
                {Pola.X, Pola.O, Pola.O},
                {Pola.O, Pola.X, Pola.X}
        };
        char rezultat = nowaPlansza.sprawdzWynik(plansza);

        assertEquals('R',rezultat);
    }

    @Test
    public void graSieToczyDalej(){
        Pola[][] plansza = {
                {Pola.O, Pola.P, Pola.O},
                {Pola.P, Pola.P, Pola.X},
                {Pola.P, Pola.X, Pola.P}
        };
        char rezultat = nowaPlansza.sprawdzWynik(plansza);

        assertEquals(' ',rezultat);
    }
}
