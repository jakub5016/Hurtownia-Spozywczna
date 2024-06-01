async function getPriceHistory(productID){

    try {
        const response = await fetch(
            "http://localhost:8080/product/"+productID+"/price/all",
            {
                method: 'GET',
                credentials: 'include'
            }
        );
        const json = await response.json();
        return json.logs;
    } catch (err) {
        console.error('Error occurred:', err);
        return [];
    }

}

export default getPriceHistory
