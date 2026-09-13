import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";
import { getUserMe } from "../api/api.js"

export function ProfilePage() {

    const [user, setUser] = useState(null);
    const [error, setError] = useState(null);
    const [isLoading, setIsLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {

        async function fetchUserMe() {

            try {
                setIsLoading(true);
                const data = await getUserMe();
                setUser(data);
            } catch (err) {
                console.error("status:", err.status);
                setError(err);
            } finally {
                setIsLoading(false);
            }
        };
        fetchUserMe();
    }, []);


    if (isLoading) {
        return <p>Loading profile...</p>
    }

    if (error) {
        return <p>Erorr : {error.message}</p>
    }

    return (
        <div>
            <h1>Profile Page</h1>
            <div style={{
                border: "1px solid black",
                padding: "10px",
                fontSize: "15px",
                fontFamily: "monospace",
                margin: "10px"
            }}>
                Id:{user.id} <br />
                Name:{user.name} <br />
                Email:{user.email} <br />
                Roles: {user.role.map(r => r + " ")}
            </div>
            <button onClick={() => navigate("/")}>Back</button>
        </div>
    )
}

// {
//   "id": 1,
//   "name": "Onkar Raut",
//   "role": [
//     "ADMIN",
//     "USER"
//   ],
//   "email": "onkar99@gmail.com"
// }