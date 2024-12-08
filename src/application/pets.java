package application;

// Sākotnējā klase, kas reprezentē dzīvnieku (pet)
public class Pets {
    // Privātie mainīgie, kas saglabā dzīvnieka ID, šķirni un aprakstu
    int id;
    String breed, description;
    
    // Metode, kas atgriež dzīvnieka ID
    public int getId() {
        return id;
    }
    
    // Metode, kas atgriež dzīvnieka šķirni
    public String getBreed() {
        return breed;
    }
    
    // Metode, kas atgriež dzīvnieka aprakstu
    public String getDescription() {
        return description;
    }
    
    // Metode, kas piešķir dzīvniekam ID vērtību
    public void setId(int id) {
        this.id = id;
    }
    
    // Metode, kas piešķir dzīvniekam šķirni
    public void setBreed(String breed) {
        this.breed = breed;
    }
    
    // Metode, kas piešķir dzīvniekam aprakstu
    public void setDescription(String description) {
        this.description = description;
    }
    
    // Konstruktors, kas inicializē dzīvnieka ID, šķirni un aprakstu
    public Pets(int id, String breed, String description) {
        this.id = id;
        this.breed = breed;
        this.description = description;
    }
}
