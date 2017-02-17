package com.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Class to handle user authentication to be handled by spring authentication
 */
public class CMSUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	  private Long id;
	  private String username;
	  private String password;
	  private String email;
	  private Collection<? extends GrantedAuthority> authorities;
	  private Boolean accountNonExpired = true;
	  private Boolean accountNonLocked = true;
	  private Boolean credentialsNonExpired = true;
	  private Boolean enabled = true;
	  private String staffId;

	  public CMSUser() {
	    super();
	  }

	  public CMSUser(Long id, String username, String password, String email,
	      Collection<? extends GrantedAuthority> authorities, String staffId) {
	    this.setId(id);
	    this.setUsername(username);
	    this.setPassword(password);
	    this.setEmail(email);
	    this.setAuthorities(authorities);
	    this.staffId = staffId;
	  }

	  public Long getId() {
	    return this.id;
	  }

	  public void setId(Long id) {
	    this.id = id;
	  }

	  public String getUsername() {
	    return this.username;
	  }

	  public void setUsername(String username) {
	    this.username = username;
	  }

	  @JsonIgnore
	  public String getPassword() {
	    return this.password;
	  }

	  public void setPassword(String password) {
	    this.password = password;
	  }

	  public String getEmail() {
	    return this.email;
	  }

	  public void setEmail(String email) {
	    this.email = email;
	  }

	  @Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return this.authorities;
	  }

	  public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
	    this.authorities = authorities;
	  }

	  @JsonIgnore
	  public Boolean getAccountNonExpired() {
	    return this.accountNonExpired;
	  }

	  public void setAccountNonExpired(Boolean accountNonExpired) {
	    this.accountNonExpired = accountNonExpired;
	  }

	  @Override
	  public boolean isAccountNonExpired() {
	    return this.getAccountNonExpired();
	  }

	  @JsonIgnore
	  public Boolean getAccountNonLocked() {
	    return this.accountNonLocked;
	  }

	  public void setAccountNonLocked(Boolean accountNonLocked) {
	    this.accountNonLocked = accountNonLocked;
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return this.getAccountNonLocked();
	  }

	  @JsonIgnore
	  public Boolean getCredentialsNonExpired() {
	    return this.credentialsNonExpired;
	  }

	  public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
	    this.credentialsNonExpired = credentialsNonExpired;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return this.getCredentialsNonExpired();
	  }

	  @JsonIgnore
	  public Boolean getEnabled() {
	    return this.enabled;
	  }

	  public void setEnabled(Boolean enabled) {
	    this.enabled = enabled;
	  }

	  public String getStaffId() {
	    return staffId;
	  }

	  public void setStaffId(String staffId) {
	    this.staffId = staffId;
	  }

	  @Override
	  public boolean isEnabled() {
	    return this.getEnabled();
	  }

}
