<html lang="en">
<head>
    <title>Hello task!</title>
    <link rel="stylesheet" type="text/css"
          href="css/style.css" />
    <script src="js/script.js"></script>
</head>
<body>
<div id="myDIV" class="header">
    <h2>Возьмите меня на работу, пожалуйста. :)</h2>
    <input type="text" id="title" placeholder="Название...">
    <span onclick="addObject()" class="addBtn">Add</span>
    <textarea rows="5" id="content" placeholder="Опишите задачу, если хотите..."></textarea>
</div>
<ul id="myUL">
<#list tasks as task>
    <li class="<#if task.complited()>complited</#if>"><b>${task.title()}</b>&nbsp;${task.content()}
        <div class="cont">
            <span class="close" onclick="toggleStatus(this)" data-id="${task.id()}">&#10003;</span>
            <span class="close" onclick="remove(this)" data-id="${task.id()}">&#10005;</span>
        </div>
    </li>
</#list>
</ul>
</body>
</html>
