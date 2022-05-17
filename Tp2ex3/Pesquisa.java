import java.io.*;
import java.text.Normalizer;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Pesquisa {
   public static boolean isFim(String s) {
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }
   public static void main(String[] args) throws Exception {
      BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream("pub.in"), "UTF-8"));
      String linha = "";
      int contador = 0;      
      
      String[] vetorNomes = new String[30];      

      while ((linha = bReader.readLine()) != null && !isFim(linha)) {
         String tiraHtml = linha.replace(".html", "");
         vetorNomes[contador] = tiraHtml;         
         contador++;
      }      
      while ((linha = bReader.readLine()) != null && !isFim(linha)) {         
         String removeDoisPontos = linha.replace(":", " -");         
         pesquisaSeq(removerAcento(removeDoisPontos), vetorNomes);                 
      }

      try {
         boolean append = true;
         FileHandler handler = new FileHandler("matrícula_sequencial.txt", append);
         Logger logger = Logger.getLogger("teste");
         logger.addHandler(handler);
         logger.info("aaaaaa");
         
      } catch (Exception e) {
         //TODO: handle exception
      }

   }
   public static String removerAcento(String str){
      return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
   }
   public static void pesquisaSeq(String nome, String[] vetor) {
      int posix = 0;
      while (posix < vetor.length-1 && !vetor[posix].equals(nome)) {
         posix += 1;
      }
      if (vetor[posix].equals(nome) == true) {
         System.out.println("SIM");
      } else {
         System.out.println("NAO");
      }
   }

   
   


}