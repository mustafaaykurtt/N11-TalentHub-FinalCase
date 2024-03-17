package com.n11.userservice.repository;

import com.n11.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created By Mustafa Aykurt
 * Date:28.02.2024
 * Time:23:09
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findByEmail(String email);
}
