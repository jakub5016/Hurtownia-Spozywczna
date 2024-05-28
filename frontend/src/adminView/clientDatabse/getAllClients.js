async function getAllClients(setClients, pageNo = 0, pageSize = 10){

    try {
        const resp = await fetch(
            `http://localhost:8080/client?pageNo=${pageNo}&pageSize=${pageSize}`,
            {
                method: "GET",
                credentials: "include"
            }
        );
        let json = await resp.json();
        if (json.content.length ==0){
            setClients({content:[{name:"", address:"", orders:[]}]})
        }
        else{
            setClients(json);
        }
    } catch (error) {
        console.error("Fetch error:", error);
    }
}

export default getAllClients