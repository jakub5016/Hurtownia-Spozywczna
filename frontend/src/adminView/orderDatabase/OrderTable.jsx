
import { Paper, Table, TableCell, TableContainer, TableHead, TableRow, useThemeProps } from "@mui/material"

function OrderTable(props){

    return(
    <TableContainer component={Paper}  sx={{width:"80vw"}}>

        <Table>
            <TableHead>
                <TableRow>
                    <TableCell sx={{fontWeight:"bold"}}>
                        ID
                    </TableCell>
                    <TableCell  sx={{fontWeight:"bold"}}>
                        Data Zamówienia
                    </TableCell>
                    <TableCell  sx={{fontWeight:"bold"}}>
                        Data dostarczenia zamówiena
                    </TableCell>
                    { props.type == null ?
                    <TableCell  sx={{fontWeight:"bold"}}>
                        Status zamówiena
                    </TableCell> : null
                    }
                    <TableCell  sx={{fontWeight:"bold"}}>
                        Cena Razem
                    </TableCell>
                </TableRow>
            </TableHead>
        </Table>

    </TableContainer> 
    )
}

export default OrderTable