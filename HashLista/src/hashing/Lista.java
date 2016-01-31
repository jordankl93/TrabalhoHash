/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashing;

import javax.swing.JOptionPane;

/**
 *
 * @author Jordan-PC
 */
public class Lista {

    private No primeiro, ultimo;
    private int totalNos, colisoes; 

    public Lista() {
        primeiro = ultimo = null;
        totalNos = 0;
        colisoes = 1;
    }
    
    //Função relativa ao número de colisões que aconteceram para achar o nó
    public int colisoes(){
        return colisoes;
    }
    
    //Função que retorna o tamanho da lista
    public int size() {
        return totalNos;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(No n) {
        if (isEmpty()) {
            primeiro = ultimo = n;
        } else {
            n.prox = primeiro;
            primeiro = n;
        }
        totalNos++;
    }
    
    public boolean existeNo(String elemento) {
        No no = primeiro;
        //int contador = 1;
        colisoes = 1; // Reinicia o contador de colisões
        if (isEmpty() == false) {
            while (colisoes <= size()) {
                if (no.elemento.equals(elemento)) {
                    return true;
                } else {
                    no = no.prox;
                    colisoes++;
                }
            }
        }
        
        return false;
    }
}
