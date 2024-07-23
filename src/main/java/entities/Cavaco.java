package main.java.entities;

import main.java.enuns.CoresEnum;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Cavaco extends Produto
{

	public String tipo;

	public Cavaco(){}

	public Cavaco(CoresEnum cor, Double preco, String marca, String modelo, String tipo)
	{
		super(cor, preco, marca, modelo);
		this.tipo = tipo;
	}

	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	public String getCaptador()
	{
		return this.tipo;
	}

	@Override
	public String toString()
	{
		return 	"Instrumento: "
				+ "Cavaco, "
				+ "preço: "
				+ String.format("%.2f", preco)
				+ ", marca: "
				+ marca
				+ ", modelo: "
				+ modelo
				+ ", cor: "
				+ cor
				+ ", eletro/acustico: "
				+ tipo;
	}

	public static void cadastrar(Scanner sc, List<Produto> estoque)
	{
		System.out.println();
		System.out.println("---------------Cadastrar Cavaco----------------");
		System.out.println();

		try {
			System.out.print("Digite a quantidade de produtos: ");
			int quantidadeDeProdutos = sc.nextInt();
			sc.nextLine();

			System.out.print("Digite o preço: R$ ");
			double preco = sc.nextDouble();
			sc.nextLine();

			System.out.print("Digite a marca: ");
			String marca = sc.nextLine();

			System.out.print("Digite o modelo: ");
			String modelo = sc.nextLine();

			System.out.print("Digite o tipo (eletro/acustico): ");
			String tipo = sc.nextLine();
			//sc.nextLine();

			FileWriter fileWriter = new FileWriter("Estoque.txt", true); // O segundo parâmetro true permite anexar ao arquivo
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (int i = 0; i < quantidadeDeProdutos; i++) {
				Produto produto = new Cavaco(cor, preco, marca, modelo, tipo);
				produto.setCodigo(produto.gerarCodigo());
				estoque.add(produto);

				// Escrever os detalhes do produto no arquivo
				bufferedWriter.write("Nome: Cavaco, " +
						"Código: " + produto.getCodigo() +
						", Marca: " + produto.getMarca() +
						", Modelo: " + produto.getModelo() +
						", Preço: R$" + produto.getPreco() +
						", Tipo (eletro/acústico): " + tipo);
				bufferedWriter.newLine(); // Adicionar uma nova linha para o próximo produto
			}

			// Fechar o BufferedWriter
			bufferedWriter.close();

			System.out.println();
			System.out.println("Produto cadastrado com sucesso");
			System.out.println();
			Iniciar.iniciar(sc, estoque);

		} catch (IOException e) {
			System.out.println("Erro ao escrever no arquivo: " + e.getMessage());

		} catch(InputMismatchException e) {
			System.out.println("Por favor digite um número.");
			System.out.println();
			sc.next();
			cadastrar(sc, estoque);
		}
	}

}

