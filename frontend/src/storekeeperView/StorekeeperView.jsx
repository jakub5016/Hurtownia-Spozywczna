import OrderTable from "../adminView/orderDatabase/OrderTable"
import ProductTable from "../adminView/productDatabase/PoductTable"

function StorekeeperView(props){
    console.log(props.selectedSite)
    return(
        props.logged && props.userType=="EMPLOYEE" ?
        <div className="container">
        {props.selectedSite == 1 ? <OrderTable type={"current"}/> : props.selectedSite == 2? <OrderTable type={"old"}/> : <ProductTable userType="EMPLOYEE"/>}
        </div> : null
    )
}

export default StorekeeperView