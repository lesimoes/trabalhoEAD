import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class main{
	
	static int estrutura;
	static File[] files;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		files = null;
		
		
		
		showEscolhas();
		
							
	}
	
	public static void showEscolhas(){
		int palavras;
		int escolha;
		int arquivo;
		int menu = 0;
		Scanner scanIn = new Scanner(System.in);
		
		files = handleFiles.openFiles();
		criarEstrutura();
		
		while(menu != 5){
			
			System.out.println("1- Mostrar as x palavras mais frequentes\n2- Remover palavra  3- Reiniciar 4- Alterar Estrutura");
			menu = Integer.parseInt(scanIn.nextLine());
			
			
			switch(menu){
				case 1:{
					
					System.out.println("Digite o número de palavras para exibir: ");
					
				    palavras = Integer.parseInt(scanIn.nextLine());
				    System.out.println("1- Mostrar de todos os arquivos ou 2- Mostrar de um arquivo especifico?");
				    escolha = Integer.parseInt(scanIn.nextLine());
				    
				    if(escolha == 2){
				    	System.out.println("Digite o número do arquivo: ");
				    	arquivo = Integer.parseInt(scanIn.nextLine());
				    	if(estrutura == 1)
				    		handleFiles.lista[arquivo].showLista(palavras);
				    	if(estrutura == 2)
				    		handleFiles.showTST(handleFiles.tst, palavras, arquivo);
				    	if(estrutura == 3)
				    		handleFiles.showTREAP(handleFiles.treap, palavras, arquivo);
				    	
				    }
				    	else{
					    	if(estrutura == 1)
					    		handleFiles.mainLista.showLista(palavras);
					    	if(estrutura == 2)
						    	handleFiles.showTST(handleFiles.tstMain, palavras, 0);
					    	if(estrutura == 3)
						    	handleFiles.showTREAP(handleFiles.treapMain, palavras, 0);
					    	
				    	}
					    
				}break;
				
				case 2:{
					System.out.println("Digite a palavra a remover: ");
					String removerPalavra = scanIn.nextLine();
					
					System.out.println("1- Todos os arquivos ou 2- Arquivo especifico?");
					int escolhaArquivo = Integer.parseInt(scanIn.nextLine());
					if(escolhaArquivo == 1)
						handleFiles.removeTST(-1, removerPalavra);
					if(escolhaArquivo == 2){
						System.out.println("Escolha o número do arquivo: ");
						int removerArquivo = Integer.parseInt(scanIn.nextLine());
						if(estrutura == 2)
							handleFiles.removeTST(removerArquivo, removerPalavra);
						if(estrutura == 3)
							handleFiles.removeTreap(removerArquivo, removerPalavra);
					}
				
				}break;
				case 3:showEscolhas();
					break;
				case 4:
					criarEstrutura();
					break;
				default: break;
	 
	    
	}
		}
		
		
		
		
	}
	
	
	public static void criarEstrutura(){
		
		System.out.println("Escolha a estrutura: 1- Lista 2- TST 3- TREAP ");
		Scanner scanIn = new Scanner(System.in);
		estrutura = Integer.parseInt(scanIn.nextLine());
		switch(estrutura){
		case 1: 
			handleFiles.readFiles(files);
		break;
			
		case 2: handleFiles.readFilesOnTST(files);
		break;
		
		case 3:handleFiles.readFilesOnTREAP(files);
			break;
			
			default: break;
			
		}
	}
	

}
