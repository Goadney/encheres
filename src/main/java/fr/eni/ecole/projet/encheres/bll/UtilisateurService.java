package fr.eni.ecole.projet.encheres.bll;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.ecole.projet.encheres.bo.Utilisateur;
import fr.eni.ecole.projet.encheres.dal.UtilisateurDAO;

@Service
public class UtilisateurService {

    private final UtilisateurDAO UtilisateurDAO;

    @Autowired
    public UtilisateurService(UtilisateurDAO UtilisateurDAO) {
        this.UtilisateurDAO = UtilisateurDAO;
    }

    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return UtilisateurDAO.save(utilisateur);
    }
    
    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return UtilisateurDAO.findById(id);
    }

    public Optional<Utilisateur> getUtilisateurByEmail(String email) {
        return UtilisateurDAO.findByEmail(email);
    }

    public Optional<Utilisateur> getUtilisateurByPseudo(String pseudo) {
        return UtilisateurDAO.findByPseudo(pseudo);
    }

    public boolean pseudoExists(String pseudo) {
        return UtilisateurDAO.existsByPseudo(pseudo);
    }

    public void deleteUtilisateurById(Long id) {
        UtilisateurDAO.deleteById(id);
    }
}
