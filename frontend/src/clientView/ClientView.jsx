import OrderTable from "../adminView/orderDatabase/OrderTable"
import ProductTable from "../adminView/productDatabase/PoductTable"

function ClientView(props){

    return(
        localStorage.getItem("logged") != "null" && localStorage.getItem("userType") == 'client' ?
        <div className="container">
        {props.selectedSite == 1 ? <OrderTable/> : <ProductTable/>}
        </div> : null
    )
}

export default ClientView