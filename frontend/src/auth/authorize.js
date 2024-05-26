async function authourize(setUserType, setLogged){
    await fetch('http://localhost:8080/user',{
        method:"GET",
        credentials:"include"
    }).then(async (resp)=>{
        if (resp.status == 401){
            setLogged(false)
            console.log("Not logged")
        }
        else{
            console.log("Logged")
            setLogged(true)
            let json = await resp.json()
            setUserType(json.roles[0])
        }
    })
}

export default authourize