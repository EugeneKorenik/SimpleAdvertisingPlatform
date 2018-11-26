package by.bsuir.advertismentplatform.controller;

import by.bsuir.advertismentplatform.entity.ActorRole;
import by.bsuir.advertismentplatform.form.ActorForm;
import by.bsuir.advertismentplatform.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    @Autowired
    private ActorService service;

    @GetMapping("/toRegistration")
    public ModelAndView toRegistrationPage() {
        ModelAndView model = new ModelAndView("registration");
        model.addObject("actorForm", new ActorForm());
        return model;
    }

    @PostMapping("/registration")
    public ModelAndView registration(@ModelAttribute ActorForm actorForm) {
        actorForm.setActorRole(ActorRole.USER);
        service.save(actorForm);
        ModelAndView model = new ModelAndView("redirect:/");
        return model;
    }
}
