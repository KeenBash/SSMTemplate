package com.ssm.template.controller;

import com.ssm.template.entity.User;
import com.ssm.template.service.UserService;
import com.ssm.template.util.RandomSalt;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        Subject subject = SecurityUtils.getSubject();
        // isRemembered在点击记住我关闭浏览器后再打开为true
        if (subject.isRemembered()) {
            return "redirect: home";
        }
        return "login";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(String username,
                          String password,
                          Model model,
                          @RequestParam(defaultValue = "false") boolean rememberMe) {
        // 获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        // 封装为token
        // shiro的rememberMe
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        System.out.println("token.isRememberMe() = " + token.isRememberMe());
        System.out.println("token.getPrincipal() = " + token.getPrincipal());
        System.out.println("subject.isRemembered() = " + subject.isRemembered());
        // login
        try {
            subject.login(token);
            return "redirect:home";
        } catch (UnknownAccountException e) {
            System.out.println("用户名错误!");
            model.addAttribute("unameTip", "用户名错误!");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误!");
            model.addAttribute("pwdTip", "密码错误!");
        }
        return "login";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        model.addAttribute("username", user.getUsername());
        return "home";
    }

    @RequestMapping("/manage")
    @RequiresRoles("admin")
    public String manage() {
        Subject subject = SecurityUtils.getSubject();
        System.out.println("subject.getPrincipal() = " + subject.getPrincipal());
        System.out.println("subject.hasRole('admin') = " + subject.hasRole("admin"));
        return "manage";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    // @ResponseBody乱码
    // 方法一
    // @RequestMapping 加入 produces = "text/html;charset=UTF-8"
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    @ResponseBody
    public String doRegister(String username,
                             String password) {
        if (username.length() > 0 && password.length() > 0) {
            String salt = "" + RandomSalt.numSalt();
            service.registerUser(username, password, salt);
            return "注册成功";
        } else {
            return "注册失败";
        }
    }
}
