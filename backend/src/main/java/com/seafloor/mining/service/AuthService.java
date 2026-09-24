package com.seafloor.mining.service;

import com.seafloor.mining.dto.LoginRequest;
import com.seafloor.mining.dto.RegisterRequest;
import com.seafloor.mining.entity.Operator;
import com.seafloor.mining.repository.OperatorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final OperatorRepository operatorRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    public String register(RegisterRequest request) {
        String operatorId = request.getOperatorId().trim();
        if (operatorRepository.existsByOperatorIdIgnoreCase(operatorId)) {
            throw new IllegalArgumentException("That operator ID is already registered. Please sign in.");
        }

        Operator operator = new Operator(operatorId, passwordEncoder.encode(request.getPasscode()));
        operatorRepository.save(operator);
        return operatorId;
    }

    public String login(LoginRequest request) {
        String operatorId = request.getOperatorId().trim();
        Operator operator = operatorRepository.findByOperatorIdIgnoreCase(operatorId)
                .orElseThrow(() -> new IllegalArgumentException("Operator ID is not registered. Please register first."));

        if (!passwordEncoder.matches(request.getPasscode(), operator.getPasswordHash())) {
            throw new IllegalArgumentException("Incorrect passcode. Please try again.");
        }

        return operator.getOperatorId();
    }
}
