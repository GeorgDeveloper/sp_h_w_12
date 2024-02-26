<!DOCTYPE html>
<html>
<head>
    <title>Задачи</title>
</head>
<body>
<h1>Задачи</h1><hr>
<h3>Подробная информация о пользователе ${user.name}</h3>
<b>Email: </b> ${user.email}<br>
<h4>Задачи пользователя ${user.name}</h4>
<#list tasks as regularTask>
    <table border="1">
        <tr>
            <th>Номер задачи</th>
            <th>Название</th>
            <th>Задача</th>
            <th>Статус</th>
            <th>Дата</th>
        </tr>
        <#list tasks as regularTask>
            <tr>
                <th>${regularTask.id}</th>
                <th>${regularTask.nameTask}</th>
                <th>${regularTask.taskBody}</th>
                <th> <#list regularTask.status as status>${status}</#list></th>
                <th> <#list regularTask.executors as executor>${executor}</#list></th>
                <th> ${regularTask.getDateOfCreated()}</th>
            </tr>
        <#else>
            <h3>Таких задач нет</h3>
        </#list>
    </table>
<#else>
    <h3>Задач нет</h3>
</#list>

</body>
</html>