package by.bsuir.advertismentplatform.controller;

import by.bsuir.advertismentplatform.entity.Actor;
import by.bsuir.advertismentplatform.form.ActorForm;
import by.bsuir.advertismentplatform.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizationController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/toAuthorization")
    public ModelAndView toAuthorization() {
        ModelAndView model = new ModelAndView("authorization");
        model.addObject("actor", new ActorForm());
        return model;
    }

    @PostMapping("/signIn")
    public ModelAndView authorization(@ModelAttribute("actor") ActorForm actorForm,
                                      HttpServletRequest request) {
        Actor actor = actorService.findActorByLoginAndPassword(actorForm);
        if(actor == null) {
            ModelAndView model = new ModelAndView("authorization");
            model.addObject("actorForm", actorForm);
            model.addObject("message", "Неверный логин или пароль");
            return model;
        }
        request.getSession().setAttribute("userId", actor.getId());
        request.getSession().setAttribute("role", actor.getRole());
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/signOut")
    public ModelAndView signOut(HttpServletRequest request) {
        request.getSession().removeAttribute("role");
        return new ModelAndView("redirect:/");
    }
}
