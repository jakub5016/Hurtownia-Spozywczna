
import { Button, Dialog, Pagination, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material"
import { useEffect, useState } from "react"
import getAllClients from "./getAllClients"
import deleteClient from "./deleteClient"

function ClientTable(){
    const [clients, setClients] = useState({content:[{name:"", address:"", orders:[]}]})
    const [currentPage, setCurrentPage] = useState(0)
    const [open, setOpen] = useState(false);
    const [deleteID, setDeleteID] = useState(null)

    useEffect(()=>{
        getAllClients(setClients, currentPage)
    }, [])

    return(
    <div style={{display:"flex", flexDirection:"column"}}>
        <Dialog open={open} onClose={()=>{setOpen(false)}}>
            <div style={{padding:"2vw"}}>
                <h1>
                    Czy napewno chcesz usunąć klienta?
                </h1>
                <h2 style={{color:"red"}}>
                    Uwaga ta operacja jest nieodwracalna!
                </h2>
                <Button variant="contained" sx={{background:"red"}} onClick={()=>deleteClient(deleteID)}>
                    Usuń klienta
                </Button>
            </div>
        </Dialog>
    <TableContainer component={Paper}  sx={{width:"80vw"}}>

        <Table>
            <TableHead>
                <TableRow>
                    <TableCell sx={{fontWeight:"bold"}}>
                        ID
                    </TableCell>
                    <TableCell  sx={{fontWeight:"bold"}}>
                        Imie i nazwisko
                    </TableCell>
                    <TableCell  sx={{fontWeight:"bold"}}>
                        Adres
                    </TableCell>
                </TableRow>
            </TableHead>

            <TableBody>
                {
                    clients.content.map((element, index)=>{console.log(element);return(
                        <TableRow key={index}>
                            <TableCell>
                                {element.id}
                            </TableCell>
                            <TableCell>
                                {element.name}
                            </TableCell>
                            <TableCell>
                                {element.address}
                            </TableCell>
                            <Button variant="contained" sx={{background:"red"}} onClick={()=>{setOpen(true), setDeleteID(element.id)}}>
                                Usuń klienta
                            </Button>
                        </TableRow>
                    )})
                }
            </TableBody>

        </Table>

    </TableContainer> 

    <Pagination
    sx={{ margin: "0 auto", marginTop:"30px" }}
    color="primary"
    count={clients.totalPages}
    onChange={(event, page)=>{setCurrentPage(page-1)}}
    />
    </div>
    )
}

export default ClientTable