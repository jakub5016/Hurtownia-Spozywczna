import { Button, Grid } from "@mui/material"

function StorekeeperNav(props){
    return(
        localStorage.getItem("logged") != "null" && localStorage.getItem("userType") == 'storekeeper' ? 
        <Grid  container
        direction="row"
        justifyContent="flex-start"
        alignItems="center">
            <Grid  item xs={2}>
                <Button variant="outlined" onClick={()=>props.setSelectedSite(1)}>
                    Aktualne Zamówienia
                </Button>
            </Grid>

            <Grid item xs={2}>
                <Button variant="outlined" onClick={()=>props.setSelectedSite(2)}>
                    Historia Zamówień
                </Button>
            </Grid>

            <Grid  item xs={1}>
                <Button variant="outlined" onClick={()=>props.setSelectedSite(3)}>
                    Lista Produktów
                </Button>
            </Grid>
        </Grid> : null
    )
}

export default StorekeeperNav