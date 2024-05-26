import { Button, Dialog, Paper } from "@mui/material"
import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import register from "../auth/register"

function Register(){
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [firstname, setFirstname] = useState("")
    const [secondname, setSecondname] = useState("")
    const [adress, setAdress] = useState("")
    const [registered, setRegistered] = useState(false)
    const navigate = useNavigate()
    useEffect(()=>{
        console.log(registered)
    }, [registered])

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
        <Button onClick={()=>{register(username, password, firstname, secondname, adress, setRegistered)}} variant="contained">
            Zarejestruj się
            </Button>
        </Paper>

        
        <Dialog open={registered} onClose={()=>{setRegistered(false)}}>
            <h1>
            Zostałeś zarejestrowany pomyślnie <a href="" onClick={()=>navigate("/")}>przejdź do strony głównej</a>.
            </h1>
        </Dialog>
    </div>
    )
}

export default Register