import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.xml.crypto.Data;

public class Filmes {
    static Filme filmes = new Filme();
    public static String TagRemover(String original){
        String tiraTitle = original.replaceAll("<[^>]*>", "");
        String limpa = "";
        int i = 0;
        do{
            i++;
            limpa += tiraTitle.charAt(i);
        }while(original.charAt(i+9) != '(');
        return limpa.trim();
    }    
    public static void main(String[] args) throws Exception {
        String entrada = "src/pub.in";
        String entradaVerde = "pub.in";

        FileReader fReader = new FileReader(entradaVerde);
        BufferedReader bReader = new BufferedReader(fReader);
        
        String ler = "";
        String caminho = "";
        String caminhoVerde = "";

        while ((ler = bReader.readLine()) != null) {
            caminho = "src/filmes/" + ler;
            caminhoVerde = "/tmp/filmes/" + ler;            
            if (!caminhoVerde.contains("FIM")) {
                LerTitulo(caminhoVerde);
                System.out.print(filmes.getNome() + " ");
                
                LerTituloOriginal(caminhoVerde, filmes);
                if (filmes.getTitloOriginal() != null) {                    
                    System.out.print(filmes.getTitloOriginal() + " ");
                }
                LerDataLancamento(caminhoVerde);
                SimpleDateFormat fdata = new SimpleDateFormat("dd/MM/yyyy");
                System.out.print(fdata.format(filmes.getDataDeLancamento()) + " ");

                lerDuracao(caminhoVerde);
                System.out.print(filmes.getDuracao() + " ");

                LerGenero(caminhoVerde);
                System.out.print(filmes.getGenero() + " ");

                LerIdiomaOriginal(caminhoVerde);
                System.out.print(filmes.getIdiomaOriginal() + " ");

                LerSituacao(caminhoVerde);
                System.out.print(filmes.getSituacao() + " ");

                LerOrcamento(caminhoVerde);
                System.out.print(filmes.getOrcamento() + " ");

                LerPalavrasChave(caminhoVerde);
                System.out.println("");              
                
            }
        } 
        bReader.close();
    }
    public static void Clonar(String nome, String string, Date date){
        Filme filmes = new Filme();
        filmes.setNome(nome);
        filmes.setTitloOriginal(nome);        
        filmes.setDataDeLancamento(date);
    }

    public static void LerTitulo(String arquivo) throws Exception{
        
        BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF-8"));
        String linha = "";        
        while (!linha.contains("<title>")) {
            linha = bReader.readLine();
            if (linha.contains("<title>")) {
                filmes.setNome(TagRemover(linha));
            }                        
        }
        bReader.close();                  
    }

    public static void LerTituloOriginal(String arquivo, Filme filmes) throws Exception{
        filmes.setTitloOriginal(filmes.getNome());
        BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF-8"));
        String linha = "";        
        while ((linha = bReader.readLine()) != null) {
            String limpaOficial = "";            
            if (linha.contains("<p class=\"wrap\">")) {                
                String[] separa = linha.split(">"); 
                int i = 0; 
                String limpa = "";
                limpa = separa[3];                
                do {
                    i++;
                    limpaOficial += limpa.charAt(i);
                } while (limpa.charAt(i+1) != '<');
                filmes.setTitloOriginal(limpaOficial);
            }
        }
        bReader.close();
    }

    public static void LerDataLancamento(String arquivo) throws Exception{
        
        Date data = new Date();       
        Scanner scan = new Scanner(new File(arquivo));
        while(scan.hasNextLine()){
            String linha = scan.nextLine();            
            if (linha.contains("<span class=\"release\">")) {          
                linha = scan.nextLine();
                String armazenaLinha = "";
                int i = 0;
                do {
                    i++;
                    armazenaLinha += linha.charAt(i);
                } while (linha.charAt(i+2) != '(');
                
                String[] datinha = armazenaLinha.split("/");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                data = sdf.parse(datinha[0].trim()+"/"+datinha[1].trim()+"/"+datinha[2].trim());
                filmes.setDataDeLancamento(data);
            }
        }                
    }

