public class DigitalVideoDisc {
    // Class (classifier) member: one copy shared by every DVD object
    private static int nbDigitalVideoDiscs = 0;

    // Instance members
    private int id;
    private String title;
    private String category;
    private String director;
    private int length;
    private float cost;

    // Create a DVD object by title
    public DigitalVideoDisc(String title) {
        super();
        this.title = title;
        assignId();
    }

    // Create a DVD object by category, title and cost
    public DigitalVideoDisc(String title, String category, float cost) {
        super();
        this.title = title;
        this.category = category;
        this.cost = cost;
        assignId();
    }

    // Create a DVD object by director, category, title and cost
    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super();
        this.title = title;
        this.category = category;
        this.director = director;
        this.cost = cost;
        assignId();
    }

    // Create a DVD object by all attributes: title, category, director, length and cost
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super();
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
        assignId();
    }

    // Every new DVD increases the class counter and takes the new value as its id
    private void assignId() {
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    public static int getNbDigitalVideoDiscs() {
        return nbDigitalVideoDiscs;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    // Temporary setter, only needed for the TestPassingParameter exercise (section 15)
    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public float getCost() {
        return cost;
    }
}
