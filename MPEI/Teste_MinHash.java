package ProjetoMPEI_88867_89185.MPEI;

import java.util.*;

public class Teste_MinHash {
	
	public static void main(String[] args) {
		System.out.println("TESTE:\n");
		int numHash = 20; // Numero de hash
		int tamanhoShingle = 2; // Tamanha da divisao das shingles
		MinHash minhash = new MinHash(numHash);
		
		// Frases para comparar usando minhash
		String frase1 = "Similaridade";
		String frase2 = "Metodos Probabilisticos para Engenharia Informatica";
		String frase3 = "Similaridade";
		String frase4 = "Menos Similaridade";
		String frase5 = ",,,,, ,,,,,a,ida,,";
		
		// print das frases a comparar
		System.out.print("frase1: " + frase1);
		System.out.print("\nfrase2: " + frase2);
		System.out.print("\nfrase3: " + frase3);
		System.out.print("\nfrase4: " + frase4);
		System.out.print("\nfrase5: " + frase5 + "\n");
		
		// Divide em shingles de 2 entre dois sets de shingles
		Set<String> set1 = Shingles.criarShingle(frase1, tamanhoShingle);
		Set<String> set2 = Shingles.criarShingle(frase2, tamanhoShingle);
		Set<String> set3 = Shingles.criarShingle(frase3, tamanhoShingle);
		Set<String> set4 = Shingles.criarShingle(frase4, tamanhoShingle);
		Set<String> set5 = Shingles.criarShingle(frase5, tamanhoShingle);
		
		System.out.println("\nSimilaridade 1 e 2: "+minhash.similaridadeJaccard(set1, set2));
		System.out.println("Similaridade 1 e 3: "+minhash.similaridadeJaccard(set1, set3));
		System.out.println("Similaridade 1 e 4: "+minhash.similaridadeJaccard(set1, set4));
		System.out.println("Similaridade 2 e 4: "+minhash.similaridadeJaccard(set2, set4));
		System.out.println("Similaridade 4 e 5: "+minhash.similaridadeJaccard(set4, set5));
		
		
		System.out.println("\n ______________________________________________________________________");
		System.out.println("|Comparar 100000 Strings e contar as que têm mais de 20% similaridade |");
		System.out.println("|_____________________________________________________________________|\n");
		
		
		int N = 100000; // O N � uma vari�vel que vai ser comparada com a random String
		double limite = 0.2; // 
		
		String string = randomString(); //Vai comparar as strings
		System.out.print("String a ser comparada: '" + string + "'.\n");
		int dentroLimite = 0; // Contar o numero de Strings dentro do limite
		for(int i = 0; i < N; i++) {
			String random = randomString();
			double SimilaridadeJaccardd = minhash.similaridadeJaccard(Shingles.criarShingle(random,tamanhoShingle),
				  			 	  					  Shingles.criarShingle(string,tamanhoShingle));
			
			if(SimilaridadeJaccardd >= limite) dentroLimite++;
		}
		System.out.println("De 100000, apenas "+dentroLimite+" têm acima de "+limite+" de similaridade com '"+string+"'.");
	}
	
	public static String randomString() {
		
		String caracteres = "abcdefghijklmnopqrstuvwxyz";
		String randomStringg = "";
		int length = 5;
		
		Random rand = new Random();
		char[] text = new char[length];
		
		for(int i = 0; i < length; i++) {
			text[i] = caracteres.charAt(rand.nextInt(caracteres.length()));
		}
		for(int i = 0; i < text.length; i++) {
			randomStringg += text[i];
		}
		
		return randomStringg;
	}
}	
		
		
		
		
		
		