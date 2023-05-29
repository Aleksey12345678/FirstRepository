package com.mycompany.project1.controller.products;


import com.mycompany.project1.controller.ControllerUtils;
import com.mycompany.project1.domain.Message;
import com.mycompany.project1.domain.User;
import com.mycompany.project1.domain.dto.products.BookDTO;
import com.mycompany.project1.domain.dto.products.EBookDTO;
import com.mycompany.project1.domain.products.Book;
import com.mycompany.project1.domain.products.EBook;
import com.mycompany.project1.mapper.EBookMapper;
import com.mycompany.project1.repository.MessageRepository;
import com.mycompany.project1.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller

public class EBookController { private ProductService<Book, BookDTO> productService;
    private MessageRepository messageRepository;

    public EBookController(ProductService<Book, BookDTO> productService, MessageRepository messageRepository) {
        this.productService = productService;
        this.messageRepository = messageRepository;
    }

    EBookMapper ebookMapper = new EBookMapper();
    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping("/ebook/bucket/{id}/{redirectPath}")
    public String addBucket(@PathVariable Long id, @PathVariable Integer redirectPath, Principal principal) {
        if (principal == null) {
            return "redirect:/books";
        }
        productService.addToUserBucket(id, principal.getName());
        if (redirectPath == 2) {
            String redirect = "redirect:/book/" + id.toString();
            return redirect;
        }
        return "redirect:/ebooks";
    }

    @GetMapping("/ebook/{id}")
    public String getProductById(@PathVariable Long id, @RequestParam(required = false, defaultValue = "") String filter, Model model) {
        EBookDTO book = ebookMapper.fromBook(productService.getProductById(id));
        model.addAttribute("book", book);

        Iterable<Message> messages = messageRepository.findAll();
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepository.findByTextContains(filter);
        } else {
            messages = messageRepository.findAllByProductId(id);

        }
        String redirectAddress;
        redirectAddress = "/book/" + id;
        model.addAttribute("redirectaddress", redirectAddress);
        model.addAttribute("listmessage", messages);
        model.addAttribute("filter", filter);
        return "products/ebook";
    }

    @GetMapping("/ebooks")
    public String bookList(Model model) {
        List<EBookDTO> list = ebookMapper.fromBookList(productService.getAll("EBook"));
        model.addAttribute("books", list);
        return "products/ebooks";
    }

    @GetMapping("/ebook/new")
    public String formAddNewProduct() {

        return "products/ebookAddForm";
    }


    @GetMapping("/ebook/{id}/edit")
    public String editProductById(@PathVariable Long id, Model model) {
        EBookDTO book = ebookMapper.fromBook(productService.getProductById(id));
        model.addAttribute("edit", 1);
        model.addAttribute("book", book);
        return "products/ebookAddForm";
    }

    @PostMapping("/ebook/{id}/edit")
    public String updateBookById(@PathVariable Long id, @ModelAttribute EBookDTO book,
                                 @RequestParam("productfile") MultipartFile productfile) throws IOException {
        String fileName = productService.getResultFilename(productfile, uploadPath);
       EBook bookDb = (EBook) productService.getProductById(id);
        ebookMapper.setFieldValuesFromDto(book, fileName, bookDb);
        productService.updateProduct(bookDb);
        return "redirect:/ebooks";
    }




    @PostMapping("/ebook/new")
    public String addNewProduct(@ModelAttribute EBookDTO book, @RequestParam("productfile") MultipartFile productfile) throws IOException {
        String fileName = productService.getResultFilename(productfile, uploadPath);
        EBook bookDb=new EBook();
        ebookMapper.setFieldValuesFromDto(book, fileName, bookDb);

        productService.saveNewProduct(bookDb);

        return "redirect:/ebooks";
    }

    @PostMapping("/ebook/{id}")
    public String add(
            @PathVariable Long id,
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        } else {
            productService.saveMessage(message, file, uploadPath, user, id);
        }
        final Iterable<Message> messages = productService.getMessagesByProductId(id);
        model.addAttribute("messages", messages);
        model.addAttribute("filter", "");

        return "redirect:/ebook/{id}";
    }

}