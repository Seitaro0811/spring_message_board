package message_board.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import message_board.model.EditMessageForm;
import message_board.model.Message;
import message_board.model.MessageForm;
import message_board.repository.MessageRepository;

@Service
public class MessageService {
	@Autowired
	MessageRepository repository;
	
	public List<Message> showAllMessage() {
		List<Message> messages = repository.findAll();
		return messages;
	}
	
	public void newMessage(MessageForm form) {
		Message newMessage = new Message();
		newMessage.setTitle(form.getTitle());
		newMessage.setName(form.getName());
		newMessage.setContent(form.getContent());
		Timestamp current_time = new Timestamp(System.currentTimeMillis());
		newMessage.setCreated_at(current_time);
		newMessage.setUpdated_at(current_time);
		repository.saveAndFlush(newMessage);
	}
	
	public Message findById(Integer id) {
		return repository.getOne(id);
	}
	
	public void editMessage(EditMessageForm form) {
		Message message = repository.getOne(form.getId());
		message.setTitle(form.getTitle());
		message.setName(form.getName());
		message.setContent(form.getContent());
		Timestamp current_time = new Timestamp(System.currentTimeMillis());
		message.setUpdated_at(current_time);
		repository.saveAndFlush(message);
	}
	
	public void deleteMessage(Integer id) {
		Message message = repository.getOne(id);
		repository.delete(message);
		repository.flush();
	}
}
