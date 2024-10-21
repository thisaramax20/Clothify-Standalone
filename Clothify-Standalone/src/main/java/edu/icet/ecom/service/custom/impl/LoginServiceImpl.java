package edu.icet.ecom.service.custom.impl;

import edu.icet.ecom.entity.Admin;
import edu.icet.ecom.repository.DaoFactory;
import edu.icet.ecom.repository.custom.impl.AdminDaoImpl;
import edu.icet.ecom.service.custom.LoginService;
import edu.icet.ecom.util.DaoType;
import edu.icet.ecom.util.EmailSending;
import edu.icet.ecom.util.EncryptPassword;
import edu.icet.ecom.util.OtpGenerator;

public class LoginServiceImpl implements LoginService {
    AdminDaoImpl adminDao = DaoFactory.getInstance().getDaoType(DaoType.ADMIN);

    @Override
    public boolean validateUser(String username, String password) {
        Admin admin = adminDao.getById(username);
        if(admin==null) return false;
        String hashedPassword = admin.getHashedPassword();
        return EncryptPassword.checkPassword(password,hashedPassword);
    }

    @Override
    public String sendEmail(String username) {
        Admin admin = adminDao.getById(username);
        String otp = OtpGenerator.generateOtp(6);
        boolean executed = EmailSending.sendOTP(admin.getEmail(), otp);
        if (executed) return otp;
        return null;
    }
}
