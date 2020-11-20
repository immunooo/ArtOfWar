package Events;

public class StoryEvent {
	private String name;
	
	public StoryEvent(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object other) {
		StoryEvent story = (StoryEvent) other;
		return name.equals(story.getName());
	}

}
