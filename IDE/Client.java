package IDE;

public class Client {
    private String nume;
    private int PIN;
    private int suma;

    public Client(String nume, int PIN, int suma) {
        this.nume = nume;
        this.PIN = PIN;
        this.suma = suma;
    }

    public String getNume() {
        return nume;
    }

    public int getPIN() {
        return PIN;
    }

    public Integer getSuma() {
        return suma;
    }

}
