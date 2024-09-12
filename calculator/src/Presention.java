import dao.IDao;
import metier.IMetier;

import java.io.File;
 import java.lang.reflect.Method;
import java.util.Scanner;

public class Presention {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(new File("src/config.txt"));

        String daoClassName = scanner.nextLine();
        Class cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.newInstance();

        String metierClassName = scanner.nextLine();
        Class cMetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cMetier.newInstance();
        Method method = cMetier.getMethod("setDao",IDao.class);
        method.invoke(metier,dao);
        double resultat = metier.calcule();

        System.out.println("resultat " + resultat);
    }
}
