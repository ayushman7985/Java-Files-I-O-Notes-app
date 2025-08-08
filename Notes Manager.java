import java.util.*;
import java.util.stream.Collectors;

/**
 * Main class for managing notes operations
 */
public class NotesManager {
    private List<Note> notes;
    private Scanner scanner;
    
    public NotesManager() {
        this.notes = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        loadNotesFromFile();
    }
    
    /**
     * Load notes from file on startup
     */
    private void loadNotesFromFile() {
        this.notes = NotesFileManager.loadNotes();
    }
    
    /**
     * Add a new note
     */
    public void addNote() {
        System.out.println("\n=== ADD NEW NOTE ===");
        
        System.out.print("Enter note title: ");
        String title = scanner.nextLine().trim();
        
        if (title.isEmpty()) {
            System.out.println("✗ Title cannot be empty!");
            return;
        }
        
        System.out.print("Enter category: ");
        String category = scanner.nextLine().trim();
        
        if (category.isEmpty()) {
            category = "General";
        }
        
        System.out.println("Enter note content (type 'END' on a new line to finish):");
        StringBuilder content = new StringBuilder();
        String line;
        
        while (!(line = scanner.nextLine()).equals("END")) {
            if (content.length() > 0) {
                content.append("\n");
            }
            content.append(line);
        }
        
        Note note = new Note(title, content.toString(), category);
        notes.add(note);
        
        if (NotesFileManager.saveNotes(notes)) {
            System.out.println("✓ Note added successfully!");
        }
    }
    
    /**
     * Display all notes
     */
    public void displayAllNotes() {
        if (notes.isEmpty()) {
            System.out.println("\nNo notes found.");
            return;
        }
        
        System.out.println("\n=== ALL NOTES (" + notes.size() + ") ===");
        for (int i = 0; i < notes.size(); i++) {
            System.out.println("\n" + (i + 1) + ". " + notes.get(i));
            System.out.println("-".repeat(50));
        }
    }
    
    /**
     * Search notes by keyword
     */
    public void searchNotes() {
        System.out.print("\nEnter search keyword: ");
        String query = scanner.nextLine().trim();
        
        if (query.isEmpty()) {
            System.out.println("✗ Search query cannot be empty!");
            return;
        }
        
        List<Note> matchingNotes = notes.stream()
                .filter(note -> note.matches(query))
                .collect(Collectors.toList());
        
        if (matchingNotes.isEmpty()) {
            System.out.println("No notes found matching: " + query);
            return;
        }
        
        System.out.println("\n=== SEARCH RESULTS (" + matchingNotes.size() + ") ===");
        for (int i = 0; i < matchingNotes.size(); i++) {
            System.out.println("\n" + (i + 1) + ". " + matchingNotes.get(i));
            System.out.println("-".repeat(50));
        }
    }
    
    /**
     * Display notes by category
     */
    public void displayNotesByCategory() {
        if (notes.isEmpty()) {
            System.out.println("\nNo notes found.");
            return;
        }
        
        // Group notes by category
        Map<String, List<Note>> notesByCategory = notes.stream()
                .collect(Collectors.groupingBy(Note::getCategory));
        
        System.out.println("\n=== NOTES BY CATEGORY ===");
        for (Map.Entry<String, List<Note>> entry : notesByCategory.entrySet()) {
            System.out.println("\n📁 " + entry.getKey() + " (" + entry.getValue().size() + " notes)");
            System.out.println("=".repeat(30));
            
            for (int i = 0; i < entry.getValue().size(); i++) {
                Note note = entry.getValue().get(i);
                System.out.println((i + 1) + ". " + note.getTitle());
                System.out.println("   " + note.getContent().substring(0, 
                    Math.min(note.getContent().length(), 100)) + 
                    (note.getContent().length() > 100 ? "..." : ""));
                System.out.println();
            }
        }
    }
    
