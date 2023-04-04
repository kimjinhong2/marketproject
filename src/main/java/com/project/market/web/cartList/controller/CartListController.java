package com.project.market.web.cartList.controller;

import com.project.market.web.cartList.dto.CartListItemDto;
import com.project.market.web.cartList.service.CartListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartListController {
    private final CartListService cartListService;

    @GetMapping("/cart")
    public String cart(Model model, Principal principal) {

        List<CartListItemDto> cartItems = cartListService.getCartList(principal);

        model.addAttribute("cartItems", cartItems);

        return "cart/cartlist";
    }
}