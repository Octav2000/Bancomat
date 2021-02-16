package IDE;

import java.util.*;

public class MeniuBanca implements  OptiuniBanca {

    @Override
    public void interogareSold(StocarePersoane persoane, String username) {
        System.out.println("Suma curenta este: " + persoane.getSuma(username));
    }

    @Override
    public void schimbarePIN(StocarePersoane persoane, String username, Scanner citesc) {
        System.out.println("Pinul curent este: " + persoane.getPin(username));
        System.out.println("Introduceti noul PIN: ");
        int PINnou = Integer.parseInt(citesc.nextLine());

        if (PINnou == persoane.getPin(username))
            System.out.println("Ne pare rau, dar este acelasi PIN");

        else {
            Client c = new Client(username, PINnou, persoane.getSuma(username));
            persoane.actualizareClient(c);
            System.out.println("PIN schimbat");
        }
    }

    @Override
    public void depunereNumerar(StocarePersoane persoane,String username, Scanner citesc) {
        System.out.println("Suma curenta este de: " + persoane.getSuma(username));
        System.out.println("Care este suma pe care vreti sa o depuneti?");
        int sumaDepusa = Integer.parseInt(citesc.nextLine());

            int sumaCurenta = persoane.getSuma(username);
            sumaCurenta += sumaDepusa;

            Client c = new Client(username, persoane.getPin(username),sumaCurenta );
            persoane.actualizareClient(c);

            System.out.println("Efectuat");

    }

    @Override
    public void retragereNumerar(StocarePersoane persoane, String username,Scanner citesc) {
        System.out.println("Suma curenta este de: " + persoane.getSuma(username));
        System.out.println("Care este suma pe care vreti sa o retrageti?");
        int sumaRetrasa = Integer.parseInt(citesc.nextLine());

        if (sumaRetrasa > persoane.getSuma(username)) {
            System.out.println(
                    "Ne pare rau, dar suma pe care vreti sa o retrageti este mai mare decat suma valabila in cont");
        } else {

            int diferenta = persoane.getSuma(username) - sumaRetrasa;

            Client c = new Client(username, persoane.getPin(username),diferenta );
            persoane.actualizareClient(c);

            System.out.println("Efectuat");
        }
    }

    @Override
    public void transferNumerar(StocarePersoane persoane,String username,int password,Scanner citesc) {
        System.out.println("Suma curenta este de: " + persoane.getSuma(username));
        System.out.println("Care este suma pe care vreti sa o transferati?");
        int sumaTransfer = Integer.parseInt(citesc.nextLine());
        if (sumaTransfer > persoane.getSuma(username))
            System.out.println(
                    "Ne pare rau, dar suma pe care doriti sa o transferati este mai mare decat suma valabila in contul dumneavoastra");
        else {
            System.out.println("Introduceti numele contului in care vreti sa transferati banii");
            String utilizator = citesc.nextLine();
            if (persoane.existaPersoana(utilizator)) {
                int diferenta = persoane.getSuma(username) - sumaTransfer;
                int adunare = persoane.getSuma(utilizator) + sumaTransfer;
                int pinUtilizator = persoane.getPin(utilizator);

                Client c = new Client(username,password,diferenta);
                persoane.actualizareClient(c);

                c = new Client(utilizator,pinUtilizator,adunare);
                persoane.actualizareClient(c);

                System.out.println("Efectuat");
            }

        }
    }

}