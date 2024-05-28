async function searchProducts(name, pageNo = 0, pageSize = 10) {
    console.log(`http://localhost:8080/product?name=${name}&pageNo=${pageNo}&pageSize=${pageSize}`)
    try {
        const response = await fetch(
            `http://localhost:8080/product/?name=${name}&pageNo=${pageNo}&pageSize=${pageSize}`,
            {
                method: 'GET',
                credentials: 'include'
            }
        );
        const json = await response.json();
        return json;
    } catch (err) {
        console.error('Error occurred:', err);
        return [];
    }
}

export default searchProducts