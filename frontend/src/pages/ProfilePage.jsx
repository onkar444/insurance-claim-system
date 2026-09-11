import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";

export function ProfilePage() {

    const [user, setUser] = useState(null);
    const [error, setError] = useState(null);
    const [isLoading, setIsLoading] = useState(true);
    const token = localStorage.getItem("token");
    const navigate = useNavigate();

    useEffect(() => {

        async function fetchUserMe() {

            try {
                setIsLoading(true);
                const response = await fetch("http://localhost:8080/api/user/me", {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });

                if (!response.ok) {
                    throw new Error("Erorr loading profile");
                }
                const data = await response.json();
                setUser(data);
                console.log(data);
            } catch (err) {
                console.error(err);
                setError(err);
                alert(err.message);
            }
            finally {
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
        <>
            <div>

                <h1>Profile Page</h1>
                Id:{user.id} <br />
                Name:{user.name} <br />
                Email:{user.email} <br />
                Roles: {user.role.map(r => r + " ")}
            </div>
            <button onClick={() => navigate("/")}>Back</button>
        </>
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