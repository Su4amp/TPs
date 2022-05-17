#include <stdio.h>
#include <stdlib.h>
int main() {
    
    int atual = 0;
    int prox = 1986;
    do{
        scanf("%d", &atual);
        if(atual != 0){
            
            while(atual >= prox) {

                prox = prox + 76;
            }    
            printf("%d\n", prox);
        }
    }while(atual != 0);

    return 0;
}
