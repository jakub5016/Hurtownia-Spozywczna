import { Paper, Table, TableCell, TableContainer, TableHead, TableRow } from "@mui/material"


function ProductTable(){

    return(
        <TableContainer component={Paper}  sx={{width:"80vw"}}>

        <Table>
            <TableHead>
                <TableRow>
                    <TableCell sx={{fontWeight:"bold"}}>
                        ID
                    </TableCell>
                    <TableCell align="right" sx={{fontWeight:"bold"}}>
                        Nazwa
                    </TableCell>
                    <TableCell align="right" sx={{fontWeight:"bold"}}>
                        Ilość
                    </TableCell>
                    <TableCell align="right" sx={{fontWeight:"bold"}}>
                        Kategoria
                    </TableCell>
                    <TableCell align="right" sx={{fontWeight:"bold"}}>
                        Cena
                    </TableCell>
                </TableRow>
            </TableHead>
        </Table>

    </TableContainer> 
    )
}
export default ProductTable