async function getMyID(){
    try {
        const resp = await fetch('http://localhost:8080/user', {
            method: "GET",
            credentials: "include"
        });

        if (resp.status == 401){
            console.log("Not logged");
            return null;  // Return null to explicitly indicate not logged in
        } else {
            const json = await resp.json();
            return json.clientInfo.id;
        }
    } catch (error) {
        console.error("Fetch error:", error);
        return null;  // Return null if fetch fails
    }
}

export default getMyID