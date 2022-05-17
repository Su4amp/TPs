#include <stdio.h>
#include <stdlib.h>
    int main(){

        int monica = 0, primeiraCriança = 0, segundaCriança = 0, terceiraCriança = 0;

        scanf("%d", &monica);
        while(monica != 0){

            scanf("%d", &primeiraCriança);
            
            scanf("%d", &primeiraCriança);
            terceiraCriança = monica - primeiraCriança - segundaCriança;

            if(primeiraCriança > segundaCriança && primeiraCriança > terceiraCriança){
                printf("%d\n", primeiraCriança);
            }
            else if(segundaCriança > primeiraCriança && segundaCriança > terceiraCriança){
                printf("%d\n", segundaCriança);
            }
            else{

                printf("%d\n", terceiraCriança);
            }
            scanf("%d", &monica);
        }
        return 0;
    }
