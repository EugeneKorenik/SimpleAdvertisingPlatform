package by.bsuir.advertismentplatform.controller;

import by.bsuir.advertismentplatform.entity.Actor;
import by.bsuir.advertismentplatform.entity.Advertisement;
import by.bsuir.advertismentplatform.service.ActorService;
import by.bsuir.advertismentplatform.service.AdvertismentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdvertisementController {

    @Autowired
    private AdvertismentService service;

    @Autowired
    private ActorService actorService;

    @GetMapping("/")
    public ModelAndView toMainPage(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("advertisementList");
        List<Advertisement> advertisementList = service.findAll();
        model.addObject("role", request.getSession().getAttribute("role"));
        model.addObject("advertisements", advertisementList);
        return model;
    }

    @GetMapping("/toMyAdvertisements")
    public ModelAndView toMyAdvertisementList(HttpServletRequest request) {
        long userId = (long)request.getSession().getAttribute("userId");
        Actor actor = actorService.findEntityById(userId);
        List<Advertisement> advertisements = actor.getAdvertisementList();

        ModelAndView model = new ModelAndView("advertisementList");
        model.addObject("role", actor.getRole());
        model.addObject("advertisements", advertisements);
        return model;
    }

    @GetMapping("/toAddAdvertisement")
    public ModelAndView toAddAdvertisement() {
        return new ModelAndView("createAdvertisement", "advertisement", new Advertisement());
    }

    @GetMapping("/toEditAdvertisement")
    public ModelAndView toEditAdvertisement(@RequestParam long id) {
        Advertisement advertisement = service.findById(id);
        return new ModelAndView("editAdvertisement", "advertisement", advertisement);
    }

    @PostMapping("/createAdvertisement")
    public ModelAndView addAdvertisement(@ModelAttribute Advertisement advertisement,
                                         HttpServletRequest request) {
        long userId = (long) request.getSession().getAttribute("userId");
        Actor actor = actorService.findEntityById(userId);
        List<Advertisement> advertisements = actor.getAdvertisementList();
        if(advertisements == null) {
            System.out.println("error");
        } else {
            advertisements.add(advertisement);
        }
        actorService.save(actor);
        return new ModelAndView("redirect:/toMyAdvertisements");
    }

    @PutMapping("/updateAdvertisement")
    public ModelAndView editAdvertisement(@ModelAttribute Advertisement advertisement) {
        service.save(advertisement);
        return new ModelAndView("redirect:/toMyAdvertisements");
    }

    @DeleteMapping("/deleteAdvertisement")
    public ModelAndView deleteAdvertisement(@RequestParam long id, HttpServletRequest request) {
        service.deleteById(id);
        return new ModelAndView("redirect:/toMyAdvertisements");
    }

    @GetMapping("/toConcreteAdvertisement")
    public ModelAndView toConcreteAdvertisement(@RequestParam long id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("concreteAdvertisement");
        if(request.getSession().getAttribute("userId") != null) {
            Actor actor = actorService.findEntityById((long) request.getSession().getAttribute("userId"));
            List<Advertisement> advertisements = actor.getAdvertisementList();
            for (int i = 0; i < advertisements.size(); i++) {
                Advertisement advertisement = advertisements.get(i);
                if (advertisement.getId() == id) {
                    model.addObject("user", "true");
                }
            }
            model.addObject("role", actor.getRole());
        }
        model.addObject("advertisement", service.findById(id));
        return model;
    }
}
