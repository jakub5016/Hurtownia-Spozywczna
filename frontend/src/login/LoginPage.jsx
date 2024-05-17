import { Button, Paper } from '@mui/material'
import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import login from '../auth/login'


function LoginPage(props) {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const navigate = useNavigate();
    props.logged ? navigate("/client") : null

        return (
            <div className='container'>
                <Paper sx={{width:"40vw", padding:"2vw"}}>
                <h1>
                    Zaloguj się
                </h1>
                <form style={{marginBottom:"5vh"}}>
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
                <Button onClick={()=>{login(username, password, props.setLogged, navigate)}} variant="contained">
                    Zaloguj się
                    </Button>
                </Paper>
            </div>
        )
}

export default LoginPage
