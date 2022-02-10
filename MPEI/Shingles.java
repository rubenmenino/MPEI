package ProjetoMPEI_88867_89185.MPEI;

import java.util.*;

public class Shingles {
	public static Set<String> criarShingle(String texto, int size) {		
		TreeSet<String> set = new TreeSet<>();
		for(int i=0 ; i+size<=texto.length() ; i++) {
			set.add(texto.substring(i,i+size));
		}
		return set;
	}
}