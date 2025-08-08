import java.io.*;
import java.util.*;

/**
 * Handles file I/O operations for notes
 */
public class NotesFileManager {
    private static final String NOTES_FILE = "notes.txt";
    private static final String BACKUP_FILE = "notes_backup.txt";
    
    /**
     * Save all notes to file
     */
    public static boolean saveNotes(List<Note> notes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOTES_FILE))) {
            for (Note note : notes) {
                writer.println(note.toFileString());
            }
            System.out.println("✓ Notes saved successfully to " + NOTES_FILE);
            return true;
        } catch (IOException e) {
            System.err.println("✗ Error saving notes: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Load all notes from file
     */
    public static List<Note> loadNotes() {
        List<Note> notes = new ArrayList<>();
        File file = new File(NOTES_FILE);
        
        if (!file.exists()) {
            System.out.println("No existing notes file found. Starting fresh.");
            return notes;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    if (!line.trim().isEmpty()) {
                        Note note = Note.fromFileString(line);
                        notes.add(note);
                    }
                } catch (Exception e) {
                    System.err.println("✗ Error parsing line " + lineNumber + ": " + e.getMessage());
                }
            }
            
            System.out.println("✓ Loaded " + notes.size() + " notes from " + NOTES_FILE);
        } catch (IOException e) {
            System.err.println("✗ Error loading notes: " + e.getMessage());
        }
        
        return notes;
    }
    
    /**
     * Create backup of current notes file
     */
    public static boolean createBackup() {
        File notesFile = new File(NOTES_FILE);
        File backupFile = new File(BACKUP_FILE);
        
        if (!notesFile.exists()) {
            System.out.println("No notes file to backup.");
            return false;
        }
        
        try (FileInputStream fis = new FileInputStream(notesFile);
             FileOutputStream fos = new FileOutputStream(backupFile)) {
            
            byte[] buffer = new byte[1024];
            int length;
            
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            
            System.out.println("✓ Backup created: " + BACKUP_FILE);
            return true;
        } catch (IOException e) {
            System.err.println("✗ Error creating backup: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Restore notes from backup
     */
    public static boolean restoreFromBackup() {
        File backupFile = new File(BACKUP_FILE);
        File notesFile = new File(NOTES_FILE);
        
        if (!backupFile.exists()) {
            System.out.println("No backup file found.");
            return false;
        }
        
        try (FileInputStream fis = new FileInputStream(backupFile);
             FileOutputStream fos = new FileOutputStream(notesFile)) {
            
            byte[] buffer = new byte[1024];
            int length;
            
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            
            System.out.println("✓ Notes restored from backup");
            return true;
        } catch (IOException e) {
            System.err.println("✗ Error restoring from backup: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Export notes to a specified file
     */
    public static boolean exportNotes(List<Note> notes, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("=== NOTES EXPORT ===");
            writer.println("Export Date: " + new Date());
            writer.println("Total Notes: " + notes.size());
            writer.println("=" + "=".repeat(50));
            writer.println();
            
            for (int i = 0; i < notes.size(); i++) {
                Note note = notes.get(i);
                writer.println("Note #" + (i + 1));
                writer.println("-".repeat(20));
                writer.println("Title: " + note.getTitle());
                writer.println("Category: " + note.getCategory());
                writer.println("Created: " + note.getCreatedAt());
                writer.println("Modified: " + note.getLastModified());
                writer.println();
                writer.println("Content:");
                writer.println(note.getContent());
                writer.println();
                writer.println("=" + "=".repeat(50));
                writer.println();
            }
            
            System.out.println("✓ Notes exported to " + filename);
            return true;
        } catch (IOException e) {
            System.err.println("✗ Error exporting notes: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get file statistics
     */
    public static void showFileStats() {
        File notesFile = new File(NOTES_FILE);
        File backupFile = new File(BACKUP_FILE);
        
        System.out.println("\n=== FILE STATISTICS ===");
        
        if (notesFile.exists()) {
            System.out.println("Notes file: " + NOTES_FILE);
            System.out.println("Size: " + notesFile.length() + " bytes");
            System.out.println("Last modified: " + new Date(notesFile.lastModified()));
        } else {
            System.out.println("Notes file: Not found");
        }
        
        if (backupFile.exists()) {
            System.out.println("Backup file: " + BACKUP_FILE);
            System.out.println("Size: " + backupFile.length() + " bytes");
            System.out.println("Last modified: " + new Date(backupFile.lastModified()));
        } else {
            System.out.println("Backup file: Not found");
        }
    }
}
