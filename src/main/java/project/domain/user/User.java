package project.domain.user;

import project.domain.sweet.AbstractSweet;

import java.util.Objects;

public class User {
    private final Long id;
    private final String name;
    private final String surname;
    private final String email;
    private String password;

    private User(UserBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.password = builder.password;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("User № ").append(id).append(", ").
                append("name: ").append(name).append(", ").
                append("surname: ").append(surname).append(", ").
                append("email: ").append(email);

        return stringBuilder.toString();
    }

    public static class UserBuilder {
        private static Long counter = 0L;

        private Long id = ++counter;
        private String name;
        private String surname;
        private String email;
        private String password;

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public static class UserPasswordBuilder {
        public void setPassword(User user, String password) {
            user.password = password;
        }
    }
}
