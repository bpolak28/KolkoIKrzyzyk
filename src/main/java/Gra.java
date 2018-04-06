import java.util.Scanner;

public class Gra {

    Plansza plansza;

    public Plansza stworzPlansze(){
        Scanner skan = new Scanner(System.in);
        int wielkosc = skan.nextInt();
        this.plansza= new Plansza(wielkosc);
        return this.plansza;
    }



}
