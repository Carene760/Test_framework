<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Test Routes Paramétrées - Framework Maison</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            line-height: 1.6;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
        }
        .test-list {
            list-style: none;
            padding: 0;
        }
        .test-item {
            background: #f5f5f5;
            margin: 10px 0;
            padding: 15px;
            border-radius: 5px;
            border-left: 4px solid #4CAF50;
        }
        .url {
            font-family: monospace;
            background: #333;
            color: white;
            padding: 5px 10px;
            border-radius: 3px;
            display: inline-block;
            margin: 5px 0;
        }
        .btn {
            background: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            margin: 10px 5px;
        }
        .btn:hover {
            background: #45a049;
        }
        .description {
            color: #666;
            font-size: 0.9em;
            margin: 5px 0;
        }
        h1 {
            color: #333;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🧪 Test des Routes Paramétrées</h1>
        <p>Ceci est une page de test pour vérifier le fonctionnement des routes paramétrées dans votre framework.</p>
        
        <h2>Routes à tester :</h2>
        <ul class="test-list">
            <li class="test-item">
                <h3>1. Route simple (existant)</h3>
                <div class="url">/test/user/list</div>
                <div class="description">Devrait afficher: "Voici la liste des utilisateurs"</div>
                <a href="${pageContext.request.contextPath}/user/list" class="btn">Tester</a>
            </li>
            
            <li class="test-item">
                <h3>2. Route paramétrée simple</h3>
                <div class="url">/test/user/123</div>
                <div class="description">Devrait matcher avec /user/{id} et afficher un message</div>
                <a href="${pageContext.request.contextPath}/user/123" class="btn">Tester avec ID 123</a>
                <a href="${pageContext.request.contextPath}/user/999" class="btn">Tester avec ID 999</a>
            </li>
            
            <li class="test-item">
                <h3>3. Route paramétrée avec slug</h3>
                <div class="url">/test/article/mon-premier-article/view</div>
                <div class="description">Devrait matcher avec /article/{slug}/view</div>
                <a href="${pageContext.request.contextPath}/article/mon-premier-article/view" class="btn">Tester</a>
            </li>
            
            <li class="test-item">
                <h3>4. Route avec deux paramètres</h3>
                <div class="url">/test/category/5/product/42</div>
                <div class="description">Devrait matcher avec /category/{categoryId}/product/{productId}</div>
                <a href="${pageContext.request.contextPath}/category/5/product/42" class="btn">Tester</a>
            </li>
            
            <li class="test-item">
                <h3>5. Route inexistante</h3>
                <div class="url">/test/user/123/profile (n'existe pas)</div>
                <div class="description">Devrait afficher "Aucune route trouvée"</div>
                <a href="${pageContext.request.contextPath}/user/123/profile" class="btn">Tester erreur</a>
            </li>
        </ul>
        
        <h2>Résultats attendus :</h2>
        <p>Pour les routes paramétrées, vous devriez voir dans la console Tomcat :</p>
        <pre>
🔍 Route paramétrée matchée : /test/user/123 -> /user/{id}
🔍 Route paramétrée matchée : /test/article/titre/view -> /article/{slug}/view
        </pre>
    </div>
</body>
</html>