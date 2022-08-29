package com.mycompany.tictactoe;

import java.util.Scanner;

public class TicTacToe {
	
	public static int Dados(String jogador[]){ // função para decidir quem começará primeiro
	       System.out.println("Será decidido quem jogará primeiro através de um sistema de dados.");
	       System.out.println("O computador lançara um dado com número aleatório de 1 a 6 para cada um dos jogadores.");
	       System.out.println("O jogador cujo dado tiver tido o maior número começará a rodada.");
	       System.out.println("Em caso de empate os dados serão lançados novamente");
	       int d1 = 0; // variaveis dos dados
	       int d2 = 0;
	       int turno = 0; // retorna qual jogador ganhou
	       do {
	    	   d1 = (int) ((Math.random()*6+1)); // para pegar um número aleatório de 1 a 6.
	    	   d2 = (int) ((Math.random()*6+1));
	    	   Pontos(); // chamando a funcao de animacao dos pontos
	    	   if (d1 == d2) {
	    		   System.out.println("A rodada terminou empatada, ambos os dados deram "+d1);
	    	   } else if (d1 > d2) {
	    		   System.out.println("O "+jogador[0]+" ganhou a rodada. " + d1 + " > " + d2);
	    	   } else {
	    		   System.out.println("O "+jogador[1]+" ganhou a rodada. " + d2 + " > " + d1);
	    		   turno = 1;
	    	   }
	       } while(d1 == d2); // looping para garantir que tenha um vencedor 
		return turno;
	}
	
	public static void Pontos() { // função de animação dos pontos
		for (int i = 0; i < 3; i++) {
			Delay(500);
			System.out.print(".");
		}
		System.out.println();
		
	}

    public static boolean Fim(Scanner sc) {
        String sair;
        boolean opcao=false;
        do {
            System.out.println("Deseja finalizar? S/N");
            sair = sc.next();
            sair = sair.toUpperCase(); // para não precisar de 2 switch cases a mais
            switch (sair) {
                case "S" -> {
                    opcao = true;
                    sair = "0";
                }
                case "N" -> {
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
        Scanner sc = new Scanner(System.in); //criacao do scanner, usado para o usuário informar um valor
        String[][] matriz = new String[3][3]; //criacao da matriz usada para armazenar os valores do tic tac toe
        String linhaHorizontal = "-------"; // criacao do texto na horizontal, usado para facilitar a geracao do tabuleiro
        LimparMatriz(matriz); //usado para resetar a matriz
        System.out.println(); //quebra de linha
        boolean finalizar = false; //variavel de finalização
        
        String[] jogadores = new String[2];

        for (int i = 0; i < 2; i++) {
            System.out.println("Informe o nome do " + (i + 1) + "º jogador(a):");
            jogadores[i] = sc.nextLine(); // para evitar um erro que estava dando caso a pessoa colocasse o nome composto
            jogadores[i] = jogadores[i].toUpperCase(); // Matheus q pediu n sei pq 
        }
       
        int turno = Dados(jogadores); // funcao para decidir quem começará primeiro
        
        while (!finalizar) {
            
            /* A partir daqui, tendo como o turno, o jogador que iniciara, 0 como jogador 1, 1 como jogador 2.
             devera ser feito o inicio do jogo, jogador 1 como x, jogador 2 como bolinha, fixo ao jogador.
             devera apresentar o tik tak toe, e criar os primeiros 4 inputs sem validacao.
       
              
             */
            System.out.println("A pontuação está de " + "pontuação" + " para o(a) jogador(a) " + jogadores[0]);
            System.out.println("e " + "pontuação" + " para o(a) jogador(a) " + jogadores[1]);
            finalizar=Fim(sc);
        }

        System.out.println();
        sc.close(); //fechar o scanner, usado para não ter um uso de dados desnecessário.
    }
}
