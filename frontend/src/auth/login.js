async function login(login, password, setLogged, navigate){
    console.log("AAA")
    await fetch("http://localhost:8080/auth/login", {
        method:"POST", 
        credentials: "include", 
        body:JSON.stringify({"userName": login, "password": password}),
        headers:{
            "accept": '*/*',
            'Content-Type' : 'application/json'
        }
    }
    ).then(resp=>{
        console.log(resp)   
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
            navigate("/")
            window.location.reload()
        }
        
    }).catch(err=>{
        console.error("Got en error :" + err)
    })
}

export default login