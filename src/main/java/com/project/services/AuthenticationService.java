package com.project.services;

import com.project.Repositories.CompanyRepository;
import com.project.Repositories.JobSeekerRepository;
import com.project.exceptions.ResourceNotFoundException;
import com.project.models.Company;
import com.project.models.JobSeeker;
import com.project.payloads.LoginResponseDTO;
import com.project.models.Role;
import com.project.models.User;
import com.project.Repositories.RoleRepository;
import com.project.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public User registerUser(String username, String password, String role){
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority(role).get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        User user = userRepository.save(new User(username, encodedPassword, authorities));

        if(role.equals("JOB_SEEKER"))
        {
            JobSeeker js = new JobSeeker();
            js.setUser(user);
            this.jobSeekerRepository.save(js);
        }

        if(role.equals("COMPANY"))
        {
            Company company = new Company();
            company.setUser(user);
            this.companyRepository.save(company);
        }

        return user;
    }


    public LoginResponseDTO loginUser(String username, String password){
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            User user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException());

            Integer jsId=null;
            Integer companyId=null;

            for(GrantedAuthority authority : user.getAuthorities())
            {
                if(authority.getAuthority().equals("JOB_SEEKER")){
                    JobSeeker jobSeeker = jobSeekerRepository.findByUser(user).orElseThrow(()->new RuntimeException());
                    jsId=jobSeeker.getJsId();
                }
                else if(authority.getAuthority().equals("COMPANY")){
                    Company company = companyRepository.findByUser(user).orElseThrow(()->new RuntimeException());
                    companyId=company.getCompanyId();
                }
            }



            return new LoginResponseDTO(userRepository.findByUsername(username).get(), jsId, companyId,token);
        }
        catch (AuthenticationException e){
            return new LoginResponseDTO(null,null, null, "");
        }
    }




}
