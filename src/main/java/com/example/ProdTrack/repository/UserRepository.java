package com.example.ProdTrack.repository;

import com.example.ProdTrack.model.Task;
import com.example.ProdTrack.model.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    List<Users> findAllByRole(String role);
}
