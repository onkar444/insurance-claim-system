import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom"

export function PolicyDetails() {

    const { id } = useParams();
    const [policy, setPolicy] = useState('');
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {

        async function fetchPolicy() {
            try {
                setIsLoading(true);
                const res = await fetch(`http://localhost:8080/api/policy/${id}`);
                const data = await res.json();
                setPolicy(data);
            } catch (err) {
                setError(err)
            } finally {
                setIsLoading(false);
            }
        }
        fetchPolicy();
    }, [])

    if (isLoading) return <p>Loading Policy...</p>
    if (error) return <p>Erorr Loading policy : {error.message}</p>

    return (
        <div style={{
            border: "1px solid black",
            padding: "10px",
            fontSize: "15px",
            fontFamily: "monospace",
            margin: "10px"
        }}>
            <h1>Policy Details</h1>
            Policy Id: {id} <br />
            Policy Type: {policy.policyType}<br />
            Premium: {policy.premium}<br />
            Coverage Amount: {policy.coverageAmount}<br />
            Start Date: {policy.startDate}<br />
            End Date: {policy.endDate}<br />
            Status : {policy.status}<br />
            <button onClick={() => navigate(`/policy/${id}/claims`)}>Claims</button>
            <button onClick={() => navigate(`/policy/${id}/submitClaims`)}>Submit Claim</button>
            <button onClick={() => navigate(`/policy/${id}/update`)}>Update</button>
            <button onClick={() => navigate(`/policies`)}>Back</button>

        </div>
    )
}