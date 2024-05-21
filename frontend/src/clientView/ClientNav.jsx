import { Button, Grid } from "@mui/material"

function ClientNav(props){
    return(
        <Grid  container
        direction="row"
        justifyContent="flex-start"
        alignItems="center">
            <Grid  item xs={2}>
                <Button variant="outlined" onClick={()=>props.setSelectedSite(1)}>
                    Zamówienia
                </Button>
            </Grid>

            <Grid item xs={2}>
                <Button variant="outlined" onClick={()=>props.setSelectedSite(2)}>
                    Lista produktów
                </Button>
            </Grid>
        </Grid>
    )
}

export default ClientNav