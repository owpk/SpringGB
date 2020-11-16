package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.entities.Acc;
import com.geekbrains.geek.market.repositories.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileService {
    private ProfileRepository profileRepository;

    public Optional<Acc> findByUserName(String username) {
        return profileRepository.findByUserName(username);
    }

    public Optional<Acc> findById(Long id) {
        return profileRepository.findById(id);
    }

    public Acc saveOrUpdate(Acc a) {
        return profileRepository.save(a);
    }
}
