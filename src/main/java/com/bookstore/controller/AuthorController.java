package com.bookstore.controller;

import com.bookstore.entity.Author;
import com.bookstore.exception.ResourceNotFoundException;
import com.bookstore.repository.AuthorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@Tag(name = "Author Management", description = "APIs for managing authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping
    @Operation(summary = "Create a new author", description = "Creates a new author in the system")
    public ResponseEntity<Author> createAuthor(@Valid @RequestBody Author author) {
        if (authorRepository.existsByEmail(author.getEmail())) {
            throw new IllegalArgumentException("Author with email " + author.getEmail() + " already exists");
        }
        Author savedAuthor = authorRepository.save(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all authors", description = "Retrieves a list of all authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get author by ID", description = "Retrieves a specific author by their ID")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        return ResponseEntity.ok(author);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update author", description = "Updates an existing author's information")
    public ResponseEntity<Author> updateAuthor(
            @PathVariable Long id,
            @Valid @RequestBody Author authorDetails) {

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));

        // Check if email is being changed and if new email already exists
        if (!author.getEmail().equals(authorDetails.getEmail()) &&
                authorRepository.existsByEmail(authorDetails.getEmail())) {
            throw new IllegalArgumentException("Author with email " + authorDetails.getEmail() + " already exists");
        }

        author.setName(authorDetails.getName());
        author.setEmail(authorDetails.getEmail());

        Author updatedAuthor = authorRepository.save(author);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete author", description = "Deletes an author from the system")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));

        authorRepository.delete(author);
        return ResponseEntity.noContent().build();
    }
}