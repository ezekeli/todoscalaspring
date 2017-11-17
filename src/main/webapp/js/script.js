function remove(object) {
    if(removeFromDB(object)) removeFromDOM(object);
}

function removeFromDOM(object) {
    var li = object.parentNode.parentNode;
    li.parentNode.removeChild(li);
}

function removeFromDB(object) {
    var id = object.dataset.id;
    var xhr = new XMLHttpRequest();
    xhr.open('DELETE', '/rest/' + id, false);
    xhr.send();
    if(xhr.status !== 200) {
        alert("Ошибка во время удаления объекта.");
        return false;
    } else return true;
}


function addObject() {
    var task = createFromDOM();
    if (task !== null) {
    var result = addToDB(task);
        if(result !== null) addToDOM(result);
        else alert("Ошибка при добавлении задачи.");
    } else alert("Заполните поле Название!");
}

function createFromDOM() {
    var task = {
        content: document.getElementById("content").value
    }
    var title = document.getElementById("title").value
    if (title.length > 0) {
        task.title = title
        return JSON.stringify(task)
    } else return null
}

function addToDB(task) {
    var xhr = new XMLHttpRequest()
    xhr.open('POST', '/rest', false)
    xhr.setRequestHeader("content-type", "application/json")
    xhr.send(task)
    return (xhr.status == 200)?xhr.responseText:null;
}

function addToDOM(taskInString) {
    var task = JSON.parse(taskInString)
    var newItem = `<li class="${task.complited ? 'complited' : ''}"><b>${task.title}</b>&nbsp;${task.content}
                          <div class="cont">
                              <span class="close" onclick="toggleStatus(this)" data-id="${task.id}">&#10003;</span>
                              <span class="close" onclick="remove(this)" data-id="${task.id}">&#10005;</span>
                          </div>
                  </li>`;
    var div = document.createElement('div');
    div.innerHTML = newItem
    document.querySelector('#myUL').appendChild(div.firstChild);
}
function toggleStatus(object) {
    if(toggleStatusInDB(object)) object.parentElement.parentElement.classList.toggle("complited");
    else alert("Ошибка при изменении статуса задачи.");

}

function toggleStatusInDB(object) {
    var id = object.dataset.id;
    var xhr = new XMLHttpRequest();
    xhr.open('PUT', '/rest/status/' + id, false);
    xhr.send();
    return xhr.status == 200;
}