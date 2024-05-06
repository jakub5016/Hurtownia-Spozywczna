import { useState } from 'react'
import './App.css'
import { Button, Paper } from '@mui/material'

function App() {
  const [count, setCount] = useState(0)

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

export default App
