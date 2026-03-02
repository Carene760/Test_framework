<!DOCTYPE html>
<html>
<head>
    <title>Test Priorité @Param</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        .test { border: 1px solid #ccc; padding: 20px; margin: 20px 0; }
        .code { background: #f5f5f5; padding: 10px; font-family: monospace; }
        a { color: #0066cc; text-decoration: none; }
        a:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <h1>🧪 Tests Priorité @Param</h1>
    
    <div class="test">
        <h2>Test 1: @Param prioritaire</h2>
        <div class="code">
            @Url("/test/priority1")<br>
            public String testPriority1(<br>
            &nbsp;&nbsp;@Param("id") int var1,  // Prend "id"<br>
            &nbsp;&nbsp;String var2,           // Prend "var2"<br>
            &nbsp;&nbsp;int id)               // Prend "id" aussi<br>
        </div>
        <p><a href="${pageContext.request.contextPath}/test/priority1?id=100&var2=hello">/test/priority1?id=100&var2=hello</a></p>
    </div>
    
    <div class="test">
        <h2>Test 2: Conflit nom/@Param</h2>
        <div class="code">
            @Url("/test/priority2")<br>
            public String testPriority2(<br>
            &nbsp;&nbsp;@Param("name") String monNom,  // Cherche "name"<br>
            &nbsp;&nbsp;String name)                   // Cherche "name" aussi<br>
        </div>
        <p><a href="${pageContext.request.contextPath}/test/priority2?name=Alice">/test/priority2?name=Alice</a></p>
    </div>
    
    <div class="test">
        <h2>Test 3: Route paramétrée</h2>
        <div class="code">
            @Url("/etudiant/{id}")<br>
            public String testUrlParam(<br>
            &nbsp;&nbsp;@Param("id") int var1,  // Via @Param<br>
            &nbsp;&nbsp;String var2,           // Via nom<br>
            &nbsp;&nbsp;int id)               // Via nom<br>
        </div>
        <p><a href="${pageContext.request.contextPath}/etudiant/123?var2=test">/etudiant/123?var2=test</a></p>
        <p><a href="${pageContext.request.contextPath}/etudiant/456?id=999&var2=hello">/etudiant/456?id=999&var2=hello</a></p>
    </div>
    
    <div class="test">
        <h2>Test 4: Types différents</h2>
        <div class="code">
            @Url("/test/types")<br>
            public String testTypes(<br>
            &nbsp;&nbsp;@Param("num") int number,<br>
            &nbsp;&nbsp;@Param("active") boolean isActive,<br>
            &nbsp;&nbsp;@Param("price") double price,<br>
            &nbsp;&nbsp;String comment)<br>
        </div>
        <p><a href="${pageContext.request.contextPath}/test/types?num=42&active=true&price=99.99&comment=Super">/test/types avec tous les params</a></p>
    </div>
    
    <div class="test">
        <h2>Test 5: Sans @Param</h2>
        <div class="code">
            @Url("/test/simple")<br>
            public String testSimple(String query, int limit, boolean debug)<br>
        </div>
        <p><a href="${pageContext.request.contextPath}/test/simple?query=test&limit=10&debug=true">/test/simple?query=test&limit=10&debug=true</a></p>
    </div>
    
    <h3>📝 À observer dans la console :</h3>
    <ul>
        <li>Priorité: @Param > nom du paramètre</li>
        <li>Deux paramètres peuvent chercher la même valeur</li>
        <li>Conversion automatique des types</li>
    </ul>
</body>
</html>