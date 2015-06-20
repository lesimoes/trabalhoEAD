import java.io.File;


public class Lista {

	private No first;
	public int word = 0;
	
	
	
	private void insertFirst(String valor){
		No novo = new No(valor);
		novo.setProximo(first);
		first = novo;	
	}
	
	
	public void cleanLista(){
		this.first = null;
		this.word = 0;
	}

	
	public void insertNo(String valor){
			insertFirst(valor);
			this.word ++;
			
	}
	
	
	public void insertNo(No novo){
		novo.setProximo(first);
		this.word ++;
		first = novo;
	}
	
	public No getFirst(){
		return this.first;
	}
	
	
	public No getNo(No novo){
		return novo;
	}
	
	
	public void showLista(int limit){
		long tStart = System.currentTimeMillis();
		No novo = first;
		for(int i = 0; i < limit ; i ++){
			System.out.println("No: " + novo.valor + " | Qtd. Total: " + novo.count);
			novo = novo.proximo;
			
		}
		
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.println("Exibir tempo: " + elapsedSeconds);
		
	}
	
	public void countList(){
		No novo = this.first;
		while(novo != null){
			countNo(novo);
			novo = novo.proximo;
			
		}
	}
	
	public void countNo(No novo){
		No head, tail;
		head = tail = novo;
		int qtdNo = 0;
		
		while((tail != null) && (tail.valor.compareTo(head.valor) == 0)){
			qtdNo = qtdNo + tail.count;
			cleanNo(tail);
			tail = tail.proximo;
		}
		
		head.count = qtdNo;
		head.proximo = tail;
		
		
	}
	
	public void cleanNo(No novo){
		novo = null;
	}
	
	

	
	
	private void CountNo(No novo){
		novo.count ++;
		
	}
	

	
	public No mergeSort(No novo){
		if((novo == null) || (novo.proximo == null))
			return novo;
		No half = halfList(novo);
		No shalf = half.proximo;
		half.proximo = null;
		
		
		return merge(mergeSort(novo), mergeSort(shalf));
		
	}
	
	public No merge(No no1, No no2){
		No head, current;
		head = new No();
		current = head;
		while(no1 != null && no2 != null){
			if((no1.valor.compareTo(no2.valor) < 0)){
				current.proximo	= no1;
				no1 = no1.proximo;
			}
			else{
				current.proximo = no2;
				no2 = no2.proximo;
			}
			
			current = current.proximo;
			if((first.valor.compareTo(current.valor) > 0))
				first = current;
		}
		current.proximo = (no1 == null) ? no2 : no1;
		return head.proximo;
	}
	
	
	public No halfList(No head){
		if(head == null)
			return head;
		No slow, fast;
		slow = fast = head;
		while(fast.proximo != null && fast.proximo.proximo != null){
			slow = slow.proximo;
			fast = fast.proximo.proximo;
		}
		return slow;
	}
	
	
	public No mergeSortCount(No novo){
		if((novo == null) || (novo.proximo == null))
			return novo;
		No half = halfList(novo);
		No shalf = half.proximo;
		half.proximo = null;
		
		
		return mergeCount(mergeSortCount(novo), mergeSortCount(shalf));
		
	}
	
	public No mergeCount(No no1, No no2){
		No head, current;
		head = new No();
		current = head;
		while(no1 != null && no2 != null){
			if(no1.count >= no2.count){
				current.proximo	= no1;
				no1 = no1.proximo;
			}
			else{
				current.proximo = no2;
				no2 = no2.proximo;
			}
			
			current = current.proximo;
			if((first.count < current.count))
				first = current;
		}
		current.proximo = (no1 == null) ? no2 : no1;
		return head.proximo;
	}
	
	
	
}
