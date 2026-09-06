import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom"
import { Claim } from "../components/Claim";

export function ClaimsPage() {

    const { id } = useParams();
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const [claims, setClaims] = useState([]);
    const navigate = useNavigate();

    function handleRemovedId(id) {
        setClaims(claims.filter(c => c.id != id));
    }

    useEffect(() => {

        async function fetchClaims() {
            try {
                setIsLoading(true);
                const res = await fetch(`http://localhost:8080/api/policy/${id}/claims`);
                const data = await res.json();
                setClaims(data);
            }
            catch (e) {
                setError(e)
            } finally {
                setIsLoading(false);
            }
        }

        fetchClaims();

    }, [id])

    if (isLoading) return <p>Loading Claims...</p>
    if (error) return <p>Error while loading claims:{error.message}</p>
    if (claims.length === 0) return (
        <>
            <h2>No claims submitted...</h2>
            <button onClick={() => navigate(`/policy/${id}/submitClaims`)}>Submit Claim</button>
            <button onClick={() => navigate(`/policies/${id}`)}>Back</button>
        </>
    );

    return (
        <>
            <h1>Claims list</h1>
            {
                claims.map(claim => (
                    <Claim key={claim.id} claim={claim} onDelete={handleRemovedId} />
                ))
            }
            <button onClick={() => navigate(`/policies/${id}`)}>Back</button>
        </>
    )
}