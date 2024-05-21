import { Paper } from "@mui/material"
import { useNavigate } from "react-router-dom"

function BasicView(){
    const navigate = useNavigate()
    return(
        <Paper sx={{padding:"1vw"}}>
            <h1>
                Witaj na stronie hurtowni spożywczej <h1 style={{color:"red"}}> Śrubex </h1>
            </h1>
            <h2>
                W naszej hurtowni poza atrykułami spożywczymi zaopatrzysz się także w podstawowe artykuły budowlane takie jak śruby, nakrętki, krzyżaczki do glazury i wiele więcej!
            </h2>
            <h2>
                Aby przejść do logowania kliknij <a onClick={()=>{navigate("/login")}} href="" style={{color:"blue"}}> tutaj </a>
            </h2>
        </Paper>
    )
}

export default BasicView