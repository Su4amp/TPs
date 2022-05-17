public class CometaHaley {
    public static void main(String[] args) {        
        
        int prox = 1986;
        int atual = 0;

        do {
            atual = MyIO.readInt();              
            if(atual != 0){
                while(atual >= prox) {
                    prox = prox + 76;
                }
                MyIO.println(prox);
            }
        }while(atual != 0);        
    }        
}
