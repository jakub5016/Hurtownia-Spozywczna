import { Button, Paper } from "@mui/material"
import { useState } from "react"
import { useNavigate } from "react-router-dom"
import register from "../auth/register"

function Register(){
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [firstname, setFirstname] = useState("")
    const [secondname, setSecondname] = useState("")
    const [adress, setAdress] = useState("")
    
    const navigate = useNavigate()
    return(
        <div className='container'>
        <Paper sx={{width:"40vw", padding:"2vw"}}>
        <h1>
            Zarejestruj się 
        </h1>
        <form style={{marginBottom:"5vh"}}>
            
            <label>
            <h3>
                Imię
            </h3>
            </label>
            <input type="text" onChange={(e)=>{setFirstname(e.target.value)}}/>
            <label>
            <h3>
                Nazwisko
            </h3>
            </label>
            <input type="text" onChange={(e)=>{setSecondname(e.target.value)}}/>
            <label>
            <h3>
                Adres
            </h3>
            </label>
            <input type="text" onChange={(e)=>{setAdress(e.target.value)}}/>
            <label>
            <h3>
                Nazwa użytkownika
            </h3>
            </label>
            <input type="text" onChange={(e)=>{setUsername(e.target.value)}}/>
            <label>
            <h3>
                Hasło
            </h3>
            </label>
            <input type="password" onChange={(e)=>{setPassword(e.target.value)}}/>
        </form>
        <h4>UWAGA przed rejestracją na stronie przeczytaj naszą <a style={{color:"blue"}} href="" onClick={()=>{navigate("/privacy")}}>Politykę prywatności</a></h4>
        <Button onClick={()=>{register(username, password, firstname, secondname, adress)}} variant="contained">
            Zarejestruj się
            </Button>
        </Paper>

        
    </div>
    )
}

export default Register