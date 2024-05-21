async function logout(){
    await fetch('http://localhost:8080/auth/logout', {method:"GET", credentials:"include"})
    .then(resp=>{
        if (!resp.ok){
            throw new Error(resp)
        }
        else{
            console.log(resp)
        }
    })
    .catch(err=>{
        console.error("Somethig go wrong" + err)
    })
}

export default logout