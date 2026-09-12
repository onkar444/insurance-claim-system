import { useNavigate } from "react-router-dom";

export function Dashboard({ handleLogout }) {
    const navigate = useNavigate();

    return (
        <>
            <h1>Dashboard</h1>
            <nav>
                <button onClick={() => navigate(`/policies`)}>Policies</button>
                <button onClick={() => navigate("/profile")}>Profile</button>
                <button onClick={() => navigate("/user/all")}>User List</button>
            </nav>
            <div>
                <button onClick={handleLogout}>Logout</button>
            </div>
        </>
    )
}