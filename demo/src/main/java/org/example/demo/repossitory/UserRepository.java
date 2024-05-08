package org.example.demo.repossitory;

import org.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByIdLessThan(Integer nr);
    List<User> findAllByChatSessionId(Integer id);
}