    /**
     * Edit an existing note
     */
    public void editNote() {
        if (notes.isEmpty()) {
            System.out.println("\nNo notes to edit.");
            return;
        }
        
        displayAllNotes();
        System.out.print("\nEnter note number to edit (1-" + notes.size() + "): ");
        
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (index < 0 || index >= notes.size()) {
                System.out.println("✗ Invalid note number!");
                return;
            }
            
            Note note = notes.get(index);
            System.out.println("\nEditing note: " + note.getTitle());
            System.out.println("1. Edit title");
            System.out.println("2. Edit content");
            System.out.println("3. Edit category");
            System.out.print("Choose option (1-3): ");
            
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine().trim();
                    if (!newTitle.isEmpty()) {
                        note.setTitle(newTitle);
                        System.out.println("✓ Title updated!");
                    }
                    break;
                    
                case 2:
                    System.out.println("Enter new content (type 'END' on a new line to finish):");
                    StringBuilder newContent = new StringBuilder();
                    String line;
                    
                    while (!(line = scanner.nextLine()).equals("END")) {
                        if (newContent.length() > 0) {
                            newContent.append("\n");
                        }
                        newContent.append(line);
                    }
                    
                    note.setContent(newContent.toString());
                    System.out.println("✓ Content updated!");
                    break;
                    
                case 3:
                    System.out.print("Enter new category: ");
                    String newCategory = scanner.nextLine().trim();
                    if (!newCategory.isEmpty()) {
                        note.setCategory(newCategory);
                        System.out.println("✓ Category updated!");
                    }
                    break;
                    
                default:
                    System.out.println("✗ Invalid option!");
                    return;
            }
            
            NotesFileManager.saveNotes(notes);
            
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input! Please enter a number.");
        }
    }
    
    /**
     * Delete a note
     */
    public void deleteNote() {
        if (notes.isEmpty()) {
            System.out.println("\nNo notes to delete.");
            return;
        }
        
        displayAllNotes();
        System.out.print("\nEnter note number to delete (1-" + notes.size() + "): ");
        
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (index < 0 || index >= notes.size()) {
                System.out.println("✗ Invalid note number!");
                return;
            }
            
            Note note = notes.get(index);
            System.out.print("Are you sure you want to delete '" + note.getTitle() + "'? (y/N): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            
            if (confirm.equals("y") || confirm.equals("yes")) {
                notes.remove(index);
                NotesFileManager.saveNotes(notes);
                System.out.println("✓ Note deleted successfully!");
            } else {
                System.out.println("Delete cancelled.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input! Please enter a number.");
        }
    }
    
    /**
     * Export notes to file
     */
    public void exportNotes() {
        if (notes.isEmpty()) {
            System.out.println("\nNo notes to export.");
            return;
        }
        
        System.out.print("Enter export filename (e.g., my_notes.txt): ");
        String filename = scanner.nextLine().trim();
        
        if (filename.isEmpty()) {
            filename = "notes_export.txt";
        }
        
        NotesFileManager.exportNotes(notes, filename);
    }
    
    /**
     * Show statistics
     */
    public void showStatistics() {
        System.out.println("\n=== NOTES STATISTICS ===");
        System.out.println("Total notes: " + notes.size());
        
        if (!notes.isEmpty()) {
            // Category statistics
            Map<String, Long> categoryCount = notes.stream()
                    .collect(Collectors.groupingBy(Note::getCategory, Collectors.counting()));
            
            System.out.println("Categories: " + categoryCount.size());
            categoryCount.forEach((category, count) -> 
                System.out.println("  " + category + ": " + count + " notes"));
            
            // Content statistics
            int totalWords = notes.stream()
                    .mapToInt(note -> note.getContent().split("\\s+").length)
                    .sum();
            
            int totalCharacters = notes.stream()
                    .mapToInt(note -> note.getContent().length())
                    .sum();
            
            System.out.println("Total words: " + totalWords);
            System.out.println("Total characters: " + totalCharacters);
            System.out.println("Average words per note: " + (totalWords / notes.size()));
        }
        
        NotesFileManager.showFileStats();
    }
    
    /**
     * Backup and restore operations
     */
    public void backupOperations() {
        System.out.println("\n=== BACKUP OPERATIONS ===");
        System.out.println("1. Create backup");
        System.out.println("2. Restore from backup");
        System.out.print("Choose option (1-2): ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    NotesFileManager.createBackup();
                    break;
                    
                case 2:
                    System.out.print("This will overwrite current notes. Continue? (y/N): ");
                    String confirm = scanner.nextLine().trim().toLowerCase();
                    
                    if (confirm.equals("y") || confirm.equals("yes")) {
                        if (NotesFileManager.restoreFromBackup()) {
                            loadNotesFromFile(); // Reload notes from restored file
                        }
                    } else {
                        System.out.println("Restore cancelled.");
                    }
                    break;
                    
                default:
                    System.out.println("✗ Invalid option!");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input! Please enter a number.");
        }
    }
    
    /**
     * Get total number of notes
     */
    public int getNotesCount() {
        return notes.size();
    }
}
