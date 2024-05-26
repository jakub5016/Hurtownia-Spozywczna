import getMyID from "../../auth/getMyID";

async function getClientOrders(setOrders){
    const ID_logged = await getMyID();

    try {
        const resp = await fetch(
            "http://localhost:8080/client/" + ID_logged + "/order",
            {
                method: "GET",
                credentials: "include"
            }
        );
        let json = await resp.json();
        console.log(json)
        setOrders(json);
    } catch (error) {
        console.error("Fetch error:", error);
    }
    
}

export default getClientOrders;
