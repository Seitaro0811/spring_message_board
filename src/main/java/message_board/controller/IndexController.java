package message_board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
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
		MessageForm form = new MessageForm();
		mav.addObject("form", form);
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submitMessage(@Validated @ModelAttribute MessageForm form, BindingResult bindingResult
			, ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("messages", service.showAllMessage());
		if(bindingResult.hasErrors()) {
			mav.addObject("form", form);
			List<String> error_message = new ArrayList<String>();
			for(ObjectError error : bindingResult.getAllErrors()) {
				error_message.add(error.getDefaultMessage());
			}
			mav.addObject("error", error_message);
			return mav;
		}
		MessageForm new_form = new MessageForm();
		mav.addObject("form", new_form);
		service.newMessage(form);
		
		return mav;
	}
}
