import { Button, Grid } from "@mui/material"
import { useNavigate } from "react-router-dom";

function StorekeeperNav(props){
    const navigate = useNavigate()

    return(
        <Grid  container
        direction="row"
        justifyContent="flex-start"
        alignItems="center">
            <Grid  item xs={2}>
                <Button variant="outlined" onClick={()=>{props.setSelectedSite(1);navigate("/storekeeper")}}>
                    Aktualne Zamówienia
                </Button>
            </Grid>

            <Grid item xs={2}>
                <Button variant="outlined" onClick={()=>{props.setSelectedSite(2);navigate("/storekeeper")}}>
                    Historia Zamówień
                </Button>
            </Grid>

            <Grid  item xs={1}>
                <Button variant="outlined" onClick={()=>{props.setSelectedSite(3);navigate("/storekeeper")}}>
                    Lista Produktów
                </Button>
            </Grid>
        </Grid> 
    )
}

export default StorekeeperNav