/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashing;

import static java.lang.Math.abs;
import java.math.BigDecimal;

/**
 *
 * @author Jordan-PC
 */
public class TabelaHash {
    
    private static final int MAX = 1000001; // Tamanho máximo do vetor que representa a tabela
    private Lista[] tab;
    
    //Construtor que cria uma nova lista em cada posição
    public TabelaHash() {
        this.tab = new Lista[MAX];

        for (int i=0; i<MAX; i++) {
            tab[i] = new Lista();
        }
    }
    
    //Funcção que transforma o (String)elemento em um indice da hash
    public int Hashing(String elemento) {
        int soma = 0;
        //Pega cada representação numerica dos caracteres da palavra, multiplica com o index corrente e incrementa
        for (int i = 0; i < elemento.length(); i++) {
            soma += i * abs(elemento.charAt(i));
        }
        
        //Como padrão calcula o resto da divisão com o tamanho do vetor
        return soma % MAX;
    }
    
    //Insere o elemento na tabala hash
    public void insert(String elemento) {
        Lista lista;
        int posicao = Hashing(elemento);
        //tratar as colisões, por enquanto não vou mexer com isso
        lista = tab[posicao];
        lista.add(new No(elemento));
    }
    
    //Função de busca na Hash. Assumi que o número de colisões é o tamanho da lista
    public String read(String chave) {
        Lista lista;
        BigDecimal big;
        long timeInit = System.nanoTime(), timeSearch;
        int posicao = Hashing(chave);
        
        lista = tab[posicao];
        //A função de busca na lista retorna se existe o elemento
        if (lista.existeNo(chave)) {            
            timeSearch = System.nanoTime() - timeInit;
            big = new BigDecimal(timeSearch);
            return chave + " " + big.movePointLeft(6).abs() + "ms [" + lista.colisoes()+ " colisoes]";
        } 
        else {
            timeSearch = System.nanoTime() - timeInit;
            big = new BigDecimal(timeSearch);
            return chave + " " + big.movePointLeft(6).abs() + "ms [" + lista.colisoes()+ " colisoes] NAO";
        }
    }
}
