package com.example.tenderhak.handler;

import com.example.tenderhak.model.LoginModel;
import com.example.tenderhak.model.RoleModel;
import com.example.tenderhak.repository.UserCred;
import com.example.tenderhak.repository.UserCredRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoginHandler {

    private final UserCredRepository userCredRepository;

    public  ResponseEntity handle(LoginModel request) {
        UserCred cred = Optional.ofNullable(userCredRepository.findByLogin(request.getLogin())).orElse(null);
        if(cred == null) {
            return new ResponseEntity<>("Пользователь не найден", HttpStatus.NOT_FOUND);
        }
        if(!cred.getPassword().equals(request.getPassword())) {
            return new ResponseEntity<>("Неверный пароль", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(RoleModel.builder()
                .role(cred.getRole())
                .name(cred.getName())
                .surname(cred.getSurname())
                .build(), HttpStatus.OK);

    }
}
