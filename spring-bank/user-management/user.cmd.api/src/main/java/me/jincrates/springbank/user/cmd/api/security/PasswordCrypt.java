package me.jincrates.springbank.user.cmd.api.security;

public interface PasswordCrypt {

    String encodedPassword(String password);

    boolean matchesPassword(String plainPassword, String encodedPassword);
}
