import { Button, Grid } from "@mui/material"

function AdminNav(){

    return(
        <Grid  container
        direction="row"
        justifyContent="flex-start"
        alignItems="center">
            <Grid  item xs={2}>
                <Button variant="outlined">
                    Baza produktów
                </Button>
            </Grid>

            <Grid item xs={2}>
                <Button variant="outlined">
                    Klienci
                </Button>
            </Grid>

            <Grid  item xs={1}>
                <Button variant="outlined">
                    Zamówienia
                </Button>
            </Grid>
        </Grid>
    )
}

export default AdminNav