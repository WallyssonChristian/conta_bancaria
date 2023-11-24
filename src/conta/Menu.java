package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.*;
import conta.util.Cores;

public class Menu {
	
	final static String line = ("====================================================");
	
	public static Scanner read = new Scanner(System.in);
	public static void main(String[] args) {
	
		ContaController contas = new ContaController();
		int op, numero, agencia, tipo, aniversario, numeroDestino;
		String titular, confirm;
		float saldo, limite, valor;
		
		System.out.println("\nCriar Contas\n");
		
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);
		
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);
		
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Aline Araujo", 4000f, 12);
		contas.cadastrar(cp1);
		
		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Wallysson Araujo", 8000f, 25);
		contas.cadastrar(cp2);

		
		// MENU
		do {
			
			System.out.print(Cores.tema);
			System.out.println("                                                    ");
			System.out.println(line);
			System.out.println("                                                    ");
			System.out.println("                    CyanTree                        ");
			System.out.println("                                                    ");
			System.out.println(line);
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
			System.out.println(line);
			System.out.print("Entre com a opção desejada: ");
			
			try {
				op = read.nextInt();
			} catch (InputMismatchException e){
				System.out.println(Cores.error + "\nDigite valores inteiros!\n" + Cores.tema);
				read.nextLine();
				op = 0;
			}
				
			switch(op) {
				case 1:
					System.out.println("\n==================== Criar Conta ===================\n");
					
					try {
						System.out.print("Digite o Número da Agência: ");
						agencia = read.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Deve conter apenas Números!");
						keyPress();
						break;
					}
					
					try {
						System.out.print("\nDigite o Nome do Titular: ");
						read.skip("\\R?");
						titular = read.nextLine();
						verificarString(titular);
					} catch (InputMismatchException e) {
						System.out.println("o: " + e.getMessage());
						keyPress();
						break;
					}
					
					do {
						System.out.print("\nDigite o Tipo da Conta (1-CC ou 2-CP): ");
						tipo = read.nextInt();
					}while(tipo < 1 || tipo > 2);
					
					do {
						
						System.out.print("\nDigite o Saldo da Conta (R$): ");
						while(!read.hasNextFloat()) {
							System.out.println("Por favor, digite um valor válido.");
							System.out.print("\nDigite o Saldo da Conta (R$): ");
							read.next();
						}
						saldo = read.nextFloat();
						if (saldo < 0) System.out.println("O valor não pode ser negativo!");
					} while (saldo < 0);

					
					switch(tipo) {
						case 1: 
							System.out.print("\nDigite o Limite de Crédito (R$): ");
							limite = read.nextFloat();
							contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
							break;
						case 2:
							System.out.print("\nDigite o dia do Aniversario da Conta: ");
							aniversario = read.nextInt();
							contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
							break;
					}
					
					keyPress();
					break;
				case 2:
					System.out.println("\n============== Listar todas as Contas ==============");
					contas.listarTodas();
					keyPress();
					break;
				case 3:
					System.out.println("\n======== Consultar dados da Conta por número =======\n");
					System.out.print("Digite o número da conta: ");
					numero = read.nextInt();
					
					contas.procurarPorNumero(numero);
					
					keyPress();
					break;
				case 4:
					System.out.println("\n============= Atualizar Dados da Conta =============\n");
					
					System.out.print("Digite o número da conta: ");
					numero = read.nextInt();
					
					if (contas.buscarNaCollection(numero) != null) {
						System.out.print("Digite o novo Numero da Agência: ");
						agencia = read.nextInt();
						System.out.print("\nDigite o novo Nome do Titular: ");
						read.skip("\\R?");
						titular = read.nextLine();						
						System.out.print("\nDigite o novo Saldo da Conta (R$): ");
						saldo = read.nextFloat();
						
						tipo = contas.retornaTipo(numero);
						
						switch(tipo) {
							case 1: 
								System.out.print("\nDigite o novo Limite de Crédito (R$): ");
								limite = read.nextFloat();
								contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
								break;
							case 2:
								System.out.print("\nDigite o novo dia do Aniversario da Conta: ");
								aniversario = read.nextInt();
								contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
								break;
							default:
								System.out.println("Tipo de conta inválido");
								break;
						}
					} else {
						System.out.println("\nConta não encontrada!");
					}
					keyPress();
					break;
				case 5:
					System.out.println("\n=================== Apagar Conta ===================");
					System.out.print("Digite o número da conta: ");
					numero = read.nextInt();
					
					contas.procurarPorNumero(numero);
					System.out.println("\n" + Cores.error + "AVISO" + Cores.tema);
					System.out.println("Tem certeza que deseja deletar a conta?");
					System.out.print("Esse processo não pode ser desfeito(S/N): ");
					read.skip("\\R?");
					confirm = read.nextLine();
					if (confirm.equalsIgnoreCase("S")) contas.deletar(numero);
					else System.out.println("Conta não deletada");
					keyPress();
					break;
				case 6:
					System.out.print("\n======================= Sacar ======================\n");
					
					System.out.print("Digite o Numero da conta: ");
					numero = read.nextInt();
					
					do {
						System.out.print("Digite o Valor do Saque (R$): ");
						valor = read.nextFloat();
					}while(valor <= 0);
					
					contas.sacar(numero, valor);
					keyPress();
					break;
				case 7:
					System.out.println("\n==================== Depositar ====================\n");
					System.out.print("Digite o Número da conta: ");
					numero = read.nextInt();
					
					do {
						System.out.print("Digite o Valor do Depósito (R$): ");
						valor = read.nextFloat();
					}while(valor <= 0);
					
					contas.depositar(numero, valor);
					
					keyPress();
					break;
				case 8:
					System.out.println("\n====================Transferir valores entre Contas ====================\n");
	
					System.out.print("Digite o Número da Conta de Origem: ");
					numero = read.nextInt();
					System.out.print("Digite o Número da Conta de Destino: ");
					numeroDestino = read.nextInt();
					
					do {
						System.out.println("Digite o Valor da Transferência (R$): ");
						valor = read.nextFloat();
					}while(valor <=0);
					
					contas.transferir(numero, numeroDestino, valor);
					
					keyPress();
					break;
				case 9:
					about();
	
					keyPress();
					break;
				default:
					System.out.println("Opção Inválida");
	
					keyPress();
					break;
			}
			
		} while(op!=9);
	}

	public static void about() {
		System.out.println("\n                        *                        		 ");
		System.out.println("                       ***                               ");
		System.out.println("                      *****                              ");
		System.out.println("                     *******                             ");
		System.out.println("                    *********                            ");
		System.out.println("                   ***********                           ");
		System.out.println("                  *************                          ");
		System.out.println("                 ***************                         ");
		System.out.println("                    *********                            ");
		System.out.println("                   ***********                           ");
		System.out.println("                  *************                   		 ");
		System.out.println("                 ***************                     	 ");
		System.out.println("                       ***                               ");
		System.out.println("                       ***                               ");
		System.out.println("                       ***                               ");
		System.out.println("                      *****                              ");
		System.out.println("                     *******                             ");
		System.out.println(line);
		System.out.println("    CyanTree - Onde o dinheiro nasce em árvores!    ");
		System.out.println(line);
		System.out.println("Projeto Desenvolvido por: Wallysson Araujo          ");
		System.out.println("wallysson.christian@outlook.com                     ");
		System.out.println("github.com/WallyssonChristian                       ");
		System.out.println(line);
	}
	
	public static void keyPress() {
		try {
			System.out.println("\nPressione Enter para Continuar...");
			System.in.read();
		} catch (IOException e) {
			System.out.println("Você pressionou uma tecla diferente de enter!");
		}
	}
	
	public static void verificarString(String input) {
		if (!input.matches("^[\\p{L}\\s]+$")) {
			throw new InputMismatchException("O nome não deve conter números.");
		}
	}
}
