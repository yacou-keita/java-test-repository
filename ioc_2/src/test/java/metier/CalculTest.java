package metier;

import org.junit.Assert;
import org.junit.Test;

public class CalculTest {

    @Test
    public void TestSomme(){
        double nbr1 = 5; double nbr2 = 4;
        double expectedResult = 9;
        Calcul calcul = new Calcul();
        double calculResponse = calcul.somme(nbr1,nbr2);
        Assert.assertTrue(expectedResult == calculResponse);
    }


}
