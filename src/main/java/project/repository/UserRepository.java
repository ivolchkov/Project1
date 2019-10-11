package project.repository;


import project.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CRUDRepository<User> {
    Optional<User> findByEmail(String email);
    List<User> findByName(String name);

    Optional<User> deleteByEmail(String email);
}
