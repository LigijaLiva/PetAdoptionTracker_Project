package application;

public class pets  {
	int id;
	String breed, description;
	
	public int getId() {
		return id;
	}
	
	public String getBreed() {
		return breed;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public pets(int id, String breed, String description) {
		this.id = id;
		this.breed = breed;
		this.description = description;
	}
}
