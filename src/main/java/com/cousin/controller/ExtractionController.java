package com.cousin.controller;

import com.framework.annotation.Controller;
import com.framework.annotation.Url;
import com.framework.annotation.Param;

@Controller
public class ExtractionController {
    
    // TEST 1: Extraction simple depuis l'URL
    @Url("/etudiant/{id}")
    public String testExtractionSimple(int id) {
        return "<h2>Test 1: Extraction Simple</h2>" +
               "<h3>Pattern: /etudiant/{id}</h3>" +
               "<ul>" +
               "<li>id (depuis URL): " + id + "</li>" +
               "</ul>" +
               "<p><a href='/test/etudiant/25'>Tester avec id=25</a></p>" +
               "<p><a href='/test/etudiant/100'>Tester avec id=100</a></p>";
    }
    
    // TEST 2: URL + paramètres GET
    @Url("/etudiant/{id}/details")
    public String testUrlPlusGet(int id, String view) {
        return "<h2>Test 2: URL + GET</h2>" +
               "<h3>Pattern: /etudiant/{id}/details</h3>" +
               "<ul>" +
               "<li>id (depuis URL): " + id + "</li>" +
               "<li>view (depuis GET): " + (view != null ? view : "null") + "</li>" +
               "</ul>" +
               "<p><a href='/test/etudiant/25/details?view=full'>Tester id=25, view=full</a></p>" +
               "<p><a href='/test/etudiant/42/details'>Tester sans paramètre view</a></p>";
    }
    
    // TEST 3: @Param avec extraction URL
    @Url("/produit/{produitId}")
    public String testParamWithUrl(
            @Param("produitId") int var1,
            String produitId,
            @Param("action") String action) {
        
        return "<h2>Test 3: @Param avec URL</h2>" +
               "<h3>Pattern: /produit/{produitId}</h3>" +
               "<ul>" +
               "<li>var1 (@Param='produitId'): " + var1 + "</li>" +
               "<li>produitId (nom paramètre): " + produitId + "</li>" +
               "<li>action (@Param='action'): " + (action != null ? action : "null") + "</li>" +
               "</ul>" +
               "<p><a href='/test/produit/999?action=acheter'>Tester produitId=999, action=acheter</a></p>" +
               "<p><a href='/test/produit/456'>Tester sans action</a></p>";
    }
    
    // TEST 4: Conflit URL vs GET (URL prioritaire)
    @Url("/article/{slug}")
    public String testUrlPriority(String slug, @Param("slug") String slugFromGet) {
        return "<h2>Test 4: Priorité URL > GET</h2>" +
               "<h3>Pattern: /article/{slug}</h3>" +
               "<ul>" +
               "<li>slug (depuis URL): " + slug + " (devrait être depuis l'URL)</li>" +
               "<li>slugFromGet (@Param='slug' depuis GET): " + 
               (slugFromGet != null ? slugFromGet : "null") + " (devrait être le même)</li>" +
               "</ul>" +
               "<p><strong>Note:</strong> L'URL a priorité sur GET</p>" +
               "<p><a href='/test/article/mon-article?slug=autre'>/article/mon-article?slug=autre</a></p>" +
               "<p>→ slug = 'mon-article' (URL) pas 'autre' (GET)</p>";
    }
    
    // TEST 5: Multiples paramètres dans l'URL
    @Url("/categorie/{catId}/produit/{prodId}")
    public String testMultipleUrlParams(
            @Param("catId") int categoryId,
            @Param("prodId") int productId,
            String format) {
        
        return "<h2>Test 5: Multiples paramètres URL</h2>" +
               "<h3>Pattern: /categorie/{catId}/produit/{prodId}</h3>" +
               "<ul>" +
               "<li>categoryId (@Param='catId'): " + categoryId + "</li>" +
               "<li>productId (@Param='prodId'): " + productId + "</li>" +
               "<li>format (depuis GET): " + (format != null ? format : "null") + "</li>" +
               "</ul>" +
               "<p><a href='/test/categorie/5/produit/42?format=json'>Tester cat=5, prod=42, format=json</a></p>";
    }
    
    // TEST 6: Types différents depuis URL
    @Url("/user/{id}/status/{active}")
    public String testDifferentTypes(
            int id,
            boolean active,
            @Param("details") String showDetails) {
        
        return "<h2>Test 6: Types différents</h2>" +
               "<h3>Pattern: /user/{id}/status/{active}</h3>" +
               "<ul>" +
               "<li>id (int depuis URL): " + id + "</li>" +
               "<li>active (boolean depuis URL): " + active + "</li>" +
               "<li>showDetails (String depuis GET): " + (showDetails != null ? showDetails : "null") + "</li>" +
               "</ul>" +
               "<p><a href='/test/user/123/status/true?details=yes'>Tester id=123, active=true, details=yes</a></p>" +
               "<p><a href='/test/user/456/status/false'>Tester sans details</a></p>";
    }
}