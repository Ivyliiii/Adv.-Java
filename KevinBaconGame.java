import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import LabeledGraph.Vertex;

public class KevinBaconGame {

	public KevinBaconGame() {
		// TODO Auto-generated constructor stub
	}
	
	LabeledGraph<String, String> graph = new LabeledGraph<String, String>();
	HashMap<String, String> actorsMap = new HashMap<String, String>();
	HashMap<String, String> moviesMap = new HashMap<String, String>();
	HashMap<String, String> map = new HashMap<String, String>();


	
	public void MapGeneration(String actorsFile, String moviesFile, String actorsMoviesMap) {
		BufferedReader reader;
		try {
			String line = "";
			String[] arr;
			reader = new BufferedReader(new FileReader(actorsFile));
			while((line =reader.readLine()) != null) {
				arr = line.split("~");
				actorsMap.put(arr[0], arr[1]);
				graph.addVertex(arr[1]);
			}
			System.out.println(actorsMap.size());
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			reader = new BufferedReader(new FileReader(moviesFile));
			String line = "";
			String[] arr;
			while((line =reader.readLine()) != null) {
				arr = line.split("~");
				moviesMap.put(arr[0], arr[1]);
			}
			System.out.println(moviesMap.size());
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			reader = new BufferedReader(new FileReader(actorsMoviesMap));
			String[] arr = reader.readLine().split("~");
			String[] pre = {"",""};
			ArrayList<String[]> arrayList = new ArrayList<String[]>();
			String line = "";
			int connections = 0;
			while((line =reader.readLine()) != null) {
				arr = line.split("~");
				if(arr[0].equals(pre[0])) {
					for(int i = 0; i < arrayList.size(); i++) {
						graph.connect(actorsMap.get(arr[1]), actorsMap.get(arrayList.get(i)[1]), moviesMap.get(arr[0]));
						connections++;
					}
					arrayList.add(arr);
				}
				else {
					arrayList = new ArrayList<String[]>();
					arrayList.add(arr);
				}
				pre = arr;
			}
			System.out.println(connections);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public int DFS(String actor1, String actor2){
		ArrayList<LabeledGraph.Vertex> toVisit = new ArrayList<LabeledGraph.Vertex>();
		HashMap<LabeledGraph.Vertex, LabeledGraph.Vertex> leadsTo = new HashMap<LabeledGraph.Vertex, LabeledGraph.Vertex>();
		toVisit.add(graph.get(actor1));
		String curr = toVisit.remove(0);
		leadsTo.put(curr, null);
		while(curr != null) {
			for(Edge e:curr.neighbors) {
				Vertex v = e.getOtherVertex(curr);
				if(v.equals(actor2)) {
					return backTrace(leadsTo, actor1, actor2);
				}
				if(!leadsTo.containsKey(v)) {
					toVisit.add(v);
					leadsTo.put(v, curr);
					curr = toVisit.remove(0);
				}
			}
		}
		return -1;
	}
	
	public int backTrace(ArrayList leadsTo, String actor1, String actor2) {
		ArrayList<LabeledGraph.Vertex> path = new ArrayList<LabeledGraph.Vertex>();
		HashMap<LabeledGraph.Vertex, LabeledGraph.Vertex> map = leadsTo;
		Vertex curr = graph.get(actor1);
		while(curr!=null) {
			path.add(curr);
			curr = map.get(curr);
		}
		return path.size();
	}
	
	public static void main(String[] args) {
		KevinBaconGame run = new KevinBaconGame();
		run.MapGeneration("actors.txt", "movies.txt", "movie-actors.txt");
		
	}


}
