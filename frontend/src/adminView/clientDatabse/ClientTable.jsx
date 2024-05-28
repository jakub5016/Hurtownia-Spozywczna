
import { Pagination, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material"
import { useEffect, useState } from "react"
import getAllClients from "./getAllClients"

function ClientTable(){
    const [clients, setClients] = useState({content:[{name:"", address:"", orders:[]}]})

    useEffect(()=>{
        getAllClients(setClients)
    }, [])

    return(
    <div style={{display:"flex", flexDirection:"column"}}>
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
                        </TableRow>
                    )})
                }
            </TableBody>

        </Table>

    </TableContainer> 

    <Pagination
    sx={{ margin: "0 auto", marginTop:"30px" }}
    color="primary"
    // count={orders.totalPages}
    // onChange={(event, page)=>{setCurrentPage(page-1)}}
    />
    </div>
    )
}

export default ClientTable