function loadform(String) {
    var data = {
        name: document.getElementById("name").value,
        lastname: document.getElementById("lastname").value,
    }
    let request = new Request(event.target.action, {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json',
            'charset':'UTF-8',
        },
    });

    // Отправляем (асинхронно!)
    fetch(request).then(
        function (response) {
            // Запрос успешно выполнен
            console.log(response);
            // return response.json() и так далее см. документацию
        },
        function (error) {
            // Запрос не получилось отправить
            console.error(error);
        })
};