package it.fi.itismeucci;

import java.util.ArrayList;

public class Messaggio {
    ArrayList <Biglietto> biglietti=new ArrayList();

    public Messaggio() {
    }

    public ArrayList<Biglietto> getBiglietti() {
        return biglietti;
    }

    public void setBiglietti(ArrayList<Biglietto> biglietti) {
        this.biglietti = biglietti;
    }
    public boolean Aggiungi(Biglietto b)
    {
        for(int i=0;i<biglietti.size();i++)
        {
            if(biglietti.get(i)==b)
            {
                return false;
            }
        }
        biglietti.add(b);
        return true;
    }
}
