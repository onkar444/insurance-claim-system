import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { saveClaim } from "../api/api";

export function ClaimForm() {
    const { id } = useParams();

    const [amount, setAmount] = useState("");
    const [description, setDescription] = useState("");
    const [errors, setErrors] = useState({});

    const navigate = useNavigate();

    function handleErrors() {
        const newErrors = {};

        // Description validation
        if (!description.trim()) {
            newErrors.description = "Description is required";
        } else if (description.trim().length <= 5) {
            newErrors.description =
                "Description must be more than 5 characters";
        }

        // Amount validation
        if (!amount) {
            newErrors.amount = "Amount is required";
        } else if (Number(amount) <= 0) {
            newErrors.amount = "Amount must be positive";
        }

        setErrors(newErrors);

        return Object.keys(newErrors).length === 0;
    }

    async function handleSubmit(e) {
        e.preventDefault();

        // Stop submission if validation fails
        if (!handleErrors()) {
            return;
        }

        const claim = {
            policyId: Number(id),
            amount: Number(amount),
            description: description,
        };

        console.log("Input", claim);

        try {
            const data = await saveClaim(claim);

            console.log("Claim created:", data);

            setAmount("");
            setDescription("");
            setErrors({});
            navigate(`/policies/${id}`);
        } catch (e) {
            console.error("Error", e);
        }
    }

    return (
        <>
            <h1>Claim Form</h1>

            <form onSubmit={handleSubmit}>
                {/* Amount */}
                <div>
                    <label htmlFor="amount">
                        Amount ::
                    </label>

                    <input
                        id="amount"
                        type="text"
                        placeholder="Enter Amount..."
                        value={amount}
                        onChange={(e) => {
                            setAmount(e.target.value);

                            setErrors({
                                ...errors,
                                amount: "",
                            });
                        }}
                    />

                    <p style={{ color: "red" }}>
                        {errors.amount}
                    </p>
                </div>

                {/* Description */}
                <div>
                    <label htmlFor="description">
                        Description ::
                    </label>

                    <input
                        id="description"
                        type="text"
                        placeholder="Enter Description..."
                        value={description}
                        onChange={(e) => {
                            setDescription(e.target.value);

                            setErrors({
                                ...errors,
                                description: "",
                            });
                        }}
                    />

                    <p style={{ color: "red" }}>
                        {errors.description}
                    </p>
                </div>

                {/* Buttons */}
                <button type="submit">
                    Submit
                </button>

                <button
                    type="button"
                    onClick={() => navigate(-1)}
                >
                    Back
                </button>
            </form>
        </>
    );
}
