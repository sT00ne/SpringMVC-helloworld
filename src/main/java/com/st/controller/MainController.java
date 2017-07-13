package com.st.controller;

import com.st.dao.SchoolMapper;
import com.st.dao.UserMapper;
import com.st.domain.School;
import com.st.domain.User;
import com.st.domain.UserValidator;
import com.st.impl.SchoolImpl;
import com.st.impl.UserImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @RequestMapping(value = "addList", method = RequestMethod.POST)
    @ResponseBody
    public String addList() throws IOException {
        List<User> userList = new ArrayList<User>();

        User u = new User();
        u.setName("aaa");
        u.setAge(30);
        u.setSchool(1);
        userList.add(u);

        User u2 = new User();
        u2.setName("bbb");
        u2.setAge(20);
        u2.setSchool(2);
        userList.add(u2);

        User u3 = new User();
        u3.setName("ccc");
        u3.setAge(10);
        u3.setSchool(3);
        userList.add(u3);

        UserMapper userMapper = new UserImpl();
        if (userMapper.AddList(userList) != 0) {
            return "success";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "editList", method = RequestMethod.POST)
    @ResponseBody
    public String editList() {
        User user = new User();
        user.setId(44);
        user.setName("aabbcc");
        User user2 = new User();
        user2.setId(45);
        user2.setAge(99);
        List<User> userList = new ArrayList<User>();
        userList.add(user);
        userList.add(user2);
        UserMapper userMapper = new UserImpl();
        if (userMapper.EditList(userList) != 0) {
            return "success";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "delList", method = RequestMethod.POST)
    @ResponseBody
    public String delList(String id) {
        String[] ids = id.split(",");
        UserMapper userMapper = new UserImpl();
        if (userMapper.DelList(ids) != 0) {
            return "success";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "path/{pathvalue}")
    public ModelAndView path(@PathVariable String pathvalue){
        ModelAndView mv = new ModelAndView();
        mv.addObject("path",pathvalue);
        mv.setViewName("user");
        return mv;
    }

}

