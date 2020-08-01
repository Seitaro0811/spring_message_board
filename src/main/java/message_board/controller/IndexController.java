package message_board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import message_board.model.MessageForm;
import message_board.service.MessageService;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	MessageService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("messages", service.showAllMessage());
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submitMessage(@ModelAttribute MessageForm form, ModelAndView mav) {
		service.newMessage(form);
		mav.setViewName("index");
		mav.addObject("messages", service.showAllMessage());
		
		return mav;
	}
}
