package ProjetoMPEI_88867_89185.MPEI;

import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Projeto {

	static Scanner sc = new Scanner(System.in);
	static int x = 1;
	
	// Variaveis usadas para a comparação de duas obras de Saramago
	static int escolha11 = 0;
	static int escolha12 = 0;
	static int k = 0;
	
	// Variaveis usadas para a comparação de Saramago com outras obras
	static int escolha21 = 0;
	static int escolha22 = 0;
	
	// Criação de Paths para textos de Saramago
	static Path Saramago1 = Paths.get("ensaioSobreACegueira.txt");
	static Path Saramago2 = Paths.get("itermitenciasDaMorte.txt");
	static Path Saramago3 = Paths.get("memorialDoConvento.txt");
	static Path Saramago4 = Paths.get("homemDuplicado.txt");
	static Path Saramago5 = Paths.get("ensaioSobreALucidez.txt");
			
	// Criação de Paths para textos de outros escritores
	static Path Obra1 = Paths.get("canto1.txt");
	static Path Obra2 = Paths.get("canto3.txt");
	static Path Obra3 = Paths.get("canto10.txt");
	static Path Obra4 = Paths.get("OsMaias.txt");
	static Path Obra5 = Paths.get("CidadeEAsSerras.txt");
			
	public static void main(String[] args) throws IOException {
		do {
			System.out.println("-- Escolha a opcao -- ");
			System.out.println(" 1- Comparar dois textos de Saramago");
			System.out.println(" 2- Comparar Saramago com outro escritor");
			System.out.println(" 0- Terminar programa\n");
			x = sc.nextInt();
			
			switch(x) {
				case 1:
					k = 0;
					while(k < 2) {
						System.out.print("-- Excertos de Saramago --\n");
						System.out.print("1- Excerto de 'Ensaio sobre a Cegueira'\n");
						System.out.print("2- Excerto de 'Itermitencias da Morte'\n");
						System.out.print("3- Excerto de 'Memorial do Convento'\n");
						System.out.print("4- Excerto de 'Homem Duplicado'\n");
						System.out.print("5- Excerto de 'Ensaio sobre a lucidez'\n");
						if(k==0) {
							escolha11 = sc.nextInt();
						}
						if(k==1) {
							escolha12 = sc.nextInt();
						}
						k++;
					}
					
					// Guardar os excertos escolhidos em ArrayList<String> usando o metodo readAllLines
					ArrayList<String> texto1 = lerFicheiro(1); 
					ArrayList<String> texto2 = lerFicheiro(2);
					
					// Guarda todo o texto numa string e remove os espaços em branco, usando o metodo concatenar()
					String string1 = concatenar(texto1);
					String string2 = concatenar(texto2);
					
					// Criar minhash com 20 hashfunctions
					MinHash minhash = new MinHash(20);
	
					// Dividir ambas as strings em shingles de 4
					Set<String> set1 = Shingles.criarShingle(string1, 4);
					Set<String> set2 = Shingles.criarShingle(string2, 4);
					
					// Similaridade entre os dois textos
					System.out.println("\nSimilaridade entre Saramago"+escolha11+" e Saramago"+escolha12+": "+minhash.similaridadeJaccard(set1, set2));
					
					System.out.println("\n ----------------------------------------------------------------------------------------- ");	
					break;
					
				case 2:
					System.out.print("-- Excertos de Saramago --\n");
					System.out.print("1- Excerto de 'Ensaio sobre a Cegueira'\n");
					System.out.print("2- Excerto de 'Itermitencias da Morte'\n");
					System.out.print("3- Excerto de 'Memorial do Convento'\n");
					System.out.print("4- Excerto de 'Homem Duplicado'\n");
					System.out.print("5- Excerto de 'Ensaio sobre a lucidez'\n");
					escolha21 = sc.nextInt();
					
					System.out.print("-- Outras obras --\n");
					System.out.print("1- 'canto1' d'Os Lusíadas\n");
					System.out.print("2- 'canto3' d'Os Lusíadas\n");
					System.out.print("3- 'canto10' d'Os Lusíadas\n");
					System.out.print("4- Excerto de 'Os Maias'\n");
					System.out.print("5- Excerto de 'Cidade e as Serras'\n");
					escolha22 = sc.nextInt();
					
					// Guardar os .txt escolhidos em ArrayList<String> usando o metodo readAllLines
					ArrayList<String> texto3 = lerFicheiro(3); 
					ArrayList<String> texto4 = lerFicheiro(4);
					
					// Guarda todo o texto numa string e remove os espaços em branco, usando o metodo concatenar()
					String string3 = concatenar(texto3);
					String string4 = concatenar(texto4);
					
					// Criar minhash com 20 hashfunctions
					MinHash minhash2 = new MinHash(20);
	
					// Dividir ambas as strings em shingles de 4
					Set<String> set3 = Shingles.criarShingle(string3, 4);
					Set<String> set4 = Shingles.criarShingle(string4, 4);
					
					// Similaridade entre o texto de Saramago escolhido e o canto d'Os Lusíadas escolhido
					System.out.println("\nSimilaridade entre Saramago"+escolha21+" e Obra"+escolha22+": "+minhash2.similaridadeJaccard(set3, set4));
					
					System.out.println("\n ----------------------------------------------------------------------------------------- ");
					break;
			}
		}while(x != 0);
		
	}
	
	// Método que devolve ArrayList com todas as linhas do ficheiro .txt escolhido
	public static ArrayList<String> lerFicheiro(int parte) throws IOException{
		if(parte == 1) {
			switch(escolha11) {
				case 1:
					return (ArrayList<String>) Files.readAllLines(Saramago1);
				case 2:
					return (ArrayList<String>) Files.readAllLines(Saramago2);
				case 3:
					return (ArrayList<String>) Files.readAllLines(Saramago3);
				case 4:
					return (ArrayList<String>) Files.readAllLines(Saramago4);
				case 5:
					return (ArrayList<String>) Files.readAllLines(Saramago5);
				default: 
					return (ArrayList<String>) Files.readAllLines(Saramago5);
			}
		}
		else if(parte == 2){
			switch(escolha12) {
				case 1:
					return (ArrayList<String>) Files.readAllLines(Saramago1);
				case 2:
					return (ArrayList<String>) Files.readAllLines(Saramago2);
				case 3:
					return (ArrayList<String>) Files.readAllLines(Saramago3);
				case 4:
					return (ArrayList<String>) Files.readAllLines(Saramago4);
				case 5:
					return (ArrayList<String>) Files.readAllLines(Saramago5);
				default: 
					return (ArrayList<String>) Files.readAllLines(Saramago5);
			}
		}
		else if(parte == 3) {
			switch(escolha21) {
				case 1:
					return (ArrayList<String>) Files.readAllLines(Saramago1);
				case 2:
					return (ArrayList<String>) Files.readAllLines(Saramago2);
				case 3:
					return (ArrayList<String>) Files.readAllLines(Saramago3);
				case 4:
					return (ArrayList<String>) Files.readAllLines(Saramago4);
				case 5:
					return (ArrayList<String>) Files.readAllLines(Saramago5);
				default: 
					return (ArrayList<String>) Files.readAllLines(Saramago5);
			}
		}
		else {
			switch(escolha22) {
				case 1:
					return (ArrayList<String>) Files.readAllLines(Obra1);
				case 2:
					return (ArrayList<String>) Files.readAllLines(Obra2);
				case 3:
					return (ArrayList<String>) Files.readAllLines(Obra3);
				case 4:
					return (ArrayList<String>) Files.readAllLines(Obra4);
				case 5:
					return (ArrayList<String>) Files.readAllLines(Obra5);
				default: 
					return (ArrayList<String>) Files.readAllLines(Obra5);
			}
		}
	}
	
	// Método para colocar todas as linhas numa só string, e remover os espaços em branco
	public static String concatenar(ArrayList<String> lista) {
		String s = "";		
		for(String frase : lista) {
			s = s + frase;
		}
		s.replaceAll("\\s+","");
		return s;
	}
}