package IDE;


import java.io.*;
import java.util.*;

public class StocarePersoane extends  AbstractVerificareClient{
    private ArrayList<Client> clienti;

    public StocarePersoane() {
        this.clienti = new ArrayList<>();

        try {
            // citire din fisier a datelor clientilor si stocarea lor intr-un ArrayList
            File fisier = new File("D:\\Proiecte\\Bancomat\\listaPersoane.txt");
            Scanner citesc = new Scanner(fisier);
            while(citesc.hasNextLine()) {
                String s = citesc.nextLine(); // numele utilizatorului
                int a = Integer.parseInt(citesc.nextLine()); // pinul acestuia
                int b = Integer.parseInt(citesc.nextLine()); // suma acestuia
                Client c = new Client(s, a, b);
                this.clienti.add(c);
            }
            citesc.close();
        }catch(FileNotFoundException e){
            System.out.println("Fisierul nu a fost gasit");
        }
    }

    public void afisareFisier() {
        try{
            //Afisarea clientilor in acelasi fisier ( pentru actualizarea datelor)
            FileWriter afisez = new FileWriter("D:\\Proiecte\\Bancomat\\listaPersoane.txt");
            for (Client c : this.clienti) {
                afisez.write(c.getNume() + "\n");
                afisez.write(c.getPIN() + "\n");
                afisez.write(c.getSuma() + "\n");
            }
            afisez.close();
        }catch (IOException e){
            System.out.println("Nu s-a gasit fisierul");
        }
    }

    // metoda pentru a verifica daca numele persoanei exista in fisier
    public boolean existaPersoana(String x) {
        for (Client client : this.clienti) {
            if (x.equalsIgnoreCase(client.getNume()))
                return true;
        }
        return false;
    }

    // obtinerea sumei unei persoane folosind numele ei
    public int getSuma(String x) {
        for (Client client : this.clienti) {
            if (x.equals(client.getNume()))
                return client.getSuma();
        }
        return -1;
    }

    // obtinerea pinului unei persoane folosing numele ei
    public int getPin(String x) {
        for (Client client : this.clienti) {
            if (x.equals(client.getNume()))
                return client.getPIN();
        }
        return -1;
    }

    // verificare daca PIN-ul y corespunde cu pinul utilizatorului cu numele x
    public boolean corectitudinePin(String x, int y) {
        for (Client client : this.clienti) {
            if (x.equals(client.getNume()))
                if (client.getPIN() == y)
                    return true;
        }
        return false;
    }

    // actualizarea unui client din ArrayList cu clientul c
    public void actualizareClient(Client c){
        for(int i=0;i<this.clienti.size();i++)
            if(c.getNume().equals(this.clienti.get(i).getNume()))
                this.clienti.set(i,c);
    }
}
