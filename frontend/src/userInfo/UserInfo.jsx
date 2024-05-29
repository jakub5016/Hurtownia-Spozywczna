import { useState } from "react"
import getUserInfo from "./getUserInfo"
import { Paper } from "@mui/material"

function UserInfo(props){
    const [info, setInfo] =useState({clientInfo:{name:"", address:""}, username:""})

    useState(()=>{
        getUserInfo(setInfo)
    }, [])

    console.log(info)
    return(
        <Paper sx={{padding:"20px"}}>
            <h1>
                Informacje na temat konta
            </h1>
            <div style={{textAlign:"left", padding:"60px"}}>
                <h2 style={{fontWeight:"normal"}}>
                    Nazwa użytkownika: <a style={{fontWeight:"bold"}}>{info.username}</a>
                </h2>
                <h2 style={{fontWeight:"normal"}}>
                    Imię i nazwisko: <a style={{fontWeight:"bold"}}>{info.clientInfo.name}</a>
                </h2>
                <h2 style={{fontWeight:"normal"}}>
                    Adres: <a style={{fontWeight:"bold"}}>{info.clientInfo.address}</a>
                </h2>
                <h2 style={{fontWeight:"normal"}}>
                    Uprawnienia:  <a style={{fontWeight:"bold"}}>{info.roles}</a>
                </h2>
            </div>
        </Paper>
    )
}

export default UserInfo