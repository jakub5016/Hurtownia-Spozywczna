import { Button, Dialog, Paper, TextField } from "@mui/material"
import { useState } from "react"
import changeProductPrice from './changeProductPrice'
import changeProductQuantity from "./changeProductQuantity"

async function handleEdit(productID, newPrice, newQuantity){
    console.log("New price in handle edit " + newPrice)

    let changed = false

    if (newPrice > 0){
        await changeProductPrice(productID, newPrice)
        changed = true
    }

    if (newQuantity > 0 ){
        await changeProductQuantity(productID, newQuantity)
        changed = true
    }

    changed ? window.location.reload(): null
}

async function archive(productID){
    await fetch(
        "http://localhost:8080/product/"+ productID +"/archive",
        {
            method:"PUT",
            credentials:"include"
        }
    ).then(resp=>{
        if (resp.status != 204){
            throw new Error(resp.status)
        }
    }).catch(err=>{
        console.error("Got into error + " + err);
    })
}


function EditProductDialog(props){
    const [newPrice, setNewPrice] = useState(props.price)
    const [newQuantity, setNewQuantity] = useState(props.quantity)
    console.log("New price "+ newPrice + " " + props.price)

    return(
        <Dialog open={props.open} onClose={()=>{props.setOpen(false)}}>
            <Paper style={{display:"flex", flexDirection:"column", padding:"3vw"}}>
                <div style={{display:"flex", flexDirection:"row"}}>
                    <h2>
                        Nowa cena produktu: 
                    </h2>
                    <TextField
                        onChange={(e)=>{e.target.value == "0" ? console.log(e.target.value) : setNewPrice(e.target.value)}}
                        sx={{justifyContent:"center"}}
                        defaultValue={props.price}
                    />
                </div>
                <div style={{display:"flex", flexDirection:"row"}}>
                    <h2>
                        Nowa ilość produktu: 
                    </h2>
                    <TextField
                        onChange={(e)=>{setNewQuantity(e.target.value)}}
                        sx={{justifyContent:"center"}}
                        defaultValue={props.quantity}
                    />
                </div>
                <div style={{display:"flex", flexDirection:"row", justifyContent:"space-evenly", marginTop:"30px"}}>
                    <Button variant="outlined" color="warning" onClick={()=>{archive(props.id); window.location.reload()}}>
                        Zarchiwizuj
                    </Button>
                    <Button variant="outlined" color="info" onClick={()=>{handleEdit(props.id ,newPrice, newQuantity)}}>
                        Zaaktualizuj zmiany
                    </Button>
                </div>
            </Paper>
        </Dialog>
    )
}

export default EditProductDialog