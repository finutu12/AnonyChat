package org.example.demo.repossitory;

import org.example.demo.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByUserIdAndChatSessionId(Integer UserId, Integer ChatSessionId);

    List<Message> findAllByChatSessionId(Integer chatSessionID);
}