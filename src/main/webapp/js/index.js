/**
 * Автор: Туров Данил
 * Релизует функционал на странице index.jsp
 * 01.11.2016.
 */

$(function () {
    /**
     * Функция обновления иформации о списке файлов на вьюхе
     * с использованием AJAX
     */
    var update = function () {
        $.ajax({    //вызываем ajax из jquery
            type: "GET",    //тип запроса GET
            url: "fileList",    //запрос полетит на /fileList
            data: "application/json",   //Тип позвращаемых данных
            async: false,   //отключаем ассинхронные запросы
            dataType: "text",   //dataType данных
            success: function (result) {    //вызывается при успешном запросе

                var list = JSON.parse(result).list; //парсит полученный JSON файл и обращается к полю list
                $('#fileInfo').empty(); //обнуляет состояние div с id fileInfo
                for (var i = 0; i < list.length; i++) {     //Формируем отображение файлов на вьюхе
                    var filename = list[i].filename;    //получаем имя файла
                    var url = 'download?filename='+ filename;   //получаем у файла название и пишем его в поле url
                    $('#fileInfo').append('<p>' +
                        '<div class="ajax-file-upload-container">' +
                        '<div class="ajax-file-upload-statusbar" style="width: 400px;">' +
                        '<div class="ajax-file-upload-filename">'+ filename+'</div>' +
                        '<div class="ajax-file-upload-progress" style=""><div class="ajax-file-upload-bar" style="width: 100%;"></div>' +
                        '</div>' +
                        '<div class="nin"><a target="_blank" href="'+url+'">Download </a> </div>' +
                        '</div>' +
                        '</p>');

                }

            }
        })
    };

    update(); //вызываем для первоначального обновления формы

    $("#fileuploader").uploadFile({     //Ининциализируем загрузчик файлов
        url: "upload",  //url, который будет обрабатывать PUT запросы на загрузку файлов
        enctype: 'multipart/form-data;charset=utf-8',   //тип передаваемых данных и кодировка
        showDone: "true",   //отображать кнопку DONE
        showFileSize: "false",  //не отображать размер файла
        onSuccess: function (files, data, xhr, pd) {    //в случае успешного завершения выполнить update
            update();

        }
    });
});