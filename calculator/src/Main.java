import dao.DaoImpl;
import dao.DaoImpl2;
import metier.MetierImpl;

public class Main {
    public static void main(String[] args) {
       DaoImpl dao = new DaoImpl();
        DaoImpl2 dao2 = new DaoImpl2();
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao2);
        double resultat = metier.calcule();
        System.out.println("resutat " + resultat);
    }
}