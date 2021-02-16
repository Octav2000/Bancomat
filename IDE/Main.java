package IDE;
import java.util.Scanner;
import GUI.Login;
public class Main {

    public static String username;
    public static int password;

    public static int comanda;
    public static boolean loginSucces;

    public static MeniuBanca menu = new MeniuBanca();
    public static StocarePersoane persoane = new StocarePersoane();
    public static Scanner citesc = new Scanner(System.in);

    public static boolean contineNumaiLitere(String x) {
        for (int i = 0; i < x.length(); i++) {
            char c = x.charAt(i);
            if (!Character.isLetter(c) && c != ' ')
                return false;
        }
        return true;
    }

    public static boolean contineNumaiCifre(String passwordTest) {
        return passwordTest.chars().allMatch(Character::isDigit);
    }

    public static void logare() {
        int count = 3;
        boolean timp = true;

        System.out.print("Te rugam sa introduci numele si prenumele\nExemplu: Nume Prenume\nUsername: ");
        while (timp) {
            username = citesc.nextLine();
            try {
                boolean numaiLitere = contineNumaiLitere(username);
                if (!numaiLitere)
                    throw new ExceptieUsername();
            } catch (ExceptieUsername e1) {
                loginSucces = false;
                timp = false;
                break;
            }
            ;

            if (!persoane.existaPersoana(username)) {
                System.out.println("Ne pare rau, dar nu existi in baza noastra de date");
                loginSucces = false;
                timp = false;

            } else {
                System.out.print(
                        "Va rugam sa introduceti PIN-ul dumneavoastra. Tastati numai 4 cifre fara alte caractere\nPassword: ");
                while (count != 0) {
                    String passwordTest = citesc.nextLine();
                    try {
                        boolean numaiCifre = contineNumaiCifre(passwordTest);
                        if (!numaiCifre)
                            throw new ExceptiePassword();
                    } catch (ExceptiePassword e2) {
                        count--;
                        if (count > 0)
                            System.out.println("Ai introdus parola gresita. Mai ai " + count + " incercari");
                    }
                    password = Integer.parseInt(passwordTest);

                    if (!persoane.corectitudinePin(username, password)) {
                        count--;
                        if (count > 0)
                            System.out.println("Ai introdus parola gresita. Mai ai " + count + " incercari");

                    } else {
                        System.out.println("Bine ai venit!");
                        loginSucces = true;
                        timp = false;
                        break;
                    }

                }
                if (count == 0) {
                    loginSucces = false;
                    System.out.println("Ne pare rau, dar ai introdus parola gresita de 3 ori!");
                    timp = false;
                }
                count = 3;
            }
        }
    }

    public static void alegere() {
        int iesit = 0;
        if (loginSucces) {
            String x = "Da";// variabila pentru a verifica daca se doresc mai multe tranzactii

            while (x.equalsIgnoreCase("Da")) {
                System.out.println(
                        "Introduceti: \n 1 -> Interogare sold \n 2 -> Schimbare PIN \n 3 -> Depunere numerar \n 4 -> Retragere numerar \n 5 -> Transfer numerar \n 6 -> Iesire");
                comanda = Integer.parseInt(citesc.nextLine());

                switch (comanda) {
                    case 1 -> menu.interogareSold(persoane, username);
                    case 2 -> menu.schimbarePIN(persoane, username, citesc);
                    case 3 -> menu.depunereNumerar(persoane, username, citesc);
                    case 4 -> menu.retragereNumerar(persoane, username, citesc);
                    case 5 -> menu.transferNumerar(persoane, username, password, citesc);
                    case 6 -> iesit = 1;
                }

                if (iesit == 0) {
                    System.out.println("Doriti alta tranzactie?");
                    x = citesc.nextLine();
                } else {
                    x = "Nu";
                }
            }
        }

        System.out.println("La revedere!");
        persoane.afisareFisier();
        citesc.close();
    }

    public static void main(String[] args) {
         //logare();
        // alegere();
        new Login(persoane);
    }
}
