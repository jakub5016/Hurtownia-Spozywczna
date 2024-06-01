async function getAllOrders(type, setOrders ,pageNo = 0, pageSize = 10) {

    try {
        const resp = await fetch(
            `http://localhost:8080/order?pageNo=${pageNo}&pageSize=${pageSize}&type=${type}`,
            {
                method: "GET",
                credentials: "include"
            }
        );
        let json = await resp.json();
        if (json.content.length ==0){
            setOrders({content:[{client:{name:"", address:""}, orderedProducts:[]}]})
        }
        else{
            setOrders(json);
        }
    } catch (error) {
        console.error("Fetch error:", error);
    }
}

export default getAllOrders