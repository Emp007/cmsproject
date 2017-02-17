package com.cms.app.contollers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cms.app.dto.Response;
import com.cms.app.security.TokenUtils;
import com.cms.app.util.AuthenticationUtil;
import com.cms.model.Constants;
import com.cms.model.CMSUser;
import com.cms.model.User;


@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

  private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenUtils tokenUtils;

  @Autowired
  @Qualifier("userDetailServiceImpl")
  private UserDetailsService userDetailsService;

  @Autowired
  private AuthenticationUtil authenticationUtil;

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<Map<String, Object>>> generateAuthentication(@RequestBody User user) {

    LOGGER.info(String.format("Attempting to generate authorization token for user : %s", user.getEmail()));

    CMSUser userDetails = (CMSUser) this.userDetailsService.loadUserByUsername(user.getEmail());

    //String samlResponse = authenticationUtil.authenticate(user.getEmail(), user.getPassword());

    Authentication auth =  new UsernamePasswordAuthenticationToken(userDetails.getEmail(), Constants.DUMMY_PASSWORD,
            userDetails.getAuthorities());

    SecurityContextHolder.getContext().setAuthentication(auth);
    String token = this.tokenUtils.generateToken(userDetails);
    LOGGER.info(String.format("Authorization token generated for user : %s successfully",user.getEmail()));

    Map<String, Object> dataMap = new HashMap<>();
    dataMap.put(Constants.AUTH_TOKEN, token);
    dataMap.put(Constants.USER, userDetails);

    return ResponseEntity.ok(new Response<Map<String, Object>>(HttpStatus.OK.value(), "Token generated successfully", dataMap));
  }
}
