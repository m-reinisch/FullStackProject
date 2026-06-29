package de.mreinisch.backend.service;

import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class IdService {
    public String generateId(){
        String uuid= UUID.randomUUID().toString();

        System.out.println(uuid);
        System.out.println(uuid);
        System.out.println(uuid);
        return uuid;
    }
}
