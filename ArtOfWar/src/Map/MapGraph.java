package Map;
import Events.Event;
import Events.RandomEventList;
import Events.StoryEvent;
import java.util.LinkedList;
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;
/**
 * MapGraph : Stores the story events object in a graph ADT to keep traversal simple
 *
 * @author Joseph Berlucchi
 * @version 1.0
 */
public class MapGraph {
	private ArrayList<LinkedList<MapNode>> adjacencyList;
	private RandomEventList eventList;
	private LinkedList<MapNode> currentNode;
	
	
	public MapGraph() {
		adjacencyList = new ArrayList<>();
		eventList = new RandomEventList("randomevents.txt");
		currentNode = null;
	}
	
	/**
	 * Adds a story event into the graph, doesnt allow duplicates
	 * 
	 * @param s the story event to be added to the graph
	 * 
	 * @return true if added or false if not
	 */
	public boolean addNode(StoryEvent s) {
		//Create new prereq arraylist
		ArrayList<Event> prereq = new ArrayList<>();
		LinkedList<MapNode> adjacency = new LinkedList<>();
		
		//Adds random events to the prereqs
		for(int i = 0; i < 3; i++) {
			prereq.add(eventList.getRandomEvent(s.getLocation()).clone());
		}
		
		//Creates a map node 
		MapNode newNode = new MapNode(s, prereq);
		
		//Determines whether the map node already exists
		for(LinkedList<MapNode> adj: adjacencyList) {
			if(adj.get(0).equals(newNode)) {
				return false;
			}
		}
		
		//adds the node to the linked list
		adjacency.addFirst(newNode);
		//adds the linked list to the adjacency list
		adjacencyList.add(adjacency);
		
		//changes the current node to the first node added to the graph
		if(currentNode == null) {
			currentNode = adjacencyList.get(0);
		}
		return true;
		
	}
	
	/**
	 * Adds an edge for two node in the graph.
	 * 
	 * @param s1 
	 * @param s2 
	 * 
	 * @returns true if an edge was added or false if not
	 */
	public boolean addEdge(StoryEvent s1, StoryEvent s2) {
		boolean s1eg = false, s2eg = false;
		int index = 0, index1 = 0, index2 = 0;
		
		//Determines wheter the storyevents exists in the adjacencylist
		for(LinkedList<MapNode> adj: adjacencyList) {
			
			if(adj.getFirst().peekStoryEvent().equals(s1)) {
				s1eg = true;
				index1 = index;
			}
			if(adj.getFirst().peekStoryEvent().equals(s2)) {
				s2eg = true;
				index2 = index;
			}
			index++;
			
		}
		
		if(!s1eg || !s2eg) {
			return false;
		}
		
		//gets boths map nodes from the list
		MapNode mn1 = adjacencyList.get(index1).getFirst();
		MapNode mn2 = adjacencyList.get(index2).getFirst();
		
		//adds the map node from one to the linked list of the other
		adjacencyList.get(index1).add(mn2);
		adjacencyList.get(index2).add(mn1);
		
		return true;
	}
	
	/**
	 * gets the amount of edges of a node
	 * 
	 * @return the amount of edges
	 */
	public int getNumEdges() {
		if(currentNode == null) return -1;
		return currentNode.size();
	}
	
	/**
	 * sets the current node to a new one from the edges if the prereq is complete
	 * 
	 * @param index the index of what node you want to traverse to
	 * 
	 * @return true if it traverses or false if not
	 */
	public boolean traverseEdge(int index) {
		if(!currentNode.getFirst().isPrereqComplete()) {
			return false;
		}
		if(index > getNumEdges() - 1 || index < 1) {
			return false;
		}
		
		MapNode nextmn = currentNode.get(index);
		
		for(LinkedList<MapNode> mn : adjacencyList ) {
			if(mn.getFirst().equals(nextmn)) {
				currentNode = mn;
				return true;
			}
		}
		return false;
		
	}
	
	/**
	 * gets and array list of random events object
	 * 
	 * @return the array list of random events object
	 */
	public ArrayList<Event> getRandomEvents() {
		MapNode current = currentNode.getFirst();
		ArrayList<Event> rndEvents = new ArrayList<>();
		for(int i = 0; i < current.getEventCount(); i++) {
			current.getEvent(i);
		}
		return rndEvents;
	}
	
	/**
	 * gets the story event
	 * 
	 * @return story event, if null means prereq are not complete.
	 */
	public StoryEvent getStoryEvent() {
		return currentNode.getFirst().getStoryEvent();
	}
	
	
	/**
	 * MapNode : a node object that stores a story events and random events (prereq)
	 *
	 * @author Joseph Berlucchi
	 * @version 1.0
	 */
	private class MapNode {
		
		ArrayList<Event> prereq;
		StoryEvent mainEvent;
		
		
		public MapNode(StoryEvent mainEvent, ArrayList<Event> prereq) {
			this.mainEvent = mainEvent;
			this.prereq = prereq;
		}
		
		public StoryEvent getStoryEvent() {
			if(isPrereqComplete()) {
				return mainEvent;
			}
			return null;
		}
		
		public StoryEvent peekStoryEvent() {
			return mainEvent;
		}
		
		public Event getEvent(int index) {
			if(index < 0 || index > prereq.size() - 1) {
				throw new IndexOutOfBoundsException("Invalid index: " + index);
			}
			return prereq.get(index);
		}
		
		public int getEventCount() {
			return prereq.size();
		}
		
		public boolean isPrereqComplete() {
			for(Event event: prereq) {
				if(!event.isEventComplete()) {
					return false;
				}
			}
			return true;
		}
		@Override
		public boolean equals(Object other) {
			MapNode n = (MapNode) other;
			return n.peekStoryEvent().equals(mainEvent);
			
		}
		
	}
}
