package edu.icet.ecom.service.custom;

import edu.icet.ecom.service.SuperService;

public interface LoginService extends SuperService {
    boolean validateUser(String username,String password);
    String sendEmail(String username);
}
