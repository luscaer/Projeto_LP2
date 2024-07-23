package main.java.entities;
import java.util.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Cliente {
	public static void acao(Scanner sc, List<Produto> estoque) {

		List<Integer> carrinho = new ArrayList<>();

		System.out.println();
		System.out.println("-------------PÁGINA DO CLIENTE----------");
		System.out.println();


		System.out.println("0 - Instrumentos disponíveis");
		System.out.println("1 - Comprar");
		System.out.println("2 - Voltar para a sessão anterior");
		System.out.print("Digite 0 / 1 / 2: ");

		if (sc.hasNextInt()) {
			int option = sc.nextInt();

			switch (option) {
				case 0:
					imprimirEstoque(sc, estoque);
					break;
				case 1:
					comprar(sc, estoque, carrinho);
					break;
				case 2:
					Iniciar.iniciar(sc, estoque);
					break;
				default:
					System.out.println();
					System.out.println("Opção inválida.");
					System.out.println();
					acao(sc, estoque);
			}
		} else {
			System.out.println();
			System.out.println("Opção inválida.");
			System.out.println();
			sc.next();
			acao(sc, estoque);
		}

	}

	public static void comprar(Scanner sc, List<Produto> estoque, List<Integer> carrinho){

		System.out.println();
		System.out.println("-------------PÁGINA DE COMPRA----------");
		System.out.println();


		System.out.println("0 - Verificar Carrinho");
		System.out.println("1 - Adicionar Itens ao Carrinho");
		System.out.println("2 - Remover Itens do Carrinho");
		System.out.println("3 - Comprar Itens do Carrinho");
		System.out.println("4 - Voltar para a sessão anterior");
		System.out.print("Digite 0 / 1 / 2: ");

		if (sc.hasNextInt()) {
			int option = sc.nextInt();

			switch (option) {
				case 0:
					imprimirCarrinho(sc, estoque, carrinho);
					break;
				case 1:
					adicionarCarrinho(sc, estoque, carrinho);
					break;
				case 2:
					removerCarrinho(sc, estoque, carrinho);
					break;
				case 3:
					for(Integer codigo : carrinho){
						Produto.remover(sc, estoque, "Cliente" , codigo);
					}
					Cliente.comprar(sc, estoque, carrinho);
					break;
				case 4:
					acao(sc, estoque);
					break;
				default:
					System.out.println();
					System.out.println("Opção inválida.");
					System.out.println();
					comprar(sc, estoque, carrinho);
			}
		} else {
			System.out.println();
			System.out.println("Opção inválida.");
			System.out.println();
			sc.next();
			comprar(sc, estoque, carrinho);
		}
	}

	public static void removerCarrinho(Scanner sc, List<Produto> estoque, List<Integer> carrinho){
		System.out.print("Digite quantos produtos deseja remover: ");

		int quantidade = sc.nextInt();
		sc.nextLine();

		if(quantidade == 0){
			comprar(sc, estoque, carrinho);
		}
		else if(quantidade >= 1){
			for(int i = 0; i < quantidade; i++){
				System.out.println("Digite o código do produto que deseja remover:");
				boolean removido = false;

				int codigoCarrinho = sc.nextInt();
				sc.nextLine();

				// Utilizando Iterator para procurar o item durante a iteração
				Iterator<Integer> iterator = carrinho.iterator();
				while (iterator.hasNext()) {
					int cod = iterator.next();
					if (codigoCarrinho == cod) {

						iterator.remove();

						System.out.println();
						System.out.println("Produto removido carrinho!");

						removido = true;
						break;
					}
				}
				if (!removido) {
					System.out.println();
					System.out.println("Produto não encontrado no carrinho!");
					i--;
				}
			}
		}
		else{
			System.out.println("Opção inválida, digite novamente!");
			removerCarrinho(sc, estoque, carrinho);
		}

		comprar(sc, estoque, carrinho);
	}

	public static void adicionarCarrinho(Scanner sc, List<Produto> estoque, List<Integer> carrinho){
		System.out.print("Digite quantos produtos deseja adicionar: ");

		int quantidade = sc.nextInt();
		sc.nextLine();

		if(quantidade == 0){
			comprar(sc, estoque, carrinho);
		}
		else if(quantidade >= 1){
			for(int i = 0; i < quantidade; i++){
				System.out.println("Digite o código do produto que deseja adicionar:");
				boolean adicionado = false;

				int cod = sc.nextInt();
				sc.nextLine();

				// Utilizando Iterator para procurar o item durante a iteração
				Iterator<Produto> iterator = estoque.iterator();
				while (iterator.hasNext()) {
					Produto produto = iterator.next();
					if (produto.getCodigo() == cod) {

						carrinho.add(cod);

						System.out.println();
						System.out.println("Produto adicionado ao carrinho!");

						adicionado = true;
						break;
					}
				}
				if (!adicionado) {
					System.out.println();
					System.out.println("Produto não encontrado!");
					i--;
				}
			}
		}
		else{
			System.out.println("Opção inválida, digite novamente!");
			adicionarCarrinho(sc, estoque, carrinho);
		}

		comprar(sc, estoque, carrinho);
	}
	public static void imprimirCarrinho(Scanner sc, List<Produto> estoque, List<Integer> carrinho) {
		System.out.println();
		System.out.println("===================================================================================================================");
		System.out.println();
		System.out.println("Carrinho: ");
		System.out.println();

		try (BufferedReader reader = new BufferedReader(new FileReader("Estoque.txt"))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				for (int codigo : carrinho) {
					if (linha.contains("Código: " + codigo)) {
						System.out.println(linha);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		}
		System.out.println("===================================================================================================================");
		System.out.println();

		comprar(sc, estoque, carrinho);
	}
	public static void imprimirEstoque(Scanner sc, List<Produto> estoque) {
		System.out.println();
		System.out.println("===================================================================================================================");
		System.out.println();
		System.out.println("Estoque: ");
		System.out.println();

		try (BufferedReader reader = new BufferedReader(new FileReader("Estoque.txt"))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				System.out.println(linha);
			}
		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		}
		System.out.println("===================================================================================================================");
		System.out.println();
		acao(sc, estoque);
	}

	/*private static void salvarProdutosNoArquivo(List<Produto> produtos)
	{
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Estoque.txt")))
		{
			for (Produto produto : produtos)
			{
				if(produto instanceof  Violao)
				{
					Violao violao = (Violao) produto;
				bw.write("Nome: " + produto.getClass().getSimpleName() +
						", Código: " + produto.getCodigo() +
						", Marca: " + produto.getMarca() +
						", Modelo: " + produto.getModelo() +
						", Preço: R$" + String.format("%.2f", produto.getPreco()) +
						", Quantidade de Cordas: " + violao.getQuantidadeDeCordas() +
						", Tipo: " + violao.getTipo());
				bw.newLine();
				}
				else if(produto instanceof  Guitarra)
				{
					Guitarra guitarra = (Guitarra) produto;
					bw.write("Nome: " + produto.getClass().getSimpleName() +
							", Código: " + produto.getCodigo() +
							", Marca: " + produto.getMarca() +
							", Modelo: " + produto.getModelo() +
							", Preço: R$" + String.format("%.2f", produto.getPreco()) +
							", Captador: " + guitarra.getCaptador());
					bw.newLine();
				}
				else if(produto instanceof  Cavaco)
				{
					Cavaco cavaco = (Cavaco) produto;
					bw.write("Nome: " + produto.getClass().getSimpleName() +
							", Código: " + produto.getCodigo() +
							", Marca: " + produto.getMarca() +
							", Modelo: " + produto.getModelo() +
							", Preço: R$" + String.format("%.2f", produto.getPreco()) +
							", Tipo (eletro/acústico):: " + cavaco.getCaptador());
					bw.newLine();
				}
				else if(produto instanceof  Violino)
				{
					Violino violino = (Violino) produto;
					bw.write("Nome: " + produto.getClass().getSimpleName() +
							", Código: " + produto.getCodigo() +
							", Marca: " + produto.getMarca() +
							", Modelo: " + produto.getModelo() +
							", Preço: R$" + String.format("%.2f", produto.getPreco()) +
							", Tipo: " + violino.getTipo());
					bw.newLine();
				}

				if(produto.equals(produtos.get(produtos.size() - 1)))
				{
					bw.close();
				}
			}
		} catch (IOException e)
		{
			System.out.println("Erro ao salvar no arquivo: " + e.getMessage());
		}

	}

*/
}