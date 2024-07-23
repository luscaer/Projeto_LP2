package main.java.entities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import main.java.enuns.CoresEnum;

public class Produto {
	
		protected static CoresEnum cor;
		protected Integer codigo;
		protected Double preco;
		protected String marca;
		protected String modelo;
		
		public Produto() {
			
		}	

		public Produto(CoresEnum cor, Double preco, String marca, String modelo) {
			this.cor = cor;
			this.preco = preco;
			this.marca = marca;
			this.modelo = modelo;
		}

		public void setCodigo(Integer codigo) {
			this.codigo = codigo;
		}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

		public Double getPreco() {
			return Double.parseDouble(String.format("%.2f", preco));
		}

		public String getMarca() {
			return marca;
		}

		public String getModelo() {
			return modelo;
		}
		
		public Integer getCodigo() {
			return codigo;
		}
		
		public Integer gerarCodigo() {
			Random random = new Random();
			return random.nextInt(900000) + 100000;
		}
		
		
		public static void remover(Scanner sc, List<Produto> estoque, String chamador, int codigo) {
		    
			try {
				int codigoRemover;

				if(chamador.equals("Vendedor")){

					System.out.println("Digite 0 para voltar à sessão anterior");
					System.out.print("Ou digite o código do produto que deseja remover: ");

					codigoRemover = sc.nextInt();
					sc.nextLine(); // Consumir a quebra de linha

					if(codigoRemover == 0)
					{
						Vendedor.acao(sc, estoque);
					}

					boolean removido = false;

					Iterator<Produto> iterator = estoque.iterator();
					while (iterator.hasNext()) {
						Produto produto = iterator.next();
						if (produto.getCodigo() == codigoRemover) {
							iterator.remove();
							System.out.println();
							if(chamador.equals("Vendedor"))
							{
								System.out.println("Produto removido com sucesso!");
							}
							removido = true;
							atualizarArquivoEstoque(estoque);
							break;
						}
					}
					if (!removido) {
						System.out.println();
						System.out.println("Produto não encontrado!");
					}

					Vendedor.acao(sc, estoque);
				}
				else{
					codigoRemover = codigo;

					Iterator<Produto> iterator = estoque.iterator();
					while (iterator.hasNext()) {
						Produto produto = iterator.next();
						if (produto.getCodigo() == codigoRemover) {
							iterator.remove();

							System.out.println();
							System.out.println("Produto comprado com sucesso!");

							atualizarArquivoEstoque(estoque);
							break;
						}
					}
				}

				/*// Utilizando Iterator para remover o item durante a iteração
				Iterator<Produto> iterator = estoque.iterator();
				while (iterator.hasNext()) {
					Produto produto = iterator.next();
					if (produto.getCodigo() == codigoRemover) {
						iterator.remove();
						System.out.println();
						if(chamador.equals("Vendedor"))
						{
							System.out.println("Produto removido com sucesso!");
						}

						else if (chamador.equals("Cliente"))
						{
							System.out.println("Produto comprado com sucesso!");
						}
						removido = true;
						atualizarArquivoEstoque(estoque);
						break;
					}
				}
				if (!removido) {
					System.out.println();
					System.out.println("Produto não encontrado!");
				}

				if(chamador.equals("Vendedor"))
				{
					Vendedor.acao(sc, estoque);
				}

				else if (chamador.equals("Cliente"))
				{
					Cliente.acao(sc, estoque);
				}*/
			}
			catch(InputMismatchException e) {
				System.out.println();
				System.out.println("Por favor, digite um valor numérico para o código");
				sc.next();
				remover(sc, estoque, chamador, 0);

			}
		}
		
		private static void atualizarArquivoEstoque(List<Produto> estoque) {
		    try (BufferedWriter writer = new BufferedWriter(new FileWriter("Estoque.txt"))) {
		        for (Produto produto : estoque) {
		            if (produto instanceof Violao) {
		                Violao violao = (Violao) produto;
		                writer.write("Nome: Violao, " +
		                        "Código: " + violao.getCodigo() +
		                        ", Marca: " + violao.getMarca() +
		                        ", Modelo: " + violao.getModelo() +
		                        ", Preço: R$" + String.format("%.2f", violao.getPreco()) +
		                        ", Quantidade de Cordas: " + violao.getQuantidadeDeCordas() +
		                        ", Tipo: " + violao.getTipo());
		                writer.newLine();

		            } else if (produto instanceof Guitarra) {
		                Guitarra guitarra = (Guitarra) produto;

						writer.write("Nome: " + produto.getClass().getSimpleName() +
								", Código: " + produto.getCodigo() +
								", Marca: " + produto.getMarca() +
								", Modelo: " + produto.getModelo() +
								", Preço: R$" + String.format("%.2f", produto.getPreco()) +
								", Captador: " + guitarra.getCaptador());
						writer.newLine();

		            } else if (produto instanceof Cavaco) {
		                Cavaco cavaco = (Cavaco) produto;

						writer.write("Nome: " + produto.getClass().getSimpleName() +
								", Código: " + produto.getCodigo() +
								", Marca: " + produto.getMarca() +
								", Modelo: " + produto.getModelo() +
								", Preço: R$" + String.format("%.2f", produto.getPreco()) +
								", Tipo (eletro/acústico): " + cavaco.getCaptador());
						writer.newLine();

		            } else if (produto instanceof Violino) {
		                Violino violino = (Violino) produto;

						writer.write("Nome: " + produto.getClass().getSimpleName() +
								", Código: " + produto.getCodigo() +
								", Marca: " + produto.getMarca() +
								", Modelo: " + produto.getModelo() +
								", Preço: R$" + String.format("%.2f", produto.getPreco()) +
								", Tipo: " + violino.getTipo());
						writer.newLine();

		            }

					if(produto.equals(estoque.get(estoque.size() - 1)))
					{
						writer.close();
					}
		        }
		    } catch (IOException e) {
		        System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
		    }
		}

}
