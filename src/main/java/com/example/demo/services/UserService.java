package com.example.demo.services;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.EmailMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired //nadpisuje konstruktor
    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public Long addUser(User user) throws Exception {

        Optional<User> optionalUser = getUserByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            throw new Exception("Taki użytkownik już istnieje");
        }
        user = repository.save(user);
        return user.getId();

    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserByEmail(final String email) {
        return repository.getUserByEmail(email);
    }

    public void deleteUser(final Long id){
        repository.deleteById(id);
    }

    public void updateUser(final Long id, User user){
        user.setId(id);
        repository.save(user);
    }

    public Optional<User> getUserById(final Long id){
        return repository.getUserById(id);
    }

    public void sendEmail(Long id){
        Optional<User> optionalUser = getUserById(id);
        User user = optionalUser.get();
        EmailMessageSender.sendEmail(user.getEmail(), user.getName());

    }
}
