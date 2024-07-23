package main.java.entities;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class Iniciar {

    public static void iniciar(Scanner sc, List<Produto> estoque) {      
    	
    	Locale.setDefault(Locale.US);	
    	
    	System.out.println();
    	System.out.println("-------------------------------------------------------------------------------------------------------------------");
    	
		System.out.println();
        System.out.println("Você é vendedor ou cliente? ");

        try {	        
    	    System.out.println("0 - Vendedor");
            System.out.println("1 - Cliente");
            System.out.println("2 - Encerrar execução do programa");
    	    System.out.print("Digite 0 / 1 / 2: ");
            if (sc.hasNextInt()) {
            int opcao = sc.nextInt();                  
	            switch(opcao) {
	            case 0:
					Vendedor.acao(sc, estoque);
					sc.next();
					break;
				case 1:
					Cliente.acao(sc, estoque);
					break;
				case 2:
					System.out.println();
					System.out.println("Programa encerrado!");
					System.exit(0);
	            default:
	            	System.out.println();
	                System.out.println("Opção inválida.");
	            	iniciar(sc, estoque);
	           }
           } 
           else {
        	   System.out.println();
               System.out.println("Opção inválida.");
               sc.next();
               iniciar(sc, estoque);
           }
	       
       } finally {
       }
    }
}
