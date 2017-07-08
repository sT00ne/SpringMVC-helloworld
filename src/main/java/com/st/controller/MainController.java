package com.st.controller;

import com.st.dao.SchoolMapper;
import com.st.dao.UserMapper;
import com.st.domain.School;
import com.st.domain.User;
import com.st.domain.UserValidator;
import com.st.impl.SchoolImpl;
import com.st.impl.UserImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

/**
 * Created by stone on 2017/6/12.
 */
@Controller
public class MainController {
    @InitBinder
    public void initBinder(DataBinder binder) {
        binder.setValidator(new UserValidator());
    }

    @RequestMapping("/welcome")
    public @ResponseBody
    ModelAndView helloWorld(HttpServletRequest request) {
        UserMapper user = new UserImpl();
        Integer page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        Integer pagesize = 5;
        Integer count = user.SelectCount();
        List<User> u = new ArrayList<User>();
        Integer totalPages;
        if ((count % pagesize) == 0) {
            totalPages = count / pagesize;
        } else {
            totalPages = count / pagesize + 1;
        }
        page = page > totalPages ? totalPages : page;
        page = page <= 0 ? 1 : page;
        Integer prePage = 1;
        Integer nextPage = 1;
        if (count != 0) {
            if (count < pagesize * page) {
                u = user.selectList(pagesize * (page - 1), count);
                prePage = page - 1 <= 0 ? 1 : page - 1;
                nextPage = page + 1 >= totalPages ? totalPages : page + 1;
            } else {
                u = user.selectList(pagesize * (page - 1), pagesize);
                prePage = page - 1 <= 0 ? 1 : page - 1;
                nextPage = page + 1 >= totalPages ? totalPages : page + 1;
            }
        }
        ModelAndView mv = new ModelAndView("new");
        SchoolMapper school = new SchoolImpl();
        List<School> schools = school.selectSchoolList();
        mv.addObject("u", u);
        mv.addObject("command", new User());
        mv.addObject("school", schools);
        mv.addObject("prePage", prePage);
        mv.addObject("nextPage", nextPage);
        return mv;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid User user, BindingResult result) {
        if (result.hasErrors())
            return "redirect:http://www.bing.com";
        if (user.getId() != null) {
            UserMapper userMapper = new UserImpl();
            userMapper.updateByPrimaryKeySelective(user);
        } else {
            UserMapper userMapper = new UserImpl();
            userMapper.insert(user);
        }
        return "redirect:welcome";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String del(Integer id) {
        UserMapper userMapper = new UserImpl();
        if (userMapper.deleteByPrimaryKey(id) == 1) {
            return "success";
        } else {
            return "error";
        }
    }
}

