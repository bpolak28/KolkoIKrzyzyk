
import java.util.Scanner;

/**
 * Created by yulia on 09.02.2018 at 19:45
 */
public class Play {
    private static final char PUSTA = ' ';
    private static final char KRZYZYK = 'X';
    private static final char KOLKO = '0';
    private static int statusGry;
    private static final int GRA_AKTUWNA = 0, GRA_REMIS = 1, STATUS_WYGRANO_X = 3, STATUS_WYGRANO_0 = 4;
    private Scanner in = new Scanner(System.in);
    private int wiesz;
    private int kolumna;
    private static char graczAktuwny;
    private char[][] plansza;
    public int liczbaKrzyzykow;
    public int liczbaKolek;

    public Play() {
        System.out.println("Podaj wielkosc planszy:");
        int ile = in.nextInt();
        this.wiesz=ile;
        this.kolumna=ile;
        plansza=new char[wiesz][kolumna];
    }

    public Play(char[][] plansza) {
        this.plansza = plansza;
    }

    public void start(){
        startPlay();
        do {
            wprowadzenieOdGracza();
            analizagry();
            rysuj();
            if (statusGry == STATUS_WYGRANO_X){
                System.out.println("Wygrał X");
            } else if (statusGry == STATUS_WYGRANO_0){
                System.out.println("Wygrał 0");
            } else if (statusGry == GRA_REMIS){
                System.out.println("Gra remis");
            }
            graczAktuwny = (graczAktuwny == KRZYZYK ? KOLKO : KRZYZYK);
        } while (statusGry == GRA_AKTUWNA);
    }
    public void startPlay(){
        System.out.println("Początek gry ! ");

        for (int i = 0; i < wiesz; i++){
            for (int j = 0; j < kolumna; j++) {
                plansza[i][j] = PUSTA;
            }
        }
        graczAktuwny = KRZYZYK;
        rysuj();
    }

    public void wprowadzenieOdGracza(){
        boolean wprowadzenie = false;
        do {
            System.out.println("Gracz '" + graczAktuwny + "', wprowadz (1-3) z spacji ");
            int wiersz1 = in.nextInt() - 1;
            int kolumna1 = in.nextInt() - 1;
            if (wiersz1 >= 0 && wiersz1 < wiesz && kolumna1 >= 0 &&
                    kolumna1 < kolumna && plansza[wiersz1][kolumna1] == PUSTA) {
                plansza[wiersz1][kolumna1] = graczAktuwny;
                wprowadzenie = true;
            } else {
                System.out.println("Ta pozycja (" + (wiersz1 + 1) + "," + (kolumna1 + 1)
                        + ") jest nie dostepna");
            }
        } while (!wprowadzenie);
    }

    public void analizagry(){
        char winLines = findByLines();
        char winColumns = findByColumns();
        char winDiagonallyLeftRight = findByDiagonallyLeftRight();
        char winDiagonallyRightLeft = findByDiagonallyRightLeft();
        if (winLines == KRZYZYK || winColumns == KRZYZYK
                || winDiagonallyLeftRight == KRZYZYK || winDiagonallyRightLeft == KRZYZYK){
            statusGry = STATUS_WYGRANO_X;
        } else if (winLines == KOLKO || winColumns == KOLKO
                || winDiagonallyLeftRight == KOLKO || winDiagonallyRightLeft == KOLKO){
            statusGry = STATUS_WYGRANO_0;
        } else if (czyPewnaPlansza()){
            statusGry = GRA_REMIS;
        } else {
            statusGry = GRA_AKTUWNA;
        }
    }


    public boolean czyPewnaPlansza(){
        for (int i = 0; i< wiesz; i++){
            for (int j = 0; j < kolumna; j++){
                if (plansza[i][j] == PUSTA){
                    return false;
                }
            }
        }
        return true;
    }

    public char findByLines(){
        for (int i = 0; i < plansza.length; i++) {
            liczbaKolek = 0;
            liczbaKrzyzykow = 0;
            for (int j = 0; j < plansza.length; j++) {
                if (plansza[i][j] == KRZYZYK) {
                    liczbaKrzyzykow++;
                    if (liczbaKrzyzykow == plansza.length){
                        return KRZYZYK;
                    }
                }
                if (plansza[i][j] == KOLKO){
                    liczbaKolek++;
                    if (liczbaKolek == plansza.length){
                        return KOLKO;
                    }
                }
            }
        }
        return PUSTA;
    }

    public char findByColumns(){
        for (int i = 0; i < plansza.length; i++) {
            liczbaKolek = 0;
            liczbaKrzyzykow = 0;
            for (int j = 0; j < plansza.length; j++) {
                if (plansza[j][i] == KRZYZYK) {
                    liczbaKrzyzykow++;
                    if (liczbaKrzyzykow == plansza.length){
                        return KRZYZYK;
                    }
                }
                if (plansza[j][i] == KOLKO){
                    liczbaKolek++;
                    if (liczbaKolek == plansza.length){
                        return KOLKO;
                    }
                }
            }
        }
        return PUSTA;
    }

    public char findByDiagonallyLeftRight(){
        int i = 0;
        liczbaKolek = 0;
        liczbaKrzyzykow = 0;
        for (int j = 0; j < plansza.length; j++) {
            if (plansza[j][i] == KRZYZYK) {
                liczbaKrzyzykow++;
                if (liczbaKrzyzykow == plansza.length){
                    return KRZYZYK;
                }
            }
            if (plansza[j][i] == KOLKO){
                liczbaKolek++;
                if (liczbaKolek == plansza.length){
                    return KOLKO;
                }
            }
            i++;
        }
        return PUSTA;
    }

    public char findByDiagonallyRightLeft(){
        liczbaKolek = 0;
        liczbaKrzyzykow = 0;
        int i = plansza.length - 1;
        for (int j = 0; j < kolumna; j++) {
            if (plansza[i][j] == KRZYZYK) {
                liczbaKrzyzykow++;
                if (liczbaKrzyzykow == plansza.length){
                    return KRZYZYK;
                }
            }
            if (plansza[i][j] == KOLKO){
                liczbaKolek++;
                if (liczbaKolek == plansza.length){
                    return KOLKO;
                }
            }
            i--;
        }
        return PUSTA;
    }

    public void rysuj(){
        for (int i = 0; i < wiesz; i++) {
            for (int j = 0; j < kolumna; j++) {
                System.out.print(plansza[i][j]);
                if (j != kolumna - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i != wiesz - 1) {
                for(int k = 0; k < wiesz; k++){
                    System.out.print("- ");
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}
