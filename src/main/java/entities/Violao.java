package main.java.entities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import main.java.enuns.CoresEnum;

public class Violao extends Produto{
	
	//private Integer quantidadeDeProdutos;
	private Integer quantidadeDeCordas;
	private String tipo;

	public Violao(){}
	
	public Violao( CoresEnum cor, Double preco, String marca, String modelo, Integer quantidadeDeCordas,
			String tipo) {
		super(cor, preco, marca, modelo);
		this.quantidadeDeCordas = quantidadeDeCordas;
		this.tipo = tipo;
	}

	public void setQuantidadeDeCordas(Integer quantidadeDeCordas) {
		this.quantidadeDeCordas = quantidadeDeCordas;
	}

	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	public Integer getQuantidadeDeCordas() {
		return quantidadeDeCordas;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	@Override
	public String toString() {
		return 	"Instrumento: "
				+ "Violão, "
				+ "preço: "
				+ String.format("%.2f", preco)
				+ ", marca: "
				+ marca
				+ ", modelo: "
				+ modelo 
				+ ", cor: "
				+ cor
				+ ", quantidade de cordas: "
				+ quantidadeDeCordas
				+ ", tipo: "
				+ tipo;
	}
	
	public static void cadastrar(Scanner sc, List<Produto> estoque) {
		
		System.out.println();
		System.out.println("---------------Cadastrar violão----------------");
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
	
		    System.out.print("Quantidade de cordas: ");
		    int quantidadeDeCordas = sc.nextInt();
		    sc.nextLine(); 
	
		    System.out.print("Qual o tipo: ");
		    String tipo = sc.nextLine();
		    
		    FileWriter fileWriter = new FileWriter("Estoque.txt", true); // O segundo parâmetro true permite anexar ao arquivo
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < quantidadeDeProdutos; i++) {
                Produto produto = new Violao(cor, preco, marca, modelo, quantidadeDeCordas, tipo);
                produto.setCodigo(produto.gerarCodigo());
                estoque.add(produto);
                
                // Escrever os detalhes do produto no arquivo
                bufferedWriter.write("Nome: Violao, " +
                		"Código: " + produto.getCodigo() +
                        ", Marca: " + produto.getMarca() +
                        ", Modelo: " + produto.getModelo() +
                        ", Preço: R$" + produto.getPreco() +
                        ", Quantidade de Cordas: " + quantidadeDeCordas +
                        ", Tipo: " + tipo);
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
