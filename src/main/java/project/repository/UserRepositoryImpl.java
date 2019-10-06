package project.repository;

import org.springframework.stereotype.Repository;
import project.domain.user.User;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<Long,User> idToUser = new HashMap<>();

    @Override
    public User save(User user) {
        return idToUser.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(idToUser.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> user = Optional.empty();

        for (User usr: idToUser.values()) {
            if ( Objects.equals(usr.getEmail(), email) ) {
                user = Optional.of(usr);

                return user;
            }
        }

        return user;
    }

    @Override
    public List<User> findByName(String name) {
        final List<User> suitableUsers = new ArrayList<>();

        for (User user: idToUser.values()) {
            if ( Objects.equals(user.getName(), name) ) {
                suitableUsers.add(user);
            }
        }

        return suitableUsers;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(idToUser.values());
    }

    @Override
    public void update(User user) {
        idToUser.put(user.getId(), user);
    }

    @Override
    public Optional<User> deleteById(Long id) {
        return Optional.ofNullable(idToUser.remove(id));
    }

    @Override
    public Optional<User> deleteByEmail(String email) {
        for (User user: idToUser.values()) {
            if ( Objects.equals(user.getEmail(), email) )  {
                return Optional.of(idToUser.remove(user.getId()));
            }
        }

        return Optional.empty();
    }
}
