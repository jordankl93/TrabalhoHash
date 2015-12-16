/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashhash;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Jordan-PC
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        assert (args.length == 3);
        String entrada = args[0];
        String busca = args[1];
        String saida = args[2];

        try {

            TabelaHash teste = new TabelaHash();
            Scanner scannerEntrada = new Scanner(new FileReader(entrada));
            Scanner scannerBusca = new Scanner(new FileReader(busca));

            while (scannerEntrada.hasNext()) {
                String texto = scannerEntrada.nextLine();
                teste.insert(texto);
                //System.out.println(texto);
            }

            FileWriter arq = new FileWriter(saida);
            PrintWriter gravarArq = new PrintWriter(arq);

            gravarArq.println("Hash de Hashes: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) + " bytes de memória");

            while (scannerBusca.hasNext()) {
                gravarArq.println(teste.read(scannerBusca.nextLine()));
            }

            gravarArq.close();
            arq.close();

            System.out.println("Tudo ocorreu bem... a principio -> perdidas:" + teste.countOut);
        } 
        catch (Exception e) {
            System.out.println("Você digitou algum arquivo errado! " + e.getMessage());
        }
    }

}
