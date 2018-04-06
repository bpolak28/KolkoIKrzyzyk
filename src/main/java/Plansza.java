
public class Plansza {
    private Pola[][] nowaPlansza;
    private int czyjaKolej = 0;

    public Pola[][] getPlansza(){
        return nowaPlansza;
    }

    public int getCzyjaKolej(){
        return czyjaKolej;
    }

    public void setCzyjaKolej(int nowaKolej){
        this.czyjaKolej=nowaKolej;
    }

    public char sprawdzWynik(Pola[][] tab){
        if(sprawdzWygrana(tab)== Pola.O || sprawdzWygranaPrzekatne(tab)== Pola.O){
            return 'O';
        } else if(sprawdzWygrana(tab)== Pola.X || sprawdzWygranaPrzekatne(tab)== Pola.X){
            return 'X';
        } else if(sprawdzCzyPelnaPlansza(tab)==1){
            return 'R';
        } else {
            return ' ';
        }
    }

    public boolean sprawdzCzyKoniec(Pola[][] plansza){
        char wynik=sprawdzWynik(plansza);
        if(wynik=='O'){
            System.out.println("=====    Wygrywa O   =====");
            return true;
        } else if(wynik=='X'){
            System.out.println("=====    Wygrywa X   =====");
            return true;
        } else if (wynik=='R'){
            System.out.println("=====    Remis   =====");
            return true;
        } else {
            return false;
        }
    }


    public Plansza(int wielkoscPlanszy){
        nowaPlansza = new Pola[wielkoscPlanszy][wielkoscPlanszy];
        for(int i=0; i<nowaPlansza.length;i++) {
            for (int j = 0; j < nowaPlansza[i].length; j++) {
                this.nowaPlansza[i][j]= Pola.P;
            }
        }
    }

    public Pola sprawdzWygranaPrzekatne(Pola[][] plansza){
        int wygranaO=0;
        int wygranaX=0;
        int b=0;
        for(int i=0;i<plansza.length;i++){
            if(plansza[i][b]== Pola.O){
                wygranaO++;
                if(wygranaO==plansza.length){
                    return Pola.O;
                }
            } else if(plansza[i][b]== Pola.X){
                wygranaX++;
                if(wygranaX==plansza.length){
                    return Pola.X;
                }
            }
            b++;
        }
        wygranaO=0;
        wygranaX=0;
        b=plansza.length-1;
        for(int i=0;i<plansza.length;i++){
            if(plansza[b][i]== Pola.O){
                wygranaO++;
                if(wygranaO==plansza.length){
                    return Pola.O;
                }
            } else if(plansza[b][i]== Pola.X){
                wygranaX++;
                if(wygranaX==plansza.length){
                    return Pola.X;
                }
            }
            b--;
        }
        return Pola.P;
    }

    public Pola sprawdzWygrana(Pola[][] plansza){
        int wygranaO=0;
        int wygranaX=0;
        for(int i=0;i<plansza.length;i++){
            for(int j=0 ;j<plansza[i].length;j++){
                if(plansza[i][j]== Pola.O){
                    wygranaO++;
                    if(wygranaO==plansza.length){
                        return Pola.O;
                    }
                } else if(plansza[i][j]== Pola.X){
                    wygranaX++;
                    if(wygranaX==plansza.length){
                        return Pola.X;
                    }
                }
            }
            wygranaO=0;
            wygranaX=0;
        }
        for(int i=0;i<plansza.length;i++){
            for(int j=0 ;j<plansza[i].length;j++){
                if(plansza[j][i]== Pola.O){
                    wygranaO++;
                    if(wygranaO==plansza.length){
                        return Pola.O;
                    }
                } else if(plansza[j][i]== Pola.X){
                    wygranaX++;
                    if(wygranaX==plansza.length){
                        return Pola.X;
                    }
                }
            }
            wygranaO=0;
            wygranaX=0;
        }
        return Pola.P;
    }

    public int sprawdzCzyPelnaPlansza(Pola[][] plansza){
        for(int i=0; i<plansza.length;i++) {
            for (int j = 0; j < plansza[i].length; j++) {
                if(plansza[i][j]==Pola.P){
                    return 0;
                }
            }
        }
        return 1;
    }

    public Pola czyjaKolej(){
        if(czyjaKolej%2==0){
            return Pola.O;
        } else {
            return Pola.X;
        }
    }

    public boolean wstawZnak(Pola[][] plansza, int i, int j) throws InvalidNumbersExeption, ArrayIndexOutOfBoundsException {
            if (plansza[i][j] != Pola.P) {
                throw new InvalidNumbersExeption();
            }
            plansza[i][j] = czyjaKolej();
            return true;
        }

    public void wyswietlPlansze(Pola[][] plansza){
      //  String planszaOut="";
        for(int i=0; i<plansza.length;i++){
            for(int j=0; j<plansza[i].length;j++){
                if(j==plansza.length-1){
                    System.out.print(plansza[i][j]);
                   // planszaOut+=plansza[i][j];
                } else {
                    System.out.print(plansza[i][j]+"|");
                   // planszaOut+=plansza[i][j]+"|";
                }
            }
            System.out.println();
        }
    }
}
