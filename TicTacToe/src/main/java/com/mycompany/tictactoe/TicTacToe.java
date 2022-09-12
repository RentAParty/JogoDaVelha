package com.mycompany.tictactoe;

import java.util.Scanner;

public class TicTacToe {

    /* Funcao para limpartela */
    public static void LimpaTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void LinhaParaSepararInformacoes() {
        System.out.println(" ---------------------------------------------------------------------------------------");
    }

    /* Funcao de atraso para usar na animacao */
    public static void Delay(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /* Funcao usada para resetar a matriz para um novo jogo */
    public static void LimparTabuleiro(String param_tabuleiro[][]) {
        for (int l = 0; l < 3; l++) {
            for (int m = 0; m < 3; m++) {
                param_tabuleiro[l][m] = " ";
            }
        }
    }

    /* Funcao usada para escrever a matriz */
    public static void EscreverMatriz(String param_tabuleiro[][]) {
        System.out.println();
        System.out.println();
        System.out.println("    A    B    C");
        for (int i = 0; i < param_tabuleiro.length; i++) {
            for (int j = 0; j < param_tabuleiro.length; j++) {
                if (j == 0) {
                    System.out.print((i + 1) + "   " + param_tabuleiro[i][j] + "  | ");
                } else if (j == 1) {
                    System.out.print(param_tabuleiro[i][j] + "  | ");
                } else {
                    System.out.println(param_tabuleiro[i][j]);
                }
            }
            if (i != 2) {
                System.out.println("   ----+----+----");
            }
        }
        System.out.println();
    }

    /* Funcao feita para decidir quem começara a rodada */
    public static int DadosPrimeiraRodada(String param_jogadorAndSimbolo[][]) {
        LinhaParaSepararInformacoes();
        System.out.println("| Será decidido quem jogará primeiro através de um sistema de dados.                    |");
        Delay(1000);
        System.out.println("| O computador lançara um dado com número aleatório de 1 a 6 para cada um dos jogadores.|");
        Delay(1000);
        System.out.println("| O jogador cujo dado tiver tido o maior número começará a rodada.                      |");
        Delay(1000);
        System.out.println("| Em caso de empate os dados serão lançados novamente                                   |");
        int[] dadosJogadores = new int[2];
        int turnoJogador = 0;
        do {
            dadosJogadores[0] = (int) ((Math.random() * 6 + 1));
            dadosJogadores[1] = (int) ((Math.random() * 6 + 1));
            Delay(1000);
            System.out.println(
                    "|                                     Dados rolados!                                    |");
            Delay(500);
            if (dadosJogadores[0] == dadosJogadores[1]) {
                System.out.println("| A rodada terminou empatada, ambos os dados deram " + dadosJogadores[0]
                        + "                                    |");
            } else if (dadosJogadores[0] > dadosJogadores[1]) {
                System.out.println("| O " + param_jogadorAndSimbolo[0][0] + " ganhou a rodada. " + dadosJogadores[0]
                        + " > " + dadosJogadores[1] + "                                                          |");
                param_jogadorAndSimbolo[0][1] = "X";
                param_jogadorAndSimbolo[1][1] = "O";
            } else {
                System.out.println("| O " + param_jogadorAndSimbolo[1][0] + " ganhou a rodada. " + dadosJogadores[1]
                        + " > " + dadosJogadores[0] + "                                                          |");
                param_jogadorAndSimbolo[0][1] = "O";
                param_jogadorAndSimbolo[1][1] = "X";
                turnoJogador = 1;
            }
        } while (dadosJogadores[0] == dadosJogadores[1]);
        LinhaParaSepararInformacoes();
        Delay(1500);
        return turnoJogador;
    }

    /* Funcao para ir alternando os turnos */
    public static int AlternarTurnos(int param_turnoAtual) {
        int proxTurno = 0;
        if (param_turnoAtual == 0) {
            proxTurno = 1;
        }
        return proxTurno;
    }

    /*
	 * Funcao usada para ler a jogada (so aceita quando uma jogada valida for
	 * inserida atarves da funcao ValidacaoJogada)
     */
    public static String LerJogadas(String[][] param_tabuleiro, String jogador[][], int param_turno) {
        Scanner sc = new Scanner(System.in);
        String jogada;
        boolean jogada_e_Valida = true;
        do {
            EscreverMatriz(param_tabuleiro);
            System.out.println("Onde deseja jogar? (Vez do jogador " + jogador[param_turno][0] + ")");
            jogada = sc.nextLine();
            jogadas = jogadas.toLowerCase();
            if (jogada.length() != 2) {
                System.out.println("Por favor digite uma opção válida");
            } else {
                jogada_e_Valida = ValidacaoJogada(jogada, param_tabuleiro);
            }
        } while (!jogada_e_Valida);
        return jogada;
    }

    /*
	 * Funcao usada para validar a jogada (chama outras funcoes para cada tipo de
	 * validacao
     */
    public static boolean ValidacaoJogada(String param_jogada, String[][] param_tabuleiro) {
        boolean jogadaValida = true;
        boolean letraDigitoValido = true;
        boolean caractereValido = true;
        boolean espacoEstaVazio = true;
        int[] coordenadaDaJogada = new int[2];
        char[] caractereDaJogada = new char[2];
        letraDigitoValido = ValidacaoLetraDigitoJogada(param_jogada);
        if (letraDigitoValido == false) {
            System.out.println("Você inseriu uma coordenada com caracteres duplicados.");
            return false;
        } else {
            char[] caractereJogada = SepararCaractereJogada(param_jogada);
            caractereValido = ValidacaoCaractereJogada(caractereJogada);
            if (caractereValido == false) {
                System.out.println(
                        "Você inseriu um caractere inválido. Letra diferente de A,B ou C ou número diferente de 1,2 ou 3 ");
                return false;
            } else {
                caractereDaJogada = SepararCaractereJogada(param_jogada);
                coordenadaDaJogada = CoordenadaDaJogada(caractereDaJogada);
                espacoEstaVazio = EspacoEstaVazio(param_tabuleiro, coordenadaDaJogada);
                if (espacoEstaVazio == false) {
                    System.out.println("Espaço escolhido já está ocupado");
                    return false;
                }
            }
        }
        return jogadaValida;
    }

    /* Funcao usada para separar a string em dois caracteres */
    public static char[] SepararCaractereJogada(String param_jogada) {
        char[] caractereJogada = new char[2];
        for (int i = 0; i < 2; i++) {
            caractereJogada[i] = param_jogada.charAt(i);
        }
        return caractereJogada;
    }

    /* Funcao usada para verificar se há uma letra e um número */
    public static boolean ValidacaoLetraDigitoJogada(String param_jogada) {
        char[] caractereJogada = new char[2];
        boolean verificacaoPrimeiroCaractereLetra = true;
        boolean verificacaoSegundoCaractereDigito = true;
        caractereJogada = SepararCaractereJogada(param_jogada);
        if (Character.isDigit(caractereJogada[0])) {
            verificacaoPrimeiroCaractereLetra = false;
        }
        if (Character.isLetter(caractereJogada[1])) {
            verificacaoSegundoCaractereDigito = false;
        }

        if (verificacaoPrimeiroCaractereLetra != verificacaoSegundoCaractereDigito) {
            return false;
        } else {
            return true;
        }
    }

    /* Funcao usada para verificar se o caractere inserido é válido */
    public static boolean ValidacaoCaractereJogada(char[] param_caractereJogada) {
        boolean caractereValido = true;
        int digitoDaJogada;
        char letraA = 'a';
        char letraB = 'b';
        char letraC = 'c';
        if (Character.isLetter(param_caractereJogada[0])) {
            Character.toLowerCase(param_caractereJogada[0]);
            digitoDaJogada = Character.getNumericValue(param_caractereJogada[1]);
            if (digitoDaJogada < 1 || digitoDaJogada > 3) {
                return false;
            } else {
                if (param_caractereJogada[0] != letraA && param_caractereJogada[0] != letraB
                        && param_caractereJogada[0] != letraC) {
                    caractereValido = false;
                }
            }
        } else {
            Character.toLowerCase(param_caractereJogada[1]);
            digitoDaJogada = Character.getNumericValue(param_caractereJogada[0]);
            if (digitoDaJogada < 1 || digitoDaJogada > 3) {
                return false;
            } else {
                if (param_caractereJogada[1] != letraA && param_caractereJogada[1] != letraB
                        && param_caractereJogada[1] != letraC) {
                    caractereValido = false;
                }
            }
        }
        return caractereValido;
    }

    /* Funcao usada para transformar os caracteres na coordenada certa */
    public static int[] CoordenadaDaJogada(char[] param_caractereJogada) {
        int[] coordenadaDaJogada = new int[2];
        if (Character.getNumericValue(param_caractereJogada[0]) > 9) {
            coordenadaDaJogada[1] = Character.getNumericValue(param_caractereJogada[0]);
            coordenadaDaJogada[0] = Character.getNumericValue(param_caractereJogada[1]);
        } else {
            coordenadaDaJogada[1] = Character.getNumericValue(param_caractereJogada[1]);
            coordenadaDaJogada[0] = Character.getNumericValue(param_caractereJogada[0]);
        }
        coordenadaDaJogada[1] -= 10;
        coordenadaDaJogada[0] -= 1;
        return coordenadaDaJogada;
    }

    /* Funcao para verificar se o espaco esta vazio */
    public static boolean EspacoEstaVazio(String[][] param_tabuleiro, int[] param_coordenadaDaJogada) {
        boolean espaco = false;
        if (param_tabuleiro[param_coordenadaDaJogada[0]][param_coordenadaDaJogada[1]].equals(" ")) {
            espaco = true;
        }
        return espaco;
    }

    /* Funcao para fazer a jogada */
    public static String[][] EfetuarJogada(String param_jogada, String[][] param_tabuleiro, int param_turno,
            String[][] param_jogadorAndSimbolo) {
        char[] caractereDaJogada = new char[2];
        int[] coordenadaDaJogada = new int[2];
        caractereDaJogada = SepararCaractereJogada(param_jogada);
        coordenadaDaJogada = CoordenadaDaJogada(caractereDaJogada);
        param_tabuleiro[coordenadaDaJogada[0]][coordenadaDaJogada[1]] = param_jogadorAndSimbolo[param_turno][1];
        return param_tabuleiro;
    }

    /* Funcao para verificar se houve um vencedor */
    public static boolean HouveUmVencedor(String[][] param_tabuleiro, String[][] param_JogadorAndSimbolo, int n) {
        boolean houveUmVencedor = false;
        boolean coluna;
        boolean linha;
        boolean diagonal;
        coluna = Coluna(param_tabuleiro, param_JogadorAndSimbolo);
        if (coluna == true) {
            return true;
        }
        linha = Linha(param_tabuleiro, param_JogadorAndSimbolo);
        if (linha == true) {
            return true;
        }
        diagonal = Diagonal(param_tabuleiro, param_JogadorAndSimbolo);
        if (diagonal == true) {
            return true;
        }

        return houveUmVencedor;
    }

    /* Funcao para verificar se houve vencedor na coluna */
    public static boolean Coluna(String[][] param_tabuleiro, String[][] param_JogadorAndSimbolo) {
        boolean vencedorColuna = false;
        String simboloJogador1 = param_JogadorAndSimbolo[0][1];
        String simboloJogador2 = param_JogadorAndSimbolo[1][1];
        for (int i = 0; i < param_tabuleiro.length; i++) {
            for (int j = 0; j < param_tabuleiro[i].length; j++) {
                if (param_tabuleiro[j][i].equals(param_JogadorAndSimbolo[0][1])) {
                    simboloJogador1 = simboloJogador1 + param_JogadorAndSimbolo[0][1];
                } else if (param_tabuleiro[j][i].equals(param_JogadorAndSimbolo[1][1])) {
                    simboloJogador2 = simboloJogador2 + param_JogadorAndSimbolo[1][1];
                }
            }
            if (simboloJogador1.length() == 4) {
                vencedorColuna = true;
                break;
            } else if (simboloJogador2.length() == 4) {
                vencedorColuna = true;
                break;
            }
            simboloJogador1 = param_JogadorAndSimbolo[0][1];
            simboloJogador2 = param_JogadorAndSimbolo[1][1];
        }
        return vencedorColuna;
    }

    /* Funcao para verificar se houve vencedor na linha */
    public static boolean Linha(String[][] param_tabuleiro, String[][] param_JogadorAndSimbolo) {
        boolean vencedorLinha = false;
        String simboloJogador1 = param_JogadorAndSimbolo[0][1];
        String simboloJogador2 = param_JogadorAndSimbolo[1][1];
        for (int i = 0; i < param_tabuleiro.length; i++) {
            for (int j = 0; j < param_tabuleiro[i].length; j++) {
                if (param_tabuleiro[i][j].equals(param_JogadorAndSimbolo[0][1])) {
                    simboloJogador1 = simboloJogador1 + param_JogadorAndSimbolo[0][1];
                } else if (param_tabuleiro[i][j].equals(param_JogadorAndSimbolo[1][1])) {
                    simboloJogador2 = simboloJogador2 + param_JogadorAndSimbolo[1][1];
                }
            }
            if (simboloJogador1.length() == 4) {
                vencedorLinha = true;
                break;
            } else if (simboloJogador2.length() == 4) {
                vencedorLinha = true;
                break;
            }
            simboloJogador1 = param_JogadorAndSimbolo[0][1];
            simboloJogador2 = param_JogadorAndSimbolo[1][1];
        }
        return vencedorLinha;
    }

    /* Funcao para verificar se houve vencedor na diagonal */
    public static boolean Diagonal(String[][] param_tabuleiro, String[][] param_JogadorAndSimbolo) {
        boolean vencedorDiagonal = false;
        if (!param_tabuleiro[0][0].equals(" ") && !param_tabuleiro[2][2].equals(" ")
                && param_tabuleiro[0][0].equals(param_tabuleiro[2][2])) {
            if (param_tabuleiro[0][0].equals(param_tabuleiro[1][1])) {
                vencedorDiagonal = true;
            }
        }
        if (!param_tabuleiro[2][0].equals(" ") && !param_tabuleiro[0][2].equals(" ")
                && param_tabuleiro[2][0].equals(param_tabuleiro[0][2])) {
            if (param_tabuleiro[2][0].equals(param_tabuleiro[1][1])) {
                vencedorDiagonal = true;
            }
        }
        return vencedorDiagonal;
    }

    /* Funcao para computar o placar do jogo da velha */
    public static int[] PlacarJogoDaVelha(int param_turno, String[][] jogadoresAndSimbolo, int[] param_placar,
            boolean param_houveUmVencedor) {
        if (param_houveUmVencedor == false) {
            param_placar[0] += 0;
            param_placar[1] += 0;
        } else {
         if (param_turno == 0) {
            param_placar[0] += 1;
            System.out.println("| O jogador " + jogadoresAndSimbolo[0][0]
                    + " venceu essa!                                                            |");
        } else if (param_turno == 1) {
            System.out.println("| O jogador " + jogadoresAndSimbolo[1][0]
                    + " venceu essa!                                                            |");
            param_placar[1] += 1;
        }
	}
        System.out.println("| Placar geral:                                                                         |");
        System.out.println("| " + jogadoresAndSimbolo[0][0] + " " + param_placar[0]
                + " pontos                                                                          |");
        System.out.println("| " + jogadoresAndSimbolo[1][0] + " " + param_placar[1]
                + " pontos                                                                          |");
        return param_placar;
    }

    /* Efetuar de fato a jogada (digitar o simbolo na matriz_ */

 /* Funcao usada para saber se o usuario quis finalizar */
    public static boolean Fim(Scanner sc) {
        String sair;
        boolean opcao = false;
        do {
            System.out.println("Deseja finalizar? S/N");
            sair = sc.next();
            sair = sair.toUpperCase();
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] tabuleiroJogoDaVelha = new String[3][3];
        LimparTabuleiro(tabuleiroJogoDaVelha);
        System.out.println();
        boolean finalizarCodigo = false;
        boolean houveUmVencedor = false;
        String[][] jogadoresAndSimbolo = new String[2][2];
        int[] placar = new int[2];
        for (int i = 0; i < 2; i++) {
            System.out.println("Informe o nome do " + (i + 1) + "º jogador(a):");
            jogadoresAndSimbolo[i][0] = sc.nextLine();
            jogadoresAndSimbolo[i][0] = jogadoresAndSimbolo[i][0].toUpperCase();
        }

        int turno = DadosPrimeiraRodada(jogadoresAndSimbolo);
        do {
            int n = 0;
            while (houveUmVencedor == false) {
                n++;
                String jogada = LerJogadas(tabuleiroJogoDaVelha, jogadoresAndSimbolo, turno);
                tabuleiroJogoDaVelha = EfetuarJogada(jogada, tabuleiroJogoDaVelha, turno, jogadoresAndSimbolo);
                if (n >= 4) {
                    houveUmVencedor = HouveUmVencedor(tabuleiroJogoDaVelha, jogadoresAndSimbolo, n);
                    if (houveUmVencedor == true) {
                        Delay(1000);
                        EscreverMatriz(tabuleiroJogoDaVelha);
                        LinhaParaSepararInformacoes();
                        placar = PlacarJogoDaVelha(turno, jogadoresAndSimbolo, placar, houveUmVencedor);
                        LinhaParaSepararInformacoes();
                    }
                }
                if (houveUmVencedor == false && n == 9) {
                    LinhaParaSepararInformacoes();
                    System.out.println(
                            "| DUELO EQUILIBRADO                                                                     |");
                    System.out.println(
                            "| HOUVE UM EMPATE                                                                       |");
                    LinhaParaSepararInformacoes();
                    placar = PlacarJogoDaVelha(turno, jogadoresAndSimbolo, placar, houveUmVencedor);
                    LinhaParaSepararInformacoes();
                    break;
                }

                turno = AlternarTurnos(turno);
            }

            Delay(1000);
            finalizarCodigo = Fim(sc);
            if (finalizarCodigo == false) {
                houveUmVencedor = false;
                LimparTabuleiro(tabuleiroJogoDaVelha);
            }
            LimpaTela();
        } while (finalizarCodigo == false);
        sc.close();
    }
}

