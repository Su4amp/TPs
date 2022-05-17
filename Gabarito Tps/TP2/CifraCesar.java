import java.util.*;

public class CifraCesar {

    public static boolean isFim(String s) {
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
     }

    public static String Ciframento(String str){
        int key = 3;
        String cripto = "";
        
        for (int i = 0; i < str.length(); i++){
            cripto += (char) (str.charAt(i) + key);
        }       
        return cripto;
    }
    public static void main(String[] args) {
        String[] entrada = new String[1000];
        int numEntrada = 0;
                
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        for (int i = 0; i < numEntrada; i++) {
            MyIO.println(Ciframento(entrada[i]));
        }
    }
}