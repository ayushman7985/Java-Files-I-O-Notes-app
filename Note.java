import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a single note with title, content, and timestamp
 */
public class Note {
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime lastModified;
    private String category;
    
    // Constructor
    public Note(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
    }
    
    // Constructor for loading from file
    public Note(String title, String content, String category, 
                LocalDateTime createdAt, LocalDateTime lastModified) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = createdAt;
        this.lastModified = lastModified;
    }
    
    // Getters
    public String getTitle() {
        return title;
    }
    
    public String getContent() {
        return content;
    }
    
    public String getCategory() {
        return category;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public LocalDateTime getLastModified() {
        return lastModified;
    }
    
    // Setters
    public void setTitle(String title) {
        this.title = title;
        updateLastModified();
    }
    
    public void setContent(String content) {
        this.content = content;
        updateLastModified();
    }
    
    public void setCategory(String category) {
        this.category = category;
        updateLastModified();
    }
    
    private void updateLastModified() {
        this.lastModified = LocalDateTime.now();
    }
    
    // Convert note to file format string
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return String.format("%s|%s|%s|%s|%s",
                title.replace("|", "\\|"),
                content.replace("|", "\\|").replace("\n", "\\n"),
                category.replace("|", "\\|"),
                createdAt.format(formatter),
                lastModified.format(formatter));
    }
    
    // Create note from file format string
    public static Note fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid file format");
        }
        
        String title = parts[0].replace("\\|", "|");
        String content = parts[1].replace("\\|", "|").replace("\\n", "\n");
        String category = parts[2].replace("\\|", "|");
        LocalDateTime createdAt = LocalDateTime.parse(parts[3]);
        LocalDateTime lastModified = LocalDateTime.parse(parts[4]);
        
        return new Note(title, content, category, createdAt, lastModified);
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("[%s] %s\nCategory: %s\nCreated: %s | Modified: %s\n%s",
                category, title, category, 
                createdAt.format(formatter),
                lastModified.format(formatter),
                content);
    }
    
    // Check if note matches search query
    public boolean matches(String query) {
        String lowerQuery = query.toLowerCase();
        return title.toLowerCase().contains(lowerQuery) ||
               content.toLowerCase().contains(lowerQuery) ||
               category.toLowerCase().contains(lowerQuery);
    }
}
