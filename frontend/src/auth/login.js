async function login(login, password, setLogged){
    await fetch("http://localhost:8080//auth/login", {
        method:"POST", 
        credentials: "include", 
        body:JSON.stringify({userName: login, passwrod: password})}
    ).then(resp=>{
        if (!resp.ok){
            if (resp.status == 401){
                return false
            }
            else{
                throw new Error("Something went bad")
            }
        }
        else{
            setLogged("true")
            console.log(resp.body)
        }
        
    }).catch(err=>{
        console.error("Got en error :" + err)
    })
}

export default login