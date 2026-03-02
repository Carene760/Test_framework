package com.cousin.controller;

import com.framework.annotation.*;
import com.framework.model.ModelView;

@Controller
@RequestMapping("/api") // Préfixe pour toutes les routes de ce contrôleur
public class HttpMethodController {
    
    // GET - Récupérer des données
    @GetMapping("/users")
    public String getAllUsers() {
        return "<h2>GET /api/users</h2>" +
               "<p>Liste de tous les utilisateurs</p>" +
               "<form method='POST' action='/test/api/users'>" +
               "<button type='submit'>Tester POST sur /api/users</button>" +
               "</form>";
    }
    
    // GET avec paramètre
    @GetMapping("/users/{id}")
    public String getUserById(@Param("id") int userId) {
        return "<h2>GET /api/users/" + userId + "</h2>" +
               "<p>Détails de l'utilisateur " + userId + "</p>" +
               "<form method='DELETE' action='/test/api/users/" + userId + "'>" +
               "<button type='submit'>Supprimer cet utilisateur (DELETE)</button>" +
               "</form>";
    }
    
    // POST - Créer une ressource
    @PostMapping("/users")
    public String createUser(
            @Param("name") String name,
            @Param("email") String email) {
        
        return "<h2>POST /api/users</h2>" +
               "<p>Utilisateur créé avec succès!</p>" +
               "<ul>" +
               "<li>Nom: " + (name != null ? name : "non spécifié") + "</li>" +
               "<li>Email: " + (email != null ? email : "non spécifié") + "</li>" +
               "</ul>" +
               "<p><a href='/test/api/users'>Retour à la liste</a></p>";
    }
    
    // POST avec ModelView (simulation de formulaire)
    @PostMapping("/articles")
    public ModelView createArticle(
            @Param("title") String title,
            @Param("content") String content) {
        
        ModelView mv = new ModelView("/WEB-INF/views/article/created.jsp");
        mv.addAttribute("title", title);
        mv.addAttribute("content", content);
        mv.addAttribute("createdAt", new java.util.Date());
        return mv;
    }
    
    // Exemple avec @RequestMapping explicite
    @RequestMapping(value = "/search", method = "GET")
    public String search(@Param("q") String query) {
        return "<h2>GET /api/search</h2>" +
               "<p>Résultats pour: " + (query != null ? query : "tout") + "</p>";
    }
    
    // Même chemin, méthodes différentes
    @GetMapping("/item/{id}")
    public String getItem(@Param("id") int itemId) {
        return "<h2>GET /api/item/" + itemId + "</h2>" +
               "<p>Affichage de l'item " + itemId + "</p>" +
               "<form method='POST' action='/test/api/item/" + itemId + "'>" +
               "<input type='hidden' name='action' value='update'>" +
               "<button type='submit'>POST sur le même chemin</button>" +
               "</form>";
    }
    
    @PostMapping("/item/{id}")
    public String updateItem(
            @Param("id") int itemId,
            @Param("action") String action) {
        
        return "<h2>POST /api/item/" + itemId + "</h2>" +
               "<p>Action: " + (action != null ? action : "non spécifiée") + "</p>" +
               "<p>Item " + itemId + " mis à jour via POST</p>";
    }
    
    // @Url toujours supporté (rétro-compatibilité)
    @Url("/legacy")
    public String legacyEndpoint() {
        return "<h2>Route legacy avec @Url</h2>" +
               "<p>Cet endpoint utilise l'ancienne annotation @Url</p>";
    }
    
    @Url(value = "/custom", method = "POST")
    public String customMethod() {
        return "<h2>POST /api/custom avec @Url</h2>" +
               "<p>@Url supporte aussi la méthode HTTP</p>";
    }
}