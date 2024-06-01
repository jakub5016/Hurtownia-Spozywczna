async function changeOrderStatus(id, status){
    await fetch(
        `http://localhost:8080/order/${id}/status?status=${status}`,
        {
            method:"PUT",
            credentials:"include"
        }
    ).then(resp=>{
        if (resp.status != 200){
            throw new Error(resp.status)
        }
        else{
            alert("Zmieniono status zamówienia")
            window.location.reload()
        }
    }).catch(err=>{
        console.error("Error accured" + err)
    })
}

export default changeOrderStatus