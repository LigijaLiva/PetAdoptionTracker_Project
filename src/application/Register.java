package application;

public class Register {

	private String breed;
	private int petID;
	private String description;
	
	public Register(String breed, String description, int petID) {
		this.breed = breed;
		this.description = description;
		this.petID = petID;

	}


	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPetID() {
		return petID;
	}

	public void setPetID(int petID) {
		this.petID = petID;
	}


	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getAge() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
