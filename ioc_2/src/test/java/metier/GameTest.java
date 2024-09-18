package metier;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    Game game = new Game();

    @Test
   public void returnNumberIfNotMultipleOf3Or5Or3And5(){
        String resultat = game.execute("1");
        Assert.assertEquals("1",resultat);
    }

    @Test
   public void return12FizzIfNumberIs3(){
    String resultat = game.execute("3");
    Assert.assertEquals("12Fizz", resultat);
    }

    @Test
   public void return12Fizz4BuzzIfNumberIs5(){
    String resultat = game.execute("5");
    Assert.assertEquals("12Fizz4Buzz", resultat);
    }

    @Test
   public void return12Fizz4BuzzFizz78Fizz1011Fizz1314FizzBuzzIfNumberIs15(){
    String resultat = game.execute("15");
    Assert.assertEquals("12Fizz4BuzzFizz78FizzBuzz11Fizz1314FizzBuzz", resultat);
    }

    @Test
   public void return12Fizz4BuzzFizz78Fizz1011Fizz1314FizzBuzz1617Fizz19BuzzIfNumberIs20(){
    String resultat = game.execute("20");
    Assert.assertEquals("12Fizz4BuzzFizz78FizzBuzz11Fizz1314FizzBuzz1617Fizz19Buzz", resultat);
    }

}
