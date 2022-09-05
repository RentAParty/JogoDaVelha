package com.mycompany.tictactoe;

import java.util.Scanner;

public class TicTacToe {

    public static int Dados(String jogador[][]) { // função para decidir quem começará primeiro
        linha();
        System.out.println("| Será decidido quem jogará primeiro através de um sistema de dados.                    |");
        Delay(1000);
        System.out.println("| O computador lançara um dado com número aleatório de 1 a 6 para cada um dos jogadores.|");
        Delay(1000);
        System.out.println("| O jogador cujo dado tiver tido o maior número começará a rodada.                      |");
        Delay(1000);
        System.out.println("| Em caso de empate os dados serão lançados novamente                                   |");
        int[] dados = new int[2]; // variaveis dos dados
        int turno = 0; // retorna qual jogador ganhou
        do {
            dados[0] = (int) ((Math.random() * 6 + 1)); // para pegar um número aleatório de 1 a 6.
            dados[1] = (int) ((Math.random() * 6 + 1));
            Delay(1000);
            System.out.println("|                                     Dados rolados!                                    |");

            Delay(500);
            if (dados[0] == dados[1]) {
                System.out.println("| A rodada terminou empatada, ambos os dados deram " + dados[0]);
            } else if (dados[0] > dados[1]) {
                System.out.println("| " + jogador[0][0] + " ganhou a rodada. " + dados[0] + " é maior que " + dados[1]);
                jogador[0][1] = "X";
                jogador[1][1] = "O";
            } else {
                System.out.println("| " + jogador[1][0] + " ganhou a rodada. " + dados[1] + " > " + dados[0]);
                jogador[0][1] = "O";
                jogador[1][1] = "X";
                turno = 1;
            }
        } while (dados[0] == dados[1]); // looping para garantir que tenha um vencedor
        linha();
        Delay(1500);
        return turno;
    }

    public static int Turno(int turnos) {
        int turno = 0;
        if (turnos == 0) {
            turno = 1;
        }
        return turno;
    }

