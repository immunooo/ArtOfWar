package Events;

public class StoryEvent {
	private String name, location;
	
	public StoryEvent(String name, String location) {
		this.name = name;
		this.location = location;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object other) {
		StoryEvent story = (StoryEvent) other;
		return name.equals(story.getName()) && location.contentEquals(story.getLocation());
	}

}
