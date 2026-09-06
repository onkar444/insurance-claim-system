import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

export function ClaimForm() {
    const { id } = useParams();
    const [amount, setAmount] = useState('');
    const [description, setDescription] = useState('');
    const navigate = useNavigate();
    const [errors, setErrors] = useState({});

    function handleErrors() {
        const newErrors = {};

        //Description validation
        if (!description.trim()) {
            newErrors.description = "Description is requried"
        } else if (description.trim().length < 5) {
            newErrors.description = "Description must be more than 5 characters"
        }

        //Amount validation
        if (!amount) {
            newErrors.amount = "Amount is required";
        }
        else if (Number(amount) < 0) {
            newErrors.amount = "Amount must be positive"
        }

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    }

    async function handleSubmit(e) {
        e.preventDefault();

        if (Number(amount) < 0) {
            alert("Must be positive");
            return;
        }


        //stop submission if validation fails
        if (!handleErrors()) {
            return;
        }

        const claim = {
            "policyId": Number(id),
            "amount": Number(amount),
            "description": description,
        };

        console.log("Input", claim);

        try {
            const res = await fetch("http://localhost:8080/api/claim/save", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(claim)

            });

            if (!(res).ok) throw new Error(`Http Error: ${res.status}`);

            const data = await res.json();
            console.log("Claim created:", data);
            setAmount("");
            setDescription("");
            navigate(`/policies/${id}`)
        } catch (e) {
            console.error("Erorr", e);
        }
    }

    return (
        <>
            <h1>Claim Form</h1>

            <form onSubmit={handleSubmit}>
                <div>
                    <label>Amount ::</label>
                    <input
                        type="text"
                        placeholder="Enter Description..."
                        value={amount}
                        onChange={(e) => {
                            setAmount(e.target.value)

                            setErrors({
                                ...errors,
                                amount: ""
                            })
                        }} />
                    <p>
                        {errors.amount && (
                            <p style={{
                                color: "red"
                            }}>{errors.amount}</p>
                        )}
                    </p>
                </div>
                <div>
                    <label>Description ::</label>
                    <input
                        type="text"
                        placeholder="Enter Description..."
                        value={description}
                        onChange={(e) => {
                            setDescription(e.target.value)
                            setErrors({
                                ...errors,
                                description: ""
                            })
                        }} />
                    <p style={{
                        color: "red"
                    }}>{errors.description}</p>
                </div>
                <button type="submit">Submit</button>
                <button type="button" onClick={() => navigate(-1)}>Back</button>
            </form>

        </>
    );
}