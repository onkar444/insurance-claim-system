import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom";

export function PolicyForm() {

    const { id } = useParams();
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const [isUpdating, setIsUpdating] = useState(false);
    const navigate = useNavigate();
    const [policy, setPolicy] = useState({
        policyType: "",
        premium: "",
        coverageAmount: "",
        startDate: "",
        endDate: "",
        status: ""
    });


    useEffect(() => {
        async function fetchPolicy() {
            try {
                setIsLoading(true);
                const res = await fetch(`http://localhost:8080/api/policy/${id}`);

                const data = await res.json();
                setPolicy(data);
            } catch (err) {
                setError(err);
                alert(err);
            }
            finally { setIsLoading(false) }
        }
        fetchPolicy();
    }, [id]);


    if (isLoading) return <p>Loading data...</p>
    if (error) return <p>Error : {error.message}</p>
    if (isUpdating) return <p>Updating data...</p>

    async function handleSubmit(e) {
        e.preventDefault();

        try {
            setIsUpdating(true);
            setError(null);
            const res = await fetch(`http://localhost:8080/api/policy/update/${id}`, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(policy)
            });

            if (!res.ok) throw new Error("Error updating policy");

            navigate(`/policies/${id}`);

        } catch (err) {
            setError(err);
        } finally {
            setIsUpdating(false);
        }


    }

    function handleChange(e) {
        const { name, value } = e.target;
        setPolicy(prev => ({
            ...prev,
            [name]: value
        }));
    }

    return (
        <div>
            <h1>Policy Form</h1>
            <form onSubmit={handleSubmit}>
                <label>
                    PolicyType:
                    <input type="text"
                        name="policyType"
                        value={policy.policyType}
                        onChange={handleChange} />
                </label><br />
                <label>
                    Premium:
                    <input type="text"
                        name="premium"
                        value={policy.premium}
                        onChange={handleChange} />
                </label>
                <br />
                <label>
                    Coverage Amount:
                    <input type="text"
                        name="coverageAmount"
                        value={policy.coverageAmount}
                        onChange={handleChange} />
                </label>
                <br />
                <label>
                    Status:
                    <select name="status"
                        value={policy.status}
                        onChange={handleChange}>
                        <option value="ACTIVE">Active</option>
                        <option value="EXPIRED">Expired</option>
                        <option value="CANCELLED">Cancelled</option>
                    </select>
                </label>
                <br />
                <br />
                <button type="submit">Update</button>
            </form>
        </div>
    )
}