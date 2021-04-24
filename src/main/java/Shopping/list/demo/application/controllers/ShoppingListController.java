package Shopping.list.demo.application.controllers;

import Shopping.list.demo.application.persist.ShoppingItem;
import Shopping.list.demo.application.persist.ShoppingItemRepository;
import Shopping.list.demo.application.persist.User;
import Shopping.list.demo.application.persist.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ShoppingListController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingListController.class);

    private final ShoppingItemRepository repository;

    private final UserRepository userRepository;

    @Autowired
    public ShoppingListController(ShoppingItemRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String indexPage(Model model, Principal principal) {
        LOGGER.info("User name: {}", principal.getName());

        model.addAttribute("items", repository.findByUserUsername(principal.getName()));
        model.addAttribute("item", new ShoppingItem());
        return "index";
    }

    @PostMapping
    public String newShoppingItem(ShoppingItem shoppingItem, Principal principal) {
        LOGGER.info("User name: {}", principal.getName());

        User user = userRepository.findByUsername(principal.getName()).get();
        shoppingItem.setUser(user);
        repository.save(shoppingItem);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteShoppingItem(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
