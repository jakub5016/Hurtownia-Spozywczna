async function deleteClient(id){
    await fetch(
        `http://localhost:8080/client/${id}`, 
        {
            method:"DELETE",
            credentials:"include"
        }
    ).then(resp=>{
        if (resp.status == 204){
            alert("Użytkownik usunięty z sukcesem")
            window.location.reload()
        }
        else{
            alert("Usuwanie użytkownika nie powiodło się")
        }
    })
}

export default deleteClient