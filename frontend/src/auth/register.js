async function register(username, password, firstname, secondname, adress, setRegistered){
    await fetch("http://localhost:8080/auth/register",
        {
            method:"POST",
            body:JSON.stringify({
                "userName": username,
                "password" : password,
                "firstName" : firstname,
                "secondName" : secondname,
                "type": 1, 
                "address" : adress
            }),
            headers:{
                "Content-Type" : "application/json"
            }
        }
    ).then((resp)=>{
        if (resp.status!=200){// Created
            console.log(resp.status)
            return false
        }   
        else{
            console.log("Uzytkownik stworzony")
            setRegistered(true)
            return true;
        }
    }
    ).catch((err)=>
        console.error("Something went wrong: " + err)
    )

}

export default register