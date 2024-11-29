package fr.eni.ecole.projet.encheres.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.ecole.projet.encheres.bo.Categorie;
import fr.eni.ecole.projet.encheres.dal.CategorieDAO;

@Service
public class CategorieServiceImpl implements CategorieService {
	
	private CategorieDAO categorieDAO;
	
	public CategorieServiceImpl(CategorieDAO categorieDAO) {
		this.categorieDAO = categorieDAO;
	}
	
	@Override
	public List<Categorie> getCategories(){
		return categorieDAO.findAll();
	}
	
	@Override
	public Categorie findById(Long id) {
		return categorieDAO.findById(id)
				.orElseThrow(() -> new RuntimeException("Catégorie avec l'ID " + id + " non trouvé."));
	}

}
