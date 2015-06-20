import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class No {
	
	public String valor;
	public int count;
	public No proximo;
	public No anterior;
	
	
	
	public No(String valor){
		this.valor = valor;
		this.count = 1;
		proximo = null;
		
	}
	
	public No(String valor, int cont){
		this.valor = valor;
		this.count = cont;
		proximo = null;
		
	}
	
	public No(){
		
	}
	
	
	
	public No getProximo(){
		return this.proximo;
	}
	
	public void setProximo(No prox){
		this.proximo = prox;
	}
	
	
	
	public void setValor(String valor){
		this.valor = valor;
		this.proximo = null;
	}

}
