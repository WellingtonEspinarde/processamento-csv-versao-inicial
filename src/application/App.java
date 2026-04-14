package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Produto;

public class App {
    public static void main(String[] args) throws Exception {
       
        Locale.setDefault(Locale.US);
        Scanner scan = new Scanner(System.in);

        ArrayList<Produto> carrinho = new ArrayList<>();

        System.out.println("Quantos produtos serao cadastrados?");
        int n =scan.nextInt();
        scan.nextLine();

        for(int i =0; i<n; i++){
            System.out.println("Digite os dados do produto vendido: ");
            System.out.print("Digite o nome do produto: ");
            String nome = scan.nextLine();
            System.out.print("Digite o preço do produto: ");
            double preco = scan.nextDouble();
            scan.nextLine();
            System.out.print("Digite a quantidade: ");
            int quantidade = scan.nextInt();
            scan.nextLine();

            Produto produto = new Produto(nome, preco, quantidade);
            carrinho.add(produto);
        }

        String caminho = "c:\\Origem";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho + "\\produto.csv" ))) { //instancia um recurso de escrita em arquivo passando o caminho como argumento.
            
            for (Produto produto: carrinho) { //foreach que escreve no arquivo.
                bw.write(produto.toString()); //pega essa linha do array e escreve no arquivo.
                bw.newLine();// escreve cada item em uma nova linha.
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever o arquivo: " + e.getMessage());
        }

        boolean sucesso = new File(caminho + "\\out" ).mkdir(); //cria uma sub pasta chamada "out" a partir do caminho e retorna um boolean com o resultado
        System.out.println("Pasta criada com sucesso: " + sucesso);

        String caminho2 = "c:\\Origem\\out\\summary.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho2))) { //instancia um recurso de escrita em arquivo passando o caminho como argumento.
            
            for (Produto produto : carrinho) { // foreach que escreve no arquivo.
                bw.write(produto.getNome() + ","); // pega essa linha do array e escreve no arquivo.
                bw.write(produto.totalString());// pega essa linha do array e escreve no arquivo.
                bw.newLine();// escreve cada item em uma nova linha.
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever o arquivo: " + e.getMessage());
        }

        scan.close();
    }
}
