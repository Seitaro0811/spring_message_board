package message_board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import message_board.model.EditMessageForm;
import message_board.model.Message;
import message_board.model.MessageForm;
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
	public ModelAndView sendEdit(@Validated @ModelAttribute EditMessageForm form, BindingResult bindingResult, ModelAndView mav) {
		if(bindingResult.hasErrors()) {
			List<String> error_message = new ArrayList<String>();
			for(ObjectError error : bindingResult.getAllErrors()) {
				error_message.add(error.getDefaultMessage());
			}
			mav.addObject("error", error_message);
			Message message = service.findById(form.getId());
			mav.addObject("message", message);
			return showEditPage(form.getId(), mav);
		}
		service.editMessage(form);
		
		mav.setViewName("index");
		mav.addObject("messages", service.showAllMessage());
		MessageForm new_form = new MessageForm();
		mav.addObject("form", new_form);
		
		return mav;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String deleteMessage(@RequestParam("delete") Integer id) {
		service.deleteMessage(id);
		
		return "redirect:/";
	}
}
