package jane.rentcarproject_gradle.database.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    ADMIN,
    CLIENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
