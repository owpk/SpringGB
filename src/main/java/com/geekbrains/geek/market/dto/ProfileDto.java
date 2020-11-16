package com.geekbrains.geek.market.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.geekbrains.geek.market.entities.Acc;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect
public class ProfileDto {
    private Long id;
    private String username;
    private String hobby;
    private String confirmationPassword;

    public ProfileDto(Acc acc) {
        id = acc.getId();
        username = acc.getUser().getUsername();
        hobby = acc.getHobby();
    }
}
