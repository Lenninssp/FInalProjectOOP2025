public class Task {
    private int id;
    private String title;
    private String description;
    private boolean completed;
    private int userId;

    public Task(int id, String title, String description, boolean completed, int userId){
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.userId = userId;
    }

    public Task (String title, String description, boolean completed, int userId){
        this(0, title, description, completed, userId);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    @Override
    public String toString() {
        return "Task{id=" + id + ", title='" + title + "', userId=" + userId + ", completed=" + completed + "}";
    }
}
