package projetoapi.profarlindo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import projetoapi.profarlindo.model.AdminModel;

public interface AdminRepository extends JpaRepository<AdminModel, String> {
    UserDetails findByLogin(String login);
}


