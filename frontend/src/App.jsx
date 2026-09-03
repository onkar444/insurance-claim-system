import { useEffect, useState } from 'react'
import './App.css'

function App() {

  const [status, setStatus] = useState([]);

  useEffect(() => {
    const fetchStatus = async () => {
      try {
        const response = await fetch("http://localhost:8080/actuator/health");
        const data = await response.json();
        setStatus(data.status);

      } catch (error) {
        console.error(error)
      };

    }
    fetchStatus();
  }, []);

  return (
    <>
      <h1>Vite React Application</h1>
      <h2>Status:{status}</h2>
    </>
  )
}

export default App
