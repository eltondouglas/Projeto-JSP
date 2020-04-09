function validateForm(frm) {
    
    if(frm.nome.value == "" || frm.nome.value == null || frm.nome.value.length < 3){
        alert('O campo nome deve ser preenchido')
        frm.nome.focus();
        return false;
    }

    if(frm.sobrenome.value == "" || frm.sobrenome.value == null || frm.sobrenome.value.length < 3){
        alert('O campo sobrenome deve ser preenchido')
        frm.sobrenome.focus();
        return false;
    }

    if(frm.datanasc.value == "" || frm.datanasc.value == null || frm.datanasc.value.length < 10 || frm.datanasc.value.length < 10 ){
        alert('Verifique o campo data de nascimento')
        frm.datanasc.focus();
        return false;
    }

    if(frm.cep.value == "" || frm.cep.value == null || frm.cep.value.length < 9 || frm.cep.value.length > 9){
        alert('Verifique o campo cep')
        frm.cep.focus();
        return false;
    }

    if(frm.cidade.value == "" || frm.cidade.value == null || frm.cidade.value.length < 3){
        alert('Verifique o campo cidade')
        frm.focus();
        return false;
    }

    if(frm.rua.value == "" || frm.rua.value == null || frm.rua.value.length < 3){
        alert('Verifique o campo rua')
        frm.rua.focus();
        return false;
    }
}