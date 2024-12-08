package application;

/**
 * The Register class holds information about a pet, including its breed, description, and unique pet ID.
 */
public class Register {

    private String breed;         // Pet's breed (e.g., Labrador)
    private int petID;            // Unique identifier for the pet
    private String description;   // Description of the pet

    /**
     * Constructor to initialize a pet's details.
     */
    public Register(String breed, String description, int petID) {
        this.breed = breed;
        this.description = description;
        this.petID = petID;
    }

    // Getter and setter methods for breed
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    // Getter and setter methods for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and setter methods for pet ID
    public int getPetID() {
        return petID;
    }

    public void setPetID(int petID) {
        this.petID = petID;
    }

    // Placeholder methods for future implementation (name and age)
    public String getName() {
        return null;  // To be implemented later
    }

    public String getAge() {
        return null;  // To be implemented later
    }
}
