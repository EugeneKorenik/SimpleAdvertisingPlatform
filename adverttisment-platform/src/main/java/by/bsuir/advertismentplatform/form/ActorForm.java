package by.bsuir.advertismentplatform.form;

import by.bsuir.advertismentplatform.entity.ActorRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActorForm {

    private long id;
    private String fullName;
    private String login;
    private String password;
    private String passwordAgain;
    private String mail;
    private String phone;
    private ActorRole actorRole;
}
