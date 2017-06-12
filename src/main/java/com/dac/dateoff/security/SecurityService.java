package com.dac.dateoff.security;

public interface SecurityService {
	String findLoggedInEmail();

    void autologin(String email, String password);
}
