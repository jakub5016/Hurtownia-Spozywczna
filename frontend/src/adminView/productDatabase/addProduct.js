async function addProduct(product){
    await fetch(
        "http://localhost:8080/product",
        {
            method:"POST",
            body:JSON.stringify(product),
            credentials:"include",
            headers:{
                "Content-Type" : "application/json"
            }
        }
    ).then(resp=>{
        if (resp.status != 201){
            throw new Error("Cannot add new product")
        }
        else{
            window.location.reload()
        }
    }).catch(err=>{
        console.error("Error appear " + err);
    })

}

export default addProduct