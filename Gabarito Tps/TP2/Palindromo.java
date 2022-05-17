import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Palindromo{    

    //verifica se é palindromo e imprimi sim e não em cada caso
    public static void retornaPalindromo(String palavra){
        String palindromo = "";        
        for(int i = palavra.length() - 1; i >= 0; i--){
            palindromo += palavra.charAt(i);            
        } 
        
        if(palindromo.equals(palavra)) {
            System.out.println("SIM");
        }
        else {
            System.out.println("NAO");            
        }
        
    }
    //verifica se existe a palavra "FIM" no final do código
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
  
    public static void main (String[] args){

        FileReader fr;
        try {
            //aqui é feita a leitura do arquivo pub.in  
            fr = new FileReader("pub.in");
            BufferedReader br = new BufferedReader(fr);
            String linha;            

            while ((linha = br.readLine()) != null) {                
                retornaPalindromo(linha);                
            }
            br.close();

        } catch (IOException e) {
            
            e.printStackTrace();
        }        
               
    }
}