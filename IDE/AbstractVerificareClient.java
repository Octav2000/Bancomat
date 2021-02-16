package IDE;

abstract class AbstractVerificareClient {
    abstract boolean existaPersoana(String x) ;
    abstract int getSuma(String x);
    abstract int getPin(String x);
    abstract boolean corectitudinePin(String x, int y);
    abstract void actualizareClient(Client c);
}
