import { useEffect, useState } from "react"

export function Status() {
    const [status, setStatus] = useState('');

    useEffect(() => {
        fetch("http://localhost:8080/actuator/health")
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
                setStatus(data.status);
            })

    }, []);

    return (
        <>
            Status:{status}
        </>
    )
}