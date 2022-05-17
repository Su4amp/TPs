#include <stdio.h>
#include <locale.h>
#include <stdbool.h>
#include <conio.h>
#include <ctype.h>

char *removeEspacos( char *out, const char *in )
{
    const char *p = in;
    int i = 0;
    while( *p )
    {
        if( !isspace(*p) )
            out[i++] = *p;
        p++;
    }
    out[i] = 0;
    return out;
}

verificaPalindromo(char text[1000]){
    
    char saida[1000];
    removeEspacos(saida, text);

    int i, tam, diferentes = 0;

    tam = strlen(saida);
    tam--;// último índice válido da string (vetor)
    i = 0; // primeiro índice válido da string (vetor)

    while(tam >= i){
        if(saida[i] != saida[tam]) // conta as letras diferentes
            diferentes ++;
        i++;
        tam--;
    }

    if(diferentes == 0)
        printf("SIM\n");
    else
        printf("NAO\n");

}

int main(){
    
    FILE *arq;
    char Linha[100];
    char *result;
    // Abre um arquivo TEXTO para LEITURA
    arq = fopen("pub.in", "rt");

    if (arq == NULL)  // Se houve erro na abertura
    {
        printf("Problemas na abertura do arquivo\n");
        return;
    }

    while (!feof(arq))
    {
	    // Lê uma linha (inclusive com o '\n')
        result = fgets(Linha, 1000, arq);  // o 'fgets' lê até 99 caracteres ou até o '\n'
        if (result)  // Se foi possível ler
	    verificaPalindromo(Linha);    
    }    
    fclose(arq);
    return 0;
}