package by.bsuir.advertismentplatform.repository;

import by.bsuir.advertismentplatform.entity.Actor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Long> {

    public Actor findActorByLoginAndPassword(String login, String password);
}
