package metier;

public class Game {

    public String execute(String nombre) {
        String resultat = "";
        for (int i = 0; i < Integer.parseInt(nombre); i++) {
            int index = i + 1;
            resultat += getResultat(index);
        }
        return resultat;
    }

    private String getResultat(int index) {
        boolean isDivisibleBy3 = index % 3 == 0;
        boolean isDivisibleBy5 = index % 5 == 0;

        if( isDivisibleBy3 && isDivisibleBy5){
            return  "FizzBuzz";
        }
        if(isDivisibleBy3){
            return "Fizz";
        }
        if(isDivisibleBy5){
            return  "Buzz";
        }

        return String.valueOf(index);
    }
}
