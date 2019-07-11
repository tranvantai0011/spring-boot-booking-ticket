package com.program.dto;

import com.program.entity.RoleEntity;

public class RoleDTO {

  private Long roleId;

  private String roleName;

  private String description;

  public RoleDTO() {
    
  }
  public RoleDTO(final RoleEntity entity) {
    this.roleId = entity.getRoleId();
    this.roleName = entity.getRoleName();
    this.description = entity.getDescription();
  }
  
  public RoleEntity convert() {
    final RoleEntity entity = new RoleEntity();
    entity.setRoleId(this.roleId);
    entity.setRoleName(this.roleName);
    entity.setDescription(this.description);
    return entity;
  }
  
  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}