package ProjetoMPEI_88867_89185.MPEI;

public class CountingBloomFilter{
	private int[] bloomfilter;
	private int size;
	private int hash;
	private int numElementos;
	
	public CountingBloomFilter(int size, int hash){
		this.size = size;
		this.hash = hash;
		bloomfilter = new int[size];
		this.numElementos = 0;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getHash() {
		return hash;
	}
	
	public int getNumElementos() {
		return numElementos;
	}
	
	public void inserir(String x) {
		for(int i=0; i<hash; i++) {
			x = x + i;
			int h = Math.abs( x.hashCode() % size );
			bloomfilter[h] = bloomfilter[h] + 1;
		}
		numElementos++;
	}
	
	public boolean verificar(String x) {
		boolean pertence = true;
		
		for(int i=0; i<hash; i++) {
			x = x + i;
			int h = Math.abs( x.hashCode() % size );
			
			if(bloomfilter[h] == 0) {
				pertence = false;
				return pertence;
			}
		}
		
		return pertence;
	}
	
	public void remove(String x){
				
		for(int i=0; i<hash; i++){
			x = x + i;
			if(bloomfilter[Math.abs( x.hashCode() % size )] > 0) {
				bloomfilter[Math.abs( x.hashCode() % size )] = 0; 
			}
		}
		numElementos--;
	}
	
	public int count(String value) {
		 if (!verificar(value))
			 return 0;
		 int tmp = Math.abs(value.hashCode()%size);
		 for (int i=0; i<hash; i++) {
			 value = value + i;
			 if (bloomfilter[Math.abs(value.hashCode()%size)] < tmp)
				 tmp = bloomfilter[Math.abs(value.hashCode()%size)];
		 }
		 return tmp;
	}
	
	public double getProbTeorica(){		
		return Math.pow((1-Math.exp((double)-hash*numElementos / size)), hash);
	}
}