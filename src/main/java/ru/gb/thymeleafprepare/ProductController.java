package ru.gb.thymeleafprepare;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.thymeleafprepare.entity.Product;
import ru.gb.thymeleafprepare.service.ProductService;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public String getProductList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product-list";
    }

    @GetMapping("/cart")
    public String getCartList(Model model) {
        model.addAttribute("carts", productService.findCartAll());
        return "cart-list";
    }

    @GetMapping
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        Product product;
        if (id != null) {
            product = productService.findById(id);
        } else {
            product = new Product();
        }
        model.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping
    public String saveProduct(Product product) {
        product.setManufactureDate(LocalDate.now());
        productService.save(product);
        return "redirect:/product/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
        return "redirect:/product/all";
    }

    @GetMapping("/deleteCart/{id}")
    public String deleteByIdCart(@PathVariable(name = "id") Long id) {
        productService.deleteByIdCart(id);
        return "redirect:/product/cart";
    }

        @GetMapping("/add")
    public String cartSave(@RequestParam(name = "id") Long id) {
            String flag = "save";
        productService.cartSave(flag, id);
        return "redirect:/product/all";
    }

//    @GetMapping("/delete")
//    public String deleteById(@RequestParam(name = "id") Long id) {
//        productService.deleteById(id);
//        return "redirect:/product/all";
//    }

}
