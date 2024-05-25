package com.picpayproject.repository;

import com.picpayproject.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.document = ?1 and u.email = ?2")
    Optional<User> findUserByDocumentAndEmail(String document, String email);
    Optional<User> findUserById(Long id);
}
