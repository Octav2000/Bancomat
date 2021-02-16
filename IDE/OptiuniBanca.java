package IDE;

import java.util.Scanner;

// toate optiunile unui bancomat
public interface OptiuniBanca{
    public void interogareSold(StocarePersoane persoane, String username);
    public void schimbarePIN(StocarePersoane persoane,String username,Scanner citesc);
    public void depunereNumerar(StocarePersoane persoane,String username, Scanner citesc);
    public void retragereNumerar(StocarePersoane persoane, String username,Scanner citesc);
    public void transferNumerar(StocarePersoane persoane,String username,int password,Scanner citesc);
}
