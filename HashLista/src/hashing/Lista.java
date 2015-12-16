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

    No primeiro, ultimo;
    int totalNos;

    public Lista() {
        primeiro = ultimo = null;
        totalNos = 0;
    }

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
        int contador = 1;
        if (isEmpty() == false) {
            while (contador <= size()) {
                if (no.elemento.equals(elemento)) {
                    return true;
                } else {
                    no = no.prox;
                    contador++;
                }
            }
        }
        
        return false;
    }
}
