package org.lgangloff.nbd.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    private AuthoritiesConstants() {
    }

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String MANAGER = "ROLE_MANAGER";
    
    public static final String COACH = "ROLE_COACH";

    public static final String ADHERENT = "ROLE_ADHERENT";
    
    public static final String USER = "ROLE_USER";
    
}
