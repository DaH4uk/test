/**
 * Created by turov on 01.11.2016.
 */

$(function () {

    $('#uploadButton').click(function () {
            var servletName = 'upload';

            $("form#form").attr('action', servletName);
            $("form#form").attr('enctype', "multipart/form-data");
            $("form#form").attr("target", "postiframe");
            $("form#form").attr("file", $('#file').val());

            $('yourform').submit(); //upload button
            $("#postiframe").load(function () {
                $.ajax({
                    type: "POST",
                    url: servletName,
                    data: "action=download",
                    async: false,
                    dataType: "text",
                    success: function (result) {
                        //do something
                    }
                });
            })
        }
    )
});