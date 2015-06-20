import java.util.ArrayList;


public class TST {
	
	public Node raiz;
	public ArrayList<String> al;
	public Lista listaTST;
	
	
	public TST(){
		listaTST = new Lista();
	}
	
	public void clean(){
		raiz = null;
		al = null;
		listaTST = null;
	}
	
	//Método chamado pela classe HandleFiles
	//Verifica se a palavra existe, se não existir insere na árvore
	public void insert(String palavra, int pos){
		if(!contains(palavra))
			raiz = insert(raiz, palavra, pos);
	}
	
	//Chamado quando a palavra ainda não existe na árvore
	//Insere a palavra na árvore
	private Node insert(Node x, String valor, int pos){
		char c = valor.charAt(pos);
		if(x == null){
			x = new Node();
			x.letra = c;
		}
		if(c < x.letra)
			x.esq = insert(x.esq, valor, pos);
		else if(c > x.letra)
			x.dir = insert(x.dir, valor, pos);
		else if (pos < valor.length() - 1)
			x.meio = insert(x.meio, valor, pos + 1);
			else
				x.isEnd = true;
		
		
		return x;
	}
	
	public int get(String valor){

		Node x = getNode(raiz, valor, 0);	
		if(x == null)
			return 0;
		
		return x.valor;
	}
	
	//Caso o nó já exista na árvore o seu valor é incrementado
	private Node getNode(Node x, String valor, int pos){
		if(x == null)
			return null;
		char c = valor.charAt(pos);
		
		if(c < x.letra)
			return getNode(x.esq, valor, pos);
		else if(c > x.letra)
			return getNode(x.dir, valor, pos);
		else if(pos < valor.length() - 1)
			return getNode(x.meio, valor, pos + 1);
		else{
			x.valor ++;
			
			return x;
		}
		
	}
	
	//Método chamado pela classe HandleFiles
	 public void remove(String word)
	    {
	        remove(raiz, word.toCharArray(), 0);
	    }
	 
	 //Método similar ao getNode(), porém faz o decremento do valor do nó
	 //Caso o valor seja <= 0, marca o campo isEnd como false,
	 //"retirando" o nó da árvore.
	 private void remove(Node r, char[] word, int ptr)
	    {
	        if (r == null)
	            return;
	 
	        if (word[ptr] < r.letra)
	            remove(r.esq, word, ptr);
	        else if (word[ptr] > r.letra)
	        	remove(r.dir, word, ptr);
	        else
	        {
	           
	            if (r.isEnd && ptr == word.length - 1){
	            		r.valor --;
	            		if(r.valor < 0)
	            			r.isEnd = false;
	            }
	            else if (ptr + 1 < word.length)
	            	remove(r.meio, word, ptr + 1);
	        }        
	    }
	 
	 //Recupera um nó com o valor específico na árvore
	 //Se o nó existir seu valor é incrementado e retorna true
	 //Caso contrário retorna false e a palavra é inserida na árvore
	 public boolean contains(String valor) {
	        if(get(valor) != 0)
	        	return true;
	        else
	        	return false;
	    }
	
	 //Zera o Array com as palavras da árvore e chama o caminhamento para forma o Array
	 public String print()
	    {
	        al = new ArrayList<String>();
	        al.clear();
	        listaTST.cleanLista();
	        traverse(raiz, "");
	        return "\nTST: " + al;
	    }
	 
	 //Caminha recursivamente na árvore unindo as letras até encontrar o campo isEnd = true
	 //que marca o fim da palavra, após isso adiciona a palavra num Array de String
	 private void traverse(Node r, String str){
	        if (r != null){
	        	
	            traverse(r.dir, str);
	 
	            str = str + r.letra;
	            if (r.isEnd){
	            	int cont = r.valor + 1;
	                al.add(str + " " + cont + " " + r.isEnd);
	                No novo = new No(str, cont);
	                listaTST.insertNo(novo);
	            }
	            traverse(r.meio, str);
	            str = str.substring(0, str.length() - 1);
	 
	            traverse(r.esq, str);
	        }
	    }
	}
	

