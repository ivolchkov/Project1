package project.service;

import project.domain.user.User;

public interface UserService {
    User register(User user);
    User login(String email, String password);
}
