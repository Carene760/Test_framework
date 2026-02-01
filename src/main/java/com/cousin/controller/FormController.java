package com.cousin.controller;

import com.framework.annotation.Controller;
import com.framework.annotation.GetMapping;
import com.framework.annotation.PostMapping;
import com.framework.annotation.Param;
import com.framework.annotation.RequestMapping;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/form")
public class FormController {
    
    // GET: Afficher le formulaire
    @GetMapping("/inscription")
    public String showInscriptionForm() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Inscription</title>
                <style>
                    body { font-family: Arial; margin: 40px; }
                    .form-group { margin: 15px 0; }
                    label { display: block; margin-bottom: 5px; }
                    input, select, textarea { 
                        padding: 8px; width: 300px; 
                        border: 1px solid #ccc; border-radius: 4px;
                    }
                    .checkbox-group { margin: 10px 0; }
                    .checkbox-group label { display: inline-block; margin-right: 15px; }
                </style>
            </head>
            <body>
                <h1>📝 Formulaire d'Inscription</h1>
                <form method="POST" action="/test/form/inscription">
                    
                    <div class="form-group">
                        <label for="nom">Nom complet:</label>
                        <input type="text" id="nom" name="nom" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="age">Âge:</label>
                        <input type="number" id="age" name="age" min="18" max="100">
                    </div>
                    
                    <div class="form-group">
                        <label>Genre:</label>
                        <select name="genre">
                            <option value="">Sélectionner...</option>
                            <option value="homme">Homme</option>
                            <option value="femme">Femme</option>
                            <option value="autre">Autre</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label>Langues parlées:</label>
                        <div class="checkbox-group">
                            <label><input type="checkbox" name="langues" value="fr"> Français</label>
                            <label><input type="checkbox" name="langues" value="en"> Anglais</label>
                            <label><input type="checkbox" name="langues" value="es"> Espagnol</label>
                            <label><input type="checkbox" name="langues" value="de"> Allemand</label>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label>Abonnements (plusieurs choix):</label>
                        <div class="checkbox-group">
                            <label><input type="checkbox" name="abonnements" value="newsletter"> Newsletter</label>
                            <label><input type="checkbox" name="abonnements" value="promotions"> Promotions</label>
                            <label><input type="checkbox" name="abonnements" value="alertes"> Alertes</label>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="niveau">Niveau d'études:</label>
                        <select name="niveau">
                            <option value="">Sélectionner...</option>
                            <option value="bac">Baccalauréat</option>
                            <option value="licence">Licence</option>
                            <option value="master">Master</option>
                            <option value="doctorat">Doctorat</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label for="commentaire">Commentaire:</label>
                        <textarea id="commentaire" name="commentaire" rows="4"></textarea>
                    </div>
                    
                    <div class="form-group">
                        <label><input type="checkbox" name="conditions" value="accepte"> J'accepte les conditions</label>
                    </div>
                    
                    <button type="submit">S'inscrire</button>
                </form>
                
