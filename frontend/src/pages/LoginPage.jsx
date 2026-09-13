import { useState } from "react"
import { useNavigate } from "react-router-dom";
import { useAuth } from "../components/AuthContext";
import { perfromLogin } from "../api/api";

export function LoginPage() {
    const { login } = useAuth();
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

            const data = await perfromLogin(email, password);
            console.log("Login Repsonse", data);
            login(data);
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