    public static void lerDuracao(String arquivo) throws Exception{
        
        Scanner scan = new Scanner(new File(arquivo));
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            if (linha.contains("<span class=\"runtime\">")) {
                linha  = scan.nextLine();
                linha  = scan.nextLine();
                if (linha.contains(" ")) {                    
                    String tiraEspaco = linha.replace(" ", "") ;      
                    String[] horas = tiraEspaco.split("h");
                    if (horas.length == 2) {
                        int HoraOficial = Integer.parseInt(horas[0]) * 60;
                       
                        String tiraM = horas[1].replace("m", "");                       
                        int MinutoOficial = Integer.parseInt(tiraM);
                        int duracao = HoraOficial + MinutoOficial;                        
                        filmes.setDuracao(duracao);
                   }           
                }                        
            }
        }
    }

    public static void LerGenero(String arquivo) throws Exception{
        
        Scanner scan = new Scanner(new InputStreamReader(new FileInputStream(arquivo), "UTF-8"));
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            if (linha.contains("<span class=\"genres\">")) {
                linha  = scan.nextLine();
                linha = scan.nextLine();
                String tiraTitle = linha.replaceAll("<[^>]*>", "");
                String tiraNbsp = tiraTitle.replaceAll("&nbsp;", "");
                filmes.setGenero(tiraNbsp.trim());               
            }
        }
    }

    public static void LerIdiomaOriginal(String arquivo) throws Exception{
       
        BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF-8"));
        String linha = "";
        while ((linha = bReader.readLine()) != null) {
            if (linha.contains("<p><strong><bdi>Idioma original</bdi></strong>")){
                String[] separa = linha.split(" ");
                String limpa = "";                           

                for (int i = 0; separa[4].charAt(i) != '<'; i++) {
                    limpa += separa[4].charAt(i);                                       
                }
                filmes.setIdiomaOriginal(limpa.trim());
            }
        }
        bReader.close();
    }

    public static void LerSituacao(String arquivo) throws Exception{        
        
        BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF-8"));
        String linha = "";
        while ((linha = bReader.readLine()) != null) {
            if (linha.contains("<strong><bdi>Situação</bdi></strong>")){
                String[] separa = linha.split(">");                                
                filmes.setSituacao(separa[4].trim());
            }
        }
        bReader.close();
    }

    public static void LerOrcamento(String arquivo) throws Exception{
        
        BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF-8"));
        String linha = "";
        while ((linha = bReader.readLine()) != null) {
            if (linha.contains("<p><strong><bdi>Orçamento</bdi></strong>")) {
                String[] separa = linha.split(">");
                String limpa = "";
                int i = 0;
                do {
                    i++;
                    limpa += separa[5].charAt(i-1);
                } while (separa[5].charAt(i) != '<');
                String tiraCifrao = limpa.replace("$", "");                
                String tiraTudo = tiraCifrao.replace(",", "");
                String tiraTraco = tiraTudo.replace(" -", "0.0");
                Float orcamento = Float.parseFloat(tiraTraco);  
                filmes.setOrcamento(orcamento); 
            }
        }
        bReader.close();
    }

    public static void LerPalavrasChave(String arquivo) throws Exception{                
           
        BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF-8"));
        String linha = ""; 
        List<String> nomes = new ArrayList<>();
        String[] chaves = new String[50]; 

        int contador = 0;
        while ((linha = bReader.readLine()) != null){                                 
            if (linha.contains("<li><a href=\"/keyword/")) {
                String limpaChave = linha.replaceAll("<[^>]*>", "");
                chaves[contador] = limpaChave.trim();
                nomes.add(chaves[contador]);
                contador++; 
            }     
        }
        bReader.close();
        System.out.print(nomes);
    }
}
class Filme{
    private String Nome;
    private String TitloOriginal;
    private String Genero;
    private String IdiomaOriginal;
    private String Situacao;
    private String[] PalavrasChave;

    private Date DataDeLancamento;
    private int Duracao;    
    private Float Orcamento;    

    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }
    public String getTitloOriginal() {
        return TitloOriginal;
    }
    public void setTitloOriginal(String titloOriginal) {
        TitloOriginal = titloOriginal;
    }
    public Date getDataDeLancamento() {
        return DataDeLancamento;
    }
    public void setDataDeLancamento(Date dataDeLancamento) {
        DataDeLancamento = dataDeLancamento;
    }
    public int getDuracao() {
        return Duracao;
    }
    public void setDuracao(int duracao) {
        Duracao = duracao;
    }
    public String getGenero() {
        return Genero;
    }
    public void setGenero(String genero) {
        Genero = genero;
    }
    public String getIdiomaOriginal() {
        return IdiomaOriginal;
    }
    public void setIdiomaOriginal(String idiomaOriginal) {
        IdiomaOriginal = idiomaOriginal;
    }
    public String getSituacao() {
        return Situacao;
    }
    public void setSituacao(String situacao) {
        Situacao = situacao;
    }
    public Float getOrcamento() {
        return Orcamento;
    }
    public void setOrcamento(Float orcamento) {
        Orcamento = orcamento;
    }
    public String[] getPalavrasChave() {
        return PalavrasChave;
    }
    public void setPalavrasChave(String[] palavrasChave) {
        PalavrasChave = palavrasChave;
    }
}