                <hr>
                <h3>Tests rapides:</h3>
                <ul>
                    <li><a href="/test/form/test-simple">Test formulaire simple</a></li>
                    <li><a href="/test/form/test-mixed">Test mixte (Map + paramètres simples)</a></li>
                </ul>
            </body>
            </html>
            """;
    }
    
    // POST: Traiter le formulaire avec Map
    @PostMapping("/inscription")
    public String processInscription(Map<String, Object> formData) {
        StringBuilder response = new StringBuilder();
        response.append("""
            <!DOCTYPE html>
            <html>
            <head>
                <title>Inscription réussie</title>
                <style>
                    body { font-family: Arial; margin: 40px; }
                    .data-table { border-collapse: collapse; width: 100%; }
                    .data-table th, .data-table td { 
                        border: 1px solid #ddd; padding: 12px; text-align: left; 
                    }
                    .data-table th { background-color: #4CAF50; color: white; }
                    .data-table tr:nth-child(even) { background-color: #f2f2f2; }
                    .list-item { margin: 5px 0; }
                </style>
            </head>
            <body>
                <h1>✅ Inscription réussie !</h1>
                <h2>Données reçues via Map:</h2>
            """);
        
        response.append("<table class='data-table'>");
        response.append("<tr><th>Clé</th><th>Valeur</th><th>Type</th></tr>");
        
        for (Map.Entry<String, Object> entry : formData.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            
            response.append("<tr>");
            response.append("<td><strong>").append(key).append("</strong></td>");
            
            if (value instanceof List) {
                response.append("<td>");
                @SuppressWarnings("unchecked")
                List<String> list = (List<String>) value;
                for (String item : list) {
                    response.append("<div class='list-item'>• ").append(item).append("</div>");
                }
                response.append("</td>");
                response.append("<td>List&lt;String&gt; (").append(list.size()).append(" éléments)</td>");
            } else {
                response.append("<td>").append(value != null ? value.toString() : "null").append("</td>");
                response.append("<td>").append(value != null ? value.getClass().getSimpleName() : "null").append("</td>");
            }
            
            response.append("</tr>");
        }
        
        response.append("</table>");
        response.append("""
                <br>
                <a href="/test/form/inscription">← Retour au formulaire</a>
            </body>
            </html>
            """);
        
        return response.toString();
    }
    
    // Test avec formulaire simple
    @GetMapping("/test-simple")
    public String showSimpleForm() {
        return """
            <h2>Formulaire simple</h2>
            <form method="POST" action="/test/form/test-simple">
                <input type="text" name="username" placeholder="Nom d'utilisateur"><br>
                <input type="password" name="password" placeholder="Mot de passe"><br>
                <input type="checkbox" name="remember" value="true"> Se souvenir de moi<br>
                <button type="submit">Envoyer</button>
            </form>
            """;
    }
    
    @PostMapping("/test-simple")
    public String processSimpleForm(Map<String, Object> data) {
        return """
            <h2>Données reçues (Map simple):</h2>
            <ul>
            """ +
            data.entrySet().stream()
                .map(entry -> "<li><strong>" + entry.getKey() + ":</strong> " + 
                     entry.getValue() + " (" + 
                     (entry.getValue() != null ? entry.getValue().getClass().getSimpleName() : "null") + 
                     ")</li>")
                .reduce("", String::concat) +
            """
            </ul>
            <a href="/test/form/test-simple">← Retour</a>
            """;
    }
    
    // Test mixte: Map + paramètres simples
    @GetMapping("/test-mixed")
    public String showMixedForm() {
        return """
            <h2>Formulaire mixte</h2>
            <p>Test avec Map et paramètres simples</p>
            <form method="POST" action="/test/form/test-mixed">
                <input type="text" name="nom" placeholder="Nom"><br>
                <input type="text" name="prenom" placeholder="Prénom"><br>
                <input type="checkbox" name="hobbies" value="sport"> Sport
                <input type="checkbox" name="hobbies" value="lecture"> Lecture
                <input type="checkbox" name="hobbies" value="musique"> Musique<br>
                <button type="submit">Envoyer</button>
            </form>
            """;
    }
    
    @PostMapping("/test-mixed")
    public String processMixedForm(
            Map<String, Object> formData,
            @Param("nom") String nom,
            @Param("prenom") String prenom) {
        
        return "<h2>Test Mixte: Map + Paramètres simples</h2>" +
            "<h3>Paramètres simples (@Param):</h3>" +
            "<ul>" +
                "<li>nom: " + nom + "</li>" +
                "<li>prenom: " + prenom + "</li>" +
            "</ul>" +
            "<h3>Toutes les données (Map):</h3>" +
            "<ul>" +
            formData.entrySet().stream()
                .map(entry -> {
                    Object value = entry.getValue();
                    String displayValue;
                    if (value instanceof List) {
                        displayValue = "List: " + value;
                    } else {
                        displayValue = value != null ? value.toString() : "null";
                    }
                    return "<li><strong>" + entry.getKey() + ":</strong> " + displayValue + "</li>";
                })
                .reduce("", String::concat) +
            "</ul>" +
            "<a href=\"/test/form/test-mixed\">← Retour</a>";
    }
    
    // Test avec checkbox multiples et types différents
    @GetMapping("/test-advanced")
    public String showAdvancedForm() {
        return "<h2>Formulaire avancé</h2>" +
            "<form method=\"POST\" action=\"/test/form/test-advanced\">" +
                "<h3>Informations personnelles</h3>" +
                "<input type=\"text\" name=\"personne.nom\" placeholder=\"Nom\"><br>" +
                "<input type=\"text\" name=\"personne.prenom\" placeholder=\"Prénom\"><br>" +
                "<input type=\"number\" name=\"personne.age\" placeholder=\"Âge\"><br>" +
                "<h3>Compétences (plusieurs choix)</h3>" +
                "<label><input type=\"checkbox\" name=\"competences\" value=\"java\"> Java</label>" +
                "<label><input type=\"checkbox\" name=\"competences\" value=\"python\"> Python</label>" +
                "<label><input type=\"checkbox\" name=\"competences\" value=\"javascript\"> JavaScript</label>" +
                "<label><input type=\"checkbox\" name=\"competences\" value=\"sql\"> SQL</label>" +
                "<h3>Préférences</h3>" +
                "<label><input type=\"radio\" name=\"preference\" value=\"frontend\"> Frontend</label>" +
                "<label><input type=\"radio\" name=\"preference\" value=\"backend\"> Backend</label>" +
                "<label><input type=\"radio\" name=\"preference\" value=\"fullstack\"> Fullstack</label>" +
                "<h3>Options</h3>" +
                "<label><input type=\"checkbox\" name=\"newsletter\" value=\"true\"> S'abonner à la newsletter</label><br>" +
                "<label><input type=\"checkbox\" name=\"notifications\" value=\"email\"> Notifications email</label>" +
                "<label><input type=\"checkbox\" name=\"notifications\" value=\"sms\"> Notifications SMS</label>" +
                "<br><br>" +
                "<button type=\"submit\">Envoyer</button>" +
            "</form>";
    }
    
    @PostMapping("/test-advanced")
    public String processAdvancedForm(Map<String, Object> data) {
        return "<h2>Formulaire avancé - Résultats</h2>" +
            "<h3>Toutes les données:</h3>" +
            "<pre>" +
            data.entrySet().stream()
                .map(entry -> entry.getKey() + " = " + 
                     (entry.getValue() instanceof List ? 
                      "List: " + entry.getValue() : 
                      entry.getValue()))
                .sorted()
                .collect(java.util.stream.Collectors.joining("\n")) +
            "</pre>" +
            "<a href=\"/test/form/test-advanced\">← Retour</a>";
    }
}