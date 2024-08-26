package com.userpassword.security.controllers;


import com.userpassword.security.bussineslayer__democontroller.BussinessOp;
import com.userpassword.security.models.DemoUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private BussinessOp op;
    @Autowired // setter injection
    private void setBussinessOp(final BussinessOp op) {
        this.op = op;
    }


    @GetMapping("/demo")
    public String demo(HttpServletRequest request) {
        return "spring Security "+request.getSession().getId();
    }

    // no security filter
    @PostMapping("/adduser")
    public DemoUser addUser(@RequestBody DemoUser user) {
      return  op.addUser(user);
    }
    // no security filter
    @PostMapping("/userlogin")
    public String userLogin(@RequestBody DemoUser user) {
        return op.userLogged(user);
    }
}
