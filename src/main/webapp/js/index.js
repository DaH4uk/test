/**
 * Created by turov on 01.11.2016.
 */

$(function () {


    var update = function () {
        $.ajax({
            type: "GET",
            url: "fileList",
            data: "application/json",
            async: false,
            dataType: "text",
            success: function (result) {
                //do something
                var list = JSON.parse(result).list;
                $('#fileInfo').reset;
                for (var i = 0; i < list.length; i++) {
                    var url = 'download?filename='+ list[i].filename;
                    var filename = list[i].filename;
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
                // $('.green').bind('click', function () {

                // })
            }
        })
    };

    update();

    $("#fileuploader").uploadFile({
        url: "upload",
        showDone: "true",
        showFileSize: "false",
        onSuccess: function (files, data, xhr, pd) {
            var url = 'download?filename='+ files;
            var filename = files;
            $('#fileInfo').append('<p>' +
                '<div class="ajax-file-upload-container">' +
                '<div class="ajax-file-upload-statusbar" style="width: 400px;"><img class="ajax-file-upload-preview" style="width: 100%; height: auto; display: none;">' +
                '<div class="ajax-file-upload-filename">'+ filename+'</div>' +
                '<div class="ajax-file-upload-progress" style=""><div class="ajax-file-upload-bar" style="width: 100%;"></div>' +
                '</div>' +
                '<div class="nin"><a target="_blank" href="'+url+'">Download </a> </div>' +
                '</div>' +
                '</p>');

        }
    });
});