package com.example.rtilogin.service;

import com.example.rtilogin.dto.LoginRequest;
import com.example.rtilogin.dto.LoginResponse;
import com.example.rtilogin.entity.NetUser;
import com.example.rtilogin.entity.LoginHistory;
import com.example.rtilogin.repository.NetUserRepository;
import com.example.rtilogin.repository.LoginHistoryRepository;
import com.example.rtilogin.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private NetUserRepository userRepo;
    @Autowired
    private LoginHistoryRepository historyRepo;

    // Autowire the JwtUtil component
    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest req) {
        Optional<NetUser> userOpt = userRepo.findByUserName(req.getUsername());
        if (!userOpt.isPresent()) {
            return new LoginResponse("7", "InActive", null, null);
        }
        NetUser user = userOpt.get();

        // Blocked user
        if (user.getLastUnSuccessfulLogin() != null &&
                user.getLastUnSuccessfulLogin().isAfter(LocalDateTime.now())) {
            return new LoginResponse("6", "BlockUser", null, null);
        }

        // Password check
        if (!user.getMobile().equals(req.getPassword())) {
            int attempts = user.getLoginAttempts() + 1;
            user.setLoginAttempts(attempts);
            if (attempts >= 4) {
                user.setLastUnSuccessfulLogin(LocalDateTime.now().plusMinutes(30));
                userRepo.save(user);
                return new LoginResponse("5", "PassIsBlock", null, null);
            } else {
                user.setLastUnSuccessfulLogin(LocalDateTime.now());
                userRepo.save(user);
                return new LoginResponse("5", "InvalidPass", null, null);
            }
        }

        // Activation logic
        if ("N".equals(user.getActiveIdle()) && user.getActivationKeyConf() == null) {
            return new LoginResponse("2", "EnterKey", null, null);
        }
        if ("N".equals(user.getActiveIdle()) &&
                !user.getActivationKey().equals(user.getActivationKeyConf())) {
            return new LoginResponse("3", "InActiveEnterKey", null, null);
        }
        if ("Y".equals(user.getActiveIdle()) &&
                !user.getActivationKey().equals(user.getActivationKeyConf())) {
            return new LoginResponse("4", "ActiveEnterKey", null, null);
        }

        // Success
        user.setLoginAttempts(0);
        user.setLastSuccessfulLogin(LocalDateTime.now());
        userRepo.save(user);

        // Log history
        LoginHistory history = new LoginHistory();
        history.setLoginDateTime(LocalDateTime.now());
        history.setIp(req.getIpAddress());
        history.setBrowser(req.getBrowser());
        history.setOs(req.getSystem());
        history.setUserName(user.getUserName());
        history.setUCode(user.getUserCode());
        historyRepo.save(history);

        // Generate JWT token using the injected jwtUtil instance
        String token = jwtUtil.generateToken(user);
        return new LoginResponse("1", "Active", user.getUserCode(), user.getUserType(), token);
    }
}
