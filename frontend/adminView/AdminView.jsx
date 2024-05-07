import { Paper, Table, TableCell, TableContainer, TableHead, TableRow } from "@mui/material"

function AdminView()
{
    return(
    <div className="container">
    <TableContainer component={Paper}  sx={{width:"80vw"}}>

        <Table>
            <TableHead>
                <TableRow>
                    <TableCell>
                        ID
                    </TableCell>
                    <TableCell align="right">
                        Nazwa
                    </TableCell>
                    <TableCell align="right">
                        Ilość
                    </TableCell>
                    <TableCell align="right">
                        Cena
                    </TableCell>
                </TableRow>
            </TableHead>
        </Table>

    </TableContainer>
    </div>
    )
}

export default AdminView