    public static boolean Fim(Scanner sc) {
        String sair;
        boolean opcao = false;
        do {
            System.out.println("DESEJA FINALIZAR? S/N");
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
                    System.out.println("OPÇÃO INVÁLIDA!");
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
        Delay(1000);
        LimpaTela();
        System.out.println("    A    B    C"); // os espaços são necessários para que fique bonito
        for (int i = 0; i < tabuleiro.length; i++) { // aqui uso o for pra navegar pela matriz e escrevcer ela
            for (int j = 0; j < tabuleiro.length; j++) {
                switch (j) {
                    case 0 ->
                        System.out.print((i + 1) + "   " + tabuleiro[i][j] + "  | ");
                    // linha,
                    // o segundo for serve pra escrever a
                    case 1 -> // a barra para separar um do outro
                        System.out.print(tabuleiro[i][j] + "  | ");
                    default ->
                        System.out.println(tabuleiro[i][j]);
                }
            }
            if (i != 2) {
                System.out.println("   ----+----+----"); // ja esse aqui é para quando for pular de linha
            }
        }
        System.out.println();
    }

    public static void Jogadas(String[][] tabuleiro, String jogador[][], int turno) {
        Scanner sc = new Scanner(System.in);
        String jogada;
        int valorAux = 0, valorAux2 = 0;
        boolean verif = false, verif2 = false, condicao; // verificacao condicao do caractere alfabetico
        char[] aux = new char[2]; // guarda os dois caracteres da string de forma separada
        do {
            EscreverMatriz(tabuleiro); // para escrever a matriz
            System.out.println("INSIRA A SUA JOGADA (VEZ DO JOGADOR(A) " + jogador[turno][0] + ")");
            jogada = sc.nextLine(); // ler a jogada
            if (jogada.length() != 2) { // se for diferente de dois caracteres ja interrompe e volta pro comeco
                System.out.println("POR FAVOR, DIGITE UMA COORDENADA VÁLIDA!");
            } else { // se for igual a dois caracteres vem nesse else
                for (int i = 0; i < jogada.length(); i++) { // para navegar nos dois caracteres da string
                    aux[i] = jogada.charAt(i); // separa a string em um caractere cada
                }
                if (Character.isLetter(aux[0])) { // se o primeiro caractere for uma letra cai aqui, se for
                    // digito cai no else
                    valorAux = Character.getNumericValue(aux[0]); // pego o codigo dela (A=10, B=11, C=12) indifere
                    // maiusc ou minusc
                    if (valorAux < 13 && valorAux > 9) { // se for 10, 11 ou 12 verdadeiro, senao fica falso e tem de
                        // fazer de novo
                        verif = true;
                    }
                    if (Character.isDigit(aux[1])) { // aqui checa o segundo caractere, se for digito vai pra
                        // frente caso contrario fica falso direto
                        valorAux2 = Character.getNumericValue(aux[1]); // pega o valor do numero q no caso e ele mesmo
                        if (valorAux2 > 0 && valorAux2 < 4) { // se for 1,2 ou 3 e verdadeiro, caso contrario falso
                            verif2 = true;
                        }
                    }
                } else if (Character.isDigit(aux[0])) { // a partir daqui e o mesmo processo que antes
                    valorAux2 = Character.getNumericValue(aux[0]); // porem com numero primeiro e letra depois
                    if (valorAux2 > 0 && valorAux2 < 4) { // para caso o usaurio digitar 1a ao inves de a1 nao der
                        // invalido
                        verif2 = true;
                    }
                    if (Character.isLetter(aux[1])) {
                        valorAux = Character.getNumericValue(aux[1]);
                        if (valorAux < 13 && valorAux > 9) {
                            verif = true;
                        }
                    }
                }
                if (verif && verif2) { // se as condicoes atenderem entra nesse if, se nao o usuario tem
                    // que por outro input
                    valorAux = valorAux - 10; // para poder entrar na coluna certa
                    condicao = EspacoVazio(tabuleiro, valorAux2, valorAux); // para checar se o espaco esta disponivel
                    if (condicao) { // se sim, entra aqui, se nao escreve que o espaco esta ocupado
                        tabuleiro[valorAux2 - 1][valorAux] = jogador[turno][1]; // aqui pega o simbolo do jogador e
                        // escreve no tabuleiro
                    } else {
                        System.out.println("ESPAÇO JÁ OCUPADO.");
                        verif = false;
                        verif2 = false;
                    }
                } else { // se for dois num ou duas letras ou for uma letra ou num invalido, acontece
                    // esse sysout
                    System.out.println("POR FAVOR, INSIRA UMA COORDENADA NO MODELO LETRA, NÚMERO! (EXEMPLO: A1).");
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

    public static int[] Ganhador(String[][] tabuleiro, String[][] jogador, int n) { // sistema para verificar se
        // houve um
        // ganhador
        int[] vencedor = new int[2]; // para armazenar se houve um ganhador (vencedor[0]) e quem ganhou
        // (venceddor[1])
        vencedor[0] = 0;
        String aux1 = jogador[0][1]; // essas duas variaveis recebem o simbolo do jogador
        String aux2 = jogador[1][1];
        for (int i = 0; i < tabuleiro.length; i++) { // esse for confere se houve ganhador na linha
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[i][j].equals(jogador[0][1])) { // aqui vai adicionando o simbolo do jogador ate o fim da
                    // linha
                    aux1 = aux1 + jogador[0][1];
                } else if (tabuleiro[i][j].equals(jogador[1][1])) {
                    aux2 = aux2 + jogador[1][1];
                }
            }
            if (aux1.length() == 4) { // se houve algum simbolo repetidos 4 vezes, houve um ganhador
                vencedor[0] = 1; // vencedor[0] recebe "true" e vencedor[1] "0" ou "1" dependendo do jogador
                vencedor[1] = 0; // foi o jeito mais otimizado que achei
                break;
            } else if (aux2.length() == 4) {
                vencedor[0] = 1;
                vencedor[1] = 1;
                break;
            }
            aux1 = jogador[0][1];
            aux2 = jogador[1][1];
        }
        for (int i = 0; i < tabuleiro.length; i++) { // mesma coisa do outro for, mas parta colunas
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[j][i].equals(jogador[0][1])) {
                    aux1 = aux1 + jogador[0][1];
                } else if (tabuleiro[j][i].equals(jogador[1][1])) {
                    aux2 = aux2 + jogador[1][1];
                }
            }
            if (aux1.length() == 4) {
                vencedor[0] = 1;
                vencedor[1] = 0;
                break;
            } else if (aux2.length() == 4) {
                vencedor[0] = 1;
                vencedor[1] = 1;
                break;
            }
            aux1 = jogador[0][1];
            aux2 = jogador[1][1];
        }
        if (!tabuleiro[0][0].equals(" ") && !tabuleiro[2][2].equals(" ") && tabuleiro[0][0].equals(tabuleiro[2][2])) {
            if (tabuleiro[0][0].equals(tabuleiro[1][1])) {
                vencedor[0] = 1;
                if (tabuleiro[0][0].equals(aux2)) {
                    vencedor[1] = 1;
                } else {
                    vencedor[1] = 0;
                }
            }
        }
        if (!tabuleiro[2][0].equals(" ") && !tabuleiro[0][2].equals(" ") && tabuleiro[2][0].equals(tabuleiro[0][2])) {
            if (tabuleiro[2][0].equals(tabuleiro[1][1])) {
                vencedor[0] = 1;
                if (tabuleiro[2][0].equals(aux2)) {
                    vencedor[1] = 1;
                } else {
                    vencedor[1] = 0;
                }
            }
        }
        if (vencedor[0] == 0 && n == 9) {
            vencedor[1] = -1;
        }

        return vencedor;
    }

    public static int[] Contador(int[] jogador, int[] contador, String[][] jogadores) { // para ccontar a quantidade
        // de pontos de cada jogador
        linha();
        switch (jogador[1]) {
            case 0 -> {
                // se tiver 0 quem ganhou foi o jogador que colocou o nome primeiro
                contador[0]++;
                System.out.println("| GANHADOR(A): " + jogadores[0][0]);
            }
            case 1 -> {
                // se tiver 1 quem ganhou foi o jogador que colocou o nome depois
                contador[1]++;
                System.out.println("| GANHADOR(A): " + jogadores[1][0]);
            }
            case -1 ->
                System.out.println("| EMPATE!");
        }
        System.out.println("| PLACAR GERAL: ");

        if (contador[0] == 0 || contador[0] == 1) {
            System.out.println("| " + jogadores[0][0] + " " + contador[0] + " PONTO");
        } else {
            System.out.println("| " + jogadores[0][0] + " " + contador[0] + " PONTOS");
        }

        if (contador[1] == 0 || contador[1] == 1) {
            System.out.println("| " + jogadores[1][0] + " " + contador[1] + " PONTO");
        } else {
            System.out.println("| " + jogadores[1][0] + " " + contador[1] + " PONTOS");
        }

        linha();
        return contador;
    }

    public static void LimpaTela() { // pula 50 linhas e "limpatela"
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void linha() {
        System.out.println(" ---------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // criacao do scanner, usado para o usuário informar um valor

        String[][] matriz = new String[3][3];
        // criacao do texto na horizontal, usado para facilitar a geracao do
        // criacao da matriz usada para armazenar os valores do tic tac toe
        LimparMatriz(matriz); // usado para resetar a matriz
        System.out.println(); // quebra de linha
        boolean finalizar = false; // variavel de finalização
        boolean ganhador = false; // para saber se houve ou nao um ganhador
        int[] numeroPlacar = new int[2]; // variavel necessaria para o placar e finalizar o jogo da velha
        String[][] jogadores = new String[2][2]; // transformei em matriz para guardar qual simnbolo e tbm
        int[] placar = new int[2];
        linha();
        for (int i = 0; i < 2; i++) {
            System.out.println("| INFORME O NOME DO " + (i + 1) + "º JOGADOR(A):");
            jogadores[i][0] = sc.nextLine();
            jogadores[i][0] = jogadores[i][0].toUpperCase();
        }

        int turno = Dados(jogadores); // funcao para decidir quem começará primeiro
        do { // adicionei esse do para que funcionesse da maneira apropriada o menu de sair
            int n = 0;
            while (!ganhador) {
                n++;
                Jogadas(matriz, jogadores, turno); // chama função jogadas para fazer a jogada
                if (n >= 4) {
                    numeroPlacar = Ganhador(matriz, jogadores, n); // a partir da 5 jogada, o sistema chama essa função de
                    // verificar se teve um ganhador
                    if (numeroPlacar[0] == 1) { // se tiver um ganhador cai nesse if e o while para

                        EscreverMatriz(matriz);
                        placar = Contador(numeroPlacar, placar, jogadores); // chama a funcao responsavel pelo placar
                        ganhador = true;
                    } else if (n == 9) {
                        placar = Contador(numeroPlacar, placar, jogadores);
                        turno = Turno(turno);
                        break;
                    }
                }
                turno = Turno(turno); // para ir alternando o turno
            }

            finalizar = Fim(sc);
            if (!finalizar) {
                ganhador = false;
                LimparMatriz(matriz);
            }
            // criei uma funcao para "limpatela" mas e uma gambiarra na verdade
        } while (!finalizar); // aqui é caso o usuario deseja sair
        sc.close(); // fechar o scanner, usado para não ter um uso de dados desnecessário.
    }
}
