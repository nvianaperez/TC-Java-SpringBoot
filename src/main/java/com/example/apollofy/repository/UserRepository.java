package com.example.apollofy.repository;

import com.example.apollofy.domain.User;
import com.example.apollofy.service.dto.UserDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("""
        SELECT new com.example.apollofy.service.dto.UserDTO(u.firstName,u.lastName,u.email) FROM User u 
        WHERE UPPER(u.firstName) LIKE UPPER(CONCAT('%', :q, '%')) OR 
        UPPER(u.lastName) LIKE UPPER(CONCAT('%', :q, '%')) OR 
        UPPER(u.email) LIKE UPPER(CONCAT('%', :q, '%'))
    """)

    List<UserDTO> searchUsers (@Param("q") String q, Pageable pageable);
}
