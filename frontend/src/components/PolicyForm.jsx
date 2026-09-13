import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom";
import { getPolicyById, updatePolicy } from "../api/api";

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
                const data = await getPolicyById(id);
                console.log("Data to be updated", data);
                setPolicy(data);
            } catch (err) {
                setError(err);
                console.error(err.message);
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
            await updatePolicy(policy);
            alert("Policy Updated succesfully!")
            navigate(`/policy/${id}`);

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
                <button type="button" onClick={() => navigate(`/policy/${id}`)}>Cancel</button>
            </form>
        </div>
    )
}