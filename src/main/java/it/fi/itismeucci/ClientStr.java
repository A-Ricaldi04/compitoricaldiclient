package it.fi.itismeucci;

import java.io.*;
import java.net.*;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ClientStr {
    String nomeServer = "localhost";
    int portaserver = 6789;
    Socket miosocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    XmlMapper invia = new XmlMapper();
    XmlMapper riceve = new XmlMapper();

    public Socket connetti() {
        System.out.println("2 CLIENT partito in esecuzione . . .");

        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            miosocket = new Socket(nomeServer, portaserver);
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
            System.exit(1);
        }
        return miosocket;
    }

    public void comunica() {
        try {
            Biglietto uno=new Biglietto("Palco 1-a");
            Messaggio one=new Messaggio();
            //SERIALIZZO IL MESSAGGIO
            String xml = invia.writeValueAsString(one);
            //Invio la richiesta
            outVersoServer.writeBytes(xml + '\n');
            stringaRicevutaDalServer = inDalServer.readLine();
            //deserializzo il messaggio inviaTO DAL SERVER
            Messaggio value= riceve.readValue(stringaRicevutaDalServer, Messaggio.class);
            for(int i=0;i<value.getBiglietti().size();i++)
            {
                System.out.println(value.getBiglietti().get(i));
            }
            //Invio il biglietto che voglio vendere
            Messaggio two=new Messaggio();
            two.Aggiungi(uno);
            String xml2=invia.writeValueAsString(two);
            outVersoServer.writeBytes(xml2 + '\n');
            stringaRicevutaDalServer = inDalServer.readLine();
            miosocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
            System.exit(1);
        }
    }
}

