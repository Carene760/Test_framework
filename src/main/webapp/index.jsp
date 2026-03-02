<!DOCTYPE html>
<html>
<head>
    <title>Test Extraction URL</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        .test { border: 1px solid #3498db; padding: 20px; margin: 20px 0; border-radius: 8px; }
        h2 { color: #2c3e50; margin-top: 0; }
        .code { background: #2c3e50; color: white; padding: 10px; border-radius: 5px; font-family: monospace; }
        a { display: inline-block; margin: 10px 10px 0 0; padding: 10px 15px; 
            background: #3498db; color: white; text-decoration: none; border-radius: 4px; }
        a:hover { background: #2980b9; }
        .result { background: #f8f9fa; padding: 15px; margin: 10px 0; border-radius: 5px; }
    </style>
</head>
<body>
    <h1>🧪 Test Extraction depuis URL</h1>
    
    <div class="test">
        <h2>Test 1: Extraction Simple</h2>
        <div class="code">@Url("/etudiant/{id}")<br>public String testExtractionSimple(int id)</div>
        <a href="${pageContext.request.contextPath}/etudiant/25">/etudiant/25</a>
        <a href="${pageContext.request.contextPath}/etudiant/100">/etudiant/100</a>
    </div>
    
    <div class="test">
        <h2>Test 2: URL + Paramètres GET</h2>
        <div class="code">@Url("/etudiant/{id}/details")<br>public String testUrlPlusGet(int id, String view)</div>
        <a href="${pageContext.request.contextPath}/etudiant/25/details?view=full">/etudiant/25/details?view=full</a>
        <a href="${pageContext.request.contextPath}/etudiant/42/details">/etudiant/42/details (sans view)</a>
    </div>
    
    <div class="test">
        <h2>Test 3: @Param avec extraction URL</h2>
        <div class="code">@Url("/produit/{produitId}")<br>
public String testParamWithUrl(<br>
&nbsp;&nbsp;@Param("produitId") int var1,<br>
&nbsp;&nbsp;String produitId,<br>
&nbsp;&nbsp;@Param("action") String action)</div>
        <a href="${pageContext.request.contextPath}/produit/999?action=acheter">/produit/999?action=acheter</a>
        <a href="${pageContext.request.contextPath}/produit/456">/produit/456 (sans action)</a>
    </div>
    
    <div class="test">
        <h2>Test 4: Priorité URL > GET</h2>
        <div class="code">@Url("/article/{slug}")<br>
public String testUrlPriority(String slug, @Param("slug") String slugFromGet)</div>
        <div class="result">
            <strong>Test important:</strong> Montre que l'URL a priorité sur GET<br>
            <a href="${pageContext.request.contextPath}/article/mon-article?slug=autre">/article/mon-article?slug=autre</a><br>
            → slug = "mon-article" (depuis URL) pas "autre" (depuis GET)
        </div>
    </div>
    
    <div class="test">
        <h2>Test 5: Multiples paramètres URL</h2>
        <div class="code">@Url("/categorie/{catId}/produit/{prodId}")<br>
public String testMultipleUrlParams(<br>
&nbsp;&nbsp;@Param("catId") int categoryId,<br>
&nbsp;&nbsp;@Param("prodId") int productId,<br>
&nbsp;&nbsp;String format)</div>
        <a href="${pageContext.request.contextPath}/categorie/5/produit/42?format=json">/categorie/5/produit/42?format=json</a>
    </div>
    
    <div class="test">
        <h2>Test 6: Types différents depuis URL</h2>
        <div class="code">@Url("/user/{id}/status/{active}")<br>
public String testDifferentTypes(<br>
&nbsp;&nbsp;int id,<br>
&nbsp;&nbsp;boolean active,<br>
&nbsp;&nbsp;@Param("details") String showDetails)</div>
        <a href="${pageContext.request.contextPath}/user/123/status/true?details=yes">/user/123/status/true?details=yes</a>
        <a href="${pageContext.request.contextPath}/user/456/status/false">/user/456/status/false (sans details)</a>
    </div>
    
    <h3>📝 À observer dans la console :</h3>
    <div class="result">
        <strong>Pour /etudiant/25?var2=hello :</strong><br>
        1. 🌐 Extraction de l'URL: {id} = 25<br>
        2. 📋 Paramètres disponibles: id=25, var2=hello<br>
        3. 🔍 Résolution des paramètres<br>
        4. 🎯 Injection dans la méthode
    </div>
</body>
</html>