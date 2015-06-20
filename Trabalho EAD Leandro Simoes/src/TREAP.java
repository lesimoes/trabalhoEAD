import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class TREAP {

	public TNode raiz;
	public Lista listaTreap;

	
	
	public TREAP(){
		raiz = null;
		listaTreap = new Lista();
	}
	
	public void clean(){
		raiz = null;
		listaTreap = null;
	}
	
	public void insert(String word){
		if(!contains(word))
			raiz = insert(word, raiz);
	}
	//Busca a posição correta da palavra na árvore
	//insere e se necessário realiza a rotação correta
	private TNode insert(String word, TNode raiz){
		
		if(raiz == null){
			return new TNode(word, null, null);
		}
			else if(word.compareTo(raiz.word) < 0){
				raiz.esq = insert(word, raiz.esq);
				if(raiz.esq.prior < raiz.prior){
					return rotateEsq(raiz);
				}
		}	
			else if(word.compareTo(raiz.word) > 0){
				raiz.dir = insert(word, raiz.dir);
				if(raiz.dir.prior > raiz.prior){
					return rotateDir(raiz);
				}
			}
		return raiz;
	}
	
	//Retorna true caso ache a palavra na árvore, e false caso contrário
	public boolean contains(String valor) {
        if(search(raiz, valor))
        	return true;
        else
        	return false;
    }
	
	//Procura pela palavra na árvore
	//Caso a encontre incrementa sua prioridade
	private boolean search(TNode raiz, String word){
		if(raiz != null){
			TNode aux = raiz; 
	
	        while(aux != null){
	            if( word.compareTo(aux.word) < 0 )
	                aux = aux.esq;
	            else if(word.compareTo(aux.word) > 0 ) 
	            	aux = aux.dir;
	            else if(aux != null){
	            	aux.prior ++;	
	            	return true;
	            }
	            else
	                return false;
	        }
		}
		return false;
	}
	
	
	 public void remove(String word){
	        if(remove(raiz, word));
	        	System.out.println("Removido!");
	    }
	 
	 //Busca pela palavra na árvore e decrementa sua prioridade
	 //caso a prioridade seja menor ou igual a 0 apaga a palavra e prioridade do nó
		private boolean remove(TNode raiz, String word){
			if(raiz != null){
				TNode aux = raiz; 
		
		        while(aux != null){
		            if( word.compareTo(aux.word) < 0 )
		                aux = aux.esq;
		            else if(word.compareTo(aux.word) > 0 ) 
		            	aux = aux.dir;
		            else if(aux != null){
		            	aux.prior --;
		            	if(aux.prior <= 0)
		            		aux = null;
		            	return true;
		            }
		            else
		                return false;
		        }
			}
			return false;
		}
	 
	
	 
	 
	 //Realiza a rotação a direita na árvore
	 public TNode rotateEsq(TNode raiz){
		 TNode esq = raiz.esq;
		 raiz.esq = esq.dir;
		 esq.dir = raiz;
		 
		 return esq;
	 }
	//Realiza a rotação a direita na árvore
	 public TNode rotateDir(TNode raiz){
		 TNode dir = raiz.dir;
			raiz.dir = dir.esq;
			dir.esq = raiz;
			
			return dir;
	 }
	
	 //Limpa a lista e chama o caminhamento In Ordem
	 //para inserir as palavras na lista
	public void inOrder(){
		listaTreap.cleanLista();
		inOrder(raiz);
	}
	
	//Caminha In Ordem inserindo as palavras na lista
	private void inOrder(TNode raiz){

		if(raiz != null){

			inOrder(raiz.esq);	
			No novo = new No(raiz.word, raiz.prior);
			listaTreap.insertNo(novo);
			inOrder(raiz.dir);

		}
	}
	

	//Método não usado!
	//Estava testando um caminhamento por nível que poderia
	//retornar os mais frequentes e caso empate por ordem alfabética.
	//Talvez seria necessário uma pequena alteração na ordem dos filhos do mesmo nó na árvore...
	public void byLevel(TNode raiz){
		Queue<TNode> level  = new LinkedList<>();
		level.add(raiz);
		while(!level.isEmpty()){
			TNode novo = level.poll();
			System.out.print(novo.word + " " + novo.prior + "  ");
			System.out.println(" ");
			if(novo.esq != null)
				level.add(novo.esq);
			if(novo.dir != null)
				level.add(novo.dir);
			
			
		}
		
	}
	
	
	
	
}
