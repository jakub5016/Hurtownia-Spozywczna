import { Button, Grid } from "@mui/material"
import { useNavigate } from "react-router-dom";

function ClientNav(props){
    const navigate = useNavigate()

    return(
        <Grid  container
        direction="row"
        justifyContent="flex-start"
        alignItems="center">
            <Grid  item xs={2}>
                <Button variant="outlined" onClick={()=>{props.setSelectedSite(1);navigate("/client")}}>
                    Zamówienia
                </Button>
            </Grid>

            <Grid item xs={2}>
                <Button variant="outlined" onClick={()=>{props.setSelectedSite(2);navigate("/client")}}>
                    Lista produktów
                </Button>
            </Grid>
        </Grid>
    )
}

export default ClientNav