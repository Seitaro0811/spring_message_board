package message_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import message_board.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
