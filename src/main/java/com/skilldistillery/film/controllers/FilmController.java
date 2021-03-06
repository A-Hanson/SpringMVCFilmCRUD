package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.FilmDAO;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDAO;

	@RequestMapping(path = { "/", "home.do" })
	public String home() {

		// TODO - add list of all films to model
		return "WEB-INF/views/index.jsp";

	}
	
	@RequestMapping(path = "findFilmById.do",
			method = RequestMethod.GET)
	public ModelAndView getFilmbyId(Integer filmid) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/Film.jsp");
		mv.addObject("film", filmDAO.findFilmById(filmid) );
		return mv;
	}
	
	
	@RequestMapping(path = "findFilmByKeyword.do", method = RequestMethod.GET)
	public ModelAndView getFilmbyKeyword (String findFilmByKeyword) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/FilmList.jsp");
		mv.addObject("film", filmDAO.findFilmByKeyword(findFilmByKeyword) );
		return mv;
	}
	
}
