import java.util.LinkedList;


public class EdgeWeightedDigraph {
	private final int V;
	private int E;
	private LinkedList<DirectedEdge>[] adj;
	private Coordinate[] coords;
	private HashST<Coordinate, Integer> ints;

	public EdgeWeightedDigraph(int v) {
		this.V = v;
		this.E = 0;
		this.coords = new Coordinate[v];
		this.ints = new HashST<>(v);
		adj = (LinkedList<DirectedEdge>[]) new LinkedList[v];
		for (int w = 0; w < v; w++)
			adj[w] = new LinkedList<DirectedEdge>();
	}
	public int V() { return V; } 
	public int E() { return E; }
	public boolean contains(int v) { return coords[v]!=null; }
	public boolean contains(Coordinate c){ return ints.contains(c); }
	public void addEdge(DirectedEdge e, Coordinate from, Coordinate to) {
		int v = e.from(), w = e.to();
		coords[v] = from;
		coords[w] = to;
		ints.put(from, v);
		ints.put(to, w);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	public int findClosestIntersection(Coordinate x){
		Double lowestDist = Double.POSITIVE_INFINITY;
		int lowIndex = 0;
		for(int i = 0;i<coords.length; i++){
			Coordinate y = coords[i];
			if(y != null){
				Double dist = x.dist(y);
				if(dist < lowestDist){
					lowestDist = dist;
					lowIndex = i;
				}
			}
		}
		return lowIndex;
	}
	public Coordinate coord(int i){return coords[i];}
	public Iterable<DirectedEdge> adj(int v) { return adj[v]; }
	public Coordinate[] coords(){
		return coords;
	}
}