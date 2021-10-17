package dk.kea.adventureproject.controllers;

import dk.kea.adventureproject.models.Product;
import dk.kea.adventureproject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Julius Panduro
 */
@Controller
public class ProductController {
    //Template For ProductController
    @Autowired
    ProductService productService;

    //Frederik
    @GetMapping("/updateProduct/{productID}")
    public String updateProduct(@PathVariable("productID") int productID, Model model) {
        model.addAttribute("product", productService.readProductById(productID));
        return "updateProduct.html";
    }
    /**
     * @author Joachim & Christian
     */
    @GetMapping("/createProduct")
    public String createProduct() {
        return "createProduct.html";
    }
    /**
     * @author Joachim & Christian
     */
    @PostMapping("/createProduct")
    public String createProduct(@ModelAttribute Product product) {
        productService.createProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String fetchAll(Model model){
        List<Product> productList = productService.fetchAll();
        model.addAttribute("products", productList);
        return "products.html";
    }

    //Frederik
    @PostMapping("updateProduct")
    public String updateProduct(@ModelAttribute Product product) {
        productService.updateProduct(product.getProductID(), product);
        return "redirect:/products";
    }

    //Alexander
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
