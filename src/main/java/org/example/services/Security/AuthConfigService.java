package org.example.services.Security;

import org.springframework.stereotype.Service;

@Service
public class AuthConfigService {

//    @Autowired
//    private AuthConfigRepository authConfigRepository;
//
//    @Transactional(readOnly = true)
//    public AuthConfigDto getConfigDto() {
//        AuthConfig authConfig = getConfig();
//        return new AuthConfigDto(
//                authConfig.getIdleSeconds()
//        );
//    }
//
//    @Transactional(readOnly = true)
//    public AuthConfig getConfig() {
//        if (!authConfigRepository.existsById(AuthConfig.ID)) {
//            authConfigRepository.save(new AuthConfig());
//        }
//        AuthConfig authConfig = authConfigRepository.findById(AuthConfig.ID).orElse(new AuthConfig());
//        return authConfig;
//    }

}
