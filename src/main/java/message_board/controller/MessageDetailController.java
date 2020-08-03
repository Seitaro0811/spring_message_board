package message_board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import message_board.model.Message;
import message_board.service.MessageService;

@Controller
public class MessageDetailController {
	@Autowired
	MessageService service;
	
	@RequestMapping(value="/detail/{id}", method=RequestMethod.GET)
	public ModelAndView showMessage(@PathVariable("id") Integer id, ModelAndView mav) {
		Message message = service.findById(id);
		mav.setViewName("message_detail");
		mav.addObject("message", message);
		
		return mav;
	}
}
