package org.example.demo.repossitory;

import org.example.demo.model.ChatSession;
import org.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatSessionRepository extends JpaRepository<ChatSession, Integer> {

}