
import { Paper, Table, TableCell, TableContainer, TableHead, TableRow } from "@mui/material"

function ClientTable(){

    return(
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
        </Table>

    </TableContainer> 
    )
}

export default ClientTable