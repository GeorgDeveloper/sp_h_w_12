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
<#list tasks as task>
    <table border="1">
        <tr>
            <th>Номер задачи</th>
            <th>Название</th>
            <th>Задача</th>
            <th>Статус</th>
            <th>Дата</th>
        </tr>
        <#list tasks as task>
            <tr>
                <th>${task.id}</th>
                <th>${task.nameTask}</th>
                <th>${task.taskBody}</th>
                <th> <#list task.status as status>${status}</#list></th>
                <th> <#list task.executors as executor>${executor}</#list></th>
                <th> ${task.getDateOfCreated()}</th>
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