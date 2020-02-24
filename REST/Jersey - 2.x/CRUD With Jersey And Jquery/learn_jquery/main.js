jQuery(function($) {
    "use strict";

    if ($.cookie('jwt')) {
        $.cookie('jwt', null, { expires: 0});
        sessionStorage.clear();
    }
    
    function getAllStudents() {
        $.ajax({
            url: "http://localhost:8080/server_learn_jquery_war_exploded/rest/student",
            type: "GET",
            dataType: "json",
        }).done(function(response) {
            drawDataToTable(response);
        }).fail(function(xhr, textStatus, errorThrown) {
            console.log(xhr, textStatus, errorThrown);
        })
    }

    function drawDataToTable(students) {

        let table = $('table.table.table-striped');

        for (let i = 0; i < students.length; i++) {
            let row = $('<tr></tr>');

            let {id, firstName, lastName, address} = students[i];

            let tdId = $('<td></td>').text(id);
            let tdFirstName = $('<td></td>').text(firstName);
            let tdLastName = $('<td></td>').text(lastName);
            let tdAddress = $('<td></td>').text(address);

            let iconDelete = $('<a href="#" id="btnDelete"><i class="fa fa-trash"></i></a>')[0];
            iconDelete.addEventListener('click', function() {
                if (confirm('Are you sure you want to delete')) {
                    $.ajax({
                        headers: {
                            'Authorization': $.cookie('jwt') ? $.cookie('jwt') : '',
                        },
                        url: `http://localhost:8080/server_learn_jquery_war_exploded/rest/student/${id}`,
                        type: 'DELETE',
                        dataType: 'text',
                    }).done(function() {
                        row.remove();
                    }).fail(function(xhr, textStatus, errorThrown) {
                        alert(xhr.responseText);
                    })
                }
            });

            let iconEdit = $('<a href="#" id="btnEdit" data-toggle="modal" data-target="#edit"><i class="fa fa-edit"></i></a>')[0];
            iconEdit.addEventListener('click', function () {
                $('input[id=editId]').val(id);
                $('input[id=editFirstName]').val(firstName);
                $('input[id=editLastName]').val(lastName);
                $('input[id=editAddress]').val(address);

                eventUpdateStudent(tdId, tdFirstName, tdLastName, tdAddress);
            });

            let tdDelete = $('<td style="text-align: center"></td>').append(iconDelete);
            let tdEdit = $('<td style="text-align: center"></td>').append(iconEdit);

            row.append(tdId, tdFirstName, tdLastName, tdAddress, tdDelete, tdEdit);
            table.append(row);
        }
    }

    function eventSortUp() {
        let ups = $('a[id$=Up]');
        for (let up of ups) {
            up.addEventListener('click', function() {
                let students = getAllStudentsFromTable();

                let id = up.getAttribute('id');
                let field = id.substring(0, id.indexOf('Up'));

                let i = up.querySelector('i[class^=fa]');

                if (i.className === 'fa fa-angle-up') {
                    students = sortDownDataStudent(students, field);
                    i.classList.replace('fa-angle-up', 'fa-angle-down')
                }
                else {
                    students = sortUpDataStudent(students, field);
                    i.classList.replace('fa-angle-down', 'fa-angle-up')
                }

                deleteDataFromTable();
                drawDataToTable(students);
            })
        }
    }

    function getAllStudentsFromTable() {
        let students = [];
        let table = $('table.table.table-striped')[0];

        for (let i = 1; i < table.rows.length; i++) {
            let id = table.rows[i].cells[0].textContent;
            let firstName = table.rows[i].cells[1].textContent;
            let lastName = table.rows[i].cells[2].textContent;
            let address = table.rows[i].cells[3].textContent;

            students.push({id, firstName, lastName, address});
        }
        return students;
    }

    function deleteDataFromTable() {
        let table = $('table.table.table-striped')[0];
        for (let i = 1; i < table.rows.length; i++) {
            table.rows[i--].remove();
        }
    }

    function sortUpDataStudent(students, field) {
        switch(field) {
            case 'id':
                return students.sort((a, b) => +a.id - +b.id);
            case 'firstName':
                return students.sort((a, b) => a.firstName.localeCompare(b.firstName));
            case 'lastName':
                return students.sort((a, b) => a.lastName.localeCompare(b.lastName));
            default:
                return students.sort((a, b) => a.address.localeCompare(b.address));
        }
    }

    function sortDownDataStudent(students, field) {
        switch(field) {
            case 'id':
                return students.sort((a, b) => +b.id - +a.id);
            case 'firstName':
                return students.sort((a, b) => b.firstName.localeCompare(a.firstName));
            case 'lastName':
                return students.sort((a, b) => b.lastName.localeCompare(a.lastName));
            default:
                return students.sort((a, b) => b.address.localeCompare(a.address));
        }
    }

    function eventUpdateStudent(tdId, tdFirstName, tdLastName, tdAddress) {
        $('button.btn.btn-primary').on('click', function () {
            let id = $('input[id=editId]').val();
            let firstName = $('input[id=editFirstName]').val();
            let lastName = $('input[id=editLastName]').val();
            let address = $('input[id=editAddress]').val();

            let student = {id, firstName, lastName, address};
            $.ajax({
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': $.cookie('jwt') ? $.cookie('jwt') : '',
                },
                url: `http://localhost:8080/server_learn_jquery_war_exploded/rest/student/${id}`,
                type: 'PUT',
                dataType: 'text',
                data: JSON.stringify(student),
            }).done(function(response) {
                alert(response);

                tdId.text(id);
                tdFirstName.text(firstName);
                tdLastName.text(lastName);
                tdAddress.text(address);

                $('div.modal.fade').modal('hide');
            }).fail(function(xhr, textStatus, errorThrown) {
                alert(xhr.responseText);
            })
        });
    }

    function eventLogin() {
        $('button.btn.btn-outline-success').on('click', function() {
            let username = $('input[name=username]').val();
            let password = $('input[name=password]').val();

            $.ajax({
                url: `http://localhost:8080/server_learn_jquery_war_exploded/rest/authentication/login/?username=${username}&password=${password}`,
                type: 'POST',
                dataType: 'text',
            }).done(function(response) {
                alert("Login Success");
                $.cookie('jwt', `bearer ${response}`);
                sessionStorage.setItem("username", username);
                $('span.navbar-text').text(username);
            }).fail(function(xhr, textStatus, errorThrown) {
                alert(xhr.responseText);
            });
        })
    }

    getAllStudents();
    eventSortUp();
    eventLogin();

    $('div.col-2 > span.navbar-text').text(sessionStorage.getItem('username')
            ? sessionStorage.getItem('username')
            : 'Anonymous');
});