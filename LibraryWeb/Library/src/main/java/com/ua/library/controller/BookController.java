package com.ua.library.controller;

import com.ua.library.domain.Author;
import com.ua.library.domain.Book;
import com.ua.library.domain.Comment;
import com.ua.library.domain.Genre;
import com.ua.library.service.BookService;
import com.ua.library.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;
    private final CommentService commentService;

    public BookController(BookService bookService, CommentService commentService) {
        this.bookService = bookService;
        this.commentService = commentService;
    }

    @GetMapping("/book")
    String booksPage(Model model){
        List<Book> books = bookService.getAll();
        model.addAttribute("books",books);
        return "listBook";
    }

    @GetMapping("/book/add")
    String addBookPage(Model model){
        model.addAttribute("book",new Book(new Author(),new Genre()));
        return "createBook";
    }

    @PostMapping("/book/add")
    String addBook(Book book){
        bookService.addNew(book);
        return "redirect:/book";
    }

    @GetMapping("/book/{id}")
    String bookPage(@PathVariable long id, Model model){
        Book book = bookService.getById(id);
        model.addAttribute("book",book);
        model.addAttribute("comment",new Comment());
        return "book";
    }

    @PostMapping("/book/{id}")
    String addComment(@PathVariable long id, Comment comment){
        comment.setBook(bookService.getById(id));
        commentService.addNew(comment);
        return "redirect:/book/"+id+"/comment";
    }

    @GetMapping("/book/{id}/comment")
    String bookCommentsPage(@PathVariable long id, Model model){
        Book book = bookService.getById(id);
        List<Comment> comments = commentService.getAllByBook(book);
        model.addAttribute("comments",comments);
        return "comments";
    }

    @GetMapping("/book/{id}/edit")
    String editBookPage(Model model, @PathVariable long id){
        Book book = bookService.getById(id);
        model.addAttribute("book",book);
        return "editBook";
    }

    @PostMapping("/book/{id}/edit")
    String editBook(Book book){
        bookService.update(book);
        return "redirect:/book";
    }

    @GetMapping("/book/{id}/delete")
    String deleteBook(@PathVariable long id){
        Book book = bookService.getById(id);
        bookService.deleteBook(book);
        return "redirect:/book";
    }

}
