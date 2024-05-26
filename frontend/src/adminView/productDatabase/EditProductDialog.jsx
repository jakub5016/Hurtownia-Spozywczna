import { Button, Dialog, Paper, TextField } from "@mui/material"
import { useState } from "react"

function EditProductDialog(props){
    return(
        <Dialog open={props.open} onClose={()=>{props.setOpen(false)}}>
            <Paper style={{display:"flex", flexDirection:"column", padding:"3vw"}}>
                <div style={{display:"flex", flexDirection:"row"}}>
                    <h2>
                        Nowa cena produktu: 
                    </h2>
                    <TextField
                        sx={{justifyContent:"center"}}
                        defaultValue={props.price}
                    />
                </div>
                <div style={{display:"flex", flexDirection:"row"}}>
                    <h2>
                        Nowa ilość produktu: 
                    </h2>
                    <TextField
                        sx={{justifyContent:"center"}}
                        defaultValue={props.quantity}
                    />
                </div>
                <Button variant="outlined" color="warning">
                    Zarchiwizuj
                </Button>
            </Paper>
        </Dialog>
    )
}

export default EditProductDialog