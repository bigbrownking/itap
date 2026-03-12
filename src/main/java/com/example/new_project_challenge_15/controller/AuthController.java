package com.example.new_project_challenge_15.controller;

import com.example.new_project_challenge_15.models.ERole;
import com.example.new_project_challenge_15.models.Role;
import com.example.new_project_challenge_15.models.TempLog;
import com.example.new_project_challenge_15.models.User;
import com.example.new_project_challenge_15.payload.request.LoginRequest;
import com.example.new_project_challenge_15.payload.request.SignupRequest;
import com.example.new_project_challenge_15.payload.response.JwtResponse;
import com.example.new_project_challenge_15.payload.response.MessageResponse;
import com.example.new_project_challenge_15.repository.RoleRepository;
import com.example.new_project_challenge_15.repository.TempLogRepository;
import com.example.new_project_challenge_15.repository.UserRepository;
import com.example.new_project_challenge_15.security.jwt.JwtUtils;
import com.example.new_project_challenge_15.security.services.UserDetailsImpl;
import com.example.new_project_challenge_15.security.services.UserDetailsServiceImpl;
import com.example.new_project_challenge_15.service.StoreLogs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/finpol/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthenticationManager authenticationManager;

  private final UserRepository userRepository;

  private final UserDetailsServiceImpl userDetailsService;

  private final RoleRepository roleRepository;

  private final PasswordEncoder encoder;

  private final JwtUtils jwtUtils;

  private final StoreLogs storeLogs;

  private  final RestTemplate restTemplate;
  private final TempLogRepository tempLogRepository;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

    storeLogs.storeAuth(loginRequest.getUsername(),"login");
    User user = userRepository.getById(userDetails.getId());
    long daysBetween = ChronoUnit.DAYS.between(user.getLast_password_change(), LocalDateTime.now());
    if (daysBetween > 30) {
      return ResponseEntity.ok(new JwtResponse(jwt,
              userDetails.getId(),
              "NEED_TO_CHANGE_PASSWORD",
              userDetails.getEmail(),
              roles));
    }

    return ResponseEntity.ok(new JwtResponse(jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles));
  }
  @PostMapping("/changePassword")
  public void changePassword( @RequestParam String password, Principal principal, @RequestParam String username){
//    System.out.println(userDetailsService.loadUserByUsernamek(principal));
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isPresent()) {
      user.get().setPassword(encoder.encode(password));
      System.out.println(user.get().getUsername());
      System.out.println(user.get().getEmail());
      System.out.println(password);
      storeLogs.storeAuth(user.get().getUsername(),"changepassword");
      user.get().setLast_password_change(LocalDateTime.now());
      userRepository.save(user.get());
    }
  }
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }

    User user = new User(signUpRequest.getEmail(),
            signUpRequest.getUsername(),
            encoder.encode(signUpRequest.getPassword()));
    Set<Role> roles = new HashSet<>();

    if (signUpRequest.getLevel().equals("2")) {
      Role userRole = roleRepository.findByName(ERole.LEVEL_2_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);  }
    if (signUpRequest.getLevel().equals("1")) {
      Role userRole = roleRepository.findByName(ERole.LEVEL_1_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);  }if (signUpRequest.getLevel().equals("3")) {
      Role userRole = roleRepository.findByName(ERole.LEVEL_3_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);  }if (signUpRequest.getLevel().equals("vip")) {
      Role userRole = roleRepository.findByName(ERole.VIP)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);  }if (signUpRequest.getLevel().equals("admin")) {
      Role userRole = roleRepository.findByName(ERole.ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);  }
    user.setActive(true);
    user.setRoles(roles);
    storeLogs.storeAuth(user.getUsername(),"signup");
    user.setLast_password_change(LocalDateTime.now());
    userRepository.save(user);
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @GetMapping("/fetchTokenCookieAndGetAccess")
  public ResponseEntity<?> fetchTokenCookieAndGetAccess(HttpServletRequest request) {
    System.out.println("Protocol: " + request.getScheme());
    try {
      String refreshToken = jwtUtils.getTokenFromCookie(request, "refresh_token");

      if (refreshToken == null || refreshToken.isEmpty()) {
        return ResponseEntity.badRequest().body("Refresh token not found in cookies");
      }

      String refreshUrl = "https://ser.afm.gov.kz/kfm_new/api/v1/auth/refresh-access-token";

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      headers.add(HttpHeaders.COOKIE, "refresh_token="+refreshToken);

      HttpEntity<Map<String, String>> entity = new HttpEntity<>(null, headers);

      ResponseEntity<Map> response = restTemplate.postForEntity(refreshUrl, entity, Map.class);
      log.error("RESPONSE: "+ response.getStatusCode());
      if (response.getStatusCode() == HttpStatus.OK) {
        Map<String, Object> responseBody = response.getBody();
        log.info("RB: " + responseBody);
        String newAccessToken = (String) responseBody.get("newAccessToken");

        if (newAccessToken != null) {
          String username = jwtUtils.getUserNameFromJwtToken(newAccessToken);
          log.error("USERNAME:  "+ username);
          UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);

          Authentication authentication =
                  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authentication);

          log.error("AUTH: " + authentication.getPrincipal());
          List<String> roles = userDetails.getAuthorities().stream()
                  .map(GrantedAuthority::getAuthority)
                  .collect(Collectors.toList());

          storeLogs.storeAuth(username,"login");
          return ResponseEntity.ok(new JwtResponse(newAccessToken,
                  userDetails.getId(),
                  userDetails.getUsername(),
                  userDetails.getEmail(),
                  roles));
        } else {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body("New access token not found in response");
        }
      } else {
        return ResponseEntity.status(response.getStatusCode())
                .body("Failed to refresh token: " + response.getBody());
      }

    } catch (Exception e) {
      String message =  e.getMessage();
      tempLogRepository.save(new TempLog(message, LocalDateTime.now()));
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body("Username not found: " + message);
    }
  }


}
