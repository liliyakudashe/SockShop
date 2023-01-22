package com.example.sockshop.service;

import com.example.sockshop.dto.SocksRequestDto;
import com.example.sockshop.exception.InSufficientSockQuantityException;
import com.example.sockshop.exception.InvalidSockException;
import com.example.sockshop.model.Size;
import com.example.sockshop.model.Sock;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class SockService {

    private final Map<Sock, Integer> socks = new HashMap<>();

    public void addSock(SocksRequestDto socksRequestDto){
        validateRequest(socksRequestDto);
        Sock sock = mapToSock(socksRequestDto);
        if (socks.containsKey(sock)){
            socks.put(sock, socks.get(sock) + socksRequestDto.getQuantity());
        }
        else {
            socks.put(sock, socksRequestDto.getQuantity());
        }
    }

    public void issueSock(SocksRequestDto socksRequestDto){
        decreaseSockQuantity(socksRequestDto);
    }

    public void removeSocks(SocksRequestDto socksRequestDto) {
        decreaseSockQuantity(socksRequestDto);
    }

    private void decreaseSockQuantity(SocksRequestDto socksRequestDto){
        validateRequest(socksRequestDto);
        Sock sock = mapToSock(socksRequestDto);
        int sockQuantity = socks.getOrDefault(sock, 0);
        if (sockQuantity >= socksRequestDto.getQuantity()){
            socks.put(sock, sockQuantity - socksRequestDto.getQuantity());
        }
        else {
            throw new InSufficientSockQuantityException("Носков больше нет");
        }
    }

    public int getSockQuantity(Color color, Size size, Integer cottonMin, Integer cottonMax){
        int total = 0;
        for (Map.Entry<Sock, Integer> entry : socks.entrySet()){
            if (color != null && !entry.getKey().getColor().equals(color)){
                continue;
            }
            if (size != null && !entry.getKey().getSize().equals(size)){
                continue;
            }
            if (cottonMin != null && entry.getKey().getCottonRatio() < cottonMin){
                continue;
            }
            if (cottonMax != null && entry.getKey().getCottonRatio() > cottonMax){
                continue;
            }
            total += entry.getValue();
        }
        return total;
    }

    private void validateRequest(SocksRequestDto socksRequestDto) {
        if (socksRequestDto.getColor() == null || socksRequestDto.getSize() == null){
            throw new InvalidSockException("Все поля должны быть заполнены!");
        }
        if (socksRequestDto.getQuantity() < 0){
            throw new InvalidSockException("Колличество должго быть больше 0");
        }
        if (socksRequestDto.getCottonRatio() <= 0 || socksRequestDto.getCottonRatio() > 100){
            throw new InvalidSockException("Процент хлопка должно быть между 0 и 100");
        }
    }

    private Sock mapToSock(SocksRequestDto socksRequestDto){
        return new Sock(socksRequestDto.getColor(), socksRequestDto.getSize(),
                socksRequestDto.getCottonRatio());
    }


}
