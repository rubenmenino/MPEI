package ProjetoMPEI_88867_89185.MPEI;

import java.util.*;

public class MinHash {
	private int numHash; // Número de hash
	private int[] hash; // para guardar valores

	public MinHash(int numHash) {
		this.numHash = numHash;
		hash = new int[numHash];
		
		for(int i=0 ; i<numHash ; i++) {
			hash[i] = Math.abs((int)(Math.random()*Integer.MAX_VALUE));
		}
	}
	
	
	// Calcula a similaridade de Jaccard entre dois sets de shingles
	public double similaridadeJaccard(Set<String> set1, Set<String> set2) {
		int[] stringhashSet1 = getStringHashSet(set1);
		int[] stringhashSet2 = getStringHashSet(set2);
		
		TreeSet<Integer> hashSet1 = new TreeSet<Integer>(intArrayToList(stringhashSet1));
		TreeSet<Integer> hashSet2 = new TreeSet<Integer>(intArrayToList(stringhashSet2));
		
		TreeSet<Integer> intersecao = new TreeSet<>();
		intersecao.addAll(hashSet1);
		intersecao.retainAll(hashSet2);
		
		TreeSet<Integer> uniao = new TreeSet<>();
		uniao.addAll(hashSet1);
		uniao.addAll(hashSet2);
		
		return (double)intersecao.size() / (double)uniao.size();
	}
	
	
	// Transforma um Array de inteiros numa Lista de inteiros
	private static List<Integer> intArrayToList(int[] array){
		List<Integer> lista = new ArrayList<Integer>();
		
		for(int i : array)
			lista.add(i);
		
		return lista;
	}
	

	// Devolve um array de inteiros que contem os hashsets minimos de cada shingle contida no set
	private int[] getStringHashSet(Set<String> shingles){
		int[] hashValues = new int[shingles.size()];
		Iterator<String> iterator = shingles.iterator();
		
		for(int i=0 ; i<hashValues.length ; i++) {
			hashValues[i] = getMinHash(iterator.next());
		}
		
		return hashValues;
	}
	
	
	// Devolve o valor de hash minimo para a shingle após aplicar o numero de hashfunctions escolhido (numHash)
	private int getMinHash(String shingle) {
		int min = Integer.MAX_VALUE; // Para poder ser sempre substituido na primeira iteração do for
		
		for(int i =0 ; i<numHash ; i++) {
			int hashCode = shingle.hashCode() ^ hash[i];
			min = Math.min(min, hashCode);
		}
		
		return min;
	}	
}