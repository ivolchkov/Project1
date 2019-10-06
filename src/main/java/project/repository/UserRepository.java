package project.repository;


import java.util.List;
import java.util.Optional;

public interface UserRepository<T> extends Repository<T> {
    Optional<T> findByEmail(String email);
    List<T> findByName(String name);

    Optional<T> deleteByEmail(String email);
}
