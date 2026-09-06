import { useState } from "react"
import { Dashboard } from "./pages/Dashboard";
import { LoginPage } from "./pages/LoginPage";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { ProfilePage } from "./pages/ProfilePage";
import { PolicyList } from "./pages/PolicyList";
import { PolicyDetails } from "./pages/PolicyDetails";
import { ClaimsPage } from "./pages/ClaimsPage";
import { ClaimForm } from "./components/ClaimForm";
import { PolicyForm } from "./components/PolicyForm";
import './App.css';

function App() {

  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <>
      <h1>Insurance Claim System</h1>
      <BrowserRouter>
        {isLoggedIn ? (
          <Routes>
            <Route path="/" element={<Dashboard handleLogout={() => setIsLoggedIn(false)} />} />
            <Route path="/profile" element={<ProfilePage />} />
            <Route path="/policies" element={<PolicyList handleLogout={() => setIsLoggedIn(false)} />} />
            <Route path="/policies/:id" element={<PolicyDetails />} />
            <Route path="/policy/:id/claims" element={<ClaimsPage />} />
            <Route path="/policy/:id/submitClaims" element={<ClaimForm />} />
            <Route path="/policy/:id/update" element={<PolicyForm />} />
          </Routes>
        ) :
          <LoginPage onLoginSuccess={() => setIsLoggedIn(true)} />
        }

      </BrowserRouter>
    </>
  )
}

export default App



