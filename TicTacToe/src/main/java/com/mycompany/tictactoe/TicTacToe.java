package com.mycompany.tictactoe;

import java.util.Scanner;

public class TicTacToe {

    public static boolean Fim(Scanner sc) {
        String sair;
        boolean opcao=false;
        do {
            System.out.println("Deseja finalizar? S/N");
            sair = sc.next();
            switch (sair) {
                case "S" -> {
                    opcao = true;
                    sair = "0";
                }
                case "s" -> {
                    opcao = true;
                    sair = "0";
                }
                case "N" -> {
                    opcao = false;
                    sair = "0";
                }
                case "n" -> {
                    opcao = false;
                    sair = "0";
                }
                default -> {
                    System.out.println("Opção inválida!");
                    sair = "1";
                }
            }
        } while (!sair.equals("0"));
        return opcao;
    }

    public static void Delay(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void LimparMatriz(String tictactoe[][]) { //chamado quando há a necessidade de resetar o game.
        for (int l = 0; l < 3; l++) {
            for (int m = 0; m < 3; m++) {
                tictactoe[l][m] = "";
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //criação do scanner, usado para o usuário informar um valor
        String[][] matriz = new String[3][3]; //criação da matriz usada para armazenar os valores do tic tac toe
        String linhaHorizontal = "-------"; // criação do texto na horizontal, usado para facilitar a geração do tabuleiro
        LimparMatriz(matriz); //usado para resetar a matriz
        System.out.println(); //quebra de linha
        boolean finalizar = false; //variável de finalização
        
        String[] jogadores = new String[2];

        for (int i = 0; i < 2; i++) {
            System.out.println("Informe o nome do " + (i + 1) + "º jogador(a):");
            jogadores[i] = sc.next();
        }

        /*Insira aqui uma função para cada jogador rodar os dados
        recomendo que use a função delay(milisegundos) para fazer uma animação
        dos dados rolando, por exemplo, "jogador 1 vai rodar os dados (delay) etc
        inventa ai!*/
        
        while (!finalizar) {
            
            
            System.out.println("A pontuação está de " + "pontuação" + " para o(a) jogador(a) " + jogadores[0]);
            System.out.println("e " + "pontuação" + " para o(a) jogador(a) " + jogadores[1]);
            finalizar=Fim(sc);
        }

        System.out.println();
        sc.close(); //fechar o scanner, usado para não ter um uso de dados desnecessário.
    }
}
