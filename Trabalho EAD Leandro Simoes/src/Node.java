
public class Node {
	 public int valor;
	 public char letra;
	 public Node esq, dir, meio;
	 public boolean isEnd;
	 
	 
	 public Node(char letra){
		 this.letra = letra;
		 this.valor = 0;
		
		 
	 }
	 public Node(){
		this.valor = 0;
	
	 }
	 
	 private void ponteiros(){
		 this.esq = null;
		 this.dir = null;
		 this.meio = null;
		
	 }
}
