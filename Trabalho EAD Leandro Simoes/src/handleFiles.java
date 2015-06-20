import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public abstract class handleFiles {
	
	static Lista[] lista;
	static Lista mainLista;
	private static FileReader reader;
	private static int escolha;
	
	static TST[] tst;
	static Lista[] listaTST;
	static TST[] tstMain;
	static Lista[] mainListaTST;
	
	static TREAP[] treap;
	static Lista[] mainListaTreap;
	static TREAP[] treapMain;

	
	
	//This method does exactly what it says do
	public static File[] openFiles(){
		
		File[] files = null;
		
		JFileChooser opener = new JFileChooser();
		opener.setMultiSelectionEnabled(true);
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text");
		opener.setFileFilter(filter);
		
		int select = opener.showOpenDialog(null);  
		           if (select == JFileChooser.APPROVE_OPTION) {
		        	   files = opener.getSelectedFiles();
		        	   showFiles(files);	               }
		           
	return files;
	}
	
	

	//Cria um Array dos arquivos abertos
	private static void showFiles(File[] files){
		System.out.println("Serão abertos " + files.length + " arquivos.");
		for(int i = 0 ; i < files.length ; i ++)           
     	   System.out.println(i + " : " + files[i].getName());
		
	
	}
	
	//Abre os arquivos e separa as palavras para a lista
	public static void readFiles(File[] files){
		String line = "";
		String[] words = {};
		lista = new Lista[files.length];
		mainLista = new Lista();
		long tStart = System.currentTimeMillis();
		
		
		
			try {
				
				for(int i = 0; i < files.length; i ++ ){
				reader = new FileReader(files[i].getPath());
				BufferedReader buffReader = new BufferedReader(reader);
				lista[i] = new Lista();
				
				while(true){
					line = buffReader.readLine();
					if(line != null){
						words = line.replaceAll("[\\p{Punct}\\W0-9&&[^\\s+]]", "")
								.split(" ");
						insertWords(words, lista, i);
					}
					else
						break;
				}
			
				reader.close();
				}
				
				
				indexListas(lista);
				copyMain(lista, mainLista);
				
				
				long tEnd = System.currentTimeMillis();
				long tDelta = tEnd - tStart;
				double elapsedSeconds = tDelta / 1000.0;
				System.out.println("Lista indexida: " + files.length +" Tempo: " + elapsedSeconds);
				
				
				
				
			} catch (Exception  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Não foi possível abrir o arquivo.");
			}
			
		
		
	}
	//Abre os arquivos e separa as letras para a TST
	public static void readFilesOnTST(File[] files){
		String line = "";
		String[] words = {};
		
		tst = new TST[files.length];
		tstMain = new TST[1];
		tstMain[0] = new TST();
		
		long tStart = System.currentTimeMillis();
		
		
		try{
			
			for(int i = 0; i < files.length; i ++ ){
				reader = new FileReader(files[i].getPath());
				BufferedReader buffReader = new BufferedReader(reader);
				
				tst[i] = new TST();
				
				while(true){
					line = buffReader.readLine();
					if(line != null){
						words = line.replaceAll("[\\p{Punct}\\W0-9&&[^\\s+]]", "")
								.split(" ");
						insertWordsOnTST(words, tst, i);
						insertWordsOnTST(words, tstMain, 0);
					}
					else
						break;
				}
				reader.close();
				
				
			}
			
			indexListasTST(tst);
			indexListasTST(tstMain);
			
			long tEnd = System.currentTimeMillis();
			long tDelta = tEnd - tStart;
			double elapsedSeconds = tDelta / 1000.0;
			System.out.println("TST indexada: " + files.length +" Tempo: " + elapsedSeconds);
			
			//showTST(tst);
			//showTST(tstMain);
			
			
		}catch(Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível abrir o arquivo.");
		}
	}
	
	//Abre os arquivos e separa as palavras para a TREAP
	public static void readFilesOnTREAP(File[] files){
		String line = "";
		String[] words = {};
		
		treap = new TREAP[files.length];
		treapMain = new TREAP[1];
		treapMain[0] = new TREAP();
		
		
		
		
		
		long tStart = System.currentTimeMillis();
		
		
		try{
			
			for(int i = 0; i < files.length; i ++ ){
				reader = new FileReader(files[i].getPath());
				BufferedReader buffReader = new BufferedReader(reader);
				
				treap[i] = new TREAP();
				
				while(true){
					line = buffReader.readLine();
					if(line != null){
						words = line.replaceAll("[\\p{Punct}\\W0-9&&[^\\s+]]", "")
								.split(" ");
						insertWordsOnTREAP(words, treap, i);
						insertWordsOnTREAP(words, treapMain, 0);
					}
					else
						break;
				}
				reader.close();
				
				
		
			}
			indexListasTreap(treap);
			indexListasTreap(treapMain);
			
			
			long tEnd = System.currentTimeMillis();
			long tDelta = tEnd - tStart;
			double elapsedSeconds = tDelta / 1000.0;
			System.out.println("Treap indexada: " + files.length +" Tempo: " + elapsedSeconds);
			
			
		}catch(Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível abrir o arquivo.");
		}
	}
	
	
	//Insere as palavras na lista
	public static void insertWords(String[] words, Lista[] lista, int numbLista){
		for(String palavra : words){
			if(palavra.length() > 1)
				lista[numbLista].insertNo(palavra);
		}
			
	}
	//Insere as letras na TST
	public static void insertWordsOnTST(String[] words, TST[] tst, int numbLista){
		for(String palavra : words){
			if(palavra.length() > 1)
				tst[numbLista].insert(palavra, 0);
		}

			
	}
	
	//Insere as palavras na TREAP
	public static void insertWordsOnTREAP(String[] words, TREAP[] treap, int numbLista){
		for(String palavra : words){
			if(palavra.length() > 1)
				treap[numbLista].insert(palavra);
		}

			
	}
	//Remove a palavra nas listas geradas pela TST
	public static void removeTST(int arquivo, String palavra){
		long tStart = System.currentTimeMillis();
		if(arquivo >= 0)
			tst[arquivo].remove(palavra);
		else{
			for(int i = 0 ; i < tst.length ; i ++)
				tst[i].remove(palavra);
				tstMain[0].remove(palavra);
			
		}
		//Re-cria as listas da TST
		indexListasTST(tst);
		indexListasTST(tstMain);
		
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.println("Remoção tempo: " + elapsedSeconds);
	}
	
	//Remove a palavra nas listas geradas pela TREAP
	public static void removeTreap(int arquivo, String palavra){
		long tStart = System.currentTimeMillis();
		if(arquivo >= 0)
			treap[arquivo].remove(palavra);
		else{
			for(int i = 0 ; i < tst.length ; i ++)
				treap[i].remove(palavra);
				tstMain[0].remove(palavra);
			
		}
		
		//Re-cria as listas usadas pela TREAP
		indexListasTreap(treap);
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.println("Remoção tempo: " + elapsedSeconds);
	}
	
	
	public static void creatListaTST(TST[] tst){
		for(int i = 0; i < tst.length; i ++)
			tst[i].print();
		
	}
	
	public static void creatListaTreap(TREAP[] treap){
		for(int i = 0; i < treap.length; i ++)
			treap[i].inOrder();
		
	}
	
	public static void showTST(TST[] tst, int palavras, int arquivo){
		long tStart = System.currentTimeMillis();
		
		tst[arquivo].listaTST.showLista(palavras);
		
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.println("Busca tempo: " + elapsedSeconds);
		
		
	}
	
	
	public static void showTREAP(TREAP[] treap, int palavras, int arquivo){		
		long tStart = System.currentTimeMillis();
		
		treap[arquivo].listaTreap.showLista(palavras);
			
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.println("Busca tempo: " + elapsedSeconds);
		
	}
	

	public static void indexListasTST(TST[] tst){
		creatListaTST(tst);
		for(int i = 0 ; i < tst.length ; i ++){
			tst[i].listaTST.mergeSortCount(tst[i].listaTST.getFirst());
		}
		
	}
	
	public static void indexListasTreap(TREAP[] treap){
		creatListaTreap(treap);
		for(int i = 0 ; i < treap.length ; i ++){
			treap[i].listaTreap.mergeSortCount(treap[i].listaTreap.getFirst());
		}
		
	}
	
	
	public static void indexListas(Lista[] lista){
		for(int i = 0 ; i < lista.length ; i ++){
		
			lista[i].mergeSort(lista[i].getFirst());
			lista[i].countList();
			lista[i].mergeSortCount(lista[i].getFirst());
			
			
		}
		
	}
	
	
	public static void copyMain(Lista[] lista, Lista mainLista){
		
		
		No novo, proximo;
		for(int i = 0 ; i < lista.length ; i ++){
			novo = lista[i].getFirst();
			while(novo != null){
				proximo = novo.getProximo();
				mainLista.insertNo(novo);
				novo = proximo;
			}
		}
		indexMain();
		
	}
	
	public static void indexMain(){
		
		mainLista.mergeSort(mainLista.getFirst());
		mainLista.mergeSortCount(mainLista.getFirst());
		
	}


	
}
