package Map;
import Events.Event;
import Events.RandomEventList;
import Events.StoryEvent;
import java.util.LinkedList;
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;

public class MapGraph {
	private ArrayList<LinkedList<MapNode>> adjacencyList;
	private RandomEventList eventList;
	private LinkedList<MapNode> currentNode;
	
	public MapGraph() {
		adjacencyList = new ArrayList<>();
		eventList = new RandomEventList("randomevents.txt");
		currentNode = null;
	}
	
	public boolean addNode(StoryEvent s) {
		ArrayList<Event> prereq = new ArrayList<>();
		LinkedList<MapNode> adjacency = new LinkedList<>();
		
		for(int i = 0; i < 3; i++) {
			prereq.add(eventList.getRandomEvent(s.getLocation()).clone());
		}
		
		MapNode newNode = new MapNode(s, prereq);
		
		for(LinkedList<MapNode> adj: adjacencyList) {
			if(adj.get(0).equals(newNode)) {
				return false;
			}
		}
		
		adjacency.addFirst(newNode);
		adjacencyList.add(adjacency);
		if(currentNode == null) {
			currentNode = adjacencyList.get(0);
		}
		return true;
		
	}
	
	public boolean addEdge(StoryEvent s1, StoryEvent s2) {
		boolean s1eg = false, s2eg = false;
		int index = 0, index1 = 0, index2 = 0;
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
		
		MapNode mn1 = adjacencyList.get(index1).getFirst();
		MapNode mn2 = adjacencyList.get(index2).getFirst();
		
		adjacencyList.get(index1).add(mn2);
		adjacencyList.get(index2).add(mn1);
		
		return true;
	}
	
	public int getNumEdges() {
		if(currentNode == null) return -1;
		return currentNode.size();
	}
	
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
	
	public ArrayList<Event> getRandomEvents() {
		MapNode current = currentNode.getFirst();
		ArrayList<Event> rndEvents = new ArrayList<>();
		for(int i = 0; i < current.getEventCount(); i++) {
			rndEvents.get(i);
		}
		return rndEvents;
	}
	
	public StoryEvent getStoryEvent() {
		return currentNode.getFirst().getStoryEvent();
	}
	
	
	
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
