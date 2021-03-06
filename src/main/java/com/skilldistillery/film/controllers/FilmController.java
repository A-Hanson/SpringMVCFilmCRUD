package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;

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
	
	@RequestMapping(path="NewFilm.do", method= RequestMethod.POST)
	public ModelAndView createNewFilm(Film film, RedirectAttributes redir) {
		filmDAO.createFilm(film);
		ModelAndView mv = new ModelAndView();
		redir.addFlashAttribute("film", film);
		mv.setViewName("redirect:filmCreated.do");
		return mv;
	}
	
	
	@RequestMapping(path="filmCreated.do", method= RequestMethod.GET)
	public String newFilm(Model model) {
		return "WEB-INF/views/Film.jsp";
	}
	
	@RequestMapping(path="goToUpdatePage.do")
	public String goToUpdate() {
		return "WEB-INF/views/updateFilm.jsp";
	}
	
	@RequestMapping(path="UpdateFilm.do", method= RequestMethod.POST)
	public ModelAndView updateFilm(Film film, RedirectAttributes redir) {
		filmDAO.createFilm(film);
		ModelAndView mv = new ModelAndView();
		redir.addFlashAttribute("film", film);
		mv.setViewName("redirect:filmUpdated.do");
		return mv;
	}
	@RequestMapping(path="filmUpdated.do", method= RequestMethod.GET)
	public String refreshFilm(Model model) {
		return "WEB-INF/views/Film.jsp";
	}
	
}
