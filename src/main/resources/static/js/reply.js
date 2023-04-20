async function get1(bno) {
    const result = await axios.get(`/replies/list/${bno}`)
    return result;
}