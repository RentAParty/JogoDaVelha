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
				jogador[0][1] = "X";
				jogador[1][1] = "O";
			} else {
				System.out.println("O " + jogador[1][0] + " ganhou a rodada. " + d2 + " > " + d1);
				jogador[0][1] = "O";
				jogador[1][1] = "X";
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
		boolean condicao;
		int letra = 0;
		int valorAux = 0; // guardar o valor numerico de caracteres
		int valorAux2 = 0; // guaradr o valor numerico de digitos
		boolean verif = false; // verificacao condicao do caractere alfabetico
		boolean verif2 = false; // verificacao condicao do digito
		char[] aux = new char[2]; // guarda os dois caracteres da string de forma separada
		do {
			EscreverMatriz(tabuleiro); // para escrever a matriz
			System.out.println("Onde deseja jogar?");
			jogada = sc.nextLine(); // ler a jogada
			if (jogada.length() != 2) { // se for diferente de dois caracteres ja interrompe e volta pro comeco
				System.out.println("Por favor digite uma opção válida");
			} else { // se for igual a dois caracteres vem nesse else
				for (int i = 0; i < jogada.length(); i++) { // para navegar nos dois caracteres da string
					aux[i] = jogada.charAt(i); // separa a string em um caractere cada
				}
				if (Character.isLetter(aux[0]) == true) { // se o primeiro caractere for uma letra cai aqui, se for
															// digito cai no else
					valorAux = Character.getNumericValue(aux[0]); // pego o codigo dela (A=10, B=11, C=12) indifere
																	// maiusc ou minusc
					if (valorAux < 13 && valorAux > 9) { // se for 10, 11 ou 12 verdadeiro, senao fica falso e tem de
															// fazer de novo
						verif = true;
					}
					if (Character.isDigit(aux[1]) == true) { // aqui checa o segundo caractere, se for digito vai pra frente caso contrario fica falso direto
						valorAux2 = Character.getNumericValue(aux[1]); // pega o valor do numero q no caso e ele mesmo
						if (valorAux2 > 0 && valorAux2 < 4) { // se for 1,2 ou 3 e verdadeiro, caso contrario falso
							verif2 = true;
						}
					}
				} else if (Character.isDigit(aux[0]) == true) { // a partir daqui e o mesmo processo que antes
					valorAux2 = Character.getNumericValue(aux[0]); // porem com numero primeiro e letra depois
					if (valorAux2 < 0 && valorAux2 < 4) {          // para caso o usaurio digitar 1a ao inves de a1 nao der invalido
						verif2 = true;
					}
					if (Character.isLetter(aux[1]) == true) {
						valorAux = Character.getNumericValue(aux[1]);
						if (valorAux < 13 && valorAux > 9) {
							verif = true;
						}
					}
				}
				if (verif == true && verif2 == true) { // se as condicoes atenderem entra nesse if, se nao o usuario tem que por outro input
					valorAux = valorAux - 10; // para poder entrar na coluna certa
					condicao = EspacoVazio(tabuleiro, valorAux2, valorAux); // para checar se o espaco esta disponivel
					if (condicao == true) { // se sim, entra aqui, se nao escreve que o espaco esta ocupado
						tabuleiro[valorAux2 - 1][valorAux] = jogador[turno][1]; // aqui pega o simbolo do jogador e escreve no tabuleiro
					} else {
						System.out.println("Espaço já ocupado.");
						verif = false;
						verif2 = false; // nao sei se precisa desses false na verdade, mas to com medo de apagar KKKKKKKKKKKKK
					}
				} else { // se for dois num ou duas letras ou for uma letra ou num invalido, acontece esse sysout 
					System.out.println("Opção inválida. Favor confira as regras novamente.");
				}
			}
		} while (verif == false || verif2 == false);
	}

	public static boolean EspacoVazio(String[][] tabuleiro, int coluna, int linha) { //  para checar se ha espaco vazio
		coluna--; // tem que tirar um da coluna para funcionar (1-3)-1 = (0-2) intervalo das
					// matrizes
		boolean espaco = false; // se espaco for falso, ent espaco ta ocupado
		if (tabuleiro[coluna][linha].equals(" ")) { // se espaco estiver em branco, espaco = true
			espaco = true;
		}

		return espaco;
	}

	public static String[] Ganhador(String[][] tabuleiro, String[][] jogador) { // sistema para verificar se houve um
																				// ganahdor
		String[] vencedor = new String[2]; // para armazenar se houve um ganhador (vencedor[0]) e quem ganhou
											// (venceddor[1])
		vencedor[0] = "false";
		String aux1 = jogador[0][1]; // essas duas variaveis recebem o simbolo do jogador
		String aux2 = jogador[1][1];
		for (int i = 0; i < tabuleiro.length; i++) { // esse for confere se houve ganhador na linha
			for (int j = 0; j < tabuleiro.length; j++) {
				if (tabuleiro[i][j].equals(jogador[0][1])) { // aqui vai adicionando o simbolo do jogador ate o fim da
																// linha
					aux1 = aux1 + aux1;
				} else if (tabuleiro[i][j].equals(jogador[1][1])) {
					aux2 = aux2 + aux2;
				}
				if (aux1.length() == 4) { // se houve algum simbolo repetidos 4 vezes, houve um ganhador
					vencedor[0] = "true"; // vencedor[0] recebe "true" e vencedor[1] "0" ou "1" dependendo do jogador
					vencedor[1] = "0"; // foi o jeito mais otimizado que achei
				} else if (aux2.length() == 4) {
					vencedor[0] = "true";
					vencedor[1] = "1";
				}
			}
			aux1 = jogador[0][1];
			aux2 = jogador[1][1];
		}
		for (int i = 0; i < tabuleiro.length; i++) { // mesma coisa do outro for, mas parta colunas
			for (int j = 0; j < tabuleiro.length; j++) {
				if (tabuleiro[j][i].equals(jogador[0][1])) {
					aux1 = aux1 + aux1;
				} else if (tabuleiro[j][i].equals(jogador[1][1])) {
					aux2 = aux2 + aux2;
				}
				if (aux1.length() == 4) {
					vencedor[0] = "true";
					vencedor[1] = "0";
				} else if (aux2.length() == 4) {
					vencedor[0] = "true";
					vencedor[1] = "1";
				}
			}
			aux1 = jogador[0][1];
			aux2 = jogador[1][1];
		}
		if (tabuleiro[0][0].equals(jogador[0][1]) && tabuleiro[1][1].equals(jogador[0][1]) // esses dois ifs sao para
																							// checar as diagonais
				&& tabuleiro[2][2].equals(jogador[0][1])
				|| tabuleiro[0][2].equals(jogador[0][1]) && tabuleiro[1][1].equals(jogador[0][1])
						&& tabuleiro[2][0].equals(jogador[0][1])) {
			vencedor[0] = "true";
			vencedor[1] = "0";
		} else if (tabuleiro[0][0].equals(jogador[1][1]) && tabuleiro[1][1].equals(jogador[1][1])
				&& tabuleiro[2][2].equals(jogador[1][1])
				|| tabuleiro[0][2].equals(jogador[1][1]) && tabuleiro[1][1].equals(jogador[1][1])
						&& tabuleiro[2][0].equals(jogador[1][1])) {
			vencedor[0] = "true";
			vencedor[1] = "1";
		}
		return vencedor;
	}

	public static int[] Contador(String[] jogador, int[] contador, String[][] jogadores) { // para ccontar a quantidade
																							// de pontos de cada jogador
		if (jogador[1].equals("0")) { // se tiver 0 quem ganhou foi o jogador que colocou o nome primeiro
			contador[0]++;
			System.out.println("O jogador 1 venceu essa!");
		} else if (jogador[1].equals("1")) { // se tiver 1 quem ganhou foi o jogador que colocou o nome depois
			contador[1]++;
			System.out.println("O jogador 2 venceu essa!");
		}
		System.out.println("Placar geral: ");
		System.out.println(jogadores[0][0] + " " + contador[0] + " pontos");
		System.out.println(jogadores[1][0] + " " + contador[1] + " pontos");
		return contador;
	}

	public static void LimpaTela() { // pula 50 linhas e "limpatela"
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // criacao do scanner, usado para o usuário informar um valor
		String[][] matriz = new String[3][3]; // criacao da matriz usada para armazenar os valores do tic tac toe
		String linhaHorizontal = "-------"; // criacao do texto na horizontal, usado para facilitar a geracao do
											// tabuleiro
		LimparMatriz(matriz); // usado para resetar a matriz
		System.out.println(); // quebra de linha
		boolean finalizar = false; // variavel de finalização
		boolean ganhador = false; // para saber se houve ou nao um ganhador
		String[] teste = new String[2]; // variavel necessaria para o placar e finalizar o jogo da velha
		String[][] jogadores = new String[2][2]; // transformei em matriz para guardar qual simnbolo e tbm
		int[] placar = new int[2];
		for (int i = 0; i < 2; i++) {
			System.out.println("Informe o nome do " + (i + 1) + "º jogador(a):");
			jogadores[i][0] = sc.nextLine(); // para evitar um erro que estava dando caso a pessoa colocasse o nome
												// composto
			jogadores[i][0] = jogadores[i][0].toUpperCase(); // Matheus q pediu n sei pq
		}

		int turno = Dados(jogadores); // funcao para decidir quem começará primeiro
		int n = 0;
		do { // adicionei esse do para que funcionesse da maneira apropriada o menu de sair
			while (ganhador == false) {
				Jogadas(matriz, jogadores, turno); // chama função jogadas para fazer a jogada
				if (n >= 4) {
					teste = Ganhador(matriz, jogadores); // a partir da 5 jogada, o sistema chama essa função de
															// verificar se teve um ganhador
					if (teste[0].equals("true")) { // se tiver um ganhador cai nesse if e o while para
						Pontos();
						EscreverMatriz(matriz);
						placar = Contador(teste, placar, jogadores); // chama a funcao responsavel pelo placar
						ganhador = true;
					}
				}
				turno = Turno(turno); // para ir alternando o turno
				n++;
			}

			Pontos();
			LimpaTela(); // criei uma funcao para "limpatela" mas e uma gambiarra na verdade
			finalizar = Fim(sc);
			if (finalizar == false) {
				ganhador = false;
				LimparMatriz(matriz);
			}
		} while (finalizar == false); // aqui é caso o usuario deseja sair
		sc.close(); // fechar o scanner, usado para não ter um uso de dados desnecessário.

	}
}
	/* falta achar os erros (com certeza tem, mas n achei) 
       e tambem verificar caso de empate, eu esqueci de fazer mas to de saco cheio de codar ja kkkkk
       se faltar mais alguma coisa, perdao e me avisa, pra eu saber q tenho q olhar as alteracoes

	*/