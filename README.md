# Library Management System

This project is designed to serve the needs of three main types of library users: Managers, Librarians, and Readers.

## Technology Stack

- **Java 8**: As the primary programming language.
- **JavaFX**: For creating a graphical user interface.
- **FXML**: To define the user interface layout.
- **SceneBuilder**: To facilitate working with FXML.

## Features

### Reader

- Access to news and updates
- Borrowing and managing books
- Purchasing e-books
- Paying fees

### Librarian

- Managing fees
- Updating the status of books
- Managing new users
- Managing the book database

### Manager

- Managing employees
- Managing employee schedules
- Placing orders

## Design Patterns Used

1. **Factory Method Pattern**: Used to create different types of books based on parameters.
2. **Facade Pattern**: Used to restrict user access to certain methods based on their roles.
3. **Memento Pattern**: Allows storing and restoring the state of objects, such as undoing the deletion of a book.

## UI
The user interface was primarily developed to demonstrate the API's functionality rather than to provide a polished and user-friendly experience. There is one of the views below - used to borrowing books.

![image](https://github.com/user-attachments/assets/80fce31e-df4a-4d17-8c8b-c06bbe163890)
