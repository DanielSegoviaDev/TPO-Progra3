package claseGrafo;

public class main {

	public static void main(String[] args) {
		
		GrafosTDA grafos = new GrafoDinamic();
		GrafosTDA grafo1 = new ImplemEstatica();
	
		
		grafos.inicializarGrafo(9);
		grafos.agregarVertice(2);
		grafos.agregarVertice(5);
		grafos.agregarVertice(7);
		grafos.agregarVertice(9);
		grafos.agregarVertice(8);
		grafos.agregarVertice(54);
		grafos.agregarVertice(22);
		grafos.agregarVertice(3);
		grafos.agregarVertice(1);
		
		grafos.agregarArista(9, 2, 5);
		grafos.agregarArista(9, 5, 5);
		grafos.agregarArista(9, 7, 5);
		grafos.agregarArista(2, 9, 5);
		grafos.agregarArista(7, 8, 5);
		grafos.agregarArista(8, 54, 5);
		grafos.agregarArista(54, 22, 5);
		grafos.agregarArista(9, 3, 5);
		grafos.agregarArista(3, 1, 5);
		
		

		
		grafos.dephtFirstSearch(9);
		System.out.println();
		System.out.println("-------------------");
		
		grafo1.inicializarGrafo(6);
		grafo1.agregarVertice(1);
		grafo1.agregarVertice(6);
		grafo1.agregarVertice(5);
		grafo1.agregarVertice(4);
		grafo1.agregarVertice(3);
		grafo1.agregarVertice(2);
		
		
		
		grafo1.agregarArista(1, 6, 5);
		grafo1.agregarArista(1, 5, 10);
		grafo1.agregarArista(1, 3, 40);
		grafo1.agregarArista(6, 2, 20);
		grafo1.agregarArista(6, 5, 10);
		grafo1.agregarArista(5, 4, 20);
		grafo1.agregarArista(4, 3, 5);
		grafo1.agregarArista(3, 5, 5);
		grafo1.agregarArista(3, 2, 10);
			
		
		grafo1.dijkistra(1);
		System.out.println("-----------------------");
		
		grafo1.prim();
		grafo1.prim2();
		
	}
}
