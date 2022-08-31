package com.mycompany.tictactoe;

import java.util.Scanner;

public class TicTacToe {

	public static int Dados(String jogador[][]) { // função para decidir quem começará primeiro
		System.out.println("Será decidido quem jogará primeiro através de um sistema de dados.");
		System.out.println("O computador lançara um dado com número aleatório de 1 a 6 para cada um dos jogadores.");
		System.out.println("O jogador cujo dado tiver tido o maior número começará a rodada.");
		System.out.println("Em caso de empate os dados serão lançados novamente");
		int d1 = 0; // variaveis dos dados
		int d2 = 0;
		int turno = 0; // retorna qual jogador ganhou
		do {
			d1 = (int) ((Math.random() * 6 + 1)); // para pegar um número aleatório de 1 a 6.
			d2 = (int) ((Math.random() * 6 + 1));
			Pontos(); // chamando a funcao de animacao dos pontos
			if (d1 == d2) {
				System.out.println("A rodada terminou empatada, ambos os dados deram " + d1);
			} else if (d1 > d2) {
				System.out.println("O " + jogador[0][0] + " ganhou a rodada. " + d1 + " > " + d2);
			} else {
				System.out.println("O " + jogador[1][0] + " ganhou a rodada. " + d2 + " > " + d1);
				turno = 1;
			}
		} while (d1 == d2); // looping para garantir que tenha um vencedor
		return turno;
	}

	public static int Turno(int turnos) {
		int turno = 0;
		if (turnos == 0) {
			turno = 1;
		}
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
		boolean opcao = false;
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

	public static void LimparMatriz(String tictactoe[][]) { // chamado quando há a necessidade de resetar o game.
		for (int l = 0; l < 3; l++) {
			for (int m = 0; m < 3; m++) {
				tictactoe[l][m] = " ";
			}
		}
	}

	public static void EscreverMatriz(String tabuleiro[][]) {
		System.out.println(); // para separar do resto de informação, seria interessante usar um limpatela
								// aqui
		Pontos(); // pra dar uma sensação de carregamento
		System.out.println();
		System.out.println("    A    B    C"); // os esppaços são necessários para que fique bonito
		for (int i = 0; i < tabuleiro.length; i++) { // aqui uso o for pra navegar pela matriz e escrevcer ela
			for (int j = 0; j < tabuleiro.length; j++) {
				if (j == 0) {
					System.out.print((i + 1) + "   " + tabuleiro[i][j] + "  | "); // o segundo for serve pra escrever a
																					// linha,
				} else if (j == 1) { // a barra para separar um do outro
					System.out.print(tabuleiro[i][j] + "  | ");
				} else {
					System.out.println(tabuleiro[i][j]);
				}
			}
			if (i != 2) {
				System.out.println("   ----+----+----"); // ja esse aqui é para quando for pular de linha
			}
		}
		System.out.println();
		Pontos(); // pro usuário conseguir ver a tabela de forma tranquila
		System.out.println(); // aqui também é pra separar, seria interessante um limpatela
	}

	public static void Jogadas(String[][] tabuleiro, String jogador[][], int turno) {
		Scanner sc = new Scanner(System.in);
		String jogada;
		int valorAux = 0; // guardar o valor numerico de caracteres
		int valorAux2 = 0; // guaradr o valor numerico de digitos
		boolean verif = false; // verificacao condicao do caractere alfabetico
		boolean verif2 = false; // verificacao condicao do digito
		do {
			EscreverMatriz(tabuleiro); // para escrever a matriz
			System.out.println("Onde deseja jogar?");
			jogada = sc.nextLine(); // ler a jogada
			if (jogada.length() != 2) { // se for diferente de dois caracteres ja interrompe e volta pro comeco
				System.out.println("Por favor digite uma opção válida");
			} else { // se for igual a dois caracteres vem nesse else
				for (int i = 0; i < jogada.length(); i++) { // para navegar nos dois caracteres da string
					char aux = jogada.charAt(i); // separa a string em um caractere cada
					if ('a' <= aux && aux <= 'c') { // se for a, b ou c a condicao e verdadeira ocorre o if
						valorAux = Character.getNumericValue(aux); // recebe um valor numerico correspondente a letra
						if (valorAux >= 10 && valorAux <= 12) { // a = 10, b = 11, c = 12; por isso os intervalos
							verif = true;
						} 
					}
					if ('0' <= aux && aux <= '9') {  // se for 1, 2 ou 3 a condicao e verdadeira e ocorre o if
						valorAux2 = Character.getNumericValue(aux); // recebe o valor do numero basciamente (1,2 ou 3)
						if (valorAux2 >= 1 && valorAux2 <= 3) {
							verif2 = true;
							break;
						}
					}
				}
				if (verif == true) {
					valorAux = valorAux - 10; // diminui em 10 para ficar na coluna certa
					tabuleiro[valorAux2 -1][valorAux] = jogador[turno][1]; // aqui salvei o caractere na matriz do jogador
				} else {
					System.out.println("Opção inválida. Favor confira as regras novamente.");
				}
			}
		} while (verif == false || verif2 == false);

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // criacao do scanner, usado para o usuário informar um valor
		String[][] matriz = new String[3][3]; // criacao da matriz usada para armazenar os valores do tic tac toe
		String linhaHorizontal = "-------"; // criacao do texto na horizontal, usado para facilitar a geracao do
											// tabuleiro
		LimparMatriz(matriz); // usado para resetar a matriz
		System.out.println(); // quebra de linha
		boolean finalizar = false; // variavel de finalização

		String[][] jogadores = new String[2][2]; // transformei em matriz para guardar qual simnbolo e tbm

		for (int i = 0; i < 2; i++) {
			System.out.println("Informe o nome do " + (i + 1) + "º jogador(a):");
			jogadores[i][0] = sc.nextLine(); // para evitar um erro que estava dando caso a pessoa colocasse o nome
												// composto
			jogadores[i][0] = jogadores[i][0].toUpperCase(); // Matheus q pediu n sei pq
		}

		int turno = Dados(jogadores); // funcao para decidir quem começará primeiro
		if (turno == 0) {
			jogadores[0][1] = "X";
			jogadores[1][1] = "O";
		} else if (turno == 1) {
			jogadores[0][1] = "O";
			jogadores[1][1] = "X";
		}

		while (!finalizar) {
			/*
			 * A partir daqui, tendo como o turno, o jogador que iniciara, 0 como jogador 1,
			 * 1 como jogador 2. devera ser feito o inicio do jogo, jogador 1 como x,
			 * jogador 2 como bolinha, fixo ao jogador. devera apresentar o tik tak toe, e
			 * criar os primeiros 4 inputs sem validacao.
			 * 
			 * 
			 */
			Jogadas(matriz, jogadores, turno);
			EscreverMatriz(matriz);
			turno = Turno(turno);

			System.out.println("A pontuação está de " + "pontuação" + " para o(a) jogador(a) " + jogadores[0][0]);
			System.out.println("e " + "pontuação" + " para o(a) jogador(a) " + jogadores[1][0]);
			finalizar = Fim(sc);
		}

		System.out.println();
		sc.close(); // fechar o scanner, usado para não ter um uso de dados desnecessário.
	}
}
