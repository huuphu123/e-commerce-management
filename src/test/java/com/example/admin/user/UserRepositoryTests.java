package com.example.admin.user;

import com.example.admin.user.repository.UserRepository;
import com.example.admin.entity.Role;
import com.example.admin.entity.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void createNewUserWithOneRole() {
        Role roleAdmin = entityManager.find(Role.class, 1);
        User user1 = new User("phamhphu22@gmail.com", "Phu", "Pham", "123456");
        user1.addRole(roleAdmin);

        User savedUser = userRepository.save(user1);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateNewUserWithTwoRoles() {
        User user2 = new User("b@hcmut.com", "a", "Pham", "12345");
        Role role1 = entityManager.find(Role.class, 2);
        Role role2 = entityManager.find(Role.class, 3);

        user2.addRole(role1);
        user2.addRole(role2);

        User savedUser = userRepository.save(user2);

        assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAllUsers() {
        List<User> listUsers = userRepository.findAll();
        listUsers.forEach(user -> System.out.println(user));
    }

    @Test
    public void testGetUserById() {
        User userNam = userRepository.findById(1).get();
        System.out.println(userNam);
        assertThat(userNam).isNotNull();
    }

    @Test
    public void testUpdateUserDetails() {
        User userNam = userRepository.findById(1).get();
        userNam.setEnabled(true);
        userNam.setEmail("namjavaprogrammer@gmail.com");

        userRepository.save(userNam);
    }

    @Test
    public void testUpdateUserRoles() {
        User userRavi = userRepository.findById(2).get();
        Role roleEditor = entityManager.find(Role.class, 3);
        Role roleSalesperson = entityManager.find(Role.class, 2);

        userRavi.getRoles().remove(roleEditor);
        userRavi.addRole(roleSalesperson);

        userRepository.save(userRavi);
    }

    @Test
    public void testDeleteUser() {
        Integer userId = 8;
        userRepository.deleteById(userId);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "a@gmail.com";
        User user = userRepository.getUserByEmail(email);

        System.out.println(user);
        assertThat(user).isNotNull();
    }
}
