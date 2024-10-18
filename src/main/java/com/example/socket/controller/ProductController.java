package com.example.socket.controller;

import com.example.socket.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;  // Để gửi tin nhắn qua WebSocket

    // API trả về danh sách sản phẩm
    @GetMapping
    public List<Product> getProducts() throws InterruptedException {
        List<Product> products = Arrays.asList(
                new Product(1L, "Product A", 100.0),
                new Product(2L, "Product B", 200.0),
                new Product(3L, "Product C", 300.0)
        );

        Thread.sleep(1000);
        // Gửi số lượng sản phẩm qua WebSocket
        messagingTemplate.convertAndSend("/topic/product-count", products);

        return products;
    }
}