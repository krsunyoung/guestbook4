package com.bit2016.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit2016.guestbook.dao.GuestbookDao;
import com.bit2016.guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	@RequestMapping("/")
	public String list(Model model){
		List<GuestbookVo> list= guestbookDao.getList();
		model.addAttribute("list",list);
		return "/WEB-INF/views/list.jsp";
	}

	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute GuestbookVo vo){
		guestbookDao.insert(vo);
		return "redirect:/";
	}
	
	
	@RequestMapping("/deleteform/{no}")
	public String deleteform(@PathVariable("no") int no, Model model){
		//parameter를 받고 싶으면 requestparam을 써주면 된다.
		model.addAttribute("no", no);
		return "/WEB-INF/views/deleteform.jsp";
		
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@ModelAttribute GuestbookVo vo){
		guestbookDao.delete(vo);
		return "redirect:/";
	}
	
}
