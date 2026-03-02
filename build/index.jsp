<!DOCTYPE html>
<html>
<head>
    <title>Test Méthodes HTTP</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        .method { margin: 30px 0; padding: 20px; border-radius: 8px; }
        .get { border-left: 5px solid #4CAF50; background: #f1f8e9; }
        .post { border-left: 5px solid #2196F3; background: #e3f2fd; }
        .put { border-left: 5px solid #FF9800; background: #fff3e0; }
        .delete { border-left: 5px solid #f44336; background: #ffebee; }
        h2 { margin-top: 0; }
        .btn { 
            display: inline-block; padding: 10px 15px; margin: 5px;
            text-decoration: none; border-radius: 4px; color: white;
        }
        .btn-get { background: #4CAF50; }
        .btn-post { background: #2196F3; }
        .btn-put { background: #FF9800; }
        .btn-delete { background: #f44336; }
        .btn:hover { opacity: 0.9; }
        form { display: inline; }
        .code { background: #2c3e50; color: white; padding: 10px; border-radius: 5px; }
    </style>
</head>
<body>
    <h1>🧪 Test des Méthodes HTTP</h1>
    
    <div class="method get">
        <h2>GET - Récupération</h2>
        <div class="code">@GetMapping("/api/users")</div>
        <a class="btn btn-get" href="${pageContext.request.contextPath}/api/users">GET /api/users</a>
        
        <div class="code" style="margin-top: 15px;">@GetMapping("/api/users/{id}")</div>
        <a class="btn btn-get" href="${pageContext.request.contextPath}/api/users/1">GET /api/users/1</a>
        <a class="btn btn-get" href="${pageContext.request.contextPath}/api/users/42">GET /api/users/42</a>
    </div>
    
    <div class="method post">
        <h2>POST - Création</h2>
        <div class="code">@PostMapping("/api/users")</div>
        <form method="POST" action="${pageContext.request.contextPath}/api/users" style="display: block;">
            <input type="text" name="name" placeholder="Nom" value="Jean Dupont">
            <input type="email" name="email" placeholder="Email" value="jean@example.com">
            <button type="submit" class="btn btn-post">POST /api/users</button>
        </form>
        
        <div class="code" style="margin-top: 15px;">@PostMapping("/api/articles")</div>
        <form method="POST" action="${pageContext.request.contextPath}/api/articles">
            <input type="text" name="title" placeholder="Titre" value="Mon Article">
            <textarea name="content" placeholder="Contenu">Contenu de l'article...</textarea>
            <button type="submit" class="btn btn-post">POST /api/articles</button>
        </form>
    </div>
    
    <div class="method get">
        <h2>Même chemin, méthodes différentes</h2>
        <div class="code">@GetMapping("/api/item/{id}")<br>@PostMapping("/api/item/{id}")</div>
        <a class="btn btn-get" href="${pageContext.request.contextPath}/api/item/100">GET /api/item/100</a>
        <form method="POST" action="${pageContext.request.contextPath}/api/item/100" style="display: inline;">
            <input type="hidden" name="action" value="test">
            <button type="submit" class="btn btn-post">POST /api/item/100</button>
        </form>
    </div>
    
    <div class="method get">
        <h2>Compatibilité @Url</h2>
        <div class="code">@Url("/legacy")<br>@Url(value="/custom", method="POST")</div>
        <a class="btn btn-get" href="${pageContext.request.contextPath}/api/legacy">GET /api/legacy (@Url)</a>
        <form method="POST" action="${pageContext.request.contextPath}/api/custom" style="display: inline;">
            <button type="submit" class="btn btn-post">POST /api/custom (@Url)</button>
        </form>
    </div>
    
    <h3>📝 À observer dans la console :</h3>
    <div style="background: #f8f9fa; padding: 15px; border-radius: 5px;">
        <pre>
=== Nouvelle requête ===
🌐 GET /api/users
✅ Route exacte trouvée: GET /api/users -> HttpMethodController.getAllUsers
📋 Tous les paramètres disponibles...
🎯 Arguments pour getAllUsers: []

=== Nouvelle requête ===
🌐 POST /api/users
✅ Route exacte trouvée: POST /api/users -> HttpMethodController.createUser
📋 Tous les paramètres disponibles...
🎯 Arguments pour createUser: [name, email]
        </pre>
    </div>
    
    <script>
        // Support des méthodes PUT/DELETE via POST + paramètre _method
        document.querySelectorAll('form').forEach(form => {
            form.addEventListener('submit', function(e) {
                const method = this.querySelector('[name="_method"]');
                if (method && method.value) {
                    // Créer un champ caché pour la méthode réelle
                    const realMethod = document.createElement('input');
                    realMethod.type = 'hidden';
                    realMethod.name = '_method';
                    realMethod.value = method.value;
                    this.appendChild(realMethod);
                }
            });
        });
    </script>
</body>
</html>