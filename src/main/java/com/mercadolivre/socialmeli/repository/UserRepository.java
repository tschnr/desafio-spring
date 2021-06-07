package com.mercadolivre.socialmeli.repository;

import com.mercadolivre.socialmeli.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByUserIdAndSeller(Long userId, Boolean seller);

}
