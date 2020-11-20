package Map;
import Events.Event;
import Events.StoryEvent;
import java.util.LinkedList;
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;

public class MapGraph {
	private ArrayList<LinkedList<MapNode>> adjacencyList;
	private ArrayList<MapNode> nodeList;
	
	public MapGraph() {
		adjacencyList = new ArrayList<>();
		nodeList = new ArrayList<>();
	}
	
	public void addNode(StoryEvent s) {
		ArrayList<Event> prereq = new ArrayList<>();
		
		
		if(adjacencyList.size() == 0) {
			adjacencyList.add(new LinkedList<MapNode>().add(new MapNode(s, prereq)));
		}
		
	}
	
	public void addEdge() {
		
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
		
		private StoryEvent peekStoryEvent() {
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
