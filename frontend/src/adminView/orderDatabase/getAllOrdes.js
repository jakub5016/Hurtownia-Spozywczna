async function getProducts(pageNo = 0, pageSize = 10) {
    try {
        const response = await fetch(
            `http://localhost:8080/orders?pageNo=${pageNo}&pageSize=${pageSize}`,
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

export default getProducts