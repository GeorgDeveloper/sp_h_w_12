<!DOCTYPE html>
<html>
<head>
    <title>Задачи</title>
</head>
<body>
<h1>МОИ ЗАДАЧИ</h1>
<hr>
<h3>Все задачи</h3>
<a href="/">Назад</a><br>
<table border="1">
    <tr>
        <th>Номер задачи</th>
        <th>Название</th>
        <th>Задача</th>
        <th>Статус</th>
        <th>Исполнитель</th>
        <th>Дата</th>
        <th>Изменить</th>
        <th>Удалить</th>
    </tr>
    <#list tasks as task>
        <tr>
            <th>${task.id}</th>
            <th>${task.nameTask}</th>
            <th>${task.taskBody}</th>
            <th> <#list task.status as status>${status}</#list></th>
            <th> <#list task.executors as user>${user.email}</#list></th>
            <th> ${task.getDateOfCreated()}</th>
            <th>
                <form action="task_table/change/${task.id}" method="post">
                    <input type="submit" value="Изменить">
                </form>
            </th>
            <th>
                <form action="task_table/delete/${task.id}" method="post">
                    <input type="submit" value="Удалить">
                </form>
            </th>
        </tr>
    <#else>
        <h3>Таких задач нет</h3>
    </#list>
</table>

</body>
</html>