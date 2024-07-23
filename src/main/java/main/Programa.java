package main.java.main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import main.java.entities.*;

public class Programa
{
	public static List<Produto> instanciarProdutos(List <Produto> estoque)
	{

		try (BufferedReader reader = new BufferedReader(new FileReader("Estoque.txt"))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				Produto produto = criarProduto(linha);
				if (produto != null) {
					estoque.add(produto);
				}
			}
		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		}

		return estoque;

	}

	private static Produto criarProduto(String linha) {
		// Extrair o nome da classe herdeira a partir da linha
		String nomeClasse = extrairNomeClasse(linha);

		// Tentar instanciar a classe herdeira correspondente
		try {
			Class<?> classe = Class.forName("main.java.entities." + nomeClasse);  // Adicionando o pacote
			Produto produto = (Produto) classe.newInstance();
			// Configurar os atributos do produto
			configurarAtributos(produto, linha);
			return produto;
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			System.out.println("Erro ao criar instância de " + nomeClasse + ": " + e.getMessage());
			e.printStackTrace(); // Adicione esta linha para imprimir o stack trace da exceção
			return null;
		}
	}


	private static String extrairNomeClasse(String linha) {
		// Extrair o nome da classe herdeira após "Nome: "
		int inicioNome = linha.indexOf("Nome: ") + "Nome: ".length();
		int fimNome = linha.indexOf(",", inicioNome);
		return linha.substring(inicioNome, fimNome).trim();
	}

	private static void configurarAtributos(Produto produto, String linha) {
		// Configurar os atributos específicos da classe herdeira
		String[] atributos = linha.split(", ");
		for (String atributo : atributos) {
			String[] partes = atributo.split(": ");
			if (partes.length == 2) {
				String nomeAtributo = partes[0].trim();
				String valorAtributo = partes[1].trim();

				// Ajustar conforme os atributos reais da classe Produto e suas subclasses
				switch (nomeAtributo) {
					case "Código":
						produto.setCodigo(Integer.parseInt(valorAtributo));
						break;
					case "Marca":
						produto.setMarca(valorAtributo);
						break;
					case "Modelo":
						produto.setModelo(valorAtributo);
						break;
					case "Preço":
						produto.setPreco(Double.parseDouble(valorAtributo.replace("R$", "").trim()));
						break;
					case "Quantidade de Cordas":
						if (produto instanceof Violao) {
							Violao violao = (Violao) produto;
							violao.setQuantidadeDeCordas(Integer.parseInt(valorAtributo));
						}
						break;
					case "Tipo":
						if (produto instanceof Cavaco)
						{
							Cavaco cavaco = (Cavaco) produto;
							cavaco.setTipo((valorAtributo));
						}
						else if(produto instanceof Violao)
						{
							Violao violao = (Violao) produto;
							violao.setTipo((valorAtributo));
						}
						else if(produto instanceof Violino)
						{
							Violino violino = (Violino) produto;
							violino.setTipo((valorAtributo));
						}
						break;

					case "Captador":
						if(produto instanceof Guitarra)
						{
						Guitarra guitarra = (Guitarra) produto;
						guitarra.setCaptador((valorAtributo));
						}
						break;

					case "Tipo (eletro/acústico)":
						Cavaco cavaco = (Cavaco) produto;
						cavaco.setTipo(valorAtributo);

				}
			}
		}
	}

	public static void main(String[] args)
	{
		List<Produto> estoque = new ArrayList<>();

		estoque = instanciarProdutos(estoque);
					
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		System.out.println("Seja bem indo à Cordas e Notas - Loja Musical!");
		System.out.println("Trabalhamos com venda de Violão, Guitarra, Violino e Cavaco");
		
		Iniciar.iniciar(sc, estoque);
	}
}
