<hr>
<h3>Все задачи</h3>
<a href="/">Назад</a><br>

<h3>Изменить задачу</h3>
<br>
<table border="1">
    <tr>
        <th>Номер задачи</th>
        <th>Название</th>
        <th>Задача</th>
        <th>Статус</th>
        <th>Исполнитель</th>
        <th>Дата</th>
    </tr>
        <tr>
            <th>${regularTask.id}</th>
            <th>${regularTask.nameTask}</th>
            <th>${regularTask.taskBody}</th>
            <th> <#list regularTask.status as status>${status}</#list></th>
            <th> <#list regularTask.executors as user>${user.email}</#list></th>
            <th> ${regularTask.getDateOfCreated()}</th>
        </tr>
</table>
<br>
<form action="/update/${regularTask.id}" method="post">
    Имя задачи: <input type="text" name="nameTask"><br><br>
    Задача: <input type="text" name="taskBody"><br><br>
    Статус:
    <p><input type="radio" checked value="NOT_STARTED" name="status">NOT_STARTED</p>
    <p><input type="radio" value="IN_PROGRESS" name="status">IN_PROGRESS</p>
    <p><input type="radio" value="COMPLETED" name="status">COMPLETED</p>
    <p>
        Добавить исполнителя:
        <select name="user">
            <#list executors as user>
                <option value=${user.id}>${user.email}</option>
            </#list>
        </select><br><br>
    </p>
    <input type="submit" value="Изменить задачу"/><br><br>
</form>

</body>
</html>