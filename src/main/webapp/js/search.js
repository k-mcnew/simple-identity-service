$( document ).ready(function() {
    $( "#search-btn" ).click(function() {
        if( $( "#searchInput" ).val() ) {
            submitSearch(JSON.stringify($('#searchForm').serializeObject()));
        }
    });

    $( '#add-cntnt-btn' ).click(function(){
        clearModalErrors();
        $( '#personModal' ).modal('show');
    });

    $("#addPersonForm").submit(function (e) {
        e.preventDefault();
        if (validPersonData()) {
            addPerson(JSON.stringify($('#addPersonForm').serializeObject()));
        }

        return false;
    });

    $("#addPersonBtn").click(function () {
        $("#addPersonForm").submit();
    });

    $('input.form-control', '#addPersonForm').change(function () {
        validPersonData();
    });

    addMessage({
        type: 'warning',
        message: "Note: This page is not complete, not all actions are currently working."
    });
});

function clearModalErrors() {
    $('input', '#addPersonForm').each(function () {
        $(this).val('');
        $(this).parent().removeClass("has-error");
    });
}

function submitSearch(data) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "Http://localhost:8080/identity",
        data: data,
        dataType: 'json',
        success: function (results) {
            // TODO - add success code here
            console.log("In submitSearch");
            console.log("results: " + results);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log(xhr);
            console.log(ajaxOptions);
            console.log(thrownError);
        }
    });
}

function addPerson(data) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "Http://localhost:8080/person",
        data: data,
        dataType: 'json',
        success: function (results) {
            // TODO - add success code here
            console.log("In addPerson");
            console.log("results: " + results);
            $( '#personModal' ).modal('hide');
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log(xhr);
            console.log(ajaxOptions);
            console.log(thrownError);
        }
    });
}

function validPersonData() {
    var isValid = true;

    $('input', '#addPersonForm').each(function () {
        var value = $(this).val();
        var validField = true;

        if ($(this).prop('required') && value.trim() === '') {
            isValid = false;
            validField = false;
        }

        if (validField) {
            $(this).parent().removeClass("has-error");
        } else {
            $(this).parent().addClass("has-error");
        }
    });
    $('#addPersonBtn').prop("disabled", !isValid);
    return isValid;
}

function addMessage (options) {
    var defaults = {
        id: "message-" + createGuid(),
        message: '',
        type: 'danger',
        containingElement: $('#msgCenter'),
        fadeIn: 1000,
        slideUp: 1000,
        timeout: 6000
    };
    options = $.extend(defaults, options);

    $(options.containingElement).prepend('<div id=' + options.id + ' class="alert alert-' + options.type + ' alert-dismissable">' +
        '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
        '<span aria-hidden="true">&times;</span></button></div>');

    var $handle = $('#' + options.id);
    $handle.append(options.message);
    $handle.fadeIn(options.fadeIn);
    window.setTimeout(function () {
        // closing the popup
        $handle.fadeTo(300, 0.5).slideUp(options.slideUp, function () {
            $handle.alert('close');
        });
    }, options.timeout);
}

function createGuid() {
     return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
         var r = Math.random() * 16 | 0, v = c === 'x' ? r : (r & 0x3 | 0x8);
         return v.toString(16);
     });
}

$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};