function validate() {
    var form = $('formAdd');

    for (let index = 0; index < form.length; index++) {
        const element = form[index];

        if (element == 'id') {

        } else if(element.value == ''){
            alert('Preencha o campo ' + element)
        }
    }
}