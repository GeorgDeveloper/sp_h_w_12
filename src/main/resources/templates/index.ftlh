<!DOCTYPE html>
<html>
<head>
    <title>Задачи</title>
</head>
<body>
<h1>МОИ ЗАДАЧИ</h1>
<hr>
<#if userIsActiveRole>
    <h3>Создать новую задачу</h3>
    <form action="/create" method="post">
        <p>
            Имя задачи: <input type="text" name="nameTask"><br><br>
            Задача: <input type="text" name="taskBody"><br><br>
        </p>
        <p>
            Назначить исполнителя:
            <select name="user">
                <#list executors as user>
                    <option value=${user.id}>${user.email}</option>
                </#list>
            </select><br><br>
        </p>

        <input type="submit" value="Добавить задачу"/><br><br>
    </form>

    <form action="/search" method="get">
        Поиск по статусу задачи: <br>
        <p><input type="radio" checked value="NOT_STARTED" name="stat">NOT_STARTED</p>
        <p><input type="radio" value="IN_PROGRESS" name="stat">IN_PROGRESS</p>
        <p><input type="radio" value="COMPLETED" name="stat">COMPLETED</p>
        <button type="submit">Поиск</button>
    </form>
    <table border="1">
        <tr>
            <th>Номер задачи</th>
            <th>Название</th>
            <th>Задача</th>
            <th>Статус</th>
            <th>Исполнитель</th>
            <th>Дата</th>
        </tr>
        <#list tasks as task>
            <tr>
                <th>${task.id}</th>
                <th>${task.nameTask}</th>
                <th>${task.taskBody}</th>
                <th> <#list task.status as status>${status}</#list></th>
                <th> <#list task.executors as user>${user.email}</#list></th>
                <th> ${task.getDateOfCreated()}</th>
            </tr>
        <#else>
            <h3>Таких задач нет</h3>
        </#list>
    </table>

    <#else>
        <table border="1">
            <tr>
                <th>Номер задачи</th>
                <th>Название</th>
                <th>Задача</th>
                <th>Статус</th>
                <th>Исполнитель</th>
                <th>Дата</th>
            </tr>
            <#list tasksUser as task>
                <#if userIsActiveRole>
                    <tr>
                        <th>${task.id}</th>
                        <th>${task.nameTask}</th>
                        <th>${task.taskBody}</th>
                        <th> <#list task.status as status>${status}</#list></th>
                        <th> <#list task.executors as user>${user.email}</#list></th>
                        <th> ${task.getDateOfCreated()}</th>
                    </tr>
                <#else>
                    <tr>
                        <th>${task.id}</th>
                        <th>${task.nameTask}</th>
                        <th>${task.taskBody}</th>
                        <th> <#list task.status as status>${status}</#list></th>
                        <th> <#list task.executors as user>${user.email}</#list></th>
                        <th> ${task.getDateOfCreated()}</th>
                    </tr>
                </#if>
            <#else>
                <h3>Таких задач нет</h3>
            </#list>
        </table>
</#if>


</body>
</html>