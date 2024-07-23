package main.java.entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Vendedor{
	
	public static void acao(Scanner sc, List<Produto> estoque) {
		
		System.out.println();
		System.out.println("-------------PÁGINA DO VENDEDOR----------");
		System.out.println();
        
        
		
		System.out.println("0 - Cadastrar Produto");
        System.out.println("1 - Remover Produto");
        System.out.println("2 - Verificar estoque");
        System.out.println("3 - Voltar para sessão anterior");
		System.out.print("Digite 0 / 1 / 2: ");
		
        if (sc.hasNextInt()) {		
            int option = sc.nextInt();

            switch(option) {
	            case 0:
	            	cadastrarProduto(sc, estoque);
	            	break;
	            case 1:	
	            	Produto.remover(sc, estoque, "Vendedor", 0);
	            	break;
	            case 2:
	            	imprimirEstoque(sc, estoque);
	            	break;
	            case 3:
	            	Iniciar.iniciar(sc, estoque);
	            default:
	            	System.out.println();
	                System.out.println("Opção inválida.");
	                System.out.println();
	                acao(sc, estoque);
            }
        } 
        else {
     	   	System.out.println();
            System.out.println("Opção inválida.");
            System.out.println();
            sc.next();
            acao(sc, estoque);
        }	
	
	}

	
	public static void cadastrarProduto(Scanner sc, List<Produto> estoque) {
	   
		System.out.println();
            
		System.out.println("-----------Página de cadastro de produtos-----------");
		
		System.out.println();
    	System.out.println("Qual produto você deseja cadastrar? ");
 	    System.out.println("0 - Violão");
 	    System.out.println("1 - Guitarra");
 	    System.out.println("2 - Violino");
 	    System.out.println("3 - Cavaco");
 	    System.out.println("4 - Voltar à sessão anterior");
	    System.out.print("Digite a opção você deseja: ");

        if (sc.hasNextInt()) {
            int tipoInstrumento = sc.nextInt();
                         	
            	switch(tipoInstrumento) {
        	    case 0:
        	    	Violao.cadastrar(sc, estoque);
        	    	break;
        	    case 1:
        	    	 Guitarra.cadastrar(sc, estoque);
        	    	break;
        	    case 2:
					Violino.cadastrar(sc, estoque);
        	    	break;
        	    case 3:
					 Cavaco.cadastrar(sc, estoque);
        	    	break;
        	    case 4:
        	    	acao(sc, estoque);
        	    	break;
    	    	default:
    	    		System.out.println();
                    System.out.println("Opção inválida");
                    cadastrarProduto(sc, estoque);
        	    }          	      
        } 
        else {
        	System.out.println();
            System.out.println("Opção inválida. ");
            sc.next();
            cadastrarProduto(sc, estoque);
        }
	        
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
}

