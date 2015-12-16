/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashhash;

import static java.lang.Math.abs;
import java.math.BigDecimal;

/**
 *
 * @author Jordan-PC
 */
public class TabelaHash {

    private static final int MAX = 1000001; // Tamanho máximo do vetor que representa a tabela
    private static final int MAXINT = 101; // Tamanho máximo das tabelas internas no vetor
    private HashStruct[] tab;
    public int countOut = 0;
    
    public TabelaHash() {
        this.tab = new HashStruct[MAX];

        for (int i = 0; i < MAX; i++) {
            tab[i] = null;
        }
    }

    //Funcção que transforma o (String)elemento em um indice da hash
    public int Hashing(String elemento, int tentativa, int tamanhoTab) {
        int chave = 0;
        //Pega cada representação numerica dos caracteres da palavra, multiplica com o index corrente e incrementa
        for (int i = 0; i < elemento.length(); i++) {
            chave += (i + tentativa) * abs(elemento.charAt(i));
        }

        //Como padrão calcula o resto da divisão com o tamanho do vetor
        return chave % tamanhoTab;
    }

    //Insere o elemento na tabala hash
    public void insert(String elemento) {
        boolean guardou = false;
        HashStruct hash;
        int tentativa = 0; // == Colisões
        int posicao = Hashing(elemento, tentativa, MAX);
        //Verifica se a tabela principal já  possui algum elemento
        if (tab[posicao] != null) {
            tentativa++;
            hash = tab[posicao];
            //Verifico se já possui colisão, senão  constroi uma nova tab hash
            while (!guardou) {
                if (hash.hashStruct != null) {
                    //Calculo de uma chave
                    posicao = Hashing(elemento, tentativa, MAXINT);
                    if (hash.hashStruct[posicao] == null) {
                        hash.hashStruct[posicao] = new HashStruct(elemento);
                        guardou = true;
                    }
                    else
                    {
                        //Passo a referência e continuo a procurar espaço vazio
                        hash = hash.hashStruct[posicao];
                        tentativa++;
                        //countOut++;
                    }
                } 
                else
                {
                    hash.hashStruct = new HashStruct[MAXINT];
                    hash.hashStruct[Hashing(elemento, tentativa, MAXINT)] = new HashStruct(elemento);
                    guardou = true;
                }
            }
        }
        else {
            tab[posicao] = new HashStruct(elemento);
        }

    }

    //Função de busca na Hash. Nesse caso o número de colisões foi dado pelo número de tabelas que
    //precisou ser vasculhada
    public String read(String chave) {
        HashStruct struct;
        BigDecimal big;
        long timeInit = System.nanoTime(), timeSearch;
        int posicao = Hashing(chave, 0, MAX), tentativa = 0;

        struct = tab[posicao];
        //A função de busca na lista retorna se existe o elemento
        if(struct != null){
            if (struct.elemento.equals(chave)) {
                timeSearch = System.nanoTime() - timeInit;
                big = new BigDecimal(timeSearch);
                return chave + " " + big.movePointLeft(6).abs() + "ms [" + 0 + " colisoes]";
            } 
            else {
                while(struct != null && struct.hashStruct != null){
                    tentativa++;
                    posicao = Hashing(chave, tentativa, MAXINT);
                    if(struct.hashStruct[posicao] != null && struct.hashStruct[posicao].elemento.equals(chave)){
                        timeSearch = System.nanoTime() - timeInit;
                        big = new BigDecimal(timeSearch);
                        return chave + " " + big.movePointLeft(6).abs() + "ms [" + tentativa + " colisoes]";
                    }
                    struct = struct.hashStruct[posicao];
                }
                timeSearch = System.nanoTime() - timeInit;
                big = new BigDecimal(timeSearch);
                return chave + " " + big.movePointLeft(6).abs() + "ms [" + tentativa + " colisoes] NAO";
            }
        }
        else{
            timeSearch = System.nanoTime() - timeInit;
            big = new BigDecimal(timeSearch);
            return chave + " " + big.movePointLeft(6).abs() + "ms [" + tentativa + " colisoes] NAO";
        }
    }
}
