package com.example.demo.dao;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User findOne(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void save(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }


    @Override
    @Transactional
    public void update(Long id, User user) {
        entityManager.merge(user);
    }

    @Override
    public Role findRoleByName(String roleName) {
        List<Role> roles = entityManager.createQuery("select r from Role r where r.name = :roleName", Role.class)
                .setParameter("roleName", roleName)
                .getResultList();
        return roles.isEmpty() ? null : roles.get(0);
    }

    @Override
    public User findByUsername(String username) {
        List<User> users = entityManager.createQuery("select u from User u where u.name = :username", User.class)
                .setParameter("username", username)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    @Transactional
    public void saveUserWithRoles(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findByEmail(String email) {
        List<User> users = entityManager.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }
}
