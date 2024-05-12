package org.example.demo.repossitory;

import org.example.demo.model.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatSessionRepository extends JpaRepository<ChatSession, Integer> {

    @Query("SELECT cs FROM ChatSession cs WHERE SIZE(cs.users) = 1")
    List<ChatSession> findChatSessionsWithSingleUser();

    @Query("SELECT s FROM Session s WHERE s.users IS EMPTY")
    List<ChatSession> findEmptySessions();
}