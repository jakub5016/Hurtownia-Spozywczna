import { Button, Grid } from "@mui/material"

function AdminNav(props){
    console.log(props)
    return(
        localStorage.getItem("logged") != "null" && localStorage.getItem("userType") == 'admin' ?
        <Grid  container
        direction="row"
        justifyContent="flex-start"
        alignItems="center">
            <Grid  item xs={2}>
                <Button variant="outlined" onClick={()=>props.setSelectedSite(1)}>
                    Baza produktów
                </Button>
            </Grid>

            <Grid item xs={2}>
                <Button variant="outlined" onClick={()=>props.setSelectedSite(2)}>
                    Klienci
                </Button>
            </Grid>

            <Grid  item xs={1}>
                <Button variant="outlined" onClick={()=>props.setSelectedSite(3)}>
                    Zamówienia
                </Button>
            </Grid>
        </Grid> : null
    )
}

export default AdminNav