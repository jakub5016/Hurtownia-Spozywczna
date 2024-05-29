async function getUserInfo(setUserInfo){
    await fetch('http://localhost:8080/user',{
        method:"GET",
        credentials:"include"
    }).then(async (resp)=>{
        if (resp.status == 401){
        }
        else{
            let json = await resp.json()
            setUserInfo(json)
        }
    })
}

export default getUserInfo