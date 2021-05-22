package com.vector.Repository;

import com.vector.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserRepository extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

    public User findByEmail(String email);
}
