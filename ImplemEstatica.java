package claseGrafo;

import java.util.ArrayList;
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
		
		System.out.println();
	}

	
		// etapa 1 de prim: Elvi Daniel! 
	
		public void prim() {
			
			@SuppressWarnings("unused")
			int longMin, menor, z;
			int[] coste = new int[indice];
			int[] masCerca= new int[indice];
			boolean[] visitado = new boolean[indice];
			
			longMin=0;
			
			for(int i=0;i<indice;i++){
				visitado[i]=false;
				longMin =0;
				visitado[0] = true;
			}
			
			for(int i = 1; i < indice;i++){
				coste[i]= matrizAdy[0][i];
				masCerca[i]=0;
			}
			for(int i=1; i<indice;i++){
				menor = coste[1];
				z=1;
				for(int j=2;j<indice;j++){
					if (coste[j]<menor){
						menor = coste[j];
						z=j;
					}
				}
				longMin += menor;
				System.out.println("Pasada: Vertice: "+etiquetas[masCerca[z]]+
						"->"+etiquetas[z]+" Peso:"+coste[z]);
				visitado[z]=true;
				coste[z]=999;
				for(int j=1;j<indice;j++){
					if((matrizAdy[z][j]<coste[j])&&!visitado[j]){
						coste[j]=matrizAdy[z][j];
						masCerca[j]=z;
					}
				}
				
			}

	}
		
		public int[][] prim2() {
			//Primeramente declaramos 3 Listas 
	        ArrayList<Boolean> verticesVisitados = new ArrayList<Boolean>();
	        ArrayList<Integer> distanciasRelativas = new ArrayList<Integer>();
	        ArrayList<Integer> nodosAdyacentes = new ArrayList<Integer>();
	        /**
	        Inicializando Variables
	        */
	        for(Integer contador=0;contador < matrizAdy[0].length;contador++)
	        {
	            verticesVisitados.add(false);
	            nodosAdyacentes.add(0);
	            distanciasRelativas.add(Integer.MAX_VALUE);
	        }
	        distanciasRelativas.set(0, new Integer(0));
	        /**
	        Definicion del punto que sera la raiz del punto resultante
	        */
	        Integer puntoevaluado = 0;
	        Integer Adyacentes = 0;
	        /**
	        Estructuras para ejecutar algoritmo de Prim
	        */
	        for(Integer contadorpuntoevaluado = 0;contadorpuntoevaluado < matrizAdy[0].length;contadorpuntoevaluado++)
	        {
	            for(Integer contadorAdyacentes = 0;contadorAdyacentes < matrizAdy[0].length;contadorAdyacentes++)
	            {
	                /**
	                Verifica si el nodo a ser valorado en esta iteracion ha sido valorado anteriormente ;
	                * si es asi, pasa para la siguiente iteracion
	                */
	                if((verticesVisitados.get(contadorAdyacentes)) || (contadorAdyacentes == puntoevaluado))
	                {
	                    continue;
	                }
	                /**
	                Dos comparaciones aki:
	                * 1-Verifica si la matrizOriginal hay algo de valor en la columna que es > 0.Si es asi
	                * Significa que existe un Arista entre estos dos puntos de la gráfica 
	                * 2-Verifica si el peso de la arista entre los dos puntos es menor que el actual distanciaRelativa
	                * del nodo vecino
	                * Si es correcto, el nodo distanciaRelativa para ser evaluado por el momento sera actualizado
	                * el valor de esta nueva distancia valorada para puntoValorado
	                */
	                if((matrizAdy[puntoevaluado][contadorAdyacentes] > 0) && ((matrizAdy[puntoevaluado][contadorAdyacentes] < distanciasRelativas.get(contadorAdyacentes))))
	                {
	                    distanciasRelativas.set(contadorAdyacentes, matrizAdy[puntoevaluado][contadorAdyacentes]);
	                    nodosAdyacentes.set(contadorAdyacentes,puntoevaluado);
	                }
	                /**
	                _Marca los vertices d puntoevaluado como un vertice ya verificado 
	                */
	                verticesVisitados.set(puntoevaluado,true);//*Paso de parametro true y de ahicomienza siguiente Vertice */
	                /**
	                *Prepara para seleccionar el proximo vértice a ser evaluado 
	                */
	                puntoevaluado = new Integer(0);
	                Integer distanciaActualacomparar = new Integer(Integer.MAX_VALUE);
	                
	                /**
	                *Seleccionar el próximo vertice a ser evaluado
	                */
	                for(Integer contador =1;contador < verticesVisitados.size();contador++)
	                {
	                    /**
	                *Si el vertice a ser verificado ya fue evaluado anteriormente (true)
	                * pasa a la proxima iteración
	                */
	                    if(verticesVisitados.get(contador))
	                    {
	                        continue;

	                    }
	                /**
	                *Si la distancia relativa de ese punto es menor que a al punto valorado
	                * asumir ese nuevo punto como un punto valorado
	                * 
	                * al final de la iteracion, será seleccionado un punto con menor distancia relativa
	                */
	                    if(distanciasRelativas.get(contador) < distanciaActualacomparar)
	                    {
	                        distanciaActualacomparar = distanciasRelativas.get(contador);
	                        puntoevaluado = contador;
	                    }
	                
	                }//end 3er for 
	        }//end 2do for
	        }//end 1er for
	        int [][] matrizResultane =new int [matrizAdy[0].length][matrizAdy[0].length];
	                /**
	                *Crear una MatrizOriginal como una arbol resultante del Algoritmo de Prim 
	                */
	                for(int contador = 0;contador < nodosAdyacentes.size();contador++)
	                {
	                    matrizResultane[contador][nodosAdyacentes.get(contador)] = matrizAdy[contador][nodosAdyacentes.get(contador)];
	                    matrizResultane[nodosAdyacentes.get(contador)][contador] = matrizResultane[contador][nodosAdyacentes.get(contador)];
	                }
	                	
	                
	                return matrizResultane;
			}//end funcion Prim
	









	

		
		
}
	
	



	

