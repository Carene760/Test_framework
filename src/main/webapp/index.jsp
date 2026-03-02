<!DOCTYPE html>
<html>
<head><title>Test Ultra Simple</title></head>
<body>
    <h1>⚡ Tests Ultra Simples</h1>
    
    <h2>1. Test basique</h2>
    <a href="${pageContext.request.contextPath}/test/simple?name=Test">/test/simple?name=Test</a><br>
    <a href="${pageContext.request.contextPath}/test/simple">/test/simple (sans param)</a>
    
    <h2>2. Avec types différents</h2>
    <a href="${pageContext.request.contextPath}/test/multi?id=10&active=false&option=ok">/test/multi avec params</a>
    
    <h2>3. Optionnels</h2>
    <a href="${pageContext.request.contextPath}/test/optional?nom=Alice">Sans âge</a><br>
    <a href="${pageContext.request.contextPath}/test/optional?nom=Bob&age=30">Avec âge</a>
    
    <h2>4. Route paramétrée</h2>
    <a href="${pageContext.request.contextPath}/user/999?action=edit">/user/999?action=edit</a>
    
    <h2>5. Sans @Param</h2>
    <a href="${pageContext.request.contextPath}/test/auto?query=search&limit=20">/test/auto?query=search&limit=20</a>
</body>
</html>