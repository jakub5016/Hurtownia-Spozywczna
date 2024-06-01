import getMyID from "../../auth/getMyID";

async function createOrder(productsArr, quantityArr, clientID=null){
    if (productsArr.length == quantityArr.length){
        if (clientID == null){
            clientID = await getMyID()
        }
        let data = {"clientId": clientID, "orderedProducts": []};
        data.orderedProducts = productsArr.map((elem, index)=>{return {"productId": elem, "quantity": quantityArr[index]}})
        console.log(data)

        await fetch(
            "http://localhost:8080/order",
            {
                method:"POST",
                credentials:"include",
                body:JSON.stringify(data),
                headers:{
                    "Content-Type" : "application/json"
                }
            }
        ).then(resp=>{
            if (resp.status != 201){
                throw new Error("Something went wrong")
            }
        }).catch(err=>{
            console.error("Got an error: ", err)
        })
    }

}

export default createOrder