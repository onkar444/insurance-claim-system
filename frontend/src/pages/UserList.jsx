import { useEffect, useState } from "react"
import { getUsersList } from "../api/api.js"

export function UserList() {

    const [users, setUsers] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        async function fetchUsersList() {
            try {
                setIsLoading(true);
                const data = await getUsersList();
                console.log(data);
                setUsers(data);
            } catch (err) {
                console.error("Error status:", err.status);
                setError(error);
            } finally {
                setIsLoading(false);
            }
        }

        fetchUsersList();
    }, []);


    if (error) return <p>Error loading users...</p>
    if (isLoading) return <p>Loading users...</p>

    return (
        <>
            <h1>User List</h1>
            <ul>
                {users.map(user => (
                    <li style={{
                        border: "1px solid black",
                        padding: "10px",
                        fontSize: "15px",
                        fontFamily: "monospace",
                        margin: "10px"
                    }} key={user.id}>
                        Id:{user.id} <br />
                        Name:{user.name} <br />
                        Email:{user.email} <br />
                        Role: {user.role.map(r => r + " ")}
                    </li>
                ))}
            </ul >
        </>
    )

}