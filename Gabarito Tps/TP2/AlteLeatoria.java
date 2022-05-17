import java.util.Random;

public class AlteLeatoria {

    public static String sorteia(String str){

        Random gerador = new Random();
        gerador.setSeed(4);
        System.out.println((char)('a' + (Math.abs(gerador.nextInt()) % 26)));
        return str;
    } 
    public static void main(String[] args) {
        String teste = "";
        sorteia(teste);
    }
}
