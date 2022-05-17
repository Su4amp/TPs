public class ex1 {
    public static void main(String[] args) {
        int[] array = {2, 5, 7, 13, 25, 17, 18, 23};
        int x = 13;
        
        for(int i = 0; i < array.length; i++){
            
        }
       
        
        
        
        
    }
    public int metodoArray(int[] array, int x){
        boolean isDentro = false;

        for(int i = 0; i < array.length; i++){
            if (array[i] == x) {
                isDentro = true;
                System.out.println(isDentro);
            }else{
                isDentro = false;
                System.out.println(isDentro);
            }
        }
        return array[x];
    }
}
