import { Button, Grid } from "@mui/material"
import { useNavigate } from "react-router-dom";

function AdminNav(props){
    const navigate = useNavigate()

    return(
        <Grid  container
        direction="row"
        justifyContent="flex-start"
        alignItems="center">
            <Grid  item xs={2}>
                <Button variant="outlined" onClick={()=>{props.setSelectedSite(1);navigate("/admin")}}>
                    Baza produktów
                </Button>
            </Grid>

            <Grid item xs={2}>
                <Button variant="outlined" onClick={()=>{props.setSelectedSite(2);navigate("/admin")}}>
                    Klienci
                </Button>
            </Grid>

            <Grid  item xs={1}>
                <Button variant="outlined" onClick={()=>{props.setSelectedSite(3);navigate("/admin")}}>
                    Zamówienia
                </Button>
            </Grid>
        </Grid>
    )
}

export default AdminNav