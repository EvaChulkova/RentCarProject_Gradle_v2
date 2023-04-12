package jane.rentcarproject_gradle.query.filter;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserFilter {
    String firstName;
    String lastname;
    String login;
}
