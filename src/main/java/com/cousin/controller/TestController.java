package com.cousin.controller;

import com.framework.annotation.Controller;
import com.framework.annotation.Url;
import com.framework.annotation.Param;

@Controller
public class TestController {
    
    // TEST 1: Priorité @Param > nom du paramètre
    @Url("/test/priority1")
    public String testPriority1(
            @Param("id") int var1,    // Doit prendre la valeur de "id" dans les paramètres
            String var2,               // Doit prendre la valeur de "var2" dans les paramètres
            int id) {                  // Doit prendre la valeur de "id" aussi
        return "<h2>Test Priorité 1</h2>" +
               "<h3>URL: /test/priority1?id=100&var2=hello</h3>" +
               "<ul>" +
               "<li>var1 (@Param='id'): " + var1 + " (devrait être 100)</li>" +
               "<li>var2 (nom='var2'): " + var2 + " (devrait être 'hello')</li>" +
               "<li>id (nom='id'): " + id + " (devrait être 100 aussi)</li>" +
               "</ul>" +
               "<p><a href='/test/test/priority1?id=100&var2=hello'>Tester</a></p>";
    }
    
    // TEST 2: Conflit entre @Param et paramètre GET
    @Url("/test/priority2")
    public String testPriority2(
            @Param("name") String monNom,   // Cherche "name" dans les paramètres
            String name) {                  // Cherche "name" aussi
        return "<h2>Test Priorité 2</h2>" +
               "<h3>Conflit: deux paramètres cherchent 'name'</h3>" +
               "<ul>" +
               "<li>monNom (@Param='name'): " + monNom + "</li>" +
               "<li>name (nom='name'): " + name + "</li>" +
               "</ul>" +
               "<p>Les deux devraient avoir la même valeur!</p>" +
               "<p><a href='/test/test/priority2?name=Alice'>Tester avec name=Alice</a></p>";
    }
    
    // TEST 3: Route paramétrée avec @Param
    @Url("/etudiant/{id}")
    public String testUrlParam(
            @Param("id") int var1,    // Pour l'instant via GET, plus tard via URL
            String var2,              // Via GET
            int id) {                 // Via GET aussi
        return "<h2>Test Route Paramétrée</h2>" +
               "<h3>Pattern: /etudiant/{id}</h3>" +
               "<ul>" +
               "<li>var1 (@Param='id'): " + var1 + "</li>" +
               "<li>var2: " + (var2 != null ? var2 : "null") + "</li>" +
               "<li>id: " + id + "</li>" +
               "</ul>" +
               "<p>Pour l'instant, l'ID dans l'URL n'est pas extrait automatiquement</p>" +
               "<p><a href='/test/etudiant/123?var2=test'>Tester /etudiant/123?var2=test</a></p>" +
               "<p><a href='/test/etudiant/456?id=999&var2=hello'>Tester avec id dans GET aussi</a></p>";
    }
    
    // TEST 4: Types différents avec @Param
    @Url("/test/types")
    public String testTypes(
            @Param("num") int number,
            @Param("active") boolean isActive,
            @Param("price") double price,
            String comment) {
        return "<h2>Test Types avec @Param</h2>" +
               "<ul>" +
               "<li>number (int): " + number + "</li>" +
               "<li>isActive (boolean): " + isActive + "</li>" +
               "<li>price (double): " + price + "</li>" +
               "<li>comment (String): " + (comment != null ? comment : "null") + "</li>" +
               "</ul>" +
               "<p><a href='/test/test/types?num=42&active=true&price=99.99&comment=Super'>Tester</a></p>";
    }
    
    // TEST 5: Sans @Param
    @Url("/test/simple")
    public String testSimple(String query, int limit, boolean debug) {
        return "<h2>Test Sans @Param</h2>" +
               "<ul>" +
               "<li>query: " + (query != null ? query : "null") + "</li>" +
               "<li>limit: " + limit + "</li>" +
               "<li>debug: " + debug + "</li>" +
               "</ul>" +
               "<p><a href='/test/test/simple?query=test&limit=10&debug=true'>Tester</a></p>";
    }
}