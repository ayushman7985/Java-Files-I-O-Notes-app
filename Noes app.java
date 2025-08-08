import java.util.Scanner;

/**
 * Main application class for the Java File I/O Notes App
 * Provides a console-based interface for managing notes with file persistence
 */
public class NotesApp {
    private NotesManager notesManager;
    private Scanner scanner;
    private boolean running;
    
    public NotesApp() {
        this.notesManager = new NotesManager();
        this.scanner = new Scanner(System.in);
        this.running = true;
    }
    
    /**
     * Start the application
     */
    public void start() {
        showWelcomeMessage();
        
        while (running) {
            showMainMenu();
            handleUserChoice();
        }
        
        showGoodbyeMessage();
        scanner.close();
    }
    
    /**
     * Display welcome message
     */
    private void showWelcomeMessage() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║        📝 JAVA NOTES APP 📝          ║");
        System.out.println("║     File I/O Based Note Manager      ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();
        System.out.println("Welcome! Your notes are automatically saved to file.");
        System.out.println("Current notes count: " + notesManager.getNotesCount());
        System.out.println();
    }
    
    /**
     * Display main menu
     */
    private void showMainMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("           📋 MAIN MENU");
        System.out.println("=".repeat(40));
        System.out.println("1.  ➕ Add new note");
        System.out.println("2.  📖 View all notes");
        System.out.println("3.  🔍 Search notes");
        System.out.println("4.  📁 View notes by category");
        System.out.println("5.  ✏️  Edit note");
        System.out.println("6.  🗑️  Delete note");
        System.out.println("7.  📤 Export notes");
        System.out.println("8.  📊 Show statistics");
        System.out.println("9.  💾 Backup operations");
        System.out.println("10. ❓ Help");
        System.out.println("0.  🚪 Exit");
        System.out.println("=".repeat(40));
        System.out.print("Choose an option (0-10): ");
    }
    
    /**
     * Handle user menu choice
     */
    private void handleUserChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            
            switch (choice) {
                case 1:
                    notesManager.addNote();
                    break;
                    
                case 2:
                    notesManager.displayAllNotes();
                    break;
                    
                case 3:
                    notesManager.searchNotes();
                    break;
                    
                case 4:
                    notesManager.displayNotesByCategory();
                    break;
                    
                case 5:
                    notesManager.editNote();
                    break;
                    
                case 6:
                    notesManager.deleteNote();
                    break;
                    
                case 7:
                    notesManager.exportNotes();
                    break;
                    
                case 8:
                    notesManager.showStatistics();
                    break;
                    
                case 9:
                    notesManager.backupOperations();
                    break;
                    
                case 10:
                    showHelp();
                    break;
                    
                case 0:
                    confirmExit();
                    break;
                    
                default:
                    System.out.println("✗ Invalid option! Please choose 0-10.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input! Please enter a number between 0-10.");
        }
        
        if (running) {
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    /**
     * Show help information
     */
    private void showHelp() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                    📚 HELP");
        System.out.println("=".repeat(50));
        System.out.println();
        System.out.println("🔹 ADD NOTE: Create a new note with title, category, and content");
        System.out.println("   • Title: Brief description of your note");
        System.out.println("   • Category: Organize notes (e.g., Work, Personal, Ideas)");
        System.out.println("   • Content: Type your note content, end with 'END' on new line");
        System.out.println();
        System.out.println("🔹 SEARCH: Find notes by searching in title, content, or category");
        System.out.println();
        System.out.println("🔹 CATEGORIES: View notes organized by their categories");
        System.out.println();
        System.out.println("🔹 EDIT: Modify existing notes (title, content, or category)");
        System.out.println();
        System.out.println("🔹 EXPORT: Save notes to a readable text file");
        System.out.println();
        System.out.println("🔹 BACKUP: Create/restore backups of your notes");
        System.out.println();
        System.out.println("📁 FILE STORAGE:");
        System.out.println("   • notes.txt: Main notes storage file");
        System.out.println("   • notes_backup.txt: Backup file");
        System.out.println("   • All changes are automatically saved!");
        System.out.println();
        System.out.println("💡 TIPS:");
        System.out.println("   • Use descriptive titles for easy searching");
        System.out.println("   • Organize with categories for better management");
        System.out.println("   • Regular backups help prevent data loss");
        System.out.println("   • Export feature creates human-readable copies");
    }
    
    /**
     * Confirm exit
     */
    private void confirmExit() {
        System.out.print("\nAre you sure you want to exit? (y/N): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (confirm.equals("y") || confirm.equals("yes")) {
            running = false;
        } else {
            System.out.println("Continuing...");
        }
    }
    
    /**
     * Display goodbye message
     */
    private void showGoodbyeMessage() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("Thank you for using Java Notes App! 📝");
        System.out.println("Your notes have been saved automatically.");
        System.out.println("Total notes managed: " + notesManager.getNotesCount());
        System.out.println("=".repeat(40));
        System.out.println("Goodbye! 👋");
    }
    
    /**
     * Main method - entry point of the application
     */
    public static void main(String[] args) {
        try {
            NotesApp app = new NotesApp();
            app.start();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
