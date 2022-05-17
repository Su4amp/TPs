public class DonaMonica {
    public static void main(String[] args) {
        
        int monica = 0, cria1 = 0, cria2 = 0, cria3 = 0;
        monica = MyIO.readInt();
        while(monica != 0){
            cria1 = MyIO.readInt();
            cria2 = MyIO.readInt();
            cria3 = monica - (cria1 + cria2);

            if(cria1 > cria2 && cria1 > cria3){
                MyIO.println(cria1);
            }
            else if(cria2 > cria1 && cria2 > cria3){
                MyIO.println(cria2);
            }
            else{
                MyIO.println(cria3);
            }
            monica = MyIO.readInt();
        }
    }
}

