package pl.edu.uwr.remindme;

public class NotesModel {
    private int id;
    private String title;
    private String description;
    private String createDate;

    public NotesModel(int id, String title, String description, String createDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createDate = createDate;
    }

    public NotesModel(String title, String description, String create_date) {
        this.title = title;
        this.description = description;
        this.createDate = create_date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String create_date) {
        this.createDate = create_date;
    }
}
