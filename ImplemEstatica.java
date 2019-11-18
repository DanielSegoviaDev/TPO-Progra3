package claseGrafo;

import java.util.PriorityQueue;

public class ImplemEstatica implements GrafosTDA {
	
	int indice ;
	int[][] matrizAdy;
	int dim ; 
	int [] etiquetas;
	boolean [] marked;
	int [] ordenadas;
	
	public void inicializarGrafo(int dim) {
		this.dim = dim ;
		indice = 0;
		matrizAdy = new int[dim][dim];		
		etiquetas = new int[dim];
		marked = new boolean[dim];
		ordenadas = new int[dim];
		
	}
	
	public void agregarVertice(int v) {
		if(indice < dim) {
			etiquetas[indice] = v;			
			for(int i = 0 ;i < dim; i++ ) {
				matrizAdy[i][indice] = 0;
				matrizAdy[indice][i] = 0;
			}
			indice ++;
		}else {
			System.out.println("No se puden agregar mas nodos");
		}		
	}
	
	public int posicionDeNodo(int v) {
		for(int i = 0;  i < indice ; i++) {
			if(etiquetas[i] == v) {
				return i;
			}
		}
		return -1;
		
	}
	public void eliminarVertice(int v) {
		int aux = this.posicionDeNodo(v);
		if(aux != -1) {
			for(int i = 0; i < indice; i++) {
				matrizAdy[aux][i] = matrizAdy[indice-1][i];
				matrizAdy[i][aux] = matrizAdy[i][indice - 1];				
			}
		etiquetas[this.posicionDeNodo(v)] = etiquetas[indice - 1];
		indice-- ;
		}else {
			System.out.println("No se encontro el vertice");
		}	
	}
	
	public void agregarArista(int v1, int v2, int peso) {
		if(this.posicionDeNodo(v1) != -1 && this.posicionDeNodo(v2) != -1) {
			matrizAdy[this.posicionDeNodo(v1)][this.posicionDeNodo(v2)] = peso;
		}else {
			System.out.println("Alguno de los nodos no existe");
		}
	}
	
	public int[] vertices() {
		int[] aux = new int[indice];
		for(int i = 0; i < indice; i++) {
			aux[i] = etiquetas[i];
		}
		return aux;
	}
	
	
	public void eliminarArista(int v1, int v2) {
		
		if(this.posicionDeNodo(v1)>=0 && this.posicionDeNodo(v2)>=0) {
			matrizAdy[this.posicionDeNodo(v1)][this.posicionDeNodo(v2)] = 0;	
		}else {
			System.out.println("No existe alguno de los nodos");
		}
	}
	



	public boolean existeArista(int v1, int v2) {

		return false;
	}

	@Override
	public int pesoArista(int v1, int v2) {
	
		return 0;
	}
	
	public void mostrarMatriz() {
		System.out.print("\t");
		this.vertices();
		System.out.println();
		for(int i = 0; i < indice; i++) {
			System.out.print(etiquetas[i] + "\t");
			for(int j = 0; j < indice; j++) {
				System.out.print(this.matrizAdy[i][j] + "\t");
			}
			System.out.println("\n");
		}
	}
	public boolean pertenece(int x) {
		return 	(this.posicionDeNodo(x) != -1) ;
	}
	
	public int mayorArista(int v) {
		int fila = this.posicionDeNodo(v);
		if(v != -1) {
			int aux = 0;
			for(int i = 0; i < indice; i++) {
				if(aux  < matrizAdy[fila][i]) {
					aux = matrizAdy[fila][i];
				}
			}
		
			return aux ;
		}else {
			System.out.println("El nodo no se encuentra");
			return v;
		}
	}
	
	public int[] conjuntoAislados() {
		int[] aislados = new int [indice];
		int flag = 0;
		for(int i = 0 ; i < indice ; i ++) {
			flag = 0;
			for(int j = 0 ; j < indice ; j++) {
				if( matrizAdy[i][j] != 0 || matrizAdy[j][i] !=0)  {
					flag = 1;
					break;
				}				
			}
			if(flag == 0) {
				aislados[i]= etiquetas[i];
				System.out.print(aislados[i] + "\t");
			}
		}
		return aislados;
			
	}
	
	public void imparAristas() {
		int contar ;
		for(int i = 0; i < indice; i ++) {
			contar = 0;
			for(int j = 0; j < indice; j++) {
				if(matrizAdy[i][j] != 0) {
					contar++;
				}
			}
			if(contar % 2 != 0) {
				System.out.print(etiquetas[i] + "\t");
			}
		}
	}

	@Override
	public void dephtFirstSearch(int origen) {
		
	
	}
	
	@Override
	public void dijkistra(int origen) {
		int[] vertices = vertices();
		int[] distancia = new int[vertices.length];
	    int[] padre = new int[vertices.length];
	    boolean[] visto = new boolean[vertices.length];
	   
	    for (int i = 1; i < vertices.length; i++) {
	    	distancia[i] = Integer.MAX_VALUE;
	        padre[i] = -1;
	        visto[i] = false;
	     	}
	     distancia[origen]=0;
	     PriorityQueue<Integer> pila = new PriorityQueue<>();
	     pila.add(distancia[origen]);
		     while (!pila.isEmpty()) {
		        int u = pila.poll();
		        visto[u] = true;
	
		        for (int i = 0; i < vertices.length; i++) { 
		        	if ( (Integer)matrizAdy[u][i] != 0) {
		        		if ((Integer)distancia[i] > distancia[u] + (Integer)matrizAdy[u][i]) {  
		        			distancia[i] = distancia[u] + (Integer)matrizAdy[u][i];
		        			padre[i] = u;
		        			pila.add(i);
	             	}
	         	}
	     	} 
	     }
		     imprimirPadre(padre);
	
	}

	
	private void imprimirPadre(int [] padre) {
		for(int i = 0; i< padre.length; i++) 
		System.out.print(padre[i]);
	}

	
		// etapa 1 de prim: Elvi Daniel! 
	
		public void prim() {
			
		int[][] matrix = matrizAdy;
		int[] visited = new int[indice];
		int[] costo = new int[indice];
		int[] cerca = new int[indice];
		int min = Integer.MAX_VALUE;
		int u = 0, v = 0;
		int total = 0;
		
		for(int i=0; i <indice; i++) {
			costo[i]=matrizAdy[0][i];
			cerca[i] =0;
		}
		
		
		for(int i = 0; i < indice; i++){
			
			visited[i] = 0;
			
			for(int j = 0; j < indice; j++){
				
				matrix[i][j] = matrizAdy[i][j];
				if(matrix[i][j]==0){
					
					matrix[i][j] = Integer.MAX_VALUE;
					
				}
				
			}
			
		}
		
		visited[0] = 1;
		
		for(int counter = 0; counter <= indice ; counter++){
			
			min = Integer.MAX_VALUE;
			
			for(int i = 0; i < indice; i++){
			
			if(visited[i]==1){
			
				for(int j = 0; j < indice; j++){
				
					if(visited[j]!=1){
						
						if(min > matrix[i][j]){
							
							min = matrix[i][j];
							u = i;
							v = j;
							
						}
						
					}
					
				}
				
			}
			
			}
	
			visited[v] = 1;
			total += min;
			System.out.println("Edge connected: "+u+" -> "+v+" : "+min);
			
			
		
		}
		System.out.println("El costo minimo total del Grafo es: "+ total);
		
	}
		
		
}
	
	



	

