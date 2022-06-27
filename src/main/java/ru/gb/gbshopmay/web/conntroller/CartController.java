package ru.gb.gbshopmay.web.conntroller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.gbshopmay.service.CartService;
import ru.gb.gbshopmay.web.model.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Artem Kropotov
 * created at 22.06.2022
 **/
@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String showCartPage(Model model, HttpSession httpSession) {
        Cart cart = cartService.getCurrentCart(httpSession);
        model.addAttribute("cart", cart);
        return "cart/cart-page";
    }

    @GetMapping("/add/{id}")
    public String addProductToCart(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        cartService.addToCart(httpServletRequest.getSession(), id);
        String referer = httpServletRequest.getHeader("referer");
        return "redirect:" + referer;
    }

    @GetMapping("/delete/{id}")
    public String deleteProductFromCart(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        cartService.removeFromCart(httpServletRequest.getSession(), id);
        String referer = httpServletRequest.getHeader("referer");
        return "redirect:" + referer;
    }

    @GetMapping("/reset")
    public String resetCart(HttpServletRequest httpServletRequest) {
        cartService.resetCart(httpServletRequest.getSession());
        String referer = httpServletRequest.getHeader("referer");
        return "redirect:" + referer;
    }


}
