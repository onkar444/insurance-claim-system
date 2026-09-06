import { useState } from "react"

export function LoginPage({ onLoginSuccess }) {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    function handleSubmit() {
        // if (email && password) {
        //     console.log("Login attempt:", { email, password });
        //     onLoginSuccess();
        // }
        onLoginSuccess();
    }

    return (
        <>
            <h1>Login</h1>
            <form onSubmit={handleSubmit}>
                <div><label>Email</label></div>
                <input
                    type="email"
                    placeholder="your@example.com"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)} />
                <hr />
                <div><label>Password</label></div>
                <input
                    type="password"
                    placeholder="*********"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)} />
                <button type="submit">Login</button>
            </form>
        </>
    )
}