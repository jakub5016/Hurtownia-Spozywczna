import { Button, Paper } from '@mui/material'
import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
function LoginPage() {
    if (localStorage.getItem("logged") == null){
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
                    <input type="text"/>
                    <label>
                    <h3>
                        Hasło
                    </h3>
                    </label>
                    <input type="password"/>
                </form>
                <Button variant="contained">
                    Zaloguj się
                    </Button>
                </Paper>
            </div>
        )
    }

    else{
        let usrType = localStorage.getItem("userType")
        const navigate = useNavigate()
        useEffect(()=>{
            navigate("/" + usrType)
        }, [])
    }
}

export default LoginPage
