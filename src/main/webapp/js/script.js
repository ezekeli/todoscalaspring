function remove(object) {
    removeFromDB(object)
    removeFromDOM(object)
}

function removeFromDOM(object) {
    var li = object.parentNode.parentNode
    li.parentNode.removeChild(li)
}

function removeFromDB(object) {
    var id = object.dataset.id
    var xhr = new XMLHttpRequest()
    xhr.open('DELETE', '/rest/' + id, false)
    xhr.send()
}


function addObject() {
    var task = createFromDOM()
    if (task != null) {
        addToDB(task)
        location.reload()
    } else alert("Заполните поле Название!")
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
}

function toggleStatus(object) {
    toggleStatusInDB(object)
    location.reload()
}

function toggleStatusInDB(object) {
    var id = object.dataset.id
    var xhr = new XMLHttpRequest()
    xhr.open('PUT', '/rest/status/' + id, false)
    xhr.send()
}