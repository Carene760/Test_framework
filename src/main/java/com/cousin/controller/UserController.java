package com.cousin.controller;

import com.framework.annotation.Controller;
import com.framework.model.ModelView;
import com.framework.annotation.Url;

@Controller
public class UserController {

    @Url("/user/list")
    public String list() {
        return "Voici la liste des utilisateurs";
    }

    @Url("/user/add")
    public String addUser() {
        return "Page d'ajout d'utilisateur";
    }

    @Url("/user/profile")
    public ModelView userProfile() {
        ModelView mv = new ModelView("/WEB-INF/views/user/profil.jsp");
        mv.addAttribute("username", "John Doe");
        mv.addAttribute("email", "john@example.com");
        return mv;
    }
    
    // NOUVELLE ROUTE PARAMETREE
    @Url("/user/{id}")
    public String getUserById() {
        // Pour l'instant, on retourne juste un message
        // L'extraction de l'ID viendra plus tard
        return "Route paramétrée /user/{id} appelée! L'ID sera extrait plus tard.";
    }
    
    // Une autre route paramétrée pour tester
    @Url("/article/{slug}/view")
    public String viewArticle() {
        return "Route paramétrée /article/{slug}/view appelée!";
    }
    
    // Route avec plusieurs paramètres
    @Url("/category/{categoryId}/product/{productId}")
    public String viewProduct() {
        return "Route avec deux paramètres : category/{categoryId}/product/{productId}";
    }
}