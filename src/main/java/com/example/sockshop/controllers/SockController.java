package com.example.sockshop.controllers;

import com.example.sockshop.dto.SocksRequestDto;
import com.example.sockshop.exception.InSufficientSockQuantityException;
import com.example.sockshop.exception.InvalidSockException;
import com.example.sockshop.model.Size;
import com.example.sockshop.service.SockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/sock")
public class SockController {

    private final SockService sockService;

    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @ExceptionHandler(InvalidSockException.class)
    public ResponseEntity<String> handleInvalidException(InvalidSockException invalidSockException){
        return ResponseEntity.badRequest().body(invalidSockException.getMessage());
    }

    @ExceptionHandler(InSufficientSockQuantityException.class)
    public ResponseEntity<String> handleQuantityException(InSufficientSockQuantityException inSufficientSockQuantityException){
        return ResponseEntity.badRequest().body(inSufficientSockQuantityException.getMessage());
    }

    @PostMapping
    public void addSocks(@RequestBody SocksRequestDto socksRequestDto) {
        sockService.addSock(socksRequestDto);
    }

    @PutMapping
    public void issueSock(@RequestBody SocksRequestDto socksRequestDto) {
        sockService.issueSock(socksRequestDto);
    }

    @GetMapping
    public int getSockCount(@RequestParam(required = false, name = "color") Color color,
                            @RequestParam(required = false, name = "size")Size size,
                            @RequestParam(required = false, name = "cottonMin")Integer cottonMin,
                            @RequestParam(required = false, name = "cottonMax")Integer cottonMax){
        return sockService.getSockQuantity(color, size, cottonMin, cottonMax);
    }

    @DeleteMapping
    public void removeSocks(@RequestBody SocksRequestDto socksRequestDto){
        sockService.removeSocks(socksRequestDto);
    }

}
