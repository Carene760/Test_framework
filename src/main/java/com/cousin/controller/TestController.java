package com.cousin.controller;

import com.framework.annotation.Controller;
import com.framework.annotation.Url;
import com.framework.annotation.Param;

@Controller
public class TestController {

    // Test 1: Paramètre simple via GET
    @Url("/test/simple")
    public String simpleTest(@Param("name") String name) {
        return "<h2>Test Simple</h2>" +
               "<p>Paramètre 'name' reçu: " + (name != null ? name : "null") + "</p>" +
               "<p>Essaie: <a href='/test/test/simple?name=Jean'>/test/simple?name=Jean</a></p>";
    }

    // Test 2: Multiple paramètres avec types différents
    @Url("/test/multi")
    public String multiTest(
            @Param("id") int id,
            @Param("active") boolean active,
            String option) {
        
        return "<h2>Test Multi-paramètres</h2>" +
               "<ul>" +
               "<li>id (int): " + id + "</li>" +
               "<li>active (boolean): " + active + "</li>" +
               "<li>option (String): " + (option != null ? option : "null") + "</li>" +
               "</ul>" +
               "<p>Essaie: <a href='/test/test/multi?id=5&active=true&option=test'>/test/multi?id=5&active=true&option=test</a></p>";
    }

    // Test 3: Paramètres optionnels (peuvent être null)
    @Url("/test/optional")
    public String optionalTest(
            String nom,
            @Param("age") Integer age) {
        
        return "<h2>Test Paramètres Optionnels</h2>" +
               "<ul>" +
               "<li>nom: " + (nom != null ? nom : "null") + "</li>" +
               "<li>age: " + (age != null ? age : "null") + "</li>" +
               "</ul>" +
               "<p>Essaie: <a href='/test/test/optional?nom=Paul'>sans age</a> | " +
               "<a href='/test/test/optional?nom=Paul&age=25'>avec age</a></p>";
    }

    // Test 4: Route paramétrée avec paramètres GET
    @Url("/user/{userId}")
    public String userRoute(
            @Param("userId") String userIdParam, // Pour l'instant toujours null
            @Param("action") String action) {
        
        return "<h2>Route paramétrée /user/{userId}</h2>" +
               "<ul>" +
               "<li>userId (dans URL): ??? (pas encore extrait)</li>" +
               "<li>action (GET): " + (action != null ? action : "null") + "</li>" +
               "</ul>" +
               "<p>Essaie: <a href='/test/user/123?action=view'>/user/123?action=view</a></p>";
    }

    // Test 5: Sans annotation @Param
    @Url("/test/auto")
    public String autoTest(String query, int limit) {
        return "<h2>Test Sans @Param</h2>" +
               "<ul>" +
               "<li>query: " + (query != null ? query : "null") + "</li>" +
               "<li>limit: " + limit + "</li>" +
               "</ul>" +
               "<p>Essaie: <a href='/test/test/auto?query=hello&limit=10'>/test/auto?query=hello&limit=10</a></p>";
    }
}