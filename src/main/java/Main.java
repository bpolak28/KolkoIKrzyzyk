
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner skan = new Scanner(System.in);
        System.out.println("Podaj wielkość planszy.");
        int wielkoscPlanszy=skan.nextInt();

        Plansza nowa = new Plansza(wielkoscPlanszy);

        while(!nowa.sprawdzCzyKoniec(nowa.getPlansza())){
            nowa.wyswietlPlansze(nowa.getPlansza());
            System.out.println("Kolej " + nowa.czyjaKolej());
            System.out.println("Podaj współrzędne pola na którym chcesz postawić znak: 'nr_wiersza''spacja''nr_kolumny'");
            int a = skan.nextInt() - 1;
            int b = skan.nextInt() - 1;
            try{
                if (nowa.wstawZnak(nowa.getPlansza(), a, b))
                    nowa.setCzyjaKolej(nowa.getCzyjaKolej() + 1);
            } catch (InvalidNumbersExeption e){
                System.out.println("Zajęte pole");
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Współrzędne wychodzą poza zakres planszy");
            }

        }
        nowa.wyswietlPlansze(nowa.getPlansza());

    }
}
