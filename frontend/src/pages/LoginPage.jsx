import { useState } from "react"
import { useNavigate } from "react-router-dom";

export function LoginPage({ onLoginSuccess }) {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    const [errors, setErrors] = useState({});


    function handleValidate() {
        const newErrors = {}

        if (!email.trim()) {
            newErrors.email = "Email is required";
        }

        if (!password.trim()) {
            newErrors.password = "Password is required";
        }

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    }

    async function handleSumbit(event) {
        event.preventDefault();

        if (!handleValidate()) {
            console.log("Inside validate")
            return;
        }

        try {

            const response = await fetch("http://localhost:8080/api/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    email: email,
                    password: password
                })
            });

            if (!response.ok) {
                throw new Error("Invalid username or password");
            }

            const data = await response.text();
            localStorage.setItem("token", data);
            console.log("Login Repsonse", data);

            onLoginSuccess();
            navigate("/");

        } catch (err) {
            console.log("Login error:", err);
            alert("Invalid Username or Password")
        }

        setEmail("");
        setPassword("");
    }

    return (
        <>
            <h1>Login Page</h1>
            <form onSubmit={handleSumbit}>
                <div>
                    <label>Email: <br />
                        <input type="email"
                            placeholder="example@gmail.com"
                            value={email}
                            onChange={(e) => {
                                setEmail(e.target.value)
                                setErrors(prev => ({
                                    ...prev,
                                    email: undefined
                                }))
                            }} />
                        {errors.email && <p style={{ color: "red" }}>{errors.email}</p>}
                    </label>
                </div>
                <div>
                    <label>Password: <br />
                        <input type="password"
                            placeholder="password..."
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
                <button type="submit">Login</button>
                <div>Or</div>
                <button type="button" onClick={() => navigate("/sign-in")}>Sign-in</button>

            </form >
        </>
    )
}