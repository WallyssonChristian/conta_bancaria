package conta.controller;

import java.util.ArrayList;
import java.util.Optional;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository{
	
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	int numero = 0;

	@Override
	public void procurarPorNumero(int numero) {
		Optional<Conta> conta = buscarNaCollection(numero);
		
		if (conta.isPresent())
			conta.get().visualizar();
		else
			System.out.println("\nA Conta número: " + numero + " não foi encontrada!");
	}

	@Override
	public void listarTodas() {
		for (var conta : listaContas) {
			conta.visualizar();
		}
		
	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("\nA Conta número: " + conta.getNumero() + " foi criada com sucesso!");		
	}

	@Override
	public void atualizar(Conta conta) {
		Optional<Conta> buscaConta = buscarNaCollection(conta.getNumero());
		
		if(buscaConta.isPresent()) {
			listaContas.set(listaContas.indexOf(buscaConta.get()), conta);
			System.out.println("\nA Conta número: " + conta.getNumero() + " foi atualizada com sucesso!");
		} else {
			System.out.println("\nA Conta número: " + conta.getNumero() + " não foi encontrada!");
		}
		
	}

	@Override
	public void deletar(int numero) {
		Optional<Conta> conta = buscarNaCollection(numero);
		
		if (conta != null) {
			if (listaContas.remove(conta.get()) == true) {
				System.out.println("\nA Conta número: " + numero + " foi deletada com sucesso!");
			} 
		} else { 
			System.out.println("\nA Conta número: " + numero + " não foi encontrada!"); // Não entra no else pois conta == null
		}
		
	}

	@Override
	public void sacar(int numero, float valor) {
		Optional<Conta> conta = buscarNaCollection(numero);
		
		if(conta != null) {
			if(conta.get().sacar(valor) == true) {
				System.out.println("\nO Saque na Conta número: " + numero + " foi efetuado com sucesso!");
			}
		} else {
			System.out.println("\nA Conta número: " + numero + " não foi encontrada!");
		}
		
	}

	@Override
	public void depositar(int numero, float valor) {
		Optional<Conta> conta = buscarNaCollection(numero);
		
		if(conta !=null) {
			conta.get().depositar(valor);
			System.out.println("\nO Depósito na Conta número: " + numero + " foi efetuado com sucesso!");
		} else {
			System.out.println("\nA Conta número: " + numero + " não foi encontrada ou a Conta destino não é uma Conta Corrente!");
		}
		
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		Optional<Conta> contaOrigem = buscarNaCollection(numeroOrigem);
		Optional<Conta> contaDestino = buscarNaCollection(numeroDestino);
		
		if(contaOrigem != null && contaDestino !=null) {
			if (contaOrigem.get().sacar(valor) == true) {
				contaDestino.get().depositar(valor);
				System.out.println("\nA Transferência foi efetuada com sucesso!");
			} 
		} else {
			System.out.println("\nA Conta de Origem e/ou Destino não foram encontradas!");
		}
		
	}
	
	public int gerarNumero() {
		return ++ numero;
	}
	
	public Optional<Conta> buscarNaCollection(int numero) {
		for (var conta: listaContas) {
			if(conta.getNumero() == numero) {
				return Optional.of(conta);
			}
		}
		
		return Optional.empty();
	}
	
	public int retornaTipo(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero) {
				return conta.getTipo();
			}
		}
		return 0;
	}

}
