import { Dialog, TableBody, TableCell, TableHead, TableRow, Table } from "@mui/material"
import { useEffect, useState } from "react"
import getPriceHistory from "./getPriceHistory"

function PriceHistoryDialog(props){
    const [priceHist, setPriceHist] = useState({prices:[], times:[]})
    
    useEffect(()=>{
        getPriceHistory(props.id).then(data=>{
            setPriceHist(data)
        })
    }, [props.id])


    return(
        <Dialog open={props.open} onClose={()=>{props.setOpen(false)}}>
            <Table sx={{width:"30vw"}}>
                <TableHead>
                    <TableRow>
                        <TableCell  align="left" sx={{ fontWeight: "bold" }}>
                            Data
                        </TableCell>
                        <TableCell  align="right" sx={{ fontWeight: "bold" }}>
                            Cena
                        </TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {priceHist.prices.map((elem, index)=>{
                        return (
                            <TableRow key={index}>
                                <TableCell align="left">
                                    {priceHist.times[index]}
                                </TableCell>
                                <TableCell align="right">
                                    {elem +"zł"}
                                </TableCell>
                            </TableRow>
                        )}
                    )
                    }

                </TableBody>

            </Table>
        </Dialog>
    )
}

export default PriceHistoryDialog