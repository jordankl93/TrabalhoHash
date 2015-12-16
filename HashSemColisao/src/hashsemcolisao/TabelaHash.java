/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashsemcolisao;

import static java.lang.Math.abs;
import java.math.BigDecimal;

/**
 *
 * @author Jordan-PC
 */
public class TabelaHash {
    private static final int MAX = 1000001;
    private int tamanhoTab; 
    private int tamanhoNovaTab;
    private int qtdItemTab;
    private String[] tab;
    private String[] tabNova;
    
    public int countOut = 0;
    
    //Construtor que cria uma nova lista em cada posição
    public TabelaHash() {
        this.tamanhoTab = MAX;
        this.qtdItemTab = 0;
        this.tab = new String[tamanhoTab];

        limpaTab(tab, tamanhoTab);
    }
    
    private void limpaTab(String[] tab, int tamanhoTab){
        for(int i=0; i<tamanhoTab; i++){
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
    
    private int insertNewTab(String elemento){
        int tentativa = 0;
        int posicao = Hashing(elemento, tentativa, tamanhoNovaTab);
        
        while(tabNova[posicao] != null){
            tentativa++;
            posicao = Hashing(elemento, tentativa, tamanhoNovaTab);
        }
        //Achou posição livre
        tabNova[posicao] = elemento;
        return posicao;        
    }
    
    private void expandeTab(){
        this.tamanhoNovaTab = 2 * tamanhoTab;
        tabNova = new String[this.tamanhoNovaTab];
        limpaTab(tabNova, tamanhoNovaTab);
        
        //insere todos os elementos na nova tabela
        for(int i=0; i<tamanhoTab; i++)
            if(tab[i] != null)
                //insere na nova tabela
                insertNewTab(tab[i]);
        
        //seta os valores da nova tabala
        this.tamanhoTab = this.tamanhoNovaTab;
        this.tab = this.tabNova;        
    }
    
    //Insere o elemento na tabala hash
    public void insert(String elemento) {
        //verifico se é necessário expandir a tabela
        //Nesse caso, quando a quantidade de itens passa a metade da tabela aumenta o tamanho
        if(tamanhoTab < qtdItemTab + qtdItemTab)
            expandeTab();
        
        int tentativa = 0;
        int posicao = Hashing(elemento, tentativa, tamanhoTab);
        while(tab[posicao] != null){
            tentativa++;
            posicao = Hashing(elemento, tentativa, tamanhoTab); //tabela circular
        }
        tab[posicao] = elemento;
        qtdItemTab++;
    }
    
    //Função de busca na Hash. Assumi que o número de colisões é o tamanho da lista
    public String read(String chave) {
        int count = 0, tentativa = 0;
        BigDecimal big;
        long timeInit = System.nanoTime(), timeSearch;
        int posicao = Hashing(chave, tentativa, tamanhoTab);
        
        //A função de busca na lista retorna se existe o elemento
        while (tab[posicao] != null && !tab[posicao].equals(chave)) {
            //caso tenha buscado em todas as posições
            if(count == qtdItemTab){
                timeSearch = System.nanoTime() - timeInit;
                big = new BigDecimal(timeSearch);
                return chave + " " + big.movePointLeft(6).abs() + "ms [" + 0 + " colisoes] NAO";
            }
            tentativa++;
            posicao = Hashing(chave, tentativa, tamanhoTab); //tabela circular
            count++;
        }
        if(tab[posicao] == null){
            timeSearch = System.nanoTime() - timeInit;
            big = new BigDecimal(timeSearch);
            return chave + " " + big.movePointLeft(6).abs() + "ms [" + 0 + " colisoes] NAO";
        }
        else{
            timeSearch = System.nanoTime() - timeInit;
            big = new BigDecimal(timeSearch);
            return chave + " " + big.movePointLeft(6).abs() + "ms [" + 0 + " colisoes]";
        }
    }
}
