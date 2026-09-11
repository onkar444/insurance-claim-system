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
import { ProtectedRoute } from "./components/ProtectedRoute";
import './App.css';
import { SignIn } from "./pages/SignIn";

function App() {

  const [isLoggedIn, setIsLoggedIn] = useState(
    () => localStorage.getItem("token") !== null
  );

  function handleLogout() {
    localStorage.removeItem("token");
    setIsLoggedIn(false);
  }

  return (
    <>
      <h1>Insurance Claim System</h1>
      <BrowserRouter>
        <Routes>
          <Route path="/"
            element={
              <ProtectedRoute isLoggedIn={isLoggedIn}>
                <Dashboard handleLogout={handleLogout} />
              </ProtectedRoute>
            } />

          <Route path="/profile" element={
            <ProtectedRoute isLoggedIn={isLoggedIn}>
              <ProfilePage />
            </ProtectedRoute>
          } />

          <Route path="/policies"
            element={
              <ProtectedRoute isLoggedIn={isLoggedIn}>
                <PolicyList />
              </ProtectedRoute>
            } />
          <Route path="/policies/:id"
            element={
              <ProtectedRoute isLoggedIn={isLoggedIn}>
                <PolicyDetails />
              </ProtectedRoute>
            } />

          <Route path="/policies/:id/claims"
            element={
              <ProtectedRoute isLoggedIn={isLoggedIn}>
                <ClaimsPage />
              </ProtectedRoute>
            } />

          <Route path="/policies/:id/submitClaims"
            element={
              <ProtectedRoute isLoggedIn={isLoggedIn}>
                <ClaimForm />
              </ProtectedRoute>
            } />

          <Route path="/policy/:id/update"
            element={
              <ProtectedRoute isLoggedIn={isLoggedIn}>
                <PolicyForm />
              </ProtectedRoute>
            } />

          <Route path="/login"
            element={
              <LoginPage onLoginSuccess={() => setIsLoggedIn(true)} />
            } />

          <Route
            path="/sign-in" element={<SignIn />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App



