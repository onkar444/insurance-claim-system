import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";

export function PolicyList() {

    const [policies, setPolicies] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {

        async function fetchPolicies() {
            try {

                setIsLoading(true);
                const response = await fetch("http://localhost:8080/api/policies");
                console.log("response", response);
                const data = await response.json();
                console.log(data)
                setPolicies(data);
            } catch (err) {
                setError(err)
            }
            finally {
                setIsLoading(false);
            }
        }
        fetchPolicies();
    }, [])

    if (isLoading) return <p>Loading Policies...</p>
    if (error) return <p>Erorr Loading policies: {error.message}</p>

    return (
        <>
            <h1>Policies</h1>
            <ul>
                {policies.map(p => (
                    <li style={{
                        border: "1px solid black",
                        padding: "10px",
                        fontSize: "15px",
                        fontFamily: "monospace",
                        margin: "10px"
                    }} key={p.id}>
                        Id: {p.id} - policyType : {p.policyType} <br />
                        <button onClick={() => navigate(`/policies/${p.id}`)}>Details</button>
                    </li>
                ))}
            </ul >

            <button onClick={() => navigate(`/`)}>Back</button>
        </>
    )
}