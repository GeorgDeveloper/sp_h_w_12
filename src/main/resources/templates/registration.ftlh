<!DOCTYPE html>
<html>
<head>
    <title>Задачи</title>
</head>
<body>
<h1>Задачи</h1><hr>
<h4>Регистрация</h4>
<form action="/registration" method="post">
    Имя пользователя: <input type="text" name="name"><br><br>
    Эл. почта: <input type="email" name="email"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    <p><input type="radio" checked value="USER" name="roles">USER</p>
    <p><input type="radio" value="ADMIN" name="roles">ADMIN</p>
    <input type="submit" value="Регистрация"/>
</form>

</body>
</html>