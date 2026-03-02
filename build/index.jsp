<!DOCTYPE html>
<html>
<head>
    <title>Test Map Formulaires</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        .card { 
            border: 1px solid #3498db; 
            padding: 20px; 
            margin: 20px 0; 
            border-radius: 8px;
            background: #f8f9fa;
        }
        h2 { color: #2c3e50; margin-top: 0; }
        .btn { 
            display: inline-block; 
            padding: 10px 20px; 
            margin: 10px 10px 0 0;
            background: #3498db; 
            color: white; 
            text-decoration: none; 
            border-radius: 4px;
        }
        .btn:hover { background: #2980b9; }
        .code { 
            background: #2c3e50; 
            color: white; 
            padding: 15px; 
            border-radius: 5px;
            font-family: monospace;
            margin: 15px 0;
        }
        .feature { color: #27ae60; font-weight: bold; }
    </style>
</head>
<body>
    <h1>🗺️ Test Map comme Paramètre</h1>
    
    <div class="card">
        <h2>Fonctionnalités implémentées:</h2>
        <ul>
            <li><span class="feature">✅</span> Injection automatique de <code>Map&lt;String, Object&gt;</code></li>
            <li><span class="feature">✅</span> Support des checkbox multiples (List&lt;String&gt;)</li>
            <li><span class="feature">✅</span> Combinaison Map + paramètres simples</li>
            <li><span class="feature">✅</span> Conversion automatique des types</li>
            <li><span class="feature">✅</span> Récupération de TOUS les paramètres du formulaire</li>
        </ul>
    </div>
    
    <div class="card">
        <h2>Exemple de code:</h2>
        <div class="code">
@PostMapping("/inscription")<br>
public String processInscription(Map&lt;String, Object&gt; formData) {<br>
&nbsp;&nbsp;// formData contient TOUS les champs du formulaire<br>
&nbsp;&nbsp;// - Champs texte: valeur String<br>
&nbsp;&nbsp;// - Checkbox multiples: List&lt;String&gt;<br>
&nbsp;&nbsp;// - Checkbox simple: "true" ou null<br>
&nbsp;&nbsp;// - Radio: String<br>
&nbsp;&nbsp;// - Select: String<br>
}
        </div>
    </div>
    
    <div class="card">
        <h2>1. Formulaire complet d'inscription</h2>
        <p>Test avec tous les types de champs: texte, email, nombre, select, checkbox multiples, textarea.</p>
        <a class="btn" href="${pageContext.request.contextPath}/form/inscription">📝 Formulaire complet</a>
    </div>
    
    <div class="card">
        <h2>2. Formulaire simple</h2>
        <p>Test basique avec quelques champs.</p>
        <a class="btn" href="${pageContext.request.contextPath}/form/test-simple">🧪 Formulaire simple</a>
    </div>
    
    <div class="card">
        <h2>3. Test mixte (Map + @Param)</h2>
        <p>Combinaison de Map et paramètres annotés avec @Param.</p>
        <div class="code">
@PostMapping("/test-mixed")<br>
public String processMixedForm(<br>
&nbsp;&nbsp;Map&lt;String, Object&gt; formData,<br>
&nbsp;&nbsp;@Param("nom") String nom,<br>
&nbsp;&nbsp;@Param("prenom") String prenom) { ... }
        </div>
        <a class="btn" href="${pageContext.request.contextPath}/form/test-mixed">🔄 Test mixte</a>
    </div>
    
    <div class="card">
        <h2>4. Formulaire avancé</h2>
        <p>Test avec noms de champs complexes (personne.nom) et multiples groupes de checkbox.</p>
        <a class="btn" href="${pageContext.request.contextPath}/form/test-advanced">🚀 Formulaire avancé</a>
    </div>
    
    <div class="card">
        <h3>📝 À observer dans la console :</h3>
        <div style="background: white; padding: 15px; border-radius: 5px;">
            <pre>
=== Nouvelle requête ===
🌐 POST /form/inscription
✅ Route exacte trouvée: POST /form/inscription
📋 Tous les paramètres disponibles:
  - nom = Jean Dupont
  - email = jean@example.com
  - age = 25
  - genre = homme
  - langues = [fr, en]
  - abonnements = [newsletter, promotions]
  - niveau = master
  - commentaire = Test commentaire
  - conditions = accepte
  🗺️  Paramètre Map détecté: formData
    📦 nom = Jean Dupont
    📦 email = jean@example.com
    📦 age = 25
    📦 genre = homme
    📦 langues = [fr, en] (liste)
    📦 abonnements = [newsletter, promotions] (liste)
    📦 niveau = master
    📦 commentaire = Test commentaire
    📦 conditions = accepte
🎯 Arguments pour processInscription:
  [0] Map formData = Map avec 9 entrées: [nom, email, age, genre, langues, ...]
            </pre>
        </div>
    </div>
</body>
</html>