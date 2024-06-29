function setError(form_name,id,errorMsg){
    document.getElementById(`${form_name}-${id}`).innerHTML=errorMsg
}
function validateForm(form_name){
    const form=document.forms[form_name]
    var rollNo=form.rollNo.value
    var name=form.name.value
    var contact=form.contact.value
    var email=form.email.value
    var rollNoErr=nameErr=contactErr=emailErr=true
    var rollNoPat=/^\d{5}[A-Z]\d{2}[A-Z0-9]\d$/
    if(rollNoPat.test(rollNo)===false)
        setError(form_name,'rollNoError','* Enter a valid Roll No. of length 10')
    else{
        setError(form_name,'rollNoError','')
        rollNoErr=false
    }
    var namePat=/^[a-zA-Z\s]{12,100}$/
    if(namePat.test(name)===false)
        setError(form_name,'nameError','* Enter a valid Name of length 12-100')
    else{
        setError(form_name,'nameError','')
        nameErr=false
    }
    var mobilePat=/^[6-9]\d{9}$/
    if(mobilePat.test(contact)===false)
        setError(form_name,'contactError','* Please enter a valid Contact No.')
    else{
        setError(form_name,'contactError','')
        contactErr=false
    }
    var emailPat=/^[a-zA-Z0-9_\-\.]+[@][a-z]+[\.][a-z]{2,3}$/
    if(emailPat.test(email)===false)
        setError(form_name,'emailError','* Please enter a valid Email')
    else{
        setError(form_name,'emailError','')
        emailErr=false
    }
    if(rollNoErr||nameErr||contactErr||emailErr)
        return false
}