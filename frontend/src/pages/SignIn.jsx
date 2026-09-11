import { useState } from "react"
import { useNavigate } from "react-router-dom";

export function SignIn() {

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [roles, setRoles] = useState([]);
    const [errors, setErrors] = useState({});


    const navigate = useNavigate();

    function handleRoleChange(e) {
        const selectedRole = e.target.value;
        if (e.target.checked) {
            setRoles([...roles, selectedRole]);
        } else {
            setRoles(roles.filter(r => r !== selectedRole));
        }
    }

    function validate() {
        const newErrors = {};
        if (!name.trim()) {
            newErrors.name = "Name is required";
        }

        if (!email.trim()) {
            newErrors.email = "Email is required"
        }

        if (!password.trim()) {
            newErrors.password = "Password is requried";
        }

        if (roles.length === 0) {
            newErrors.role = "Please select at least one role";
        }

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    }

    async function handleSignIn(e) {
        e.preventDefault();

        if (!validate()) {
            return;
        }


        const inputData = {
            name, email, password, roles
        };


        try {
            const response = await fetch("http://localhost:8080/api/auth/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(inputData)
            })

            if (!response.ok) {
                throw new Error("Register failed");
            }

            const data = await response.text();
            console.log("Registered User", data);
            alert("Registered User");
            navigate("/login");
        } catch (err) {
            console.error(err);
            alert(err.message);
        }
    }

    return (
        <>
            <h1>Sign-in</h1>

            <form onSubmit={handleSignIn}>
                <div>
                    <label>
                        Name:<input
                            type="text"
                            placeholder="Enter Name..."
                            value={name}
                            onChange={(e) => {
                                setName(e.target.value);
                                setErrors(prev => ({
                                    ...prev,
                                    name: undefined
                                }))
                            }} />
                        {errors.name && <p style={{ color: "red" }}>{errors.name}</p>}
                    </label>
                </div>

                <div>
                    <label>
                        Email:<input
                            type="email"
                            placeholder="Enter Email..."
                            value={email}
                            onChange={(e) => {
                                setEmail(e.target.value);
                                setErrors(prev => ({
                                    ...prev,
                                    email: undefined
                                }))
                            }}
                        />
                        {errors.email && <p style={{ color: "red" }}>{errors.email}</p>}

                    </label>
                </div>

                <div>
                    <label>
                        Password:<input
                            type="password"
                            placeholder="Enter Password..."
                            value={password}
                            onChange={(e) => {
                                setPassword(e.target.value)
                                setErrors(prev => ({
                                    ...prev,
                                    password: undefined
                                }))
                            }} />
                        {errors.password && <p style={{ color: "red" }}>{errors.password}</p>}
                    </label>
                </div>

                <div>
                    Roles:
                    <label>
                        <input type="checkbox" name="roles" value="USER"
                            onChange={handleRoleChange} />
                        USER
                    </label>
                    <label>
                        <input type="checkbox" name="roles" value="CLAIM_ADJUSTER"
                            onChange={handleRoleChange} />
                        CLAIM ADJUSTER
                    </label>
                    {errors.role && <p style={{ color: "red" }}>{errors.role}</p>}
                </div>
                <button type="submit">Create Account</button>
                <button type="button" onClick={() => navigate("/login")}>Cancel</button>
            </form >
        </>
    )
}
