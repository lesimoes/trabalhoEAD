
public class TNode {

	TNode esq, dir;
	int prior;
	String word;
	
	public TNode(){
		
	}
	
	public TNode(String word){
		this.word = word;
		this.prior = 1;
	}
	
	public TNode(String word, TNode esq, TNode dir){
		this.word = word;
		this.esq = esq;
		this.dir = dir;
		this.prior = 1;
	}
	
	
}
