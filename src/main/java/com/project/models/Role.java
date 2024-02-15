package com.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Integer roleId;

    private String authority;

    public Role(String authority)
    {
        this.authority=authority;
    }

    public Integer getRoleId()
    {
        return this.roleId;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
    public void setAuthority(String authority){
        this.authority=authority;
    }
}
