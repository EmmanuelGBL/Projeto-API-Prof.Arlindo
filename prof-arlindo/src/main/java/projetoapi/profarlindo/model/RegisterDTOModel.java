package projetoapi.profarlindo.model;

import projetoapi.profarlindo.Role.AdminRole;

public record RegisterDTOModel(String login, String password, AdminRole role) {
}
