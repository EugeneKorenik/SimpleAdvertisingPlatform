package by.bsuir.advertismentplatform.controller;

import by.bsuir.advertismentplatform.entity.Actor;
import by.bsuir.advertismentplatform.entity.Advertisement;
import by.bsuir.advertismentplatform.form.ActorForm;
import by.bsuir.advertismentplatform.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountController {

    @Autowired
    private ActorService actorService;

    @GetMapping(name = "/toAccount")
    public ModelAndView toAccount(HttpServletRequest request) {
        long id = (long)request.getSession().getAttribute("userId");
        ModelAndView model = new ModelAndView("account");
        model.addObject("actor", actorService.findById(id));
        return model;
    }

    @PutMapping("/updatePassword")
    public ModelAndView updatePassword(@RequestParam String password,
                                       @RequestParam String passwordAgain,
                                       @RequestParam long id) {
        ActorForm actorForm = actorService.findById(id);
        actorForm.setPassword(password);
        actorService.save(actorForm);
        return new ModelAndView("redirect:/toAccountSettings");
    }

    @PutMapping("/updateInfo")
    public ModelAndView updateInfo(@ModelAttribute ActorForm actorForm) {
        ActorForm form = actorService.findById(actorForm.getId());
        actorForm.setPassword(form.getPassword());
        actorForm.setActorRole(form.getActorRole());
        actorService.save(actorForm);
        return new ModelAndView("redirect:/toAccountSettings");
    }
}
