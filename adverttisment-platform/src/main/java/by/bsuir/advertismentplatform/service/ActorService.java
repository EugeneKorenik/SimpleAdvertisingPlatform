package by.bsuir.advertismentplatform.service;

import by.bsuir.advertismentplatform.entity.Actor;
import by.bsuir.advertismentplatform.form.ActorForm;
import by.bsuir.advertismentplatform.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ActorService {

    @Autowired
    private ActorRepository repository;

    public Actor findActorByLoginAndPassword(ActorForm actorForm) {
        return repository.findActorByLoginAndPassword(actorForm.getLogin(),
                actorForm.getPassword());
    }

    public void save(ActorForm actorForm) {
        Actor actor = Actor.builder()
                .login(actorForm.getLogin())
                .fullName(actorForm.getFullName())
                .id(actorForm.getId())
                .password(actorForm.getPassword())
                .mail(actorForm.getMail())
                .phone(actorForm.getPhone())
                .role(actorForm.getActorRole()).build();
        repository.save(actor);
    }

    public void save(Actor actor) {
        repository.save(actor);
    }

    public ActorForm findById(long id) {
        Actor actor = repository.findById(id).get();
        ActorForm actorForm = ActorForm.builder()
                .id(actor.getId())
                .login(actor.getLogin())
                .password(actor.getPassword())
                .fullName(actor.getFullName())
                .mail(actor.getMail())
                .phone(actor.getPhone())
                .actorRole(actor.getRole()).build();
        return actorForm;
    }

    public Actor findEntityById(long id) {
        return repository.findById(id).get();
    }
}
