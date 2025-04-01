package app.dao;

import app.enums.Roles;
import dk.bugelhartmann.UserDTO;
import app.entities.UserAccount;
import app.exceptions.ValiappionException;


public interface ISecurityDAO
{
    UserDTO getVerifiedUser(String username, String password) throws ValiappionException;
    UserAccount createUser(String username, String password);
    UserAccount addRoleToUser(String username, Roles role);
    UserAccount removeRoleFromUser(String username, Roles role);
}
