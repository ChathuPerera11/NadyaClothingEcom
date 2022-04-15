
package com.chathupererajay.nadyafashion.controller;

import com.chathupererajay.nadyafashion.global.GlobalData;
import com.chathupererajay.nadyafashion.model.User;
import com.chathupererajay.nadyafashion.service.CustomUserDetailsService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author CPere
 */
@Controller
public class LoginController {
    @Autowired
    CustomUserDetailsService userDetailsService;
   
    @GetMapping("/login")
    public String login(){
        GlobalData.cart.clear();
    return "login";
}
    @GetMapping("/register")
    public String registerGet(){
    return "register";
    }
    
    
    @PostMapping("/register")
    public String registerPost (@ModelAttribute("user") User user, 
            HttpServletRequest request) 
            throws ServletException {
        String encodedPassword = userDetailsService.encodePassword(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(userDetailsService.getUserRoleById());
        userDetailsService.addUser(user);
        request.login(user.getEmail(), encodedPassword);
        return "redirect:/";
    
    }
            
    
    
}
