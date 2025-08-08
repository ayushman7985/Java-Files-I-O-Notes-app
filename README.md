# 📝 Java File I/O Notes App

A comprehensive console-based notes management application built in Java with file persistence using I/O operations.

## 🌟 Features

### Core Functionality
- **Add Notes**: Create notes with title, category, and multi-line content
- **View Notes**: Display all notes or filter by category
- **Search**: Find notes by searching in title, content, or category
- **Edit Notes**: Modify existing notes (title, content, category)
- **Delete Notes**: Remove unwanted notes with confirmation
- **Auto-Save**: All changes automatically saved to file

### File I/O Operations
- **Persistent Storage**: Notes saved to `notes.txt` file
- **Backup System**: Create and restore backups (`notes_backup.txt`)
- **Export Function**: Export notes to readable text format
- **File Statistics**: View file information and storage details
- **Error Handling**: Robust file operation error management

### Advanced Features
- **Category Organization**: Group and view notes by categories
- **Statistics Dashboard**: View note counts, word counts, character counts
- **Search Functionality**: Case-insensitive search across all note fields
- **Timestamp Tracking**: Creation and modification timestamps
- **Data Validation**: Input validation and error handling

## 🏗️ Architecture

### Classes Overview

#### `Note.java`
- Represents individual notes with metadata
- Handles serialization to/from file format
- Includes search matching functionality
- Tracks creation and modification timestamps

#### `NotesFileManager.java`
- Handles all file I/O operations
- Provides backup and restore functionality
- Manages file statistics and export operations
- Implements error handling for file operations

#### `NotesManager.java`
- Core business logic for note management
- Handles CRUD operations on notes
- Manages note collections and filtering
- Provides search and categorization features

#### `NotesApp.java`
- Main application entry point
- Console-based user interface
- Menu system and user interaction
- Application lifecycle management

## 🚀 How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line access

### Compilation
```bash
javac *.java
```

### Execution
```bash
java NotesApp
```

## 📋 Usage Guide

### Main Menu Options
1. **Add New Note**: Create a new note with title, category, and content
2. **View All Notes**: Display all stored notes with details
3. **Search Notes**: Find notes using keywords
4. **View by Category**: Organize and view notes by categories
5. **Edit Note**: Modify existing note properties
6. **Delete Note**: Remove notes with confirmation
7. **Export Notes**: Save notes to external file
8. **Show Statistics**: View detailed statistics
9. **Backup Operations**: Create/restore backups
10. **Help**: Display detailed help information

### Adding Notes
1. Enter a descriptive title
2. Specify a category (optional, defaults to "General")
3. Type your note content
4. Type `END` on a new line to finish

### File Structure
- `notes.txt`: Main storage file (auto-created)
- `notes_backup.txt`: Backup file
- `exported_notes.txt`: Export files (custom names)

## 🔧 Technical Details

### File Format
Notes are stored in a pipe-delimited format:
```
Title|Content|Category|CreatedDateTime|ModifiedDateTime
```

### Error Handling
- File I/O exceptions are caught and handled gracefully
- Invalid input validation with user feedback
- Backup/restore operations with confirmation prompts
- Robust parsing of stored note data

### Features Demonstrated

#### File I/O Operations
- **FileWriter/FileReader**: Basic file writing and reading
- **BufferedReader**: Efficient line-by-line reading
- **PrintWriter**: Formatted text output
- **FileInputStream/FileOutputStream**: Binary file operations for backup

#### Java Concepts
- **Object-Oriented Design**: Proper class separation and encapsulation
- **Exception Handling**: Try-catch blocks for file operations
- **Collections**: ArrayList for note storage and management
- **Streams API**: Filtering and processing note collections
- **Date/Time API**: LocalDateTime for timestamps
- **String Manipulation**: Text processing and formatting

## 🎯 Learning Objectives

This application demonstrates:
- File I/O operations in Java
- Object-oriented programming principles
- Exception handling best practices
- User interface design for console applications
- Data persistence and management
- Backup and recovery systems

## 🔮 Potential Enhancements

- **GUI Interface**: Swing or JavaFX implementation
- **Database Integration**: SQLite or H2 database storage
- **Encryption**: Secure note storage
- **Cloud Sync**: Online backup capabilities
- **Rich Text**: Markdown or HTML support
- **Attachments**: File attachment support
- **Multi-user**: User authentication and profiles

## 📝 Sample Usage

```
Welcome! Your notes are automatically saved to file.
Current notes count: 0

========================================
           📋 MAIN MENU
========================================
1.  ➕ Add new note
2.  📖 View all notes
3.  🔍 Search notes
...

Choose an option (0-10): 1

=== ADD NEW NOTE ===
Enter note title: Meeting Notes
Enter category: Work
Enter note content (type 'END' on a new line to finish):
Discussed project timeline
- Phase 1: Design (2 weeks)
- Phase 2: Development (4 weeks)
- Phase 3: Testing (1 week)
END

✓ Notes saved successfully to notes.txt
✓ Note added successfully!
```

## 🤝 Contributing

This is a learning project demonstrating Java File I/O concepts. Feel free to:
- Add new features
- Improve error handling
- Enhance the user interface
- Optimize file operations
- Add unit tests

---

**Built with ❤️ using Java File I/O operations**
