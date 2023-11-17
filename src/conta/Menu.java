package conta;

import java.util.Scanner;
import conta.model.*;
import conta.util.Cores;

public class Menu {

	public static Scanner read = new Scanner(System.in);
	public static void main(String[] args) {
	
		int op;
		
		// Teste da Classe Conta Corrente
		ContaCorrente cc1 = new ContaCorrente(2, 123, 1, "Maria", 15000.0f, 1000.0f);
		cc1.visualizar();
		cc1.sacar(12000.0f);
		cc1.visualizar();
		cc1.depositar(5000.0f);
		cc1.visualizar();
		
		// Teste da Classe Conta Poupança
		ContaPoupanca cp1 = new ContaPoupanca(3, 123, 2, "Victor", 100000.0f, 15);
		cp1.visualizar();
		cp1.sacar(1000.0f);
		cp1.visualizar();
		cp1.depositar(5000.0f);
		cp1.visualizar();
		
		
		// MENU
		do {
			
			System.out.print(Cores.TEXT_CYAN_BOLD_BRIGHT);
			System.out.println("                                                    ");
			System.out.println("****************************************************");
			System.out.println("                                                    ");
			System.out.println("                    CyanTree                        ");
			System.out.println("                                                    ");
			System.out.println("****************************************************");
			System.out.println("                                                    ");
			System.out.println("          1 - Criar Conta                           ");
			System.out.println("          2 - Listar todas as Contas                ");
			System.out.println("          3 - Buscar Conta por Numero               ");
			System.out.println("          4 - Atualizar Dados da Conta              ");
			System.out.println("          5 - Apagar Conta                          ");
			System.out.println("          6 - Sacar                                 ");
			System.out.println("          7 - Depositar                             ");
			System.out.println("          8 - Transferir valores entre Contas       ");
			System.out.println("          9 - Sair                                  ");
			System.out.println("                                                    ");
			System.out.println("****************************************************");
			System.out.print("Entre com a opção desejada: ");
			op = read.nextInt();
			
			switch(op) {
			case 1:
				System.out.println("Criar Conta");
				break;
			case 2:
				System.out.println("Listar todas as Contas ");
				break;
			case 3:
				System.out.println("Buscar Conta por Numero");
				break;
			case 4:
				System.out.println("Atualizar Dados da Conta");
				break;
			case 5:
				System.out.println("Apagar Conta");
				break;
			case 6:
				System.out.println("Sacar");
				break;
			case 7:
				System.out.println("Depositar");
				break;
			case 8:
				System.out.println("Transferir valores entre Contas");
				break;
			case 9:
				about();
				break;
			default:
				System.out.println(Cores.TEXT_RED + "Opção Inválida");
				break;
			}
			
		} while(op!=9);
	}

	public static void about() {
		System.out.println("\n                                                    ");
		System.out.println("                           ****                     ");
		System.out.println("                 **     ********                    ");
		System.out.println("                ****    * *******                   ");
		System.out.println("                *****  **********                   ");
		System.out.println("                 ****   *******                     ");
		System.out.println("                    ** ****                         ");
		System.out.println("                ***  ***  **  **                    ");
		System.out.println("               *****  **   ******                   ");
		System.out.println("                ***  **     *****                   ");
		System.out.println("                  *****      **                     ");
		System.out.println("                   ***                              ");
		System.out.println("                  ***                               ");
		System.out.println("                 ****                               ");
		System.out.println("                 *****                              ");
		System.out.println("                *******                             ");
		System.out.println("****************************************************");
		System.out.println("    CyanTree - Onde o dinheiro nasce em árvores!    ");
		System.out.println("****************************************************");
		System.out.println("Projeto Desenvolvido por: Wallysson Araujo          ");
		System.out.println("wallysson.christian@outlook.com                     ");
		System.out.println("github.com/WallyssonChristian                       ");
		System.out.println("****************************************************");
	}
}
