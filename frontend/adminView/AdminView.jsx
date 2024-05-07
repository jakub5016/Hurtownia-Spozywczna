import { Paper, Table, TableCell, TableContainer, TableHead, TableRow } from "@mui/material"
import ProductTable from "./productDatabase/PoductTable"
import { useState } from "react"
import ClientTable from "./clientDatabse/ClientTable"
import OrderTable from "./orderDatabase/OrderTable"

function AdminView(props)
{   
    console.log(props.selectedSite)
    return(
    localStorage.getItem("logged") != "null" && localStorage.getItem("userType") == 'admin' ?
    <div className="container">
        {props.selectedSite == 1 ? <ProductTable/> : props.selectedSite == 2? <ClientTable/> : <OrderTable/>}
    </div> : null
    )
}

export default AdminView