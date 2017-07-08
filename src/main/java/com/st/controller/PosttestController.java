package com.st.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by stone on 2017/7/3.
 */
@Controller
@RequestMapping("/a/{ownerId}")
public class PosttestController {
    @RequestMapping("/b/{petId}")
    public ModelAndView hello(@PathVariable String ownerId,@PathVariable String petId) {
        ModelAndView mv = new ModelAndView("user");
        mv.addObject("a",ownerId);
        mv.addObject("b",petId);
        return mv;
    }

    @RequestMapping("/c/bar*")
    public ModelAndView hi(@PathVariable String ownerId) {
        ModelAndView mv = new ModelAndView("user");
        mv.addObject("a",ownerId);
        mv.addObject("b","bar");
        return mv;
    }

    @RequestMapping("/d/**")
    public ModelAndView path(@PathVariable String ownerId) {
        ModelAndView mv = new ModelAndView("user");
        mv.addObject("a",ownerId);
        mv.addObject("b","c**");
        return mv;
    }
}