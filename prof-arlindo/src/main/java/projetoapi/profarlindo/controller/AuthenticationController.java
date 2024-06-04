package projetoapi.profarlindo.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projetoapi.profarlindo.Services.LoginResponseDTO;
import projetoapi.profarlindo.model.AdminModel;
import projetoapi.profarlindo.model.AuthenticationDTOModel;
import projetoapi.profarlindo.model.RegisterDTOModel;
import projetoapi.profarlindo.model.repository.AdminRepository;
import projetoapi.profarlindo.Config.TokenService;

@RestController
@RequestMapping("api")

public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdminRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/admin/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTOModel data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((AdminModel) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/admin/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTOModel data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        AdminModel newAdminModel = new AdminModel(data.login(), encryptedPassword, data.role());

        this.repository.save(newAdminModel);

        return ResponseEntity.ok().build();
    }
}
