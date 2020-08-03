package message_board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import message_board.model.EditMessageForm;
import message_board.model.Message;
import message_board.service.MessageService;

@Controller
@RequestMapping
public class MessageEditController {
	@Autowired
	MessageService service;
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView showEditPage(@PathVariable("id") Integer id, ModelAndView mav) {
		Message message = service.findById(id);
		mav.setViewName("edit_message");
		mav.addObject("message", message);
		
		return mav;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String sendEdit(@ModelAttribute EditMessageForm form) {
		service.editMessage(form);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String deleteMessage(@RequestParam("delete") Integer id) {
		service.deleteMessage(id);
		
		return "redirect:/";
	}
}
