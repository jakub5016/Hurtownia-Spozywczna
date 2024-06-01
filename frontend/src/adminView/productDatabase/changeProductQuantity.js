async function changeProductQuantity(productID, newAmmout){
    await fetch(
        "http://localhost:8080/product/"+ productID + "/amount?amount=" + newAmmout,
        {
            method:"PUT",
            credentials:"include"
        }
    ).then(resp=>{
        if (resp.status != 200){
            throw new Error("Something went wrong code: " + resp.status)
        }
    }).catch(err=>{
        console.log("Error accured: " + err)
    })
}

export default changeProductQuantity