import OrderTable from "../adminView/orderDatabase/OrderTable"
import ProductTable from "../adminView/productDatabase/PoductTable"

function StorekeeperView(props){

    return(
        localStorage.getItem("logged") != "null" && localStorage.getItem("userType") == 'storekeeper' ?
        <div className="container">
        {props.selectedSite == 1 ? <OrderTable type={"current"}/> : props.selectedSite == 2? <OrderTable type={"old"}/> : <ProductTable/>}
        </div> : null
    )
}

export default StorekeeperView