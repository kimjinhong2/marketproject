package com.project.market.web.cartList.controller;

import com.project.market.global.error.exception.DtoEmptyException;
import com.project.market.global.error.exception.ErrorCode;
import com.project.market.web.cartList.dto.CartOrderDto;
import com.project.market.web.cartList.service.CartListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartListRestController {
    private final CartListService cartListService;

    @PatchMapping("/orderItem/{id}")
    public ResponseEntity change(@PathVariable Long id,
                                 @RequestParam int count) {

        cartListService.changeItemCount(id, count);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/cart/orders")
    public ResponseEntity orderCartItem(@RequestBody CartOrderDto cartOrderDto, Principal principal) {

        List<CartOrderDto> cartOrderDtoList = cartOrderDto.getCartOrderDtoList();

        if (cartOrderDtoList == null || cartOrderDtoList.size() == 0) {
            throw new DtoEmptyException(ErrorCode.CART_ITEM_NOT_SELECTED);
        }

        cartListService.orderCartItems(cartOrderDtoList, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/orderItem/{id}")
    public ResponseEntity cancelCartItem(@PathVariable Long id) {
        cartListService.cancelOrderItem